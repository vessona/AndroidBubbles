package com.veselkova.androidlivewallpaper;

import android.graphics.Color;

/**
 * Created by purch on 5/29/2017.
 */

public class MyPoint {
    double speed;
    float radius;
    double life;
    double rlife;
    double rr, gg, bb;
    int r;
    int g;
    int b;
    float x, y;
    String text;

    public MyPoint(String text, float x, float y)
    {
        this.text=text;
        this.y=y;
        this.x=x;
        speed = -2.5+Math.random()*5;
        radius = (float)(10+Math.random()*20);
        life = 20+Math.random()*10;
        rlife = life;
        rr= Math.floor(Math.random()*255) ;
        gg = Math.floor(Math.random()*255);
        bb =Math.floor(Math.random()*255);
        r = (int)rr;
        g=(int) gg;
        b = (int)bb;

    }
}
