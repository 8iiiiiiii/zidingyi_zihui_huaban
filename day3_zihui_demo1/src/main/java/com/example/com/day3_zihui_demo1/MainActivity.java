package com.example.com.day3_zihui_demo1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Bitmap bt;
    private Canvas canvas;
    private int newx;
    private int newy;
    private Paint paint;
    private int startx;
    private int starty;
    private int x;
    private int y;
    private int xx;
    private int yy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        //加载原图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.qqer);
        //创建白纸
        bt = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        //创建画板 参数是白纸
        canvas = new Canvas(bt);
        //创建画笔
        paint = new Paint();
        paint.setColor(Color.BLUE);
        //纸上画画
        canvas.drawBitmap(bitmap,new Matrix(), paint);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startx = (int) motionEvent.getX();
                        starty = (int) motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        newx = (int) motionEvent.getX();
                        newy = (int) motionEvent.getY();
                        canvas.drawLine(startx,starty,newx,newy,paint);
                        startx = newx;
                        starty = newy;
                        imageView.setImageBitmap(bt);
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return true;
            }
        });

    }

    public void red(View v){
       /* //加载原图
        Bitmap bit = BitmapFactory.decodeResource(getResources(), R.mipmap.qqer);
        //创建白纸
        bt = Bitmap.createBitmap(bit.getWidth(), bit.getHeight(), bit.getConfig());
        //创建画板
        canvas = new Canvas();
        //创建画笔
        paint = new Paint();
        //设置画笔颜色
        paint.setColor(Color.RED);
        //在纸上画画
        canvas.drawBitmap(bit,new Matrix(),paint);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x = (int) motionEvent.getX();
                        y = (int) motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        xx = (int) motionEvent.getX();
                        yy = (int) motionEvent.getY();
                        //连线
                        canvas.drawLine(x,y,xx,yy,paint);
                        x = xx;
                        y = yy;
                        imageView.setImageBitmap(bt);
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return true;
            }
        });*/
       paint.setColor(Color.RED);
    }
    public void green(View v){
        paint.setColor(Color.GREEN);
    }

    public void brush (View v){
        paint.setStrokeWidth(30);
    }

    public void ca(View v){
        paint.setAlpha(0);  //设置透明度为0
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); //设置两图相交时的模式，那相交处同
        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true);//消除拉动，使画面圓滑
        paint.setStyle(Paint.Style.STROKE); //设置画笔为空心，否则会是首尾连起来多边形内一块为透明。
        paint.setStrokeJoin(Paint.Join.ROUND); //结合方式，平滑
        paint.setStrokeCap(Paint.Cap.ROUND);  //圆头
        paint.setStrokeWidth(20);//设置空心边框宽
    }

    public void bc(View v){
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".jpg");
        try {
           FileOutputStream out = new FileOutputStream(file);
            Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
            image.compress(Bitmap.CompressFormat.JPEG, 90, out);
            System.out.println("___________保存的__sd___下_______________________");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(MainActivity.this,"保存已经至"+Environment.getExternalStorageDirectory()+"下", Toast.LENGTH_SHORT).show();
    }
}
