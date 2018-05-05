package com.example.android.androidyoga;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.androidyoga.Database.YogaDB;
import com.example.android.androidyoga.Model.Excercise;
import com.example.android.androidyoga.Utils.Common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class Daily_Training extends AppCompatActivity {

    Button btnStart;
    ImageView ex_image;
    TextView txtGetReady, txtCountdown, txtTimer, ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;

    int ex_id=0;

    List<Excercise> list = new ArrayList<>();

    YogaDB yogaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__training);

        initData();

        yogaDB = new YogaDB(this);



        btnStart = (Button)findViewById(R.id.btnStart);

        ex_image = (ImageView)findViewById(R.id.detail_image);

        txtCountdown = (TextView)findViewById(R.id.txtCountdown);
        txtGetReady = (TextView)findViewById(R.id.txtGetReady);
        txtTimer = (TextView)findViewById(R.id.timer);
        ex_name = (TextView)findViewById(R.id.title);

        layoutGetReady = (LinearLayout)findViewById(R.id.lay_get_ready);

        progressBar = (MaterialProgressBar)findViewById(R.id.progressBar);

        progressBar.setMax(list.size());

        setExInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(btnStart.getText().toString().toLowerCase().equals("start")){
                    showGetReady();
                    btnStart.setText("done");
                }
                else if(btnStart.getText().toString().toLowerCase().equals("done")) {

                    if(yogaDB.getSettingMode() == 0)
                        exEasyModeCountDown.cancel();
                    else if(yogaDB.getSettingMode() == 1)
                        exMediumModeCountDown.cancel();
                    else if(yogaDB.getSettingMode() == 2)
                        exHardModeCountDown.cancel();

                    restTimeCountDown.cancel();

                    if(ex_id < list.size()){
                        showRestTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        txtTimer.setText("");
                    }
                    else
                        showFinished();
                }
                else{
                    if(yogaDB.getSettingMode() == 0)
                        exEasyModeCountDown.cancel();
                    else if(yogaDB.getSettingMode() == 1)
                        exMediumModeCountDown.cancel();
                    else if(yogaDB.getSettingMode() == 2)
                        exHardModeCountDown.cancel();

                    restTimeCountDown.cancel();

                    if(ex_id < list.size())
                        setExInformation(ex_id);
                    else
                        showFinished();
                }

            }
        });
    }

    private void showRestTime() {
        ex_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setText("Skip");
        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        restTimeCountDown.start();
        txtGetReady.setText("Rest Time");
    }

    private void showGetReady() {
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("GET READY");

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long l) {
                txtCountdown.setText(""+(l-1000)/1000);
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showExercises() {
        if(ex_id < list.size()){
            ex_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if(yogaDB.getSettingMode() == 0)
                exEasyModeCountDown.start();
            else if(yogaDB.getSettingMode() == 1)
                exMediumModeCountDown.start();
            else if(yogaDB.getSettingMode() == 2)
                exHardModeCountDown.start();

            ex_image.setImageResource(list.get(ex_id).getImage_id());
            ex_name.setText(list.get(ex_id).getName());
        }
        else
            showFinished();
    }

    private void showFinished() {
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("Finished!!!!");
        txtCountdown.setText("Congratulations! \nYou're done with today's excercises.");
        txtCountdown.setTextSize(20);

        yogaDB.saveDay(""+ Calendar.getInstance().getTimeInMillis());
    }

    CountDownTimer exEasyModeCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long milli) {
            txtTimer.setText(""+(milli/1000));
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExInformation(ex_id);
                btnStart.setText("Start");
            }
            else{
                showFinished();
            }
        }
    };
    CountDownTimer exMediumModeCountDown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM, 1000) {
        @Override
        public void onTick(long milli) {
            txtTimer.setText(""+(milli/1000));
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExInformation(ex_id);
                btnStart.setText("Start");
            }
            else{
                showFinished();
            }
        }
    };
    CountDownTimer exHardModeCountDown = new CountDownTimer(Common.TIME_LIMIT_HARD, 1000) {
        @Override
        public void onTick(long milli) {
            txtTimer.setText(""+(milli/1000));
        }

        @Override
        public void onFinish() {
            if(ex_id < list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExInformation(ex_id);
                btnStart.setText("Start");
            }
            else{
                showFinished();
            }
        }
    };

    CountDownTimer restTimeCountDown = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {
            txtCountdown.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            setExInformation(ex_id);
            showExercises();
        }
    };

    private void setExInformation(int id) {
        ex_image.setImageResource(list.get(id).getImage_id());
        ex_name.setText(list.get(id).getName());
        btnStart.setText("Start");

        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.INVISIBLE);

    }

    private void initData() {

        list.add(new Excercise(R.drawable.easy_pose, "Easy Pose"));
        list.add(new Excercise(R.drawable.cobra_pose, "Cobra Pose"));
//        list.add(new Excercise(R.drawable.downward_facing_dog, "Downward Facing Dog"));
//        list.add(new Excercise(R.drawable.boat_pose, "Boat Pose"));
//        list.add(new Excercise(R.drawable.half_pigeon, "Hald Pigeon"));
//        list.add(new Excercise(R.drawable.low_lunge, "Low Lunge"));
//        list.add(new Excercise(R.drawable.upward_bow, "Upward Bow"));
//        list.add(new Excercise(R.drawable.crescent_lunge, "Crescent Lunge"));
//        list.add(new Excercise(R.drawable.warrior_pose, "Warrior Pose"));
//        list.add(new Excercise(R.drawable.bow_pose, "Bow Pose"));
//        list.add(new Excercise(R.drawable.warrior_pose_2, "Warrior Pose 2"));

    }
}
