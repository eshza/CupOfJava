/* HabitEventTimeLineActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */


package com.cmput301f17t11.cupofjava;

import android.net.Uri;
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
 * Opens the activity which shows the timeline of habit events.
 *
 * @version 1.0
 */
public class HabitEventTimeLineActivity extends Fragment {
    private String userName;
    private int userIndex;
    private User user;
    private ListView listView;
    private TextView textView;
    ArrayList<HabitEvent> events = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private HabitEventTimeLineActivity.OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowersTab.
     */
    // TODO: Rename and change types and number of parameters
    public static HabitEventTimeLineActivity newInstance(String param1, String param2) {
        HabitEventTimeLineActivity fragment = new HabitEventTimeLineActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public HabitEventTimeLineActivity() {

    }

    //private HabitEventAdapter habitEventAdapter;
    //private ArrayList<HabitEvent> eventArrayList = new ArrayList<>();

    /**
     * This method is called when HabitEventTimeLineActivity is instantiated.
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

        View view = inflater.inflate(R.layout.activity_habit_time_line, container, false);
        //habitEventAdapter = new HabitEventAdapter(this, eventArrayList);
        //listView.setAdapter(habitEventAdapter);

        //obtain extra info from intent
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.user = (User) bundle.getSerializable("user");
            this.userName = user.getUsername();
        }

        //this.userName = intent.getStringExtra("userName");
        //this.userIndex = intent.getIntExtra("userIndex", 0);

        //handle the bottom navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.action_timeline:
                        break;
                    case R.id.action_today:
                        Bundle bundle = new Bundle();
                        bundle.putString("userName", userName);
                        TodayViewActivity fragment = new TodayViewActivity();
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                        //intent2.putExtra("userIndex", userIndex);
                    case R.id.action_all_habits:
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("userName", userName);
                        AllHabitViewActivity fragment2 = new AllHabitViewActivity();
                        fragment2.setArguments(bundle2);
                        FragmentManager fragmentManager2 = getFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, fragment2).addToBackStack(null);
                        fragmentTransaction2.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        break;
                    case R.id.add_habit_or_habit_event:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Add New")
                                .setNegativeButton("New Habit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent4 = new Intent(getActivity(), NewHabitActivity.class);
                                        intent4.putExtra("userName", userName);
                                        //intent4.putExtra("userIndex", userIndex);
                                        startActivity(intent4);
                                    }
                                })
                                .setPositiveButton("New Habit \n   Event", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Intent intent5 = new Intent(AllHabitViewActivity.this, NewHabitEventActivity.class);
                                        //intent5.putExtra("userName", userName);
                                        //intent5.putExtra("userIndex", userIndex);

                                        //startActivity(intent5);
                                    }
                                });


                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    case R.id.action_friends:
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("userName", userName);
                        SocialFragment fragment4 = new SocialFragment();
                        fragment4.setArguments(bundle4);
                        FragmentManager fragmentManager4 = getFragmentManager();
                        FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fragment4).addToBackStack(null);
                        fragmentTransaction4.commit();
                        //intent2.putExtra("userIndex", userIndex);
                }
                return false;
            }
        });

        //set up the TextView and ListView
        this.textView = (TextView) view.findViewById(R.id.timelineHeadingTextView);
        this.listView = (ListView) view.findViewById(R.id.timeLineListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent5 = new Intent(getActivity(),
                        ViewHabitEventActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                bundle.putSerializable("eventClicked", events); //sending habitEventlist
                bundle.putInt("eventIndex", position);

                intent5.putExtras(bundle);
                //intent5.putExtra("userName", userName);
                //intent5.putExtra("userIndex", userIndex);
                //intent5.putExtra("habitEventIndex", position);
                startActivity(intent5);
            }
        });
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        //SaveFileController saveFileController = new SaveFileController();
        //ArrayList<HabitEvent> events = saveFileController.getAllHabitEvents(getApplicationContext(), this.userIndex);

        ElasticsearchController.GetEventsTask getEventsTask = new ElasticsearchController.GetEventsTask();
        getEventsTask.execute(userName);
        try {
            events = getEventsTask.get();

            updateTextView(events.size());
            updateListView(events);
            Log.i("Events", events.toString());


        } catch (Exception e) {
            Log.i("Error", "Failed to get the Habit Events from the async object");
        }


        //updateTextView(events.size());
        //updateListView(events);

    }

    /**
     * Updates text view.
     *
     * @param eventCount integer
     */
    private void updateTextView(int eventCount){
        if (eventCount == 0){
            this.textView.setText(("You did not do any habits yet!"));
        }
        else{
            this.textView.setText(("Your habit event timeline:"));
        }
    }

    /**
     * Updates list view.
     *
     * @param events Arraylist of type HabitEvent
     */
    private void updateListView(ArrayList<HabitEvent> events){
        ArrayAdapter<HabitEvent> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.habit_event_list_item, events);
        this.listView.setAdapter(arrayAdapter);
        this.listView.notify();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    // PLEASE LEAVE THIS
    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
