package com.example.android.androidyoga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.androidyoga.Adapter.RecyclerViewAdapter;
import com.example.android.androidyoga.Model.Excercise;

import java.util.ArrayList;
import java.util.List;

public class ListExcercises extends AppCompatActivity {

    List<Excercise> excerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_excercises);
        
        initData();

        recyclerView = (RecyclerView)findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(excerciseList, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {

        excerciseList.add(new Excercise(R.drawable.easy_pose, "Easy Pose"));
        excerciseList.add(new Excercise(R.drawable.cobra_pose, "Cobra Pose"));
        excerciseList.add(new Excercise(R.drawable.downward_facing_dog, "Downward Facing Dog"));
        excerciseList.add(new Excercise(R.drawable.boat_pose, "Boat Pose"));
        excerciseList.add(new Excercise(R.drawable.half_pigeon, "Hald Pigeon"));
        excerciseList.add(new Excercise(R.drawable.low_lunge, "Low Lunge"));
        excerciseList.add(new Excercise(R.drawable.upward_bow, "Upward Bow"));
        excerciseList.add(new Excercise(R.drawable.crescent_lunge, "Crescent Lunge"));
        excerciseList.add(new Excercise(R.drawable.warrior_pose, "Warrior Pose"));
        excerciseList.add(new Excercise(R.drawable.bow_pose, "Bow Pose"));
        excerciseList.add(new Excercise(R.drawable.warrior_pose_2, "Warrior Pose 2"));

    }
}
