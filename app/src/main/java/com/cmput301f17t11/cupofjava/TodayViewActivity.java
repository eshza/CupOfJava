package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class TodayViewActivity extends Activity {

    private ArrayList<Habit> habitList = new ArrayList<Habit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_view);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_today);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.action_favorites:
                        Intent intent2 = new Intent(TodayViewActivity.this, HabitEventTimeLineActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_schedules:
                        break;
                    case R.id.action_music:
                        Intent intent3 = new Intent(TodayViewActivity.this, AllHabitViewActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.add_habit:
                        Intent intent4 = new Intent(TodayViewActivity.this, NewHabitActivity.class);
                        startActivity(intent4);
                        break;

                }
                return false;
            }
        });

        FloatingActionButton newActivity = (FloatingActionButton) findViewById(R.id.selfProfileViewNewHabit);
        newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TodayViewActivity.this, NewHabitActivity.class);
                startActivity(intent);

                /*View view = LayoutInflater.from(TodayViewActivity.this).inflate(R.layout.activity_add_new_habit, null);

                final EditText editTitle = (EditText) view.findViewById(R.id.habit_title);
                final EditText editReason = (EditText) view.findViewById(R.id.habit_reason);

                // Create dialog to add counter
                AlertDialog.Builder builder = new AlertDialog.Builder(TodayViewActivity.this);
                builder.setMessage("Add Habit");
                builder.setView(view);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Check if counter Title and Reason are valid entries
                        if ( !(editTitle.getText().toString().equals("")) && !(editReason.getText().toString().equals("")) ) {
                            String title = editTitle.getText().toString();
                            String reason = editReason.getText().toString();
                            Date currentDate = new Date();


                            Habit newHabit = new Habit(title, reason, currentDate);




                            habitList.add(newHabit);
                            dialog.dismiss();
                        }

                        // Show error toast on invalid entry
                        else {
                            Toast.makeText(getApplicationContext(), "Make sure Title and Reason are not blank", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setCancelable(false);

                AlertDialog alert = builder.create();

                alert.show();*/
            }
        });
    }




}
