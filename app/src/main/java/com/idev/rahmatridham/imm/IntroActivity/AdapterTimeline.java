package com.idev.rahmatridham.imm.IntroActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.idev.rahmatridham.imm.R;

import java.util.ArrayList;

/**
 * Created by wildan on 16/03/17.
 */

public class AdapterTimeline extends BaseAdapter {
    private Context context;
    private ArrayList<TimelineModel> listtimeline;

    public AdapterTimeline(Context context, ArrayList<TimelineModel> listtimeline) {
        this.context = context;
        this.listtimeline = listtimeline;
    }

    @Override
    public int getCount() {
        return listtimeline.size();
    }

    @Override
    public Object getItem(int position) {
        return listtimeline.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.listrowtimeline, parent, false);
        TextView judul = (TextView) v.findViewById(R.id.judul);
        TextView desc = (TextView) v.findViewById(R.id.textView2);
        judul.setText(listtimeline.get(position).getJudul());
        desc.setText(listtimeline.get(position).getIsi());
        return v;
    }
}
