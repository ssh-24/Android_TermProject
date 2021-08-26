package com.example.shson_termproject;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Play extends AppCompatActivity {

    private static MediaPlayer mp; //배경 음악 재생할 객체

    TextView victroyCount, score;
    Button btn1,btn2,btn3,btn4,btn_reset,btn_reload;
    ImageView img1,img2,img3,img4;
    int cnt=0; //연승카운트
    int cnt2=0; //연패카운트
    int point=0; //점수합계
    int random=0; //당첨 번호
    boolean boo=true; //성공 or 실패여부

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_start); //화면을 두번째로 바꿈.

        //툴바
        Toolbar mytoolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);

        //효과음 설정
        //최대 음악 파일 갯수 , 스트림 타입, 음질 -기본값:0
        final SoundPool sp = new SoundPool(2,AudioManager.STREAM_MUSIC,0);
        final int successSound1 = sp.load(this,R.raw.yes_one,1); //성공 효과음1
        final int failSound1=sp.load(this,R.raw.no_one,1); //실패 효과음1

        victroyCount=findViewById(R.id.victroyCount); //몇 연승인지 표시할 텍스트뷰
        score=findViewById(R.id.score); //점수 기록할 텍스트뷰

        btn1=findViewById(R.id.btn1); //각 선택버튼
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);

        img1=findViewById(R.id.img1); //각 이미지
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        img4=findViewById(R.id.img4);
        btn_reload=findViewById(R.id.btn_reload); //섞기 버튼
        btn_reset=findViewById(R.id.btn_reset); //점수 초기화 버튼

        mp=MediaPlayer.create(this, R.raw.main_bgm); //raw폴더에 넣어둔 배경음 재생
        mp.setLooping(true); //무한반복 루프
        mp.start();

        random=(int)(Math.random()*4+1); //1부터 4까지 랜덤값 부여

        //위에 새로고침 버튼
        btn_reload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //섞어주기
                random=(int)(Math.random()*4+1); //1부터 4까지 랜덤값 부여

                if(boo==false) {victroyCount.setText(""); score.setText(point+"점");}

                img1.setImageResource(R.drawable.fail);
                img2.setImageResource(R.drawable.fail);
                img3.setImageResource(R.drawable.fail);
                img4.setImageResource(R.drawable.fail);
            }
        });

        //점수 초기화 버튼
        btn_reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //점수 초기화
                score.setText("0점");
                victroyCount.setText("");
                cnt=0;
                cnt2=0;
                point=0;
            }
        });

        //버튼1
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(random==1){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(successSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.success);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.fail);

                    cnt++; //연승카운트 추가
                    cnt2=0;
                    point+=(cnt*1000); //점수추가 연승x1000

                    score.setText(point+"점"); //점수 표시
                    victroyCount.setText(cnt+"연승!"); //연승 표시
                    boo=true;
                }
                else if(random==2){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.success);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.fail);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
                else if(random==3){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.success);
                    img4.setImageResource(R.drawable.fail);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
                else if(random==4){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.success);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
            }
        });

        //버튼2
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(random==1){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.success);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.fail);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
                else if(random==2){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(successSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.success);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.fail);

                    cnt++; //연승카운트 추가
                    cnt2=0;
                    point+=(cnt*1000); //점수추가 연승x1000

                    score.setText(point+"점"); //점수 표시
                    victroyCount.setText(cnt+"연승!"); //연승 표시
                    boo=true;
                }
                else if(random==3){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.success);
                    img4.setImageResource(R.drawable.fail);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
                else if(random==4){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.success);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
            }
        });

        //버튼3
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(random==1){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.success);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.fail);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
                else if(random==2){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.success);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.fail);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
                else if(random==3){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(successSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.success);
                    img4.setImageResource(R.drawable.fail);

                    cnt++; //연승카운트 추가
                    cnt2=0;
                    point+=(cnt*1000); //점수추가 연승x1000

                    score.setText(point+"점"); //점수 표시
                    victroyCount.setText(cnt+"연승!"); //연승 표시
                    boo=true;
                }
                else if(random==4){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.success);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
            }
        });

        //버튼4
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(random==1){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.success);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.fail);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
                else if(random==2){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.success);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.fail);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
                else if(random==3){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(failSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.success);
                    img4.setImageResource(R.drawable.fail);

                    score.setText(point+"점"); //점수 날리기 전 점수 표시
                    if(cnt!=0){
                        victroyCount.setText("꽝!\n최다연승 :"+cnt+"연승");
                    }
                    else if(cnt2==0) victroyCount.setText("꽝!");
                    else victroyCount.setText((cnt2+1)+"연꽝!!ㅋㅋㅋ");
                    cnt=0;
                    cnt2++; //연패 카운트 추가
                    point=0;
                    boo=false;
                }
                else if(random==4){
                    //효과음 재생 : 준비한 soundID, 왼쪽볼륨, 오른쪽볼륨, 우선순위, 반복횟수:0 반복x 1 무한반복, 재생속도 float.
                    sp.play(successSound1,1,1,0,0,1.0f);

                    img1.setImageResource(R.drawable.fail);
                    img2.setImageResource(R.drawable.fail);
                    img3.setImageResource(R.drawable.fail);
                    img4.setImageResource(R.drawable.success);

                    cnt++; //연승카운트 추가
                    cnt2=0;
                    point+=(cnt*1000); //점수추가 연승x1000

                    score.setText(point+"점"); //점수 표시
                    victroyCount.setText(cnt+"연승!"); //연승 표시
                    boo=true;
                }
            }
        });
    }

    //뒤로 가기, 홈키 눌렀을때 bgm 멈춰주는 메서드 정의
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
