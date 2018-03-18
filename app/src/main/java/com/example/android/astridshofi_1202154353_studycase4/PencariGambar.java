package com.example.android.astridshofi_1202154353_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class PencariGambar extends AppCompatActivity {


    private EditText mInputLink;
    private Button mButtonCari;
    private ImageView mTampilGambar;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_pencari_gambar);

        mInputLink = findViewById(R.id.inputLink);
        mButtonCari = findViewById(R.id.buttonCari);
        mTampilGambar = findViewById(R.id.tampilGambar);
    }

    public void klikCari(View view) {
        loadImageInit();
    }

    private void loadImageInit(){
        String ImgUrl = mInputLink.getText().toString(); //mengambil link yang diinputkan
        new loadImage().execute(ImgUrl); //asynctask gambar dari internet
    }

    private class loadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(PencariGambar.this); //membuat dialog
            mProgressDialog.setMessage("Loading..."); //memberi pesan dalam dialog
            mProgressDialog.show(); //menampilkan dialognya
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]); //mendownload dari link
                Thread.sleep(500);
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent()); //konversi gambar ke bitmap
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap; //menghasilkan bitmap
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mTampilGambar.setImageBitmap(bitmap); //set gambar
            mProgressDialog.dismiss(); //gambar akan ditampilkan dan dialog selesai
        }
    }
}
