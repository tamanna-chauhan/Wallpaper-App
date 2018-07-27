package com.acadview.wallaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Integer[] myImage = {
            R.drawable.img,R.drawable.imga,
            R.drawable.imgb,R.drawable.imgc,
            R.drawable.imgd,R.drawable.imge,
            R.drawable.imgf,R.drawable.imgg,
            R.drawable.imgh

    };

    GridView gridView;

    ImageView myCurrentWall;

    Drawable myDrawable;

    WallpaperManager myWallaper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        myCurrentWall = findViewById(R.id.imageView);

        gridView.setAdapter(new ImageAdapter(getApplicationContext()));

        updateWallpaper();

         }

    private void updateWallpaper() {

        myWallaper = WallpaperManager.getInstance(getApplicationContext());
        myDrawable = myWallaper.getDrawable();
        myCurrentWall.setImageDrawable(myDrawable);

    }

    public class ImageAdapter extends BaseAdapter{

        Context myContext;

        public ImageAdapter(Context applicationContext) {

            myContext = applicationContext;

        }

        @Override
        public int getCount() {
            return myImage.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ImageView myGridImage;

            if(view == null){
                myGridImage = new ImageView(myContext);
                myGridImage.setLayoutParams(new GridView.LayoutParams(512,512));
                myGridImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else{
                myGridImage = (ImageView) view;

            }

            myGridImage.setImageResource(myImage[i]);

            myGridImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        myWallaper.setResource(myImage[i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    updateWallpaper();
                }
            });

            return myGridImage;
        }
    }
}
