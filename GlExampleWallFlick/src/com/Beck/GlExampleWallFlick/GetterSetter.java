package com.Beck.GlExampleWallFlick;

public class GetterSetter {
    public static int loadtex = 1;

    public static String filecontext = null;
    public static int color = 1;
    public static float Touchx;
    public static float Touchy;

    public static float velocityX;
    public static int directionud;
    public static int direction;
    public static float velocityY;
    public static int justflicked;

    public static int getJustflicked() {
        return justflicked;
    }
    public static void setJustflicked(int justflicked) {
        GetterSetter.justflicked = justflicked;
    }
    public static float getVelocityX() {
        return velocityX;
    }
    public static void setVelocityX(float velocityX) {
        GetterSetter.velocityX = velocityX;
    }
    public static int getDirectionud() {
        return directionud;
    }
    public static void setDirectionud(int directionud) {
        GetterSetter.directionud = directionud;
    }
    public static int getDirection() {
        return direction;
    }
    public static void setDirection(int direction) {
        GetterSetter.direction = direction;
    }
    public static float getVelocityY() {
        return velocityY;
    }
    public static void setVelocityY(float velocityY) {
        GetterSetter.velocityY = velocityY;
    }


}
