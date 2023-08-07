package com.example.agetominutecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.agetominutecalculator.databinding.ActivityFirstPageBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FirstPage : AppCompatActivity() {
    private lateinit var binding: ActivityFirstPageBinding
    private var displayDate : TextView?=null
    private var minuteDisplay : TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFirstPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayDate=binding.tvDisplayDate
        minuteDisplay=binding.tvMinuteDisplay


        binding.dateButton.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        //getting calender object from java util
        val myCalender = Calendar.getInstance()
        val year= myCalender.get(Calendar.YEAR)
        val month= myCalender.get(Calendar.MONTH)
        val day= myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
        //DatePickerDialog.OnDateSetListener (We can or cannot use this thing it will work properly)
        {
              //  view, selectedYear, selectedMonth, selectedDayOfMonth ->
                _, selectedYear, selectedMonth, selectedDayOfMonth ->

            Toast.makeText(this,
                "Date Picker show",Toast.LENGTH_LONG).show()

            val selectedDate ="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            displayDate?.text = selectedDate
            val sdf =SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate =sdf.parse(selectedDate)

            // using type safety as is  the date is null it will not work
            theDate?.let {
                val selectDateInMinutes = theDate.time/60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                // using type safety as is  the date is null it will not work
                currentDate?.let{
                    val currentDateInMinutes = currentDate.time/60000

                    val differenceInMinutes =currentDateInMinutes - selectDateInMinutes

                    minuteDisplay?.text=differenceInMinutes.toString()
                }
            }
        },year, month, day )

        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000

        dpd.show()




    }
}