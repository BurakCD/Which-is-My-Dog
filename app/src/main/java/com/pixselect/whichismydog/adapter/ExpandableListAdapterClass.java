package com.pixselect.whichismydog.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.pixselect.whichismydog.R;

import java.util.List;
import java.util.Map;

public class ExpandableListAdapterClass extends BaseExpandableListAdapter {

    private Context context;
    private Map<String, List<String>> collection;
    private List<String> ParentList;

    public ExpandableListAdapterClass() {
    }

    public ExpandableListAdapterClass(Context context, Map<String, List<String>> collection, List<String> parentList) {
        this.context = context;
        this.collection = collection;
        ParentList = parentList;
    }



    @Override
    public int getGroupCount() {
        return collection.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return collection.get(ParentList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return ParentList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return collection.get(ParentList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String BreedName = ParentList.get(i);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_parent_item,null);
        }
        TextView ListViewParentTextView = view.findViewById(R.id.listViewParentTextView);
        ListViewParentTextView.setTypeface(null, Typeface.BOLD);
        ListViewParentTextView.setText(BreedName);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String model = collection.get(ParentList.get(i)).get(i1);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_child_item,null);
        }
        TextView ListViewChildTextView = view.findViewById(R.id.listViewChildTextView);
        ListViewChildTextView.setTypeface(null, Typeface.NORMAL);
        ListViewChildTextView.setText(model);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
