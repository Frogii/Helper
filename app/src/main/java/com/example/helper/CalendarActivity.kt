package com.example.helper

import android.app.Activity
import android.content.Intent
import android.icu.util.LocaleData
import android.os.Bundle
import android.widget.CalendarView
import kotlinx.android.synthetic.main.calendar_layout.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CalendarActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_layout)

        val daysCount = intent.getLongExtra("daysCount", 0)






        calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(
                view: CalendarView,
                year: Int,
                month: Int,
                dayOfMonth: Int
            ) {
                val firstDate = "$dayOfMonth/${month + 1}/$year"
                val sendDate = Intent()
                sendDate.putExtra("firstDate", firstDate)
                val lastDate: LocalDate = LocalDate.of(year, month + 1, dayOfMonth)
                    .plusDays(if (daysCount != 0L) daysCount - 1 else 0)
                val formatter = DateTimeFormatter.ofPattern("d/MM/YYYY").withLocale(Locale("ru"))
                val formattedLastDate = lastDate.format(formatter)
                sendDate.putExtra("lastDate", formattedLastDate)
                setResult(0, sendDate)

                finish()

            }

        })

    }

}
