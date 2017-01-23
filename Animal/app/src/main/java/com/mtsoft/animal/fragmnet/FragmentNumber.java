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
import com.mtsoft.animal.modell.MyNumber;
import com.mtsoft.animal.modell.Parent;
import com.mtsoft.animal.mydata.MySQLite;
import com.mtsoft.animal.orther.Config;

import java.util.ArrayList;

/**
 * Created by ManhHung on 1/10/2017.
 */

public class FragmentNumber extends Fragment implements
        AdapterView.OnItemClickListener {

    private GridView gdNumber;
    private FloatingActionButton fab;
    private ArrayList<Parent> parents;
    private MyAdapter myAdapter;

    private int[] arrIdImg = {
            R.drawable.zero,
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.n6,
            R.drawable.seven,
            R.drawable.eight,
            R.drawable.n9,
            R.drawable.ten,
            R.drawable.eleven,
            R.drawable.twelve,
            R.drawable.thirteen,
            R.drawable.fourteen,
            R.drawable.fifteen,
            R.drawable.n16,
            R.drawable.seventeen,
            R.drawable.eighteen,
            R.drawable.n19,
            R.drawable.n20,
            R.drawable.n21,
            R.drawable.n22,
            R.drawable.n23,
            R.drawable.n24,
            R.drawable.n25,
            R.drawable.n26,
            R.drawable.n27,
            R.drawable.n28,
            R.drawable.n29,
            R.drawable.n30,
            R.drawable.n31,
            R.drawable.n32,
            R.drawable.n33,
            R.drawable.n34,
            R.drawable.n35,
            R.drawable.n36,
            R.drawable.n37,
            R.drawable.n38,
            R.drawable.n39,
            R.drawable.n40,
            R.drawable.n41,
            R.drawable.n42,
            R.drawable.n43,
            R.drawable.n44,
            R.drawable.n45,
            R.drawable.n46,
            R.drawable.n47,
            R.drawable.n48,
            R.drawable.n49,
            R.drawable.n50,
            R.drawable.n51,
            R.drawable.n52,
            R.drawable.n53,
            R.drawable.n54,
            R.drawable.n55,
            R.drawable.n56,
            R.drawable.n57,
            R.drawable.n58,
            R.drawable.n59,
            R.drawable.n60,
            R.drawable.n61,
            R.drawable.n62,
            R.drawable.n63,
            R.drawable.n64,
            R.drawable.n65,
            R.drawable.n66,
            R.drawable.n67,
            R.drawable.n68,
            R.drawable.n69,
            R.drawable.n70,
            R.drawable.n71,
            R.drawable.n72,
            R.drawable.n73,
            R.drawable.n74,
            R.drawable.n75,
            R.drawable.n76,
            R.drawable.n77,
            R.drawable.n78,
            R.drawable.n79,
            R.drawable.n80,
            R.drawable.n81,
            R.drawable.n82,
            R.drawable.n83,
            R.drawable.n84,
            R.drawable.n85,
            R.drawable.n86,
            R.drawable.n87,
            R.drawable.n88,
            R.drawable.n89,
            R.drawable.n90,
            R.drawable.n91,
            R.drawable.n92,
            R.drawable.n93,
            R.drawable.n94,
            R.drawable.n95,
            R.drawable.n96,
            R.drawable.n97,
            R.drawable.n98,
            R.drawable.n99

    };
    private int[] arrIdSound = {
            R.raw.n0,
            R.raw.n1,
            R.raw.n2,
            R.raw.n3,
            R.raw.n4,
            R.raw.n5,
            R.raw.n6,
            R.raw.n7,
            R.raw.n8,
            R.raw.n9,
            R.raw.n10,
            R.raw.n11,
            R.raw.n12,
            R.raw.n13,
            R.raw.n14,
            R.raw.n15,
            R.raw.n16,
            R.raw.n17,
            R.raw.n18,
            R.raw.n19,
            R.raw.n20,
            R.raw.n21,
            R.raw.n22,
            R.raw.n23,
            R.raw.n24,
            R.raw.n25,
            R.raw.n26,
            R.raw.n27,
            R.raw.n28,
            R.raw.n29,
            R.raw.n30,
            R.raw.n31,
            R.raw.n32,
            R.raw.n33,
            R.raw.n34,
            R.raw.n35,
            R.raw.n36,
            R.raw.n37,
            R.raw.n38,
            R.raw.n39,
            R.raw.n40,
            R.raw.n41,
            R.raw.n42,
            R.raw.n43,
            R.raw.n44,
            R.raw.n45,
            R.raw.n46,
            R.raw.n47,
            R.raw.n48,
            R.raw.n49,
            R.raw.n50,
            R.raw.n51,
            R.raw.n52,
            R.raw.n53,
            R.raw.n54,
            R.raw.n55,
            R.raw.n56,
            R.raw.n57,
            R.raw.n58,
            R.raw.n59,
            R.raw.n60,
            R.raw.n61,
            R.raw.n62,
            R.raw.n63,
            R.raw.n64,
            R.raw.n65,
            R.raw.n66,
            R.raw.n67,
            R.raw.n68,
            R.raw.n69,
            R.raw.n70,
            R.raw.n71,
            R.raw.n72,
            R.raw.n73,
            R.raw.n74,
            R.raw.n75,
            R.raw.n76,
            R.raw.n77,
            R.raw.n78,
            R.raw.n79,
            R.raw.n80,
            R.raw.n81,
            R.raw.n82,
            R.raw.n83,
            R.raw.n84,
            R.raw.n85,
            R.raw.n86,
            R.raw.n87,
            R.raw.n88,
            R.raw.n89,
            R.raw.n90,
            R.raw.n91,
            R.raw.n92,
            R.raw.n93,
            R.raw.n94,
            R.raw.n95,
            R.raw.n96,
            R.raw.n97,
            R.raw.n98,
            R.raw.n99

    };
    private String[] arrName;


    public FragmentNumber() {
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

    SQLiteDatabase database;

    private void initViews(View view) {
        Context context = getContext();
        arrName = context.getResources().getStringArray(R.array.numbers);
        gdNumber = (GridView) view.findViewById(R.id.gridAnimal);
        parents = new ArrayList<>();
        createData();
        myAdapter = new MyAdapter(getContext(), parents);
        gdNumber.setAdapter(myAdapter);
        gdNumber.setOnItemClickListener(this);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateLike();
            }
        });
    }

    private void CreateLike() {
        ArrayList<MyNumber> arrNumberLike = new ArrayList<>();
        for (int i = 0; i < parents.size(); i++){
            if (parents.get(i).getLike() == 1){
                arrNumberLike.add((MyNumber) parents.get(i));
            }
        }
        Intent intent = new Intent(getActivity(), SlideShow.class);
        intent.putExtra("arr_number", arrNumberLike);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), SlideShow.class);
        intent.putExtra("arr_number", parents);
        intent.putExtra("index", i);
        startActivity(intent);
    }

    public void createData() {
        database = MySQLite.initDatabase(getActivity(), Config.DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM " + Config.TABLE_NUMBER, null);
        cursor.moveToFirst();
        parents.clear();
        for (int i = 0; i < arrIdImg.length; i++) {
            int l = cursor.getInt(2);
            int id = cursor.getInt(0);
            cursor.moveToNext();
            parents.add(new MyNumber(id, "", arrIdImg[i], arrIdSound[i], l));
        }
    }
}

