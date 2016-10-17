package com.example.bartek.myapplication;

import android.opengl.GLES20;
import android.support.annotation.NonNull;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Bartek on 2016-10-16.
 */

public class Triangle {
    private FloatBuffer m_vertexBuffer;
    private float[] m_color;
    private final int m_program;
    private int m_positionHandle;
    private int m_colorHandle;

    static final int COORDS_PER_VERTEX = 3;
    static float[] m_triangleCoords = {
            0.0f, 0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    };

    private final int m_vertexCount = m_triangleCoords.length / COORDS_PER_VERTEX;
    private final int m_vertexStride = COORDS_PER_VERTEX * 4;

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    public Triangle() {
        int l_vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int l_fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        m_color = new float[]{0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

        m_program = GLES20.glCreateProgram();

        GLES20.glAttachShader(m_program, l_vertexShader);
        GLES20.glAttachShader(m_program, l_fragmentShader);

        GLES20.glLinkProgram(m_program);
        ByteBuffer l_byteBuffer = ByteBuffer.allocateDirect(m_triangleCoords.length * 4);
        l_byteBuffer.order(ByteOrder.nativeOrder());
        m_vertexBuffer = l_byteBuffer.asFloatBuffer();
        m_vertexBuffer.put(m_triangleCoords);
        m_vertexBuffer.position(0);
    }

    public void draw() {
        GLES20.glUseProgram(m_program);
        m_positionHandle = GLES20.glGetAttribLocation(m_program, "vPosition");
        GLES20.glEnableVertexAttribArray(m_positionHandle);
        GLES20.glVertexAttribPointer(m_positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                m_vertexStride, m_vertexBuffer);

        m_colorHandle = GLES20.glGetUniformLocation(m_program, "vColor");

        GLES20.glUniform4fv(m_colorHandle, 1, m_color, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, m_vertexCount);
        GLES20.glDisableVertexAttribArray(m_positionHandle);
    }
}
