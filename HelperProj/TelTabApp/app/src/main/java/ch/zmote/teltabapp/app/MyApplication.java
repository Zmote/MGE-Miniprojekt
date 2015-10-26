package ch.zmote.teltabapp.app;

import android.app.Application;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyApplication extends Application {
    public static Notes notes;

    public MyApplication(){
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notes  = new Notes();
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