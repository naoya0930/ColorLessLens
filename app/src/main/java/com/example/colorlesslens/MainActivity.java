package com.example.colorlesslens;

import java.io.IOException;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //イメージファイルのID（任意）
    private static final int RESULT_PICK_IMAGEFILE = 1000;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //
    public void swapActivityToDisplay(View view){
        //ボタン押したら呼ばれる
        //
        Intent intentX = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        //intentX.addCategory(Intent.CATEGORY_OPENABLE);
        intentX.setType("image/*");
        startActivityForResult(intentX, RESULT_PICK_IMAGEFILE);
        //Intent intent = new Intent(this,DisplayActivity.class);
        //startActivity(intent);


    }

    //画像の描写クラス
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == RESULT_PICK_IMAGEFILE && resultCode == RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();

                try {
                    bmp = getBitmapFromUri(uri);
                    //画像の読み込みに成功すれば次の画面に遷移
                    Intent intent = new Intent(this,DisplayActivity.class);
                    intent.putExtra("Extra_data",uri);
                    startActivity(intent);
                    //imageView.setImageBitmap(bmp);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //Uriからビットマップ作成
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        //引数uri,mode(読み込みr,読み書きrw)
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        //
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

}
