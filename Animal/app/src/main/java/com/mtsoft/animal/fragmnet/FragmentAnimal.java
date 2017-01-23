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
import com.mtsoft.animal.modell.Animal;
import com.mtsoft.animal.modell.Parent;
import com.mtsoft.animal.mydata.MySQLite;
import com.mtsoft.animal.orther.Config;

import java.util.ArrayList;

/**
 * Created by ManhHung on 1/10/2017.
 */

public class FragmentAnimal extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gdAnimal;
    private FloatingActionButton fab;
    private ArrayList<Parent> parents;
    private MyAdapter myAdapter;

    SQLiteDatabase database;

    private String[] arrName;
    private int[] arrIdImg = {
            R.drawable.antelope,
            R.drawable.baboon,
            R.drawable.bear,
            R.drawable.beaver,
            R.drawable.bee,
            R.drawable.bison,
            R.drawable.black_bird,
            R.drawable.boar,
            R.drawable.buffalo,
            R.drawable.bull,
            R.drawable.bumblebee,
            R.drawable.camel,
            R.drawable.cat,
            R.drawable.cheetah,
            R.drawable.chimpanzee,
            R.drawable.cockatoo,
            R.drawable.cow,
            R.drawable.coyote,
            R.drawable.crocodile,
            R.drawable.crow,
            R.drawable.deer,
            R.drawable.dog,
            R.drawable.dolphin,
            R.drawable.donkey,
            R.drawable.duck,
            R.drawable.eagle,
            R.drawable.elephant,
            R.drawable.emu,
            R.drawable.ferret,
            R.drawable.finch,
            R.drawable.flamingo,
            R.drawable.fly,
            R.drawable.fox,
            R.drawable.frog,
            R.drawable.gecko,
            R.drawable.gibbon,
            R.drawable.giraffe,
            R.drawable.goat,
            R.drawable.goose,
            R.drawable.gorilla,
            R.drawable.grasshopper,
            R.drawable.grebe,
            R.drawable.guinea,
            R.drawable.hamster,
            R.drawable.hawk,
            R.drawable.hedgehog,
            R.drawable.heron,
            R.drawable.hippo,
            R.drawable.hoopoe,
            R.drawable.horse,
            R.drawable.hyena,
            R.drawable.jackal,
            R.drawable.jaguar,
            R.drawable.kangaroo,
            R.drawable.koala,
            R.drawable.lama,
            R.drawable.lemur,
            R.drawable.leopard,
            R.drawable.lion,
            R.drawable.lynx,
            R.drawable.mole,
            R.drawable.mongoose,
            R.drawable.moose,
            R.drawable.mosquito,
            R.drawable.ostrich,
            R.drawable.otter,
            R.drawable.owl,
            R.drawable.panda,
            R.drawable.parrot,
            R.drawable.peacock,
            R.drawable.penguin,
            R.drawable.pheasant,
            R.drawable.pig,
            R.drawable.pigeon,
            R.drawable.puma,
            R.drawable.rabbit,
            R.drawable.raccoon,
            R.drawable.raven,
            R.drawable.rhinpceros,
            R.drawable.robin,
            R.drawable.roe,
            R.drawable.rooster,
            R.drawable.seagull,
            R.drawable.seal,
            R.drawable.sheep,
            R.drawable.snake,
            R.drawable.sparrow,
            R.drawable.squirrel,
            R.drawable.stork,
            R.drawable.swan,
            R.drawable.tiger,
            R.drawable.tit,
            R.drawable.toucan,
            R.drawable.turkey,
            R.drawable.turtle,
            R.drawable.walrus,
            R.drawable.warthog,
            R.drawable.wasp,
            R.drawable.whale,
            R.drawable.wild_goose,
            R.drawable.wolf,
            R.drawable.woodpecker,
            R.drawable.yak,
            R.drawable.zebra
    };

    private int[] arrSound = {
            R.raw.antelope,
            R.raw.baboon,
            R.raw.bear,
            R.raw.beaver,
            R.raw.bee,
            R.raw.bison,
            R.raw.blackbird,
            R.raw.boar,
            R.raw.buffalo,
            R.raw.bull,
            R.raw.bumblebee,
            R.raw.camel,
            R.raw.cat,
            R.raw.cheetah,
            R.raw.chimpanzee,
            R.raw.cockatoo,
            R.raw.cow,
            R.raw.coyote,
            R.raw.crocodile,
            R.raw.crow,
            R.raw.deer,
            R.raw.dog,
            R.raw.dolphin,
            R.raw.donkey,
            R.raw.duck,
            R.raw.eagle,
            R.raw.elephant,
            R.raw.emu,
            R.raw.ferret,
            R.raw.finch,
            R.raw.flamingo,
            R.raw.fly,
            R.raw.fox,
            R.raw.frog,
            R.raw.gecko,
            R.raw.gibbon,
            R.raw.giraffe,
            R.raw.goat,
            R.raw.goose,
            R.raw.gorilla,
            R.raw.grasshopper,
            R.raw.grebe,
            R.raw.guinea,
            R.raw.hamster,
            R.raw.hawk,
            R.raw.hedgehog,
            R.raw.heron,
            R.raw.hippopotamus,
            R.raw.hooded,
            R.raw.horse,
            R.raw.hyena,
            R.raw.jackal,
            R.raw.jaguar,
            R.raw.kangaroo,
            R.raw.koala,
            R.raw.lama,
            R.raw.lemur,
            R.raw.leopard,
            R.raw.lion,
            R.raw.lynx,
            R.raw.mole,
            R.raw.mongoose,
            R.raw.moose,
            R.raw.mosquito,
            R.raw.ostrich,
            R.raw.otter,
            R.raw.owl,
            R.raw.panda,
            R.raw.parrot,
            R.raw.peacock,
            R.raw.penguin,
            R.raw.pheasant,
            R.raw.pig,
            R.raw.pigeon,
            R.raw.puma,
            R.raw.rabbit,
            R.raw.raccoon,
            R.raw.raven,
            R.raw.rhinoceros,
            R.raw.robin,
            R.raw.roe,
            R.raw.rooster,
            R.raw.seagull,
            R.raw.seal,
            R.raw.sheep,
            R.raw.snake,
            R.raw.sparrow,
            R.raw.squirrel,
            R.raw.stork,
            R.raw.swan,
            R.raw.tiger,
            R.raw.tit,
            R.raw.toucan,
            R.raw.turkey,
            R.raw.turtle,
            R.raw.walrus,
            R.raw.warthog,
            R.raw.wasp,
            R.raw.whale,
            R.raw.wild_goose,
            R.raw.wolf,
            R.raw.woodpecker,
            R.raw.yak,
            R.raw.zebra,
    };


    private int[] arrPSound = {
            R.raw.p_antelope,
            R.raw.p_baboon,
            R.raw.p_bear,
            R.raw.p_beaver,
            R.raw.p_bee,
            R.raw.p_bison,
            R.raw.p_blackbird,
            R.raw.p_boar,
            R.raw.p_buffalo,
            R.raw.p_bull,
            R.raw.p_bumblebee,
            R.raw.p_camel,
            R.raw.p_cat,
            R.raw.p_cheetah,
            R.raw.p_chimpanzee,
            R.raw.p_cockatoo,
            R.raw.p_cow,
            R.raw.p_coyote,
            R.raw.p_crocodile,
            R.raw.p_crow,
            R.raw.p_deer,
            R.raw.p_dog,
            R.raw.p_dolphin,
            R.raw.p_donkey,
            R.raw.p_duck,
            R.raw.p_eagle,
            R.raw.p_elephant,
            R.raw.p_emu,
            R.raw.p_ferret,
            R.raw.p_finch,
            R.raw.p_flamingo,
            R.raw.p_fly,
            R.raw.p_fox,
            R.raw.p_frog,
            R.raw.p_gecko,
            R.raw.p_gibbon,
            R.raw.p_giraffe,
            R.raw.p_goat,
            R.raw.p_goose,
            R.raw.p_gorilla,
            R.raw.p_grasshopper,
            R.raw.p_grebe,
            R.raw.p_guinea,
            R.raw.p_hamster,
            R.raw.p_hawk,
            R.raw.p_hedgehog,
            R.raw.p_heron,
            R.raw.p_hippopotamus,
            R.raw.p_hooded,
            R.raw.p_horse,
            R.raw.p_hyena,
            R.raw.p_jackal,
            R.raw.p_jaguar,
            R.raw.p_kangaroo,
            R.raw.p_koala,
            R.raw.p_lama,
            R.raw.p_lemur,
            R.raw.p_leopard,
            R.raw.p_lion,
            R.raw.p_lynx,
            R.raw.p_mole,
            R.raw.p_mongoose,
            R.raw.p_moose,
            R.raw.p_mosquito,
            R.raw.p_ostrich,
            R.raw.p_otter,
            R.raw.p_owl,
            R.raw.p_panda,
            R.raw.p_parrot,
            R.raw.p_peacock,
            R.raw.p_penguin,
            R.raw.p_pheasant,
            R.raw.p_pig,
            R.raw.p_pigeon,
            R.raw.p_puma,
            R.raw.p_rabbit,
            R.raw.p_raccoon,
            R.raw.p_raven,
            R.raw.p_rhinoceros,
            R.raw.p_robin,
            R.raw.p_roe,
            R.raw.p_rooster,
            R.raw.p_seagull,
            R.raw.p_seal,
            R.raw.p_sheep,
            R.raw.p_snake,
            R.raw.p_sparrow,
            R.raw.p_squirrel,
            R.raw.p_stork,
            R.raw.p_swan,
            R.raw.p_tiger,
            R.raw.p_tit,
            R.raw.p_toucan,
            R.raw.p_turkey,
            R.raw.p_turtle,
            R.raw.p_walrus,
            R.raw.p_warthog,
            R.raw.p_wasp,
            R.raw.p_whale,
            R.raw.p_wild_goose,
            R.raw.p_wolf,
            R.raw.p_woodpecker,
            R.raw.p_yak,
            R.raw.p_zebra,
    };


    public FragmentAnimal() {
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
        arrName = context.getResources().getStringArray(R.array.animals);
        gdAnimal = (GridView) view.findViewById(R.id.gridAnimal);
        parents = new ArrayList<>();
        createData();
        myAdapter = new MyAdapter(getContext(), parents);
        gdAnimal.setAdapter(myAdapter);
        gdAnimal.setOnItemClickListener(this);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateLike();
            }
        });
    }

    private void CreateLike() {
        ArrayList<Animal> arrAnimalLike = new ArrayList<>();
        for (int i = 0; i < parents.size(); i++){
            if (parents.get(i).getLike() == 1){
                arrAnimalLike.add((Animal) parents.get(i));
            }
        }
        Intent intent = new Intent(getActivity(), SlideShow.class);
        intent.putExtra("arr_animals", arrAnimalLike);
        startActivity(intent);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), SlideShow.class);
        intent.putExtra("arr_animals", parents);
        intent.putExtra("index", i);
        startActivity(intent);
    }

    public void createData(){
        database = MySQLite.initDatabase(getActivity(), Config.DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM "+ Config.TABLE_ANIMAL, null);
        cursor.moveToFirst();
        parents.clear();
        for (int i = 0; i < arrIdImg.length; i++){
            int l = cursor.getInt(2);
            int id = cursor.getInt(0);
            cursor.moveToNext();
            parents.add(new Animal(id, arrName[i], arrIdImg[i], arrPSound[i], l, arrSound[i]));
        }
    }
}
