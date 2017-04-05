package com.idev.rahmatridham.imm.ListAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.idev.rahmatridham.himaifofficialapps.R;
import com.vipul.hp_hp.timelineview.TimelineView;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    public TextView date,desc,remaining, division;
    public View views;

    public TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);
        division = (TextView) itemView.findViewById(R.id.tx_division);
        date = (TextView) itemView.findViewById(R.id.tx_date);
        desc = (TextView) itemView.findViewById(R.id.tx_desc);
        remaining = (TextView) itemView.findViewById(R.id.tx_dateRemaining);
        views = itemView;
        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
        mTimelineView.initLine(viewType);
    }
}
