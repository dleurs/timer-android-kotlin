package fr.dleurs.android.timerapp

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel() : ViewModel() {
    private lateinit var timer: CountDownTimer
    private val _currentTime = MutableLiveData<Long>()

    fun currentTime(): LiveData<Long> {
        return _currentTime
    }

    fun startTimer(lenghtTimerSeconds: Long = 10) {
        println((lenghtTimerSeconds * 1000).toString())
        timer = object : CountDownTimer(lenghtTimerSeconds * 1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/1000;
            }

            override fun onFinish() {
                println("Timer finished")
            }

        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}