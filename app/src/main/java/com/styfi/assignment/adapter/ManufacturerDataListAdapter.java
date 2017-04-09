package com.styfi.assignment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.styfi.assignment.R;
import com.styfi.assignment.model.ManufacturerData;

import java.util.List;

/**
 * @author Harsh Masand on 11/7/2016.
 */

public class ManufacturerDataListAdapter extends ArrayAdapter<ManufacturerData> {

    private Context context;
    private int resource;
    private List<ManufacturerData> manufacturerDataArrayList;

    public ManufacturerDataListAdapter(Context context, int resource, List<ManufacturerData> manufacturerDataArrayList) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.manufacturerDataArrayList = manufacturerDataArrayList;
    }

    private class ViewHolder {
        TextView manufacturerIDTextView, manufacturerDataTextView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.manufacturerIDTextView = (TextView) convertView.findViewById(R.id.tv_manufacturer_id_value);
            viewHolder.manufacturerDataTextView = (TextView) convertView.findViewById(R.id.tv_manufacturer_data_value);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ManufacturerData manufacturerData = getItem(position);

        viewHolder.manufacturerIDTextView.setText(manufacturerData.getManufacturerID());
        viewHolder.manufacturerDataTextView.setText(manufacturerData.getManufacturerData());

        return convertView;
    }

    @Nullable
    @Override
    public ManufacturerData getItem(int position) {
        return manufacturerDataArrayList.get(position);
    }

    @Override
    public int getCount() {
        return manufacturerDataArrayList.size();
    }

}
