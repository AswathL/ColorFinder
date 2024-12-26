package com.example.colorfinder.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colorfinder.data.local.ColorEntry
import com.example.colorfinder.data.repository.ColorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ColorRepository) : ViewModel() {

    private val _colors = MutableStateFlow<List<ColorEntry>>(emptyList())
    val colors: StateFlow<List<ColorEntry>> = _colors

    init {
        addColor("#FF5733", System.currentTimeMillis())
    }

    fun syncColors() {
        viewModelScope.launch {
            repository.syncColorsToFirebase() // Sync data with Firebase
            repository.clearColors() // Clear local data
            loadColors() // Refresh the list
        }
    }

    val pendingSyncCount = colors.map { it.size }

    fun loadColors() {
        viewModelScope.launch {
            _colors.value = repository.getAllColors()
        }
    }

    fun addColor(color: String, time: Long) {
        viewModelScope.launch {
            repository.insertColor(ColorEntry(color = color, time = time))
            loadColors()
        }
    }
}
