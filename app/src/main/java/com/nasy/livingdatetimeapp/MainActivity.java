package com.nasy.livingdatetimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button DOBbtn, timeBtn, exitBtn;
    TextView liveTextView;
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DOBbtn = findViewById(R.id.bodBtn);
        timeBtn = findViewById(R.id.timeBtn);
        exitBtn = findViewById(R.id.exitBtn);
        liveTextView = findViewById(R.id.liveTextView);

        DOBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDOBBtnClick(v);
            }
        });

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimeBtnClick(v);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmExitDialog();
            }
        });
    }

    public void onDOBBtnClick(View v) {
        datePickerDialog = new DatePickerDialog(MainActivity.this, dateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calLiverDay(year, month, dayOfMonth);
        }
    };

    public void onTimeBtnClick(View v) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                liveTextView.setText(hourOfDay + ":" + minute);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    public void onExitBtnClick(View v) {
        confirmExitDialog();
    }

    private void confirmExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Excuse me").setIcon(android.R.drawable.ic_menu_help)
                .setCancelable(false)
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("Yes", ((dialog, which) -> {
                    MainActivity.this.finish();
                })).setNegativeButton("No", ((dialog, which) -> {
                    dialog.dismiss();
                })).show();
    }

    private void calLiverDay(int year, int month, int day) {
        int yearToDay;
        int monthToDay;
        int liveDay;

        yearToDay = (c.get(Calendar.YEAR) - year) * 365;
        if (c.get(Calendar.MONTH) >= month) {
            monthToDay = (c.get(Calendar.MONTH) - month) * 30;
        } else {
            monthToDay = ((c.get(Calendar.MONTH) - month) - 12) * 30 - 365;
        }
        liveDay = c.get(Calendar.DAY_OF_MONTH) - day;
        liveTextView.setText("Up time: " + liveDay);
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null) // You can add an OnClickListener here if needed
                .show();
    }
}
