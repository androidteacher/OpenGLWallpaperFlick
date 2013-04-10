/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.Beck.GlExampleWallFlick;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public class GLExample extends GLWallpaperService  {
	
	public float velocity;
	   public float velocityud;
	
	public static final String SHARED_PREFS_NAME="flicker";
	
    private SharedPreferences mPrefs;
    public static Context context; 
    private OpenGLRenderer renderer;
    private float mOffset;
    private static final int SWIPE_MIN_DISTANCE = 10;
	   private static final int SWIPE_MAX_OFF_PATH = 10;
	   private static final int SWIPE_THRESHOLD_VELOCITY = 10;
	   private GestureDetector flingDetector;
	   View.OnTouchListener gestureListener;
  
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        flingDetector = new GestureDetector(new MyFlingListener());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (flingDetector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        };

      
      
        
    }
   
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Engine onCreateEngine() {
        return new CubeEngine();
    }

   

    class CubeEngine extends GLEngine  implements SharedPreferences.OnSharedPreferenceChangeListener {
    	
      
        private boolean mVisible;

       
        CubeEngine() {
        	
        	
        	
        	 mPrefs = GLExample.this.getSharedPreferences(SHARED_PREFS_NAME, 0);
             mPrefs.registerOnSharedPreferenceChangeListener(this);
             onSharedPreferenceChanged(mPrefs, null);
           
            
        }
        
        	
       
        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
        
            // By default we don't get touch events, so enable them.
            setTouchEventsEnabled(true);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            mVisible = visible;
            
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
           
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            
        }

        @Override
        public void onOffsetsChanged(float xOffset, float yOffset,
                float xStep, float yStep, int xPixels, int yPixels) {
         
        }

     
        @Override
        public void onTouchEvent(MotionEvent e) {
        	
            GetterSetter.Touchx = e.getX();
	        GetterSetter.Touchy = e.getY();
	        GetterSetter.justflicked = 1;
	        GetterSetter.setVelocityX(0);
	        GetterSetter.setVelocityY(0);
	        flingDetector.onTouchEvent(e);
     	             
     	             
        	super.onTouchEvent(e);
        	
        }
		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
			String color = sharedPreferences.getString("cube2_color", "2");
		
		
		
			
			int colorint = Integer.parseInt(color);
			GetterSetter.color = colorint;
			GetterSetter.loadtex = 1; 
			if (colorint == 6)
			{
			SharedPreferences.Editor prefEd = sharedPreferences.edit();
			prefEd.putString("cube2_color", "15");
			prefEd.commit();
			}

		}
		
       
      
    }


    class MyFlingListener extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
               
            	  if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                  	
                  	velocity = velocityX;
                  	//GetterSetter.setXpos(e2.getX());
                  	//GetterSetter.setYpos(e2.getY());
                   	GetterSetter.setVelocityX(velocity);
                  	GetterSetter.setJustflicked(1);
                  	GetterSetter.setDirection(0);
                  	
                  }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                	velocity = velocityX;
                	//GetterSetter.setXpos(e2.getX());
                  	//GetterSetter.setYpos(e2.getY());
                	GetterSetter.setVelocityX(velocity);
                	GetterSetter.setJustflicked(1);
                	GetterSetter.setDirection(1);
                  // x = e2.getX();
                  }
            	  if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    	//x = e2.getX();
                    	velocityud = velocityY;
                    	//GetterSetter.setXpos(e2.getX());
                      //	GetterSetter.setYpos(e2.getY());
                    	GetterSetter.setVelocityY(velocityud);
                    	GetterSetter.setJustflicked(1);
                    	GetterSetter.setDirectionud(0);
                    	
                    }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                  	velocityud = velocityY;
                	//GetterSetter.setXpos(e2.getX());
                  	//GetterSetter.setYpos(e2.getY());
                  	GetterSetter.setVelocityY(velocityud);
                  	GetterSetter.setJustflicked(1);
                  	GetterSetter.setDirectionud(1);
                    // x = e2.getX();
                    }
            	  
            	
            } catch (Exception e) {
                // nothing
            }
            return false;
        }
	}
    
	

   

      
       

		
            }
            


    
