package com.slave_mk14.libraryuserrecommendation;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class FragmentHelper {
    private Fragment mainFragment;
    private Fragment testFragment;
    private Fragment testResultFragment;
    private Fragment communityFragment;
    private Fragment postFragment;
    private Fragment postContentFragment;
    private Fragment settingFragment;
    private Fragment createPostFragment;

    public FragmentHelper(){
        mainFragment = new HomeFragment();
        testFragment = new TestFragment();
        communityFragment = new CommunityListFragment();
        settingFragment = new SettingFragment();
    }

    public void initialFragment(AppCompatActivity activity){
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, mainFragment).commit();
    }

    public void moveFragment(int index, int viewId, AppCompatActivity activity){
        switch(index){
            case 0:
                activity.getSupportFragmentManager().beginTransaction().remove(mainFragment).commit();
                mainFragment = new HomeFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(viewId, mainFragment).commit();
                Log.e("메인메인","프래그먼트 생성했어요");
                break;
            case 1:
                if(testResultFragment != null) activity.getSupportFragmentManager().beginTransaction().replace(viewId, testResultFragment).commit();
                else activity.getSupportFragmentManager().beginTransaction().replace(viewId, testFragment).commit();
                Log.e("테스테스","프래그먼트 생성했어요");
                break;
            case 2:
                if(postContentFragment != null) {
                    activity.getSupportFragmentManager().beginTransaction().remove(postContentFragment).commit();
                    postContentFragment = null;
                    activity.getSupportFragmentManager().beginTransaction().remove(postFragment).commit();
                    postFragment = null;
                }
                else if(postFragment != null) {
                    activity.getSupportFragmentManager().beginTransaction().remove(postFragment).commit();
                    postFragment = null;
                }
                activity.getSupportFragmentManager().beginTransaction().remove(communityFragment).commit();
                communityFragment = new CommunityListFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(viewId, communityFragment).commit();
                Log.e("커뮤커뮤","프래그먼트 생성했어요");
                break;
            case 3:
                activity.getSupportFragmentManager().beginTransaction().replace(viewId, settingFragment).commit();
                Log.e("세팅세팅","프래그먼트 생성했어요");
                break;
        }
    }

    public void goTestResultFragment(int viewId, String index, Fragment fragment){
        testResultFragment = new TestResultFragment(index);
        fragment.getFragmentManager().beginTransaction().replace(viewId, testResultFragment).commit();
    }

    public void goPostFragment(int cid, String name, int viewId, Fragment fragment)
    {
        postFragment = new PostListFragment(cid, name);
        fragment.getFragmentManager().beginTransaction().replace(viewId, postFragment).commit();
    }

    public void goCreatePostFragment(int cid, int viewId, Fragment fragment)
    {
        createPostFragment = new CreatePostFragment(cid);
        fragment.getFragmentManager().beginTransaction().replace(viewId, createPostFragment).commit();
    }

    public void goPostContentFragment(Post p, int viewId, Fragment fragment)
    {
        postContentFragment = new PostContentFragment(p);
        fragment.getFragmentManager().beginTransaction().replace(viewId, postContentFragment).commit();
    }

    public void endCreatePostFragment(int viewId, Fragment fragment)
    {
        fragment.getFragmentManager().beginTransaction().remove(createPostFragment).commit();
        createPostFragment = null;
        fragment.getFragmentManager().beginTransaction().replace(viewId, postFragment).commit();
    }

    public void endPostContentFragment(int viewId, Fragment fragment)
    {
        fragment.getFragmentManager().beginTransaction().remove(postContentFragment).commit();
        postContentFragment = null;
        fragment.getFragmentManager().beginTransaction().replace(viewId, postFragment).commit();
    }

    public void endTestResultFragment(Fragment fragment){
        fragment.getFragmentManager().beginTransaction().remove(testResultFragment).commit();
        testResultFragment = new TestResultFragment("C");
        MainActivity.tab.getTabAt(0).select();
    }

    public void resetTestResultFragment(Fragment fragment)
    {
        if(testResultFragment != null) {
            fragment.getFragmentManager().beginTransaction().remove(testResultFragment).commit();
            fragment.getFragmentManager().beginTransaction().remove(testFragment).commit();
            testResultFragment = null;
            testFragment = new TestFragment();
        }
        MainActivity.tab.getTabAt(1).select();
    }
}