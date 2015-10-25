package ch.zmotions.myapplication1.app;

import android.app.Application;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    public static List<Note> notes;

    public MyApplication(){
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notes  = new ArrayList<>();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public List<Note> getNotes(){
        return notes;
    }
}