package com.ml.zszabo.segunda.Util;

import android.util.Log;

import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

public class FrescoFailListenerController extends BaseControllerListener<ImageInfo> {
    @Override
    public void onFailure(String id, Throwable throwable) {
        super.onFailure(id, throwable);
        Log.i("fresco", throwable.getMessage());
    }
}
