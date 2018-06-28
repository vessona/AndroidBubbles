
package com.veselkova.androidlivewallpaper;

public class MyPoint
{
    int b;
    double bb;
    int g;
    double gg;
    double life;
    int r;
    float radius;
    double rlife;
    double rr;
    double speed;
    String text;
    float x;
    float y;

    public MyPoint(String paramString, float paramFloat1, float paramFloat2)
    {
        this.text = paramString;
        this.y = paramFloat2;
        this.x = paramFloat1;
        this.speed = (-2.5D + Math.random() * 5.0D);
        this.radius = ((float)(Math.random() * 20.0D + 10.0D));
        this.life = (Math.random() * 10.0D + 20.0D);
        this.rlife = this.life;
        this.rr = Math.floor(Math.random() * 255.0D);
        this.gg = Math.floor(Math.random() * 255.0D);
        this.bb = Math.floor(Math.random() * 255.0D);
        this.r = ((int)this.rr);
        this.g = ((int)this.gg);
        this.b = ((int)this.bb);
    }
}