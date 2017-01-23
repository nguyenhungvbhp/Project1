package com.mtsoft.animal.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtsoft.animal.R;
import com.mtsoft.animal.modell.Alphabet;
import com.mtsoft.animal.modell.Animal;
import com.mtsoft.animal.modell.Fruit;
import com.mtsoft.animal.modell.MyNumber;
import com.mtsoft.animal.modell.Parent;
import com.mtsoft.animal.mydata.MySQLite;
import com.mtsoft.animal.orther.Config;
import com.mtsoft.animal.orther.CustomImage;

import java.util.ArrayList;

public class CustomViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Parent> arrParent;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private int flagLike = 0;

    public CustomViewPagerAdapter(Context context, ArrayList<Parent> arrParent, Activity activity) {
        this.context = context;
        this.arrParent = arrParent;
        this.activity = activity;
        this.layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrParent.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = layoutInflater.inflate(R.layout.slideshow, container, false);
        final Parent parent = arrParent.get(position);
        final ImageView imgLike = (ImageView) view.findViewById(R.id.icLike);
        ImageView imgSound = (ImageView) view.findViewById(R.id.sound);
        final ImageView imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        ImageView imgPronounce = (ImageView) view.findViewById(R.id.pronounce);
        String name = parent.getName();
        int idImg = parent.getIdImage();
        final int idSound = parent.getPronounce();
        tvName.setText(name);
        if (parent instanceof Animal) {
            final int idPSound = ((Animal) parent).getSound();
            imgSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation scale = AnimationUtils.loadAnimation(context, R.anim.anim_blink);
                    imgAvatar.startAnimation(scale);
                    SoundPool sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
                    /** soundId for Later handling of sound pool **/
                    int soundId = sp.load(context, idPSound, 1); // in 2nd param u have to pass your desire ringtone
                    sp.play(soundId, 1, 1, 0, 0, 1);
                }
            });
        } else {
            imgSound.setVisibility(View.GONE);
        }

        final int like = parent.getLike();
        flagLike = like;
        if (like == 0) {
            imgLike.setImageResource(R.drawable.unlike);
        } else if (like == 1) {
            imgLike.setImageResource(R.drawable.like);
        }

        final int id = parent.getId();
        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parent instanceof MyNumber) {
                    if (flagLike == 0) {
                        flagLike = 1;
                        //Update cơ sở dữ liệu thành 1 và chuyển icon
                        update(1, id, Config.TABLE_NUMBER);
                        imgLike.setImageResource(R.drawable.like);
                    } else if (flagLike == 1) {
                        //Chỉnh nó thành không thích (tức thành 0)
                        flagLike = 0;
                        update(0, id, Config.TABLE_NUMBER);

                        imgLike.setImageResource(R.drawable.unlike);
                    }
                } else if (parent instanceof Fruit) {
                    if (flagLike == 0) {
                        flagLike = 1;
                        //Update cơ sở dữ liệu thành 1 và chuyển icon
                        update(1, id, Config.TABLE_FRUIT);
                        imgLike.setImageResource(R.drawable.like);
                    } else if (flagLike == 1) {
                        //Chỉnh nó thành không thích (tức thành 0)
                        flagLike = 0;
                        update(0, id, Config.TABLE_FRUIT);
                        imgLike.setImageResource(R.drawable.unlike);
                    }
                } else if (parent instanceof Animal) {
                    if (flagLike == 0) {
                        flagLike = 1;
                        //Update cơ sở dữ liệu thành 1 và chuyển icon
                        update(1, id, Config.TABLE_ANIMAL);
                        imgLike.setImageResource(R.drawable.like);
                    } else if (flagLike == 1) {
                        //Chỉnh nó thành không thích (tức thành 0)
                        flagLike = 0;
                        update(0, id, Config.TABLE_ANIMAL);
                        imgLike.setImageResource(R.drawable.unlike);
                    }
                } else if (parent instanceof Alphabet) {
                    if (flagLike == 0) {
                        flagLike = 1;
                        //Update cơ sở dữ liệu thành 1 và chuyển icon
                        update(1, id, Config.TABLE_ALPHABET);
                        imgLike.setImageResource(R.drawable.like);
                    } else if (flagLike == 1) {
                        flagLike = 0;
                        //Chỉnh nó thành không thích (tức thành 0)
                        update(0, id, Config.TABLE_ALPHABET);
                        imgLike.setImageResource(R.drawable.unlike);
                    }
                }

            }
        });

        //Cuwsstom hình ảnh
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), idImg);
        Bitmap circularBitmap = CustomImage.getRoundedCornerBitmap(bitmap, 100);

        imgPronounce.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SoundPool sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
                /** soundId for Later handling of sound pool **/
                int soundId = sp.load(context, idSound, 1); // in 2nd param u have to pass your desire ringtone
                sp.play(soundId, 1, 1, 0, 0, 1);
            }
        });

//        ImageView circularImageView = (ImageView)findViewById(R.id.imageView);
//        circularImageView.setImageBitmap(circularBitmap);
//        imgAvatar.setImageBitmap(circularBitmap);
        imgAvatar.setImageResource(idImg);
        Animation scale = AnimationUtils.loadAnimation(context, R.anim.anim_zoom);
        imgAvatar.startAnimation(scale);
        container.addView(view);
        return view;
    }

    private void update(int like, int id, String name) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("like", like);

        SQLiteDatabase database = MySQLite.initDatabase(activity, "dbAnimals.sqlite");
        database.update(name, contentValues, "id = ?", new String[]{id + ""});

    }


}
