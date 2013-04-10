package com.Beck.GlExampleWallFlick;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLU;

public class OpenGLRenderer implements GLWallpaperService.Renderer {
	public int toss;
	public int count;
	float speed = 0;
    float speedud = 0;
	private GLModel model;
	 int flicked = 0;
	    int flickdirection = 0;
	    int flickdirectionud = 0;
	    int zdirection=0;
	    int xdirection=0;
	    int ydirection=0;
	
	public Context context;
	public float angle =0;
	public float angleud = 0;
	public float anglez = 0;
    public OpenGLRenderer() {
		
		this.context = GLExample.context;
		model = new GLModel(this.context);
		
		
		
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition
	 * .khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background color to black ( rgba ).
		model.Init(gl);
		
		
		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST); 
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.microedition.
	 * khronos.opengles.GL10)
	 */
	public void onDrawFrame(GL10 gl) {
				
		if (gl == null)
			return;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		if (GetterSetter.loadtex == 1)
		{
			model.InitTex();
			//square.loadGLTexture(gl, this.context);
			GetterSetter.loadtex = 0;
			
			
		}
		flicked = GetterSetter.getJustflicked();

		if (flicked == 1)
		{
			speed = GetterSetter.getVelocityX();
			speedud = GetterSetter.getVelocityY();
			flickdirection = GetterSetter.getDirection();
			flickdirectionud = GetterSetter.getDirectionud();
			
			GetterSetter.setJustflicked(0);


		if (flickdirection == 0)
			{
				xdirection = 0;
			}
		if (flickdirection == 1)
			{
				xdirection = 1;
			}
		if (flickdirectionud == 0)
			{
				ydirection = 1;
			}
		if (flickdirectionud == 1)
			{
				ydirection = 0;
			}
		}
		
		
		 if(speed > 0 && flickdirection == 1)
		 {
			 speed = speed  -10;
		 }
		 if(speed < 0 && flickdirection == 1)
		 {
			 speed = 0; 
		 }

		 if(speed < 0 && flickdirection == 0)
		 {
			 speed = speed  + 10;
		 }
		 if(speed > 0 && flickdirection == 0)
		 {
			 speed = 0;
		 }
		 if(speed > 0 && flickdirection == 1)
		 {
			 speed = speed  -10;
		 }
		 
		 
		 if(speedud < 0 && flickdirectionud == 1)
		 {
			 speedud = 0; 
		 }

		 if(speedud < 0 && flickdirectionud == 0)
		 {
			 speedud = speedud  + 20;
		 }

		 
		 if(speedud > 0 && flickdirectionud == 0)
		 {
			 speedud = 0;
		 }
		 if(speedud > 0 && flickdirectionud == 1)
		 {
			 speedud = speedud  - 20;
		 }
		 
		 if(speedud < 0 && flickdirectionud == 1)
		 {
			 speedud = 0;
		 }
		 
		
		
		gl.glPushMatrix();
		gl.glScalef(.2f, .2f, .2f); 	
	    gl.glTranslatef(0, -1f, -20f);
			
				
	
		
		
		
		gl.glRotatef (angle +45, 0.0f, 1.0f, 0.0f);
		//gl.glRotatef (angleud, 1.0f, 0.0f, 0.0f);
		gl.glRotatef (90f, 0.0f, 0.0f, 1.0f);
		gl.glRotatef(-90f, 0.0f, 0.0f, 1.0f);
		gl.glScalef(1.6f, 1.6f, 1.6f);
		
		
	
		
			
	
		
		
	model.draw(gl, false);

	
gl.glPopMatrix();
if (speed > 0)
{
	angle = angle + speed/500;
	
}

if (speed < 0)
{
	angle = angle + speed/500;
}
if (speed == 0)
{
angle = angle + .4f;
}
if (speedud > 0)
{
	angleud = angleud + speedud/500;
	
}

if (speedud < 0)
{
	angleud = angleud + speedud/500;
}
if (speedud == 0)
{
angleud = angleud + .4f;
}


		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition
	 * .khronos.opengles.GL10, int, int)
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Sets the current view port to the new size.
		model.Init(gl);
		
		//model.InitTex();
		//square.loadGLTexture(gl, this.context);
		//square2.loadGLTexture(gl, this.context);
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
				100.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Reset the modelview matrix
		gl.glLoadIdentity();
	}
}
