package ch.zmotions.phonetabapp.app;

import android.app.Application;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private List<Note> notes;
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notes = new ArrayList<>();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}