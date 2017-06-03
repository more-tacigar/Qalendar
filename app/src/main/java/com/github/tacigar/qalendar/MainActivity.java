package com.github.tacigar.qalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Calendar calendar;
    private TextView textViewPreviousMonth;
    private TextView textViewCurrentMonth;
    private TextView textViewNextMonth;
    private TextView textViewDays[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();

        textViewPreviousMonth = (TextView)findViewById(R.id.textViewPreviousMonth);
        textViewCurrentMonth = (TextView)findViewById(R.id.textViewCurrentMonth);
        textViewNextMonth = (TextView)findViewById(R.id.textViewNextMonth);

        textViewDays = new TextView[42];
        for (int i = 0; i < textViewDays.length; ++i) {
            int id = getResources().getIdentifier("textViewDay" + i, "id", getPackageName());
            textViewDays[i] = (TextView)findViewById(id);
        }

        changeMonth();
    }

    private void changeMonth() {
        for (TextView t: textViewDays) {
            t.setText("");
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        textViewCurrentMonth.setText(year + "/" + month);

        int minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.DAY_OF_MONTH, minDay);
        int itr1 = calendar.get(Calendar.DAY_OF_WEEK);
        for (int itr2 = minDay; itr2 <= maxDay; ++itr1, ++itr2) {
            textViewDays[itr1 - 1].setText(" " + String.valueOf(itr2));
        }
    }

    public void onClickPrevMonth(View view) {
        calendar.add(Calendar.MONTH, -1);
        changeMonth();
    }

    public void onClickNextMonth(View view) {
        calendar.add(Calendar.MONTH, 1);
        changeMonth();
    }

    public void onClickDay(View view) {

    }
}
