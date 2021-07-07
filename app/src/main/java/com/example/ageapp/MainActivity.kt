package com.example.ageapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val datePicker = findViewById<Button>(R.id.btnDatePicker)
        datePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }

    }
    fun clickDatePicker (view: View){
// shows current date
        val myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)

        // how picking a date is done manually
        // "val dpd = " or datepickerdialog is at the end

        val dpd = DatePickerDialog (this, DatePickerDialog.OnDateSetListener
        { view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "Chosen year is $selectedYear, the month is $selectedMonth and the day is $selectedDayOfMonth"
                    , Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            val inputdate = findViewById<TextView>(R.id.inputdate)
            inputdate.setText (selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH) // simpleDateFormat

            val theDate = sdf.parse(selectedDate) //parse method converts from

            val selectedDateInMinutes = theDate!!.time / 60000 //getTime - returns time in mil sec

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            val minutesoutput = findViewById<TextView>(R.id.minutesoutput)
            minutesoutput.setText(differenceInMinutes.toString())

        }
            , year
            , month
            , day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}


