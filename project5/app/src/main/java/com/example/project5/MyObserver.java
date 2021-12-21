package com.example.project5;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyObserver implements LifecycleObserver {

    private static final String OB_Tag = "LifeCycleObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onMyCreate(){
        System.out.println("LifecycleObserver的create方法");
        Log.i(OB_Tag,"Activity_LifecycleObserver：StartEvent");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onMyPause(){
        System.out.println("LifecycleObserver的pause方法");
        Log.i(OB_Tag,"Activity_LifecycleObserver：onMyPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onMyStop(){
        System.out.println("LifecycleObserver的stop方法");
        Log.i(OB_Tag,"Activity_LifecycleObserver：onMyStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onMyResume(){
        System.out.println("LifecycleObserver的resume方法");
        Log.i(OB_Tag,"Activity_LifecycleObserver：onMyResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMyStart(){
        System.out.println("LifecycleObserver的start方法");
        Log.i(OB_Tag,"Activity_LifecycleObserver：onMyStart");
    }

}
