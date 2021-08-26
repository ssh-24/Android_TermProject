package com.example.shson_termproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static MediaPlayer mp; //배경 음악 재생할 객체

    Button start_btn;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_btn=findViewById(R.id.start_btn);

        textView=findViewById(R.id.textView);

        mp=MediaPlayer.create(this, R.raw.main_bgm); //raw 폴더에 넣어둔 배경음 재생
        mp.setLooping(true); //무한반복 루프
        mp.start();

        //onClick 함수 설정
        start_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                mp.stop(); //화면전환 시 배경음악 종료

                //intent 객체로 레이아웃 전환
                Intent intent = new Intent(getApplicationContext(), Play.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onUserLeaveHint(){
        mp.pause();
        super.onUserLeaveHint();
    }

    @Override
    public void onResume(){
        mp.start();
        super.onResume();
    }

    @Override
    public void onDestroy(){
        mp.stop();
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        mp.stop();
        super.onBackPressed();
    }
}
