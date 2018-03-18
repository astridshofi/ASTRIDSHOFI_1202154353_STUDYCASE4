package com.example.android.astridshofi_1202154353_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listNama(View view) { //Membuka aktivitas ListMahasiswa
        startActivity(new Intent(MainActivity.this, ListMahasiswa.class));
    }


    public void cariGambar(View view) {
        startActivity(new Intent(MainActivity.this, PencariGambar.class));
    }
}
