package com.example.bartek.myapplication;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Bartek on 2016-10-13.
 */
public class MyGLSurfaceView extends GLSurfaceView {
    private final MyGLRenderer mRenderer;
    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();

        setRenderer(mRenderer);
    }
}
