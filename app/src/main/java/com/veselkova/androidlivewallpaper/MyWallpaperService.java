
package com.veselkova.androidlivewallpaper;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.service.wallpaper.WallpaperService.Engine;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyWallpaperService extends WallpaperService
{
    public WallpaperService.Engine onCreateEngine()
    {
        return new MyWallpaperEngine();

    }

    private class MyWallpaperEngine extends WallpaperService.Engine
    {
        private List<MyPoint> circles;
        private final Runnable drawRunner = new Runnable()
        {
            public void run()
            {
                MyWallpaperService.MyWallpaperEngine.this.draw();
            }
        };
        private final Handler handler = new Handler();
        int height;
        private int maxNumber;
        private Paint paint = new Paint();
        public int str = SetWallpaperActivity.colorBg;
        private boolean touchEnabled;
        private boolean visible = true;
        private int width;

        public MyWallpaperEngine()
        {
            super();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyWallpaperService.this);
            this.maxNumber = Integer.valueOf(prefs.getString("numberOfCircles", "4")).intValue();
            this.touchEnabled = prefs.getBoolean("touch", false);
            this.circles = new ArrayList();
            this.paint.setAntiAlias(true);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setStrokeJoin(Paint.Join.ROUND);
            this.paint.setStrokeWidth(10.0F);
            this.handler.post(this.drawRunner);
            Log.d("Wallpaper Engine" , ""+str);
        }

        private void draw()
        {
            SurfaceHolder localSurfaceHolder = getSurfaceHolder();
            Canvas localObject1 = null;
            try
            {
                Canvas localCanvas = localSurfaceHolder.lockCanvas();
                if (localCanvas != null)
                {
                    localObject1 = localCanvas;
                    int i = (int)(this.width * Math.random() + 100.0D);
                    localObject1 = localCanvas;
                    int j = (int)(this.height * Math.random() + 100.0D);
                    localObject1 = localCanvas;
                    this.circles.add(new MyPoint(String.valueOf(this.circles.size() + 1), i, j));
                    localObject1 = localCanvas;
                    drawCircles(localCanvas, this.circles);
                }
                if (localCanvas != null) {
                    localSurfaceHolder.unlockCanvasAndPost(localCanvas);
                }
                this.handler.removeCallbacks(this.drawRunner);
                if (this.visible) {
                    this.handler.postDelayed(this.drawRunner, 50L);
                }
                return;
            }
            finally
            {
              /*  if (localObject1 != null) {
                    localSurfaceHolder.unlockCanvasAndPost(localObject1);
                } */

            }
        }

        private void drawCircles(Canvas paramCanvas, List<MyPoint> paramList)
        {
            new Path();
            Paint localPaint = new Paint(1);
            paramCanvas.drawColor(this.str);
            Iterator iterator = paramList.iterator();
            while (iterator.hasNext())
            {
                MyPoint localMyPoint = (MyPoint)iterator.next();
                int i = Color.argb((int)(Math.floor(localMyPoint.rlife / localMyPoint.life * 100.0D) / 100.0D), localMyPoint.r, localMyPoint.g, localMyPoint.b);
                int j = Color.rgb(localMyPoint.r, localMyPoint.g, localMyPoint.b);
                localPaint.setShader(new SweepGradient(localMyPoint.x / 2.0F, localMyPoint.y / 2.0F, new int[] { i, j }, null));
                paramCanvas.drawCircle(localMyPoint.x, localMyPoint.y, localMyPoint.radius * 5.0F, localPaint);
                localMyPoint.rlife -= 1.0D;
                localMyPoint.radius -= 1.0F;
            }
        }

        public void onSurfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
        {
            this.width = paramInt2;
            this.height = paramInt3;
            super.onSurfaceChanged(paramSurfaceHolder, paramInt1, paramInt2, paramInt3);
        }

        public void onSurfaceDestroyed(SurfaceHolder paramSurfaceHolder)
        {
            super.onSurfaceDestroyed(paramSurfaceHolder);
            this.visible = false;
            this.handler.removeCallbacks(this.drawRunner);
        }

        public void onTouchEvent(MotionEvent paramMotionEvent)
        {
            float f1 = paramMotionEvent.getX();
            float f2 = paramMotionEvent.getY();
            SurfaceHolder localSurfaceHolder = getSurfaceHolder();
            Canvas localObject = null;
            try
            {
                Canvas localCanvas = localSurfaceHolder.lockCanvas();
                if (localCanvas != null)
                {
                    localObject = localCanvas;
                    localCanvas.drawColor(this.str);
                    localObject = localCanvas;
                    this.circles.add(new MyPoint(String.valueOf(this.circles.size() + 1), f1, f2));
                    localObject = localCanvas;
                    drawCircles(localCanvas, this.circles);
                }
                if (localCanvas != null) {
                    localSurfaceHolder.unlockCanvasAndPost(localCanvas);
                }
                super.onTouchEvent(paramMotionEvent);
                return;
            }
            finally
            {
              /* if (localObject != null) {
                    localSurfaceHolder.unlockCanvasAndPost(localObject);
                } */
            }
        }

        public void onVisibilityChanged(boolean visible)
        {
            this.visible = visible;
            if (visible)
            {
                this.handler.post(this.drawRunner);
                return;
            }
            this.handler.removeCallbacks(this.drawRunner);
        }
    }
}
