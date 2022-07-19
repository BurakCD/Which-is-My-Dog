package com.pixselect.whichismydog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.pixselect.whichismydog.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> gallery;

    public GridViewAdapter(List<String> gallery, Context context) {
        this.gallery = gallery;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gallery.size();
    }

    @Override
    public Object getItem(int i) {
        return gallery.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(view.getContext()).inflate(R.layout.grid_item,viewGroup,false);
        }
        Picasso.get().load(gallery.get(i)).into((ImageView) view);

        return view;
    }

}
