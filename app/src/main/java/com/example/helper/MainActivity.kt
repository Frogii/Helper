package com.example.helper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt

private var BSA: Double? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//


        btCalendar.setOnClickListener {
            val toCalendar = Intent(this, CalendarActivity::class.java)
            if (etDaysCount.text.isNotEmpty()) toCalendar.putExtra("daysCount",
                etDaysCount.text.toString().toLong()
            )
                startActivityForResult(toCalendar, 0)

        }


        etWeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                computeBsa()
                computeTotalDose()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        etHeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                computeBsa()
                computeTotalDose()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        etDose.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                computeTotalDose()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        btClear.setOnClickListener {
            allClear()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val firstDateStr = data.getStringExtra("firstDate")
            tvFirstDay.text = "Первый день $firstDateStr"
            val lastDateStr = data.getStringExtra("lastDate")
            tvLastDate.text = "Последний день $lastDateStr"
        }
    }

    private fun allClear() {
        etHeight.setText("")
        etWeight.setText("")
        etDose.setText("")
    }

    private fun computeTotalDose() {
        if (tvTotalBSA.text.isEmpty() || etDose.text.isEmpty()
            || etWeight.text.isEmpty() || etHeight.text.isEmpty()
        ) {
            tvTotalDose.text = ""
            return
        }
        if (etDose.text.isNullOrEmpty() || etHeight.text.isNullOrEmpty()
            || etHeight.text.isNullOrEmpty() || etDose.text.toString() == "."
            || etHeight.text.toString() == "."
        ) return

        val dose = etDose.text.toString().toDouble()

        val totalDose = dose * BSA!!
        tvTotalDose.text = "%.2f".format(totalDose)
    }

    private fun computeBsa() {
        if (etWeight.text.isEmpty() || etHeight.text.isEmpty()) {
            tvTotalBSA.text = ""
            return
        }
        if (etHeight.text.isNullOrEmpty() || etHeight.text.isNullOrEmpty()
            || etWeight.text.toString() == "." || etHeight.text.toString() == "."
        ) return

        val height = etHeight.text.toString().toDouble()
        val weight = etWeight.text.toString().toDouble()
        val totalBsa = sqrt(height * weight / 3600)
        BSA = sqrt(height * weight / 3600)
        tvTotalBSA.text = "%.2f".format(totalBsa)

    }
}