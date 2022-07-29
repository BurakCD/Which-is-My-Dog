package com.ethadien.whichismydog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ethadien.whichismydog.R;
import com.ethadien.whichismydog.databinding.ActivityImagesBinding;
import com.ethadien.whichismydog.databinding.GridItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;


public class GridViewAdapter extends BaseAdapter {

    public List<String> ImagesList;
    private Context context;
    GridItemBinding binding;

    public GridViewAdapter(List<String> incomingList, Context context) {
        this.ImagesList = incomingList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ImagesList.size();
    }

    @Override
    public Object getItem(int i) {
        return ImagesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.grid_item,viewGroup,false);
        }
        if (ImagesList != null) {
            Picasso
                    .get()
                    .load(ImagesList.get(i))
                    .transform(new RoundedTransformation(16,0))
                    .into((ImageView) view);
        }

        return view;
    }

}
