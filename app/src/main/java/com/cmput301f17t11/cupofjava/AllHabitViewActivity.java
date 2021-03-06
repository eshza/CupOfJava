/* AllHabitViewActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */
package com.cmput301f17t11.cupofjava;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Opens the activity which displays all the habits the user has currently
 * created and saved. Also implements navigation bar functionality.
 *
 * @version 1.0
 */
public class AllHabitViewActivity extends Fragment {
    private ListView listView;
    private TextView textView;
    private String userName;
    private ArrayList<Habit> habits;
    private User user;
    private int userIndex;

    public ListView getListView(){
        return listView;
    }

    public void AllHabitViewActivity() {

    }

    /**
     * This method is called when AllHabitViewActivity is instantiated.
     * Implements bottom navigation menu to record which button is clicked on and
     * navigates to the appropriate activity.
     *
     * @param savedInstanceState the current saved state of the activity
     * @see TodayViewActivity
     * @see HabitEventTimeLineActivity
     * @see NewHabitActivity
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_all_habit_view, container, false);

        //obtain extra info from intent
        final Intent intent = getActivity().getIntent();
        //this.userName = intent.getStringExtra("userName");
        Bundle bundle = getArguments();
        this.user = (User) bundle.getSerializable("user");
        //this.userIndex = intent.getIntExtra("userIndex", 0);

        //handle bottom navigation bar
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.action_timeline:
                        Bundle bundle2 = new Bundle();
                        bundle2.putSerializable("user", user);
                        //bundle2.putString("userName", userName);
                        HomeFragment fragment2 = new HomeFragment();
                        fragment2.setArguments(bundle2);
                        FragmentManager fragmentManager2 = getFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, fragment2).addToBackStack(null);
                        fragmentTransaction2.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        break;
                    case R.id.action_today:
                        Bundle bundle3 = new Bundle();
                        bundle3.putSerializable("user", user);
                        //bundle3.putString("userName", userName);
                        TodayViewActivity fragment3 = new TodayViewActivity();
                        fragment3.setArguments(bundle3);
                        FragmentManager fragmentManager3 = getFragmentManager();
                        FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, fragment3).addToBackStack(null);
                        fragmentTransaction3.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        break;
                    case R.id.action_all_habits:
                        break;
                    case R.id.add_habit_or_habit_event:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Add New")
                                .setNegativeButton("New Habit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent4 = new Intent(getActivity(), NewHabitActivity.class);
                                        intent4.putExtra("user", user);
                                        //intent4.putExtra("userIndex", userIndex);
                                        startActivity(intent4);
                                    }
                                })
                                .setPositiveButton("New Habit \n   Event", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent5 = new Intent(getActivity(), NewHabitEventActivity.class);
                                        intent5.putExtra("user", user);
                                        //.putExtra("userIndex", userIndex);

                                        startActivity(intent5);
                                    }
                                });


                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;

                    case R.id.action_friends:
                        Bundle bundle4 = new Bundle();
                        bundle4.putSerializable("user", user);
                        //bundle4.putString("userName", userName);
                        SocialFragment fragment4 = new SocialFragment();
                        fragment4.setArguments(bundle4);
                        FragmentManager fragmentManager4 = getFragmentManager();
                        FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fragment4).addToBackStack(null);
                        fragmentTransaction4.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        break;
                }
                return false;
            }
        });


        //set up TextView and ListView
        this.textView = (TextView) view.findViewById(R.id.allHabitHeadingTextView);
        this.listView = (ListView) view.findViewById(R.id.allHabitListView);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*SaveFileController saveFileController = new SaveFileController();
                saveFileController.getHabit(getApplicationContext(), userIndex, position);*/

                Intent intent5 = new Intent(getActivity(), HabitDetailViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                bundle.putString("userName", userName);
                bundle.putSerializable("habitClicked", habits); //sending all habits list
                bundle.putInt("habitIndex", position);
                //intent5.putExtra("userIndex", userIndex);
                intent5.putExtras(bundle);
                startActivity(intent5);
            }
        });
        return view;
    }

    /**
     * This method is called when the activity is to be continued.
     *
     * @see SaveFileController
     */
    @Override
    public void onResume(){
        super.onResume();
        habits = user.getHabitListAsArray();
        updateTextView(habits.size());
        updateListView(habits);
        /*SaveFileController saveFileController = new SaveFileController();
        ArrayList<Habit> habitList = saveFileController
                .getHabitListAsArray(getApplicationContext(), this.userIndex);*/
        //updateTextView(habitList.size());
        //updateListView(habitList);
    }

    /**
     * Updates the text view according to the number of habits a user currently has.
     *
     * @param habitCount the number of habits in the text view
     */
    private void updateTextView(int habitCount){
        if (habitCount == 0){
            this.textView.setText(("You do have not have any habits to track. Perhaps it's time to start a new habit?"));
        }
        else{
            this.textView.setText(("Here are all of your habits:"));
        }
    }

    /**
     * Updates the list view to display the list of habits.
     *
     * @param habits array list of habits to be displayed.
     * @see Habit
     */
    private void updateListView(ArrayList<Habit> habits){
        ArrayAdapter<Habit> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.habit_list_item, habits);
        synchronized (listView){
            this.listView.setAdapter(arrayAdapter);
            this.listView.notify();
        }

    }
}
