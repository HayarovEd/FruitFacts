package facts.fruit.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {
    val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
}