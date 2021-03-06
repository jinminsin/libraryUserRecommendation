package com.slave_mk14.libraryuserrecommendation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView likeList;
    private Button goTestBtn;
    private TextView noHaveLikeCodeText;

    private AdapterLikeBook adapter = new AdapterLikeBook();
    private RequestQueue requestQueue;
    private Response.Listener<String> firstLikeBookListener, secondLikeBookListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home,container,false); //view 띄움
        likeList = rootView.findViewById(R.id.likeBookList);
        goTestBtn = rootView.findViewById(R.id.goTestBtn);
        noHaveLikeCodeText = rootView.findViewById(R.id.noHaveLikeCodeText);

        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(container.getContext());
        likeList.setLayoutManager(linearLayoutManager);

        firstLikeBookListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    ArrayList<Book> item = new ArrayList<>();
                    Log.e("책책",response);
                    Log.e("숫자 숫자", jsonArray.length()+"");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Book book = new Book(obj.getString("name"), obj.getString("publisher"), obj.getString("author"));
                        item.add(book);
                    }
                    LikeBook data = new LikeBook("당신의 원픽! " + DataHashmap.BookCodetoBookHash.get(MainActivity.mUser.getLikeBookCode()), item);
                    adapter.addItem(data);
                    DBResponse.searchRecommendBookListResponse(requestQueue, MainActivity.mUser.getSiblingLikeBookCode(), secondLikeBookListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        secondLikeBookListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    ArrayList<Book> item = new ArrayList<>();
                    Log.e("책책",response);
                    Log.e("숫자 숫자", jsonArray.length()+"");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Book book = new Book(obj.getString("name"), obj.getString("publisher"), obj.getString("author"));
                        item.add(book);
                    }
                    LikeBook data = new LikeBook("당신의 투픽! " + DataHashmap.BookCodetoBookHash.get(MainActivity.mUser.getLikeBookCode()), item);
                    adapter.addItem(data);
                    likeList.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        if(MainActivity.mUser.getLikeBookCode().equals("-1")){
            goTestBtn.setVisibility(View.VISIBLE);
            noHaveLikeCodeText.setVisibility(View.VISIBLE);
            goTestBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.tab.getTabAt(1).select();
                }
            });

        }else {
            requestQueue = Volley.newRequestQueue(container.getContext());
            DBResponse.searchRecommendBookListResponse(requestQueue, MainActivity.mUser.getLikeBookCode(), firstLikeBookListener);
        }

        return rootView;
    }


}