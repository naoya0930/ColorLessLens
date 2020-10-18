package com.example.colorlesslens;

import java.io.IOException;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.content.Intent;
//画像ファイルの入れ物
import android.widget.ImageView;
//ファイルの読み込み形式
import android.net.Uri;
//bitマップの取り扱い
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//uriの取り扱い
import android.os.ParcelFileDescriptor;
import java.io.FileDescriptor;
//Log管理
import android.util.Log;
//android内のデータにアクセス
import android.provider.MediaStore;
//androidカラー
import android.graphics.Color;
//SeekBarの設定
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.SeekBar;

public class DisplayActivity extends AppCompatActivity {
    private Bitmap bmp;
    private ImageView imageview;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        //プログレスバーの設定
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("システム");
        progressDialog.setMessage("処理中");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //xmlライブラリの設定
        Toolbar toolbar = findViewById(R.id.toolbar);
        SeekBar sb1=(SeekBar)findViewById(R.id.seekBar1);
        SeekBar sb2=(SeekBar)findViewById(R.id.seekBar2);
        SeekBar sb3=(SeekBar)findViewById(R.id.seekBar3);
        //ツールバーの設定
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");


        //画像のセット
        Intent intent = getIntent();
        Uri uri = (Uri) intent.getParcelableExtra("Extra_data");
        try {
            //bmp = getBitmapFromUri(uri);
            bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ggggds", "gggggggggggggggggg");
        }
        imageview = (ImageView) findViewById(R.id.image_view);
        imageview.setImageBitmap(bmp);
    /*画面右下にあるボタンの制御
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
     */
        sb1.setOnSeekBarChangeListener(
                new OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // ツマミをドラッグしたときに呼ばれる
                        //tv0.setText("設定値:"+sb1.getProgress());
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // ツマミに触れたときに呼ばれる
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                        // ツマミを離したときに呼ばれる
                        int current =seekBar.getProgress();
                        //ダイアログの表示
                        //progressDialog.show();
                        Bitmap bitEx = changeColorRed(bmp,current);
                        imageview.setImageBitmap(bitEx);
                        //Log.e("sss",""+Integer.toString(current));
                    }
                }
        );
        sb2.setOnSeekBarChangeListener(
                new OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // ツマミをドラッグしたときに呼ばれる
                        //tv0.setText("設定値:"+sb2.getProgress());
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // ツマミに触れたときに呼ばれる
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                        // ツマミを離したときに呼ばれる
                        int current =seekBar.getProgress();
                        //ダイアログの表示
                        //progressDialog.show();
                        Bitmap bitEx = changeColorGreen(bmp,current);
                        imageview.setImageBitmap(bitEx);
                        //Log.e("sss",""+Integer.toString(current));
                    }
                }
        );
        sb3.setOnSeekBarChangeListener(
                new OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        // ツマミをドラッグしたときに呼ばれる
                        //tv0.setText("設定値:"+sb3.getProgress());
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // ツマミに触れたときに呼ばれる
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                        // ツマミを離したときに呼ばれる
                        int current =seekBar.getProgress();
                        //ダイアログの表示
                        //progressDialog.show();
                        Bitmap bitEx = changeColorBlue(bmp,current);
                        imageview.setImageBitmap(bitEx);
                        //Log.e("sss",""+Integer.toString(current));
                    }
                }
        );
    }

    //Uriからビットマップ作成
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        //引数uri,mode(読み込みr,読み書きrw)
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "rw");
        //
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }


    //赤のバー
    private Bitmap changeColorRed(Bitmap bitmap,int divid) {
        Bitmap mutableBitmap = getMutableBitmap(bitmap);
        //色変換処理を実行
        int width = mutableBitmap.getWidth();
        int height = mutableBitmap.getHeight();
        int div=256-divid;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                // Returns the Color at the specified location.
                int pixel = mutableBitmap.getPixel(i, j);

                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                // 代入
                // RGB 値をグレースケール値に変換
                if(green<blue){
                    if(div<blue-green){
                        red=green+(blue-green)/div;
                    }
                }else if(blue<green){
                    if(div<green-blue) {
                        red = blue + (green - blue) / div;
                    }
                }
                //RGBを返す
                int bit_rgb = Color.rgb(red, green, blue);
                mutableBitmap.setPixel(i, j, bit_rgb);
            }
        }
        return mutableBitmap;
    }
    //緑のバーの設定
    private Bitmap changeColorGreen(Bitmap bitmap,int divid) {
        Bitmap mutableBitmap = getMutableBitmap(bitmap);
        //色変換処理を実行
        int width = mutableBitmap.getWidth();
        int height = mutableBitmap.getHeight();
        int div=256-divid;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                // Returns the Color at the specified location.
                int pixel = mutableBitmap.getPixel(i, j);

                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                // 代入
                // RGB 値をグレースケール値に変換
                if(red<blue){
                    if(div<blue-red){
                        green=red+(blue-red)/div;
                    }
                }else if(blue<red){
                    if(div<red-blue) {
                        green = blue + (red - blue) / div;
                    }
                }
                //RGBを返す
                int bit_rgb = Color.rgb(red, green, blue);
                mutableBitmap.setPixel(i, j, bit_rgb);
            }
        }
        return mutableBitmap;
    }
    //青のバーの設定
    private Bitmap changeColorBlue(Bitmap bitmap,int divid) {
        Bitmap mutableBitmap = getMutableBitmap(bitmap);
        //色変換処理を実行
        int width = mutableBitmap.getWidth();
        int height = mutableBitmap.getHeight();
        int div=256-divid;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                // Returns the Color at the specified location.
                int pixel = mutableBitmap.getPixel(i, j);

                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                // 代入
                // RGB 値をグレースケール値に変換
                if(green<red){
                    if(div<red-green){
                        blue=green+(red-green)/div;
                    }
                }else if(red<green){
                    if(div<green-red) {
                        blue = red + (green - red) / div;
                    }
                }
                //RGBを返す
                int bit_rgb = Color.rgb(red, green, blue);
                //Log.e("s",Integer.toString(red));
                mutableBitmap.setPixel(i, j, bit_rgb);
            }
        }
        return mutableBitmap;
    }
    //マルチブルビットマップを返す
    private Bitmap getMutableBitmap(Bitmap bitmap) {
        /*
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        */
        // 変更可能なbitmap
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        return mutableBitmap;
    }
}
