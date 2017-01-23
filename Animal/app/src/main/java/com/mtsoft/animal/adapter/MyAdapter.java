package com.mtsoft.animal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtsoft.animal.R;
import com.mtsoft.animal.modell.Alphabet;
import com.mtsoft.animal.modell.Parent;

import java.util.ArrayList;

/**
 * Created by ManhHung on 1/10/2017.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Parent> arrParents;

    public MyAdapter(Context context, ArrayList<Parent> arrParents) {
        this.context = context;
        this.arrParents = arrParents;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrParents.size();
    }

    @Override
    public Object getItem(int i) {
        return arrParents.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Parent parent = arrParents.get(i);
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            if (parent instanceof Alphabet) {
                view = inflater.inflate(R.layout.item_alphabet, viewGroup, false);
            } else {
                view = inflater.inflate(R.layout.item_grid, viewGroup, false);
            }
            viewHolder.tvName = (TextView) view.findViewById(R.id.tvName);
            viewHolder.imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String name = parent.getName();
        int idImg = parent.getIdImage();
        int idSound = parent.getPronounce();
        viewHolder.tvName.setText(name);

        viewHolder.imgAvatar.setImageResource(idImg);
        return view;
    }

    class ViewHolder {
        TextView tvName;
        ImageView imgAvatar;
    }
}
