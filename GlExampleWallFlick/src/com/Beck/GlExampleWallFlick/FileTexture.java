package com.Beck.GlExampleWallFlick;

import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.GLUtils;
import android.util.Log;

public class FileTexture {
    public Context context;
    private GL10 gl;
    private int[] textures = new int[1];
    private static BitmapFactory.Options sBitmapOptions = new BitmapFactory.Options();
    private Bitmap bitmap;

    public FileTexture(GL10 gl) {
        if(gl==null)return;
        this.gl = gl;

        sBitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;


        context = GLExample.context;

    }


    public void loadTexture(int card) {

        InputStream is = null;

        is = context.getResources().openRawResource(R.drawable.texture);
        if (GetterSetter.color == 1)
        {
            is = context.getResources().openRawResource(R.drawable.texture);
        }
        if (GetterSetter.color == 2)
        {
            is = context.getResources().openRawResource(R.drawable.bluetex);
        }
        if (GetterSetter.color == 3)
        {
            is = context.getResources().openRawResource(R.drawable.greentex);
        }
        if (GetterSetter.color == 4)
        {
            is = context.getResources().openRawResource(R.drawable.yellowtex);
        }
        if (GetterSetter.color == 5)
        {
            is = context.getResources().openRawResource(R.drawable.purpletex);
        }
        if (GetterSetter.color == 6)
        {

            Intent goToMarket = null;
            goToMarket = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.beck.pokerfliplite"));
            goToMarket.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(goToMarket);
            GetterSetter.color =7;

        }


        bitmap = BitmapFactory.decodeStream(is);



        createTexture();

    }


    private void createTexture() {

        gl.glGenTextures(0, textures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
        gl.glPixelStorei(GL10.GL_UNPACK_ALIGNMENT, 1);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
                           GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                           GL10.GL_LINEAR);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                           GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                           GL10.GL_REPEAT);

        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                     GL10.GL_MODULATE);


        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);



        bitmap.recycle();


        int error = gl.glGetError();
        if (error != GL10.GL_NO_ERROR) {
            Log.w("", "" + error);
        }
        //}
    }

    public void setTexture() {

        gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textures[0]);
    }
}
