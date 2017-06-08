package com.veselkova.androidlivewallpaper;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.radius;
import static android.R.attr.x;

/**
 * Created by purch on 5/29/2017.
 */

public class MyWallpaperService extends WallpaperService {
 @Override
    public Engine onCreateEngine(){
     return new MyWallpaperEngine();

 }
 private class MyWallpaperEngine extends Engine
 {
     private final Handler handler = new Handler();
     private final Runnable drawRunner = new Runnable() {
         @Override
         public void run() {
             draw();
         }
     };
     private List<MyPoint> circles;
     private Paint paint = new Paint();
     private int width;
     int height;
     private boolean visible = true;
     private int maxNumber;
     private boolean touchEnabled;


    /*   @Override
   public void onCreate (SurfaceHolder surfaceHolder){
         float x = width/2;
         float y = height/2;
        SurfaceHolder holder = getSurfaceHolder();
         Canvas canvas = null;
         try{
             canvas = holder.lockCanvas();

                 canvas.drawColor(Color.BLACK);
                 //  circles.clear();
                 circles.add(new MyPoint(String.valueOf(circles.size() + 1), x, y));
                 drawCircles(canvas, circles);

             }finally {
             if(canvas != null)
                 holder.unlockCanvasAndPost(canvas);
         }


     } */

     public MyWallpaperEngine(){
         SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyWallpaperService.this);
         maxNumber = Integer.valueOf(prefs.getString("numberOfCircles", "4"));
         touchEnabled = prefs.getBoolean("touch", false);
         circles = new ArrayList<MyPoint>();
         paint.setAntiAlias(true);
        // paint.setColor(Color.WHITE);
         paint.setStyle(Paint.Style.FILL);
         paint.setStrokeJoin(Paint.Join.ROUND);
         paint.setStrokeWidth(10f);
         handler.post(drawRunner);

     }


     @Override
     public void onVisibilityChanged(boolean visible)
     {
         this.visible= visible;
         if(visible){
             handler.post(drawRunner);
         }
         else{
             handler.removeCallbacks(drawRunner);
         }
     }

     @Override
     public void onSurfaceDestroyed(SurfaceHolder holder){
         super.onSurfaceDestroyed(holder);
         this.visible = false;
         handler.removeCallbacks(drawRunner);

     }
     @Override
     public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height)
 {
     this.width=width;
     this.height=height;
     super.onSurfaceChanged(holder, format, width, height);

  /*   float x = width/2;
     float y = height/2;
     holder = getSurfaceHolder();
     Canvas canvas = null;
     try{
         canvas = holder.lockCanvas();
         if(canvas != null)
         {
             canvas.drawColor(Color.BLACK);
             //  circles.clear();
             circles.add(new MyPoint(String.valueOf(circles.size() + 1), x, y));
             drawCircles(canvas, circles);

         }

     } finally {
         if(canvas != null)
             holder.unlockCanvasAndPost(canvas);
     }*/
 }


 @Override
 public void onTouchEvent(MotionEvent event)
 {
     //if(touchEnabled)
     //{
         float x = event.getX();
         float y = event.getY();
         SurfaceHolder holder = getSurfaceHolder();
         Canvas canvas = null;
         try{
             canvas = holder.lockCanvas();
             if(canvas != null)
             {
                 canvas.drawColor(Color.BLACK);
                //circles.clear();
                 circles.add(new MyPoint(String.valueOf(circles.size() + 1), x, y));
                 drawCircles(canvas, circles);

             }

         } finally {
             if(canvas != null)
                 holder.unlockCanvasAndPost(canvas);
         }
         super.onTouchEvent(event);
    // }
 }
        private void draw()
        {
            SurfaceHolder holder = getSurfaceHolder();
            Canvas canvas = null;
            try{
                canvas = holder.lockCanvas();
                if(canvas!=null){
                    //if(circles.size() >=10){
                     // circles.clear();
                  //  }
                    int x = (int)(100+width*Math.random());
                    int y = (int) (100+height*Math.random());
                    circles.add(new MyPoint(String.valueOf(circles.size()+1), x, y));
                    drawCircles(canvas, circles);

                }
            }finally {
                if(canvas != null)
                    holder.unlockCanvasAndPost(canvas);
            }
            handler.removeCallbacks(drawRunner);
            if(visible){
                handler.postDelayed(drawRunner, 50);
            }
        }
        private void drawCircles(Canvas canvas, List<MyPoint> circles)
        {
            Path path = new Path();
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            canvas.drawColor(Color.BLACK);
            for(MyPoint point : circles){
                double op = Math.floor(point.rlife/point.life*100)/100;
                int opacity = (int)op;
              //  paint.setColor(Color.);
                  //  paint.setShader(new RadialGradient(getWidth() / 2, getHeight() / 2,
                    //    getHeight() / 3, Color.TRANSPARENT, Color.BLACK, TileMode.MIRROR));
                //    canvas.drawCircle(point.x, point.y, 20.0f, paint);


                int[] Colors = {Color.argb(opacity, point.r, point.g, point.b), Color.rgb(point.r, point.g, point.b)};
                //int[] Colors = {Color.argb(opacity, 249, 75, 104), Color.rgb(251, 68, 98)};
                Shader shader = new SweepGradient(point.x /2, point.y / 2, Colors  , null);
                paint.setShader(shader);
              canvas.drawCircle(point.x , point.y , point.radius*5, paint);
              /*  while(point.radius<=100) {
                    paint.setTextSize(100-point.radius);
                    canvas.drawText("you can do it", point.x, point.y, paint);
                */

                    //regenerate particles
                    point.rlife--;
                    point.radius--;
               // }
                 //  point.x += point.speed;//
                   // point.y += point.speed;

                    //regenerate particles

          /* if(point.rlife < 0 || point.radius < 0)
                {
                    //a brand new particle replacing the dead one
                    point = new MyPoint(String.valueOf(circles.size()+1), point.x, point.y);
                }*/
        }}

 }
}
