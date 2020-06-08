package com.example.helper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt

private var BSA: Double? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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