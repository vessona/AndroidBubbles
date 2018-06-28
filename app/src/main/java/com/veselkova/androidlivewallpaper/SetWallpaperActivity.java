package com.veselkova.androidlivewallpaper;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.veselkova.androidlivewallpaper.MyWallpaperService;
import com.veselkova.androidlivewallpaper.R;

public class SetWallpaperActivity extends AppCompatActivity
{
    public static int colorBg = -1;

    public void onClick(View paramView)
    {
        Intent intent = new Intent("android.service.wallpaper.CHANGE_LIVE_WALLPAPER");
        intent.putExtra("android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT", new ComponentName(this, MyWallpaperService.class));
        startActivity(intent);
    }

    protected void onCreate(Bundle paramBundle)
    {
        getWindow().getDecorView().setBackgroundColor(-1);
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_set_wallpaper);
        ((Button)findViewById(R.id.btnBlack)).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                SetWallpaperActivity.colorBg = Color.BLACK;
                Log.d("btnBlack", "btnBlack clicked");
            }
        });
        ((Button)findViewById(R.id.btnWhite)).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                SetWallpaperActivity.colorBg = Color.WHITE;
                Log.d("btnWhite", "btnWhite clicked");
            }
        });
    }
}