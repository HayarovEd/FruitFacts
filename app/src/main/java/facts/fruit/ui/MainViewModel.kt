package facts.fruit.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import facts.fruit.data.factsFriuts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _nextNumberItem = MutableStateFlow(0)

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

    private fun MainState.updateStateUI() {
        _state.update {
            this
        }
    }
}