package com.example.android.androidyoga;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.androidyoga.Database.YogaDB;
import com.example.android.androidyoga.Utils.Common;

import org.w3c.dom.Text;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;

public class ViewExcercise extends AppCompatActivity {

    int image_id;
    String name;

    TextView timer,title;
    ImageView detail_image;

    Button btnStart;

    boolean isRunning = false;

    YogaDB yogaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_excercise);

        yogaDB = new YogaDB(this);

        timer = (TextView)findViewById(R.id.timer);
        title = (TextView)findViewById(R.id.title);
        detail_image = (ImageView)findViewById(R.id.detail_image);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!isRunning){

                    btnStart.setText("Done");

                    int timeLimt = 0;
                    if(yogaDB.getSettingMode() == 0)
                        timeLimt = Common.TIME_LIMIT_EASY;
                    else if(yogaDB.getSettingMode() == 1)
                        timeLimt = Common.TIME_LIMIT_MEDIUM;
                    else if(yogaDB.getSettingMode() == 2)
                        timeLimt = Common.TIME_LIMIT_HARD;

                    new CountDownTimer(timeLimt,1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText("" + millisUntilFinished / 1000);
                        }

                        @Override
                        public void onFinish() {

                            //find out how to put adds here.
                            Toast.makeText(ViewExcercise.this, "Finish!!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
                else {
                    //find out how to put adds here.
                    Toast.makeText(ViewExcercise.this, "Finish!!!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                isRunning = !isRunning;
            }
        });

        timer.setText("");

        if(getIntent() != null){

            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);
        }
    }
}
