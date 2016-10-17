package com.example.bartek.myapplication;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Bartek on 2016-10-13.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {
    private Triangle m_triangle;
    private Square m_square;

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        m_triangle = new Triangle();
        m_square = new Square();
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        m_triangle.draw();
    }

    public static int loadShader(int p_type, String p_shaderCode) {
        int l_shader = GLES20.glCreateShader(p_type);
        GLES20.glShaderSource(l_shader, p_shaderCode);
        GLES20.glCompileShader(l_shader);

        return l_shader;
    }
}
