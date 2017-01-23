package com.mtsoft.animal.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mtsoft.animal.R;
import com.mtsoft.animal.fragmnet.FragmentAlphabet;
import com.mtsoft.animal.fragmnet.FragmentAnimal;
import com.mtsoft.animal.fragmnet.FragmentFruit;
import com.mtsoft.animal.fragmnet.FragmentNumber;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity/* implements AdapterView.OnItemClickListener*/ {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    //Tạo cơ sở dữ liệu
    SQLiteDatabase database;

    private int[] tabIcons = {
            R.drawable.ic_tab_animal,
            R.drawable.ic_tab_number,
            R.drawable.ic_tab_vegetable,
            R.drawable.ic_tab_abc
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Set icon cho tab
        setupTabIcons();
//        database = MySQLite.initDatabase(this, "dbAnimals.sqlite");
//        Cursor cursor = database.rawQuery("SELECT * FROM Number", null);
//        cursor.moveToFirst();
//        Toast.makeText(this, cursor.getInt(1) + "", Toast.LENGTH_SHORT).show();
    }

    //Hàm insert record vào table
//    private void insertRecord(){
//        for (int i = 0 ; i < 104; i++){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("code", i);
//            contentValues.put("like", 0);
//            database.insert(TABLE_ANIMAL, null, contentValues);
//        }
//
//        for (int i = 0; i < 100; i++){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("code", i);
//            contentValues.put("like", 0);
//            database.insert(TABLE_NUMBER, null, contentValues);
//        }
//
//        for (int i = 0; i < 36; i++){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("code", i);
//            contentValues.put("like", 0);
//            database.insert(TABLE_FRUIT, null, contentValues);
//        }
//
//        for (int i = 0; i < 26; i++){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("code", i);
//            contentValues.put("like", 0);
//            database.insert(TABLE_ALPHABET, null, contentValues);
//        }
//    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentAnimal(), "Animal");
        adapter.addFragment(new FragmentNumber(), "Number");
        adapter.addFragment(new FragmentFruit(), "Fruit");
        adapter.addFragment(new FragmentAlphabet(), "Alphabet");
        viewPager.setAdapter(adapter);
    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(this, SlideShow.class);
//        startActivity(intent);
//    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
