package com.mtsoft.animal.fragmnet;

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
import com.mtsoft.animal.modell.Alphabet;
import com.mtsoft.animal.modell.Parent;
import com.mtsoft.animal.mydata.MySQLite;
import com.mtsoft.animal.orther.Config;

import java.util.ArrayList;

/**
 * Created by ManhHung on 1/10/2017.
 */

public class FragmentAlphabet extends Fragment implements
        AdapterView.OnItemClickListener{

    private GridView gdAlphabet;
    private FloatingActionButton fab;
    private ArrayList<Parent> parents;
    private MyAdapter myAdapter;


    private int[] arrIdImg = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j,
            R.drawable.k,
            R.drawable.l,
            R.drawable.m,
            R.drawable.n,
            R.drawable.o,
            R.drawable.p,
            R.drawable.q,
            R.drawable.r,
            R.drawable.s,
            R.drawable.t,
            R.drawable.u,
            R.drawable.v,
            R.drawable.w,
            R.drawable.x,
            R.drawable.y,
            R.drawable.z
    };
    private int[] arrIdSound = {
            R.raw.a,
            R.raw.b,
            R.raw.c,
            R.raw.d,
            R.raw.e,
            R.raw.f,
            R.raw.g,
            R.raw.h,
            R.raw.i,
            R.raw.j,
            R.raw.k,
            R.raw.l,
            R.raw.m,
            R.raw.n,
            R.raw.o,
            R.raw.p,
            R.raw.q,
            R.raw.r,
            R.raw.s,
            R.raw.t,
            R.raw.u,
            R.raw.v,
            R.raw.w,
            R.raw.x,
            R.raw.y,
            R.raw.z,
    };
    public FragmentAlphabet() {
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

    SQLiteDatabase database;

    @Override
    public void onResume() {
        super.onResume();
        createData();
    }

    private void initViews(View view) {
//        Context context = getContext();
//        arrName = context.getResources().getStringArray(R.array.animals);
        gdAlphabet = (GridView) view.findViewById(R.id.gridAnimal);
        parents = new ArrayList<>();
        createData();

        myAdapter = new MyAdapter(getContext(), parents);
        gdAlphabet.setAdapter(myAdapter);
        gdAlphabet.setOnItemClickListener(this);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateLike();
            }
        });
    }

    private void CreateLike() {
        ArrayList<Alphabet> arrAlphabet = new ArrayList<>();
        for (int i = 0; i < parents.size(); i++){
            if (parents.get(i).getLike() == 1){
                arrAlphabet.add((Alphabet) parents.get(i));
            }
        }
        Intent intent = new Intent(getActivity(), SlideShow.class);
        intent.putExtra("arr_alphabet", arrAlphabet);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), SlideShow.class);
        intent.putExtra("arr_alphabet", parents);
        intent.putExtra("index", i);
        startActivity(intent);
    }

    public void createData(){
        database = MySQLite.initDatabase(getActivity(), Config.DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM "+ Config.TABLE_ALPHABET, null);
        cursor.moveToFirst();
        parents.clear();
        for (int i = 0; i < arrIdImg.length; i++){
            int l = cursor.getInt(2);
            int id = cursor.getInt(0);
            cursor.moveToNext();
            parents.add(new Alphabet(id, "", arrIdImg[i], arrIdSound[i], l));
        }
    }


}
