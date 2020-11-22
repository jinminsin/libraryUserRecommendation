package com.example.myapplication0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static User Muser;
    TextView a;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    View.OnClickListener tBtnClick, fBtnClick, okBtnClick;

    private String flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      fragmentManager = getSupportFragmentManager();
        TabLayout tab = findViewById(R.id.tab);
/*
        tBtnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 tbtn 핸들러 입력
                flag += "T";
                Log.e("input = T", "flag : " + flag);
                 QuestionHashmap의 data의 길이가 1일 경우(답에 도달함)
                if(QuestionHashmap.questionHash.get(flag).length == 1) {
                    Log.e("move result view", "flag : " + flag);
                     result fragment로 이동
                    resultFragment = new ResultProcessing(flag, okBtnClick);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.yeah, resultFragment).commit();
                    return;
                }
                qnaFragment = new QuestionProcessing(flag, tBtnClick, fBtnClick);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.yeah, qnaFragment).commit();
            }
        };

        fBtnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag += "F";
                Log.e("input = F", "flag : " + flag);
                if(QuestionHashmap.questionHash.get(flag).length == 1) {
                    Log.e("move result view","flag : " + flag);
                     result Fragment로 이동
                    resultFragment = new ResultProcessing(flag, okBtnClick);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.yeah, resultFragment).commit();
                    return;
            };
                qnaFragment = new QuestionProcessing(flag,tBtnClick,fBtnClick);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.yeah, qnaFragment).commit();
            }
        };

        okBtnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view){
                 okBtn 핸들러 입력
                Log.e("okokok", "messege : ok!");

                    DB에 QuestionHashmap의 선호장르를 전달
                    홈 액티비티로 이동해야함
                    기능 추가 요망

            }
        };

        qnaFragment = new QuestionProcessing(flag,tBtnClick,fBtnClick);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.yeah, qnaFragment).commit();

        */

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.home);
        images.add(R.drawable.table);
        images.add(R.drawable.cloud);
        images.add(R.drawable.setting);

        for(int i = 0; i < 4; i++)
            tab.getTabAt(i).setIcon(images.get(i));

        getSupportFragmentManager().beginTransaction().replace(R.id.yeah,new Community_Tab()).commit();

        /*RequestQueue q = Volley.newRequestQueue(this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Result","["+response+"]");
                           }
        };

        DBResponse.searchCommunityResponse(q, responseListener);*/
    }
}