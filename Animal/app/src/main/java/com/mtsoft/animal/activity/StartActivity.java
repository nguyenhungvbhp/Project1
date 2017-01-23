package com.mtsoft.animal.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mtsoft.animal.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isRunning = true;
    private int[] imgNumber = {
            R.drawable.ic_3,
            R.drawable.ic_2,
            R.drawable.ic_1,
            R.drawable.ic,
            R.drawable.ic,
            R.drawable.ic,
            R.drawable.ic
    };

    private ImageView icNumber;
    private TextView  icFruit, icAlphabet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initViews();
    }

    private void initViews() {
        icNumber = (ImageView) findViewById(R.id.ic_number);
        Animation scale = AnimationUtils.loadAnimation(this, R.anim.anim_image);
        icNumber.startAnimation(scale);
        icAlphabet = (TextView) findViewById(R.id.ic_alphabet);
        icFruit = (TextView) findViewById(R.id.ic_fruit);
        icAlphabet.setOnClickListener(this);
        icFruit.setOnClickListener(this);

        MyAsync mAsync = new MyAsync();
        mAsync.execute();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_fruit:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.ic_alphabet:
                Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class MyAsync extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            int i = 0;
            while (isRunning){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 3){
                    isRunning = false;
                }
                publishProgress(i);
                i++;
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            icNumber.setVisibility(View.INVISIBLE);
            icAlphabet.setVisibility(View.VISIBLE);
            isRunning = false;
            icFruit.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int i = values[0];
            icNumber.setImageResource(imgNumber[i]);
        }
    }
}
