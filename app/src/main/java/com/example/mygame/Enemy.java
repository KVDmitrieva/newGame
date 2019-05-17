package com.example.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

    class Enemy extends Sprite{
    private Bitmap bitmap;
    private Rect sourceRect;
    private int frameNr;
    int currentFrame;
    private long frameTicker;
    private int framePeriod;

    int spriteWidth;
    int spriteHeight;


     int mod = 0;
     private int lines;
     int health, attack;
     int def,crit, id;

     Enemy(Bitmap bitmap, float x, float y, int fps, int frameCount,int lines,int health, int def, int attack, int crit, int id){
        super(bitmap, x, y,  fps, frameCount, lines);
        this.bitmap= bitmap;
        //this.x= x;
       // this.y= y;
        this.lines = lines;
        currentFrame=0;
        frameNr= frameCount;
        spriteWidth= bitmap.getWidth()/ frameCount;
        spriteHeight= bitmap.getHeight()/lines;
        sourceRect=new Rect(0,0, spriteWidth, spriteHeight);
        framePeriod=1000/ fps;
        frameTicker= 0l;


        this.health = health;
        this.def = def;
        this.attack = attack;
        this.crit = crit;
        this.id = id;
    }
    private int count = 0;
     void updates(long gameTime){
        if(gameTime> frameTicker+ framePeriod){
            frameTicker = gameTime;
            currentFrame++;
            if(currentFrame>= frameNr){
                currentFrame=0; count++;
                if(count==6&&lines==4){
                    mod =3; count = 0;
                } else if(count ==1 && mod ==3) mod = 0;
            }
        }
            this.sourceRect.left= currentFrame* spriteWidth;
            this.sourceRect.right= this.sourceRect.left+ spriteWidth;
            this.sourceRect.bottom = spriteHeight*(1+mod);
            this.sourceRect.top = spriteHeight*mod;
        }

     void drawe(Canvas canvas){
        Rect destRect=new Rect((int)x, (int)y, (int)x+ spriteWidth, (int)y+ spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect,null);

    }


}
