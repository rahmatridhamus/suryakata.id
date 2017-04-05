package com.idev.rahmatridham.imm.ListAdapter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.idev.rahmatridham.imm.Orientation;
import com.idev.rahmatridham.himaifofficialapps.R;
import com.idev.rahmatridham.imm.model.TimelineModel;
import com.vipul.hp_hp.timelineview.TimelineView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class AgendaAcaraAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<TimelineModel> mFeedList;
    private Context mContext;

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        final TimelineModel timeLineModel = mFeedList.get(position);


        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMMM yyyy");

        String strNow = timeLineModel.getDate().substring(0, 10);
        Date date = null;

        try {
            date = format1.parse(strNow);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.division.setText(timeLineModel.getDivision());
        holder.desc.setText(timeLineModel.getTitle());
        holder.date.setText(format2.format(date));
        holder.remaining.setText(timeLineModel.getStatus());


//        String strNow = timeLineModel.getDate().substring(0, 10);
//        Calendar c = Calendar.getInstance();
        if (timeLineModel.getStatus().equals("Today")) {
            holder.mTimelineView.setMarker(mContext.getResources().getDrawable(R.drawable.marker_white));
        }

        holder.views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, Html.fromHtml(timeLineModel.getDesc()), Toast.LENGTH_SHORT).show();
            }
        });

        holder.views.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                new AlertDialog.Builder(v.getContext())
                        .setTitle("Hubungi PIC")
                        .setMessage("Kegiatan " + timeLineModel.getTitle() + ", oleh " + timeLineModel.getDivision() + "\n\n Kontak PIC: " + "082240219493")
                        .setPositiveButton("Hubungi", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + "082240219493"));
//                                Toast.makeText(mContext, "asfsf", Toast.LENGTH_SHORT).show();
                                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                mContext.startActivity(intent);
                            }
                        })
                        .show();
                return false;
            }


        });


    }

    private Orientation mOrientation;

    public AgendaAcaraAdapter(List<TimelineModel> feedList, Orientation orientation, Context context) {
        mFeedList = feedList;
        mOrientation = orientation;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }


    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        mContext = parent.getContext();

        View view;

        if (mOrientation == Orientation.horizontal) {
            view = View.inflate(mContext, R.layout.item_timeline_horizontal, null);
        } else {
            view = View.inflate(mContext, R.layout.item_timeline, null);
        }

        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public int getItemCount() {
        return (mFeedList != null ? mFeedList.size() : 0);
    }

}
