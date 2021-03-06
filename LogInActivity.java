package com.slave_mk14.libraryuserrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LogInActivity extends AppCompatActivity {

    private EditText ID, PW;
    private Button signInBtn, signUpBtn;
    private RequestQueue requestQueue;
    private Response.Listener<String> loginListener, joinListener;
    private boolean state = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ID = (EditText) findViewById(R.id.ID);
        PW = (EditText) findViewById(R.id.PW);
        signInBtn = (Button)findViewById(R.id.signInBtn);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);

        loginListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String successCode = jsonObject.getString("success");

                    if(successCode.equals("1"))//성공
                    {
                        JSONObject data = jsonObject.getJSONArray("result").getJSONObject(0);
                        MainActivity.mUser = new User(data.getInt("seedid"),data.getString("id"),data.getInt("authority"), data.getString("likebookcode"),data.getString("siblinglikebookcode"));
                        finish();
                    }else if(successCode.equals("0")){
                        Toast.makeText(LogInActivity.this, "아이디나 비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LogInActivity.this, "서버와 연결되지 않습니다.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                state = true;
            }
        };

        joinListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if(response.equals("1"))//성공
                    {
                        DBResponse.loginResponse(requestQueue,ID.getText().toString(),PW.getText().toString(),loginListener);
                    }else if(response.equals("0")){
                        Toast.makeText(LogInActivity.this, "이미 가입된 아이디입니다.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LogInActivity.this, "서버와 연결되지 않습니다.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                state = true;
            }
        };

        requestQueue = Volley.newRequestQueue(this);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state) {
                    state = false;
                    int id = ID.getText().toString().length();
                    int pw = PW.getText().toString().length();

                    if (id > 0 && pw > 0) { // -> on (1)
                        DBResponse.loginResponse(requestQueue, ID.getText().toString(), PW.getText().toString(), loginListener);
                    }
                    // ID와 PW 둘 다 입력하면 넘어감
                    else { // -> off )(0)
                        Toast.makeText(LogInActivity.this, "ID,PW를 입력하시오", Toast.LENGTH_SHORT).show();
                        state = true;
                    }
                }
            }
            //ID와 PW 두개 중 한개라도 입력하지 않으면 넘어가지 않음
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state) {
                    state = false;
                    int id = ID.getText().toString().length();
                    int pw = PW.getText().toString().length();

                    if (id > 0 && pw > 0) { // -> on (1)
                        DBResponse.joinResponse(requestQueue, ID.getText().toString(), PW.getText().toString(), joinListener);
                    }
                    // ID와 PW 둘 다 입력하면 넘어감
                    else { // -> off )(0)
                        Toast.makeText(LogInActivity.this, "ID,PW를 입력하시오", Toast.LENGTH_SHORT).show();
                        state = true;
                    }
                }
            }
            //ID와 PW 두개 중 한개라도 입력하지 않으면 넘어가지 않음
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        View view = getCurrentFocus();

        if(view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        return true;
    }
}