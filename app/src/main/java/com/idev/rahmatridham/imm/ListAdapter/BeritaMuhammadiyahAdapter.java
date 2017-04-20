package com.idev.rahmatridham.imm.ListAdapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idev.rahmatridham.imm.R;
import com.idev.rahmatridham.imm.model.NewsModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahmatridham on 8/14/2016.
 */
public class BeritaMuhammadiyahAdapter extends BaseAdapter {
    Context context;
    List<NewsModel> newsModelList = new ArrayList<>();
    public BeritaMuhammadiyahAdapter(Context context, List<NewsModel> newsModelList) {
        this.context = context;
        this.newsModelList = newsModelList;
    }

    @Override
    public int getCount() {
        return newsModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.news_listrow, parent, false);

        NewsModel newsModel = newsModelList.get(position);

        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.relativecard);
        TextView title = (TextView) v.findViewById(R.id.textViewNamaItem);
        TextView desc = (TextView) v.findViewById(R.id.textViewDesc);
        TextView date = (TextView) v.findViewById(R.id.textdate);
        layout.bringToFront();

        final ImageView foto = (ImageView) v.findViewById(R.id.imageViewNewsFoto);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMMM yyyy");
        String dates= null;
        try {
            dates = format2.format(format1.parse(newsModel.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        title.setText(newsModel.getTitle());
        desc.setText(Html.fromHtml(newsModel.getDesc()));
        date.setText(dates);
//        foto.setBackgroundResource(R.drawable.myhome);
        Picasso.with(context).load(newsModel.getImg()).into(new Target() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                foto.setBackground(new BitmapDrawable(context.getResources(),bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                foto.setImageResource(R.drawable.news);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        return v;
    }
}
