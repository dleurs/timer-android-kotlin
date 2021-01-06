package fr.dleurs.android.timerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)

        bLaunchTimer.setOnClickListener {
            var safeLengthTime: Long
            if (etTimerInput.text.isNullOrEmpty()) {
                etTimerInput.setText("10")
                safeLengthTime = 10
            } else {
                safeLengthTime = etTimerInput.text.toString().toLong()
            }
            timerViewModel.startTimer(safeLengthTime)
            timerViewModel.currentTime().observe(this, Observer {
                tvTimer.text = it.toString()
            })
        }

        timerViewModel.finished.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finished", Toast.LENGTH_LONG).show()
            }

        })
    }
}