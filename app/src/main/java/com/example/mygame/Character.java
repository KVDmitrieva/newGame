package com.example.mygame;

import android.graphics.Bitmap;
import android.view.MotionEvent;

    class Character extends Sprite{
       int health,  attack = 5; int def = 5;


    Character(Bitmap bitmap, float x, float y, int fps, int frameCount, int lines, int health) {
        super(bitmap, x, y,  fps, frameCount, lines);

        this.health = health;
    }

    void stop(int v){

        mod = v;
    }


}
