package com.ml.zszabo.segunda;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

public class SegundaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Stetho.initializeWithDefaults(this);
    }
}
