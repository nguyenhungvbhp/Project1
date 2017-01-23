package com.mtsoft.animal.fragmnet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mtsoft.animal.R;
import com.mtsoft.animal.activity.SlideShow;
import com.mtsoft.animal.adapter.MyAdapter;
import com.mtsoft.animal.modell.Fruit;
import com.mtsoft.animal.modell.MyNumber;
import com.mtsoft.animal.modell.Parent;
import com.mtsoft.animal.mydata.MySQLite;
import com.mtsoft.animal.orther.Config;

import java.util.ArrayList;

/**
 * Created by ManhHung on 1/19/2017.
 */

public class FragmentFruit extends Fragment implements
        AdapterView.OnItemClickListener {

    private GridView gdFruit;
    private FloatingActionButton fab;
    private ArrayList<Parent> parents;
    private MyAdapter myAdapter;

    SQLiteDatabase database;

    private int[] arrIdImg = {
            R.drawable.apple,
            R.drawable.apricot,
            R.drawable.avocado,
            R.drawable.banana,
            R.drawable.cherry,
            R.drawable.coconut,
            R.drawable.custard_apple,
            R.drawable.durian,
            R.drawable.fig,
            R.drawable.grape,
            R.drawable.grapefruit,
            R.drawable.guava,
            R.drawable.jackfruit,
            R.drawable.jujube,
            R.drawable.kumquat,
            R.drawable.lemon,
            R.drawable.lime,
            R.drawable.lychee,
            R.drawable.mandarin,
            R.drawable.mango,
            R.drawable.mangosteen,
            R.drawable.melon,
            R.drawable.orange,
            R.drawable.papaya,
            R.drawable.passion,
            R.drawable.peach,
            R.drawable.pear,
            R.drawable.persimmon,
            R.drawable.pineapple,
            R.drawable.plum,
            R.drawable.pomegranate,
            R.drawable.rambutan,
            R.drawable.soursop,
            R.drawable.strawberry,
            R.drawable.tomato,
            R.drawable.watermelon

    };
    private int[] arrIdSound = {
            R.raw.apple,
            R.raw.apricot,
            R.raw.avocado,
            R.raw.banana,
            R.raw.cherry,
            R.raw.coconut,
            R.raw.custard_apple,
            R.raw.durian,
            R.raw.fig,
            R.raw.grape,
            R.raw.grapefruit,
            R.raw.guava,
            R.raw.jackfruit,
            R.raw.jujube,
            R.raw.kumquat,
            R.raw.lemon,
            R.raw.lime,
            R.raw.lychee,
            R.raw.mandarin,
            R.raw.mango,
            R.raw.mangosteen,
            R.raw.melon,
            R.raw.orange,
            R.raw.papaya,
            R.raw.passion,
            R.raw.peach,
            R.raw.pear,
            R.raw.persimmon,
            R.raw.pineapple,
            R.raw.plum,
            R.raw.pomegranate,
            R.raw.rambutan,
            R.raw.soursop,
            R.raw.strawberry,
            R.raw.tomato,
            R.raw.watermelon

    };
    private String[] arrName;

    public FragmentFruit() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_view, container, false);
        initViews(view);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        createData();
    }

    private void initViews(View view) {
        Context context = getContext();
        arrName = context.getResources().getStringArray(R.array.fruits);
        gdFruit = (GridView) view.findViewById(R.id.gridAnimal);
        parents = new ArrayList<>();
        createData();
        myAdapter = new MyAdapter(getContext(), parents);
        gdFruit.setAdapter(myAdapter);
        gdFruit.setOnItemClickListener(this);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateLike();
            }
        });
    }

    private void CreateLike() {
        ArrayList<Fruit> arrFruitLike = new ArrayList<>();
        for (int i = 0; i < parents.size(); i++){
            if (parents.get(i).getLike() == 1){
                arrFruitLike.add((Fruit) parents.get(i));
            }
        }
        Intent intent = new Intent(getActivity(), SlideShow.class);
        intent.putExtra("arr_fruit", arrFruitLike);
        startActivity(intent);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), SlideShow.class);
        intent.putExtra("arr_fruit", parents);
        intent.putExtra("index", i);
        startActivity(intent);
    }

    public void createData(){
        database = MySQLite.initDatabase(getActivity(), Config.DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM " + Config.TABLE_FRUIT, null);
        cursor.moveToFirst();
        parents.clear();
        for (int i = 0; i < arrIdImg.length; i++){
            int l = cursor.getInt(2);
            int id = cursor.getInt(0);
            cursor.moveToNext();
            parents.add(new MyNumber(id, arrName[i], arrIdImg[i], arrIdSound[i], l));
        }
    }
}