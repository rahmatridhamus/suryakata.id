package com.idev.rahmatridham.imm.ListAdapter;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idev.rahmatridham.himaifofficialapps.R;
import com.idev.rahmatridham.imm.model.JualanModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by rahmatridham on 8/14/2016.
 */
public class ProdukAdapter extends BaseAdapter {
    Context context;
    List<JualanModel> jualanModelList;

    public ProdukAdapter(Context context, List<JualanModel> jualanModelList) {
        this.context = context;
        this.jualanModelList = jualanModelList;
    }

    @Override
    public int getCount() {
        return jualanModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return jualanModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fjb_listrow, parent, false);

        final JualanModel model = jualanModelList.get(position);

        CardView cardView = (CardView) v.findViewById(R.id.card_view);
        TextView title = (TextView) v.findViewById(R.id.textViewNamaItem);
        TextView desc = (TextView) v.findViewById(R.id.textViewDesc);
        TextView price = (TextView) v.findViewById(R.id.textViewPrice);
        final ImageView foto = (ImageView) v.findViewById(R.id.imageViewNewsFoto);

        title.setText(model.getName());
        desc.setText(Html.fromHtml(model.getDesc()));
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
        price.setText("IDR " + format.format(Double.valueOf(model.getPrice())).substring(1));

        Picasso.with(context).load(model.getImg()).into(new Target() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                foto.setBackground(new BitmapDrawable(context.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
//                Toast.makeText(context, "Failed, "+errorDrawable.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Hubungi PIC")
                        .setMessage("Barang " + model.getName() + ", oleh " + "Amal Usaha Muhammadiyah" + "\n\n Kontak PIC: " + "082240219493")
                        .setPositiveButton("Hubungi", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + "082240219493"));
//                                Toast.makeText(mContext, "asfsf", Toast.LENGTH_SHORT).show();
                                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                context.startActivity(intent);
                            }
                        })
                        .show();
                return false;
            }
        });

        return v;
    }
}
