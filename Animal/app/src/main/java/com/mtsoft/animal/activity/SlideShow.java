package com.mtsoft.animal.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mtsoft.animal.R;
import com.mtsoft.animal.adapter.CustomViewPagerAdapter;
import com.mtsoft.animal.modell.Parent;

import java.util.ArrayList;

public class SlideShow extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = SlideShow.class.getSimpleName();
    private ViewPager viewPager;
    private Button btnSlideShow;
    private CustomViewPagerAdapter mAdapter;
    private final int delay = 1500;
    private int page = 0;
    private MyAsyncTask mAsyncTask;
    private ImageView imgBack;
    private ImageView imgNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Create");
        setContentView(R.layout.activity_slide_show);
        initViews();

    }

    private void initViews() {
        imgBack = (ImageView) findViewById(R.id.btnBack);
        imgNext = (ImageView) findViewById(R.id.btnNext);
        imgNext.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        setTitle("Animal");
        viewPager = (ViewPager) findViewById(R.id.hot_deal_view_pager);
        if (getData().size() == 0){
            Toast.makeText(this, this.getResources().getString(R.string.not_data), Toast.LENGTH_SHORT).show();
        }
        mAdapter = new CustomViewPagerAdapter(SlideShow.this, getData(), this);
//        SlideshowPagerAdapter adapter = new SlideshowPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        int i = getIntent().getIntExtra("index", 0);
        viewPager.setCurrentItem(i, true);

//        mAsyncTask = new MyAsyncTask();
        btnSlideShow = (Button) findViewById(R.id.btnSlideShow);
        btnSlideShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAsyncTask == null) {
                    mAsyncTask = new MyAsyncTask();
                    mAsyncTask.execute();
                    btnSlideShow.setText("Stop");
                } else if (mAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                    btnSlideShow.setText("Slide Show");
                    mAsyncTask = new MyAsyncTask();
                    mAsyncTask.execute();
                } else if (mAsyncTask.getStatus() == AsyncTask.Status.RUNNING && !mAsyncTask.getPause()) {
                    mAsyncTask.pauseMyTask();
                    btnSlideShow.setText("Resume");
                } else {
                    mAsyncTask.wakeUp();
                    btnSlideShow.setText("Stop");
                }
            }
        });
    }

    public ArrayList<Parent> getData() {
        ArrayList<Parent> arrParent = null;
        ArrayList<Parent> arrAnimal = (ArrayList<Parent>) getIntent().getSerializableExtra("arr_animals");
        ArrayList<Parent> arrAlphabet = (ArrayList<Parent>) getIntent().getSerializableExtra("arr_alphabet");
        ArrayList<Parent> arrNumber = (ArrayList<Parent>) getIntent().getSerializableExtra("arr_number");
        ArrayList<Parent> arrFruit = (ArrayList<Parent>) getIntent().getSerializableExtra("arr_fruit");

        if (arrAlphabet != null) {
            arrParent = arrAlphabet;
        }

        if (arrAnimal != null) {
            arrParent = arrAnimal;
        }

        if (arrNumber != null) {
            arrParent = arrNumber;
        }

        if (arrFruit != null){
            arrParent = arrFruit;
        }
        return arrParent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                if (page == getData().size() - 1){
                    return;
                }
                page++;
                break;
            case R.id.btnNext:
                if (page == 0){
                    return;
                }
                page--;
                break;
        }
        viewPager.setCurrentItem(page, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        resume = false;
        super.onBackPressed();

    }

    boolean resume = true;
    boolean pause = false;
    private class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
        private String NOTHING = "nothing";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



        @Override
        protected Void doInBackground(Void... voids) {
            while (resume) {
                if (page == mAdapter.getCount()) {
                    page = 0;
                } else {
                    page++;
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(page);
                if (pause) {
                    synchronized (NOTHING) {
                        try {
                            NOTHING.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        pause = false;
                    }
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            int p = values[0];

            viewPager.setCurrentItem(p, true);
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        /**
         * Pause task for a while
         */
        public void pauseMyTask() {
            pause = true;
        }

        /**
         * Wake up task from sleeping
         */
        public void wakeUp() {
            synchronized (NOTHING) {
                NOTHING.notify();
            }
        }

        /**
         * Get a loop-flag
         */
        public boolean getPause() {
            return pause;
        }


    }

}
