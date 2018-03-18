package com.example.android.astridshofi_1202154353_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {

    private ListView mListMahasiswa;
    private Button mStartAsyncTask;
    private ProgressBar mProgressBar; //untuk progressbar
    private String[] namaMahasiswaArray = { //membuat String array
            "Astrid",
            "Shofi",
            "Dzihni",
            "Riesta",
            "Imutka",
            "Cantika",
            "Lucuka",
            "Baikka",
            "Ramahka",
            "Yayaya"};
    private AddItemToListView mAddItemToListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        mListMahasiswa = findViewById(R.id.listMahasiswa);
        mProgressBar = findViewById(R.id.progressBar);
        mStartAsyncTask = findViewById(R.id.buttonMulai);

        //membuat adapter dengan tampilan list item
        mListMahasiswa.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        //Untuk menambahkan list kedalam asynctask
        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //process adapter with asyncTask
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> { //pertama input, kedua progress, ketiga output

        private ArrayAdapter<String> mAdapter;
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListMahasiswa.this); //membuat dialog

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListMahasiswa.getAdapter(); //adapter
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //proses dan isi isi dialog
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setCancelable(true); //agar dapat melakukan cancel
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setProgress(0);
            //button cancel
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) { //method yang tetap dijalankan dibelakang, ketika suatu method sedang dijalankan juga
            for (String item : namaMahasiswaArray) {
                publishProgress(item);
                try {
                    Thread.sleep(500); //delay selama 500 ms
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) { //update progress
            mAdapter.add(values[0]);
            //menghitung agar menjadi persen
            Integer current_status = (int) ((counter / (float) namaMahasiswaArray.length) * 100);
            mProgressBar.setProgress(current_status);

            mProgressDialog.setProgress(current_status);

            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressBar.setVisibility(View.GONE); //mengilangkan progressbar
            mProgressDialog.dismiss(); //menghapus dialog
            mListMahasiswa.setVisibility(View.VISIBLE);
        }
    }
}
