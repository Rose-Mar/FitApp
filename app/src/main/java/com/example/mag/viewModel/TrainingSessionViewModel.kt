package com.example.mag.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrainingSessionViewModel : ViewModel() {
    private val _distance = MutableStateFlow(0.0)
    val distance: StateFlow<Double> = _distance

    private val _time = MutableStateFlow(0L)
    val time: StateFlow<Long> = _time

    private val _calories = MutableStateFlow(0)
    val calories: StateFlow<Int> = _calories

    private val _speed = MutableStateFlow(0.0)
    val speed: StateFlow<Double> = _speed

    private var isRunning = false
    private var startTime = 0L

    fun startTraining() {
        if (isRunning) return
        isRunning = true
        startTime = System.currentTimeMillis()

        viewModelScope.launch {
            while (isRunning) {
                val elapsed = System.currentTimeMillis() - startTime
                _time.value = elapsed
                _distance.value = calculateDistance(elapsed)
                _calories.value = calculateCalories(elapsed)
                _speed.value = calculateSpeed(elapsed)
                delay(1000)
            }
        }
    }

    fun stopTraining() {
        isRunning = false
    }

    private fun calculateDistance(elapsedTime: Long): Double {
        // Przyjmijmy że dystans zwiększa się o 0.01 km co sekundę dla uproszczenia
        return elapsedTime / 1000 * 0.01
    }

    private fun calculateCalories(elapsedTime: Long): Int {
        // Przyjmijmy że spalanie kalorii wynosi 5 kalorii na minutę dla uproszczenia
        return (elapsedTime / 60000 * 5).toInt()
    }

    private fun calculateSpeed(elapsedTime: Long): Double {
        // Przyjmijmy że prędkość jest stała 10 km/h dla uproszczenia
        return 10.0
    }
}
