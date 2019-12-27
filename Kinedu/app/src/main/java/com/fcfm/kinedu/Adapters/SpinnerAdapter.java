package com.fcfm.kinedu.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fcfm.kinedu.R;
import com.fcfm.kinedu.SpinnerItem;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<SpinnerItem> {

    public SpinnerAdapter(Context context, ArrayList<SpinnerItem> spinnerList){
        super(context,0, spinnerList);
    }

    public SpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position,convertView,parent);
    }

    private View InitView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_content,
                    parent,
                    false
            );
        }
        TextView textView = convertView.findViewById(R.id.tvSpinnerContentName);

        SpinnerItem currentItem = getItem(position);

        if(currentItem!=null) {
            textView.setText(currentItem.getSpinnerName());
        }

        return convertView;
    }
}
