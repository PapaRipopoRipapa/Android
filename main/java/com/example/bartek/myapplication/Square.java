package com.example.bartek.myapplication;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Bartek on 2016-10-16.
 */

public class Square {
    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    static final int COORDS_PER_VERTEX = 3;
    static float[] squareCoords = {
            -0.5f,  0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
            0.5f, -0.5f, 0.0f,   // bottom right
            0.5f,  0.5f, 0.0f }; // top right

    private short[] drawOrder = { 0, 1, 2, 0, 2, 3 };

    public Square(){
        ByteBuffer l_byteBuffer = ByteBuffer.allocateDirect(squareCoords.length * 4);
        l_byteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = l_byteBuffer.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        ByteBuffer l_drawListBuffer = ByteBuffer.allocateDirect( drawOrder.length * 2);
        l_drawListBuffer.order(ByteOrder.nativeOrder());
        drawListBuffer = l_drawListBuffer.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
    }
}
