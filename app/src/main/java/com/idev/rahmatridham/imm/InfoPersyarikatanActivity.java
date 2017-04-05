package com.idev.rahmatridham.imm;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.idev.rahmatridham.himaifofficialapps.R;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoPersyarikatanActivity extends Fragment implements View.OnClickListener {

    public static ImageView aisyiahLog, dikdasmenLog, ipmLog, immLog, pemudaLog, nasyiatulLog, hizbulwathanLog, tapakSuciLog, muhammadiyahLog;
    TextView judul;
    static int[] daftarImage;
    FloatingActionButton fabs;

    public InfoPersyarikatanActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_portofolio, container, false);

        fabs = (FloatingActionButton) v.findViewById(R.id.about);
        fabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DescriptionActivity.class));
            }
        });


        muhammadiyahLog = (ImageView) v.findViewById(R.id.imageViewMuhammadiyah);
        aisyiahLog = (ImageView) v.findViewById(R.id.imageViewAisyiah);
        dikdasmenLog = (ImageView) v.findViewById(R.id.imageViewDikdasmen);
        ipmLog = (ImageView) v.findViewById(R.id.imageViewIPM);
        immLog = (ImageView) v.findViewById(R.id.imageViewIMM);
        pemudaLog = (ImageView) v.findViewById(R.id.imageViewPemuda);
        nasyiatulLog = (ImageView) v.findViewById(R.id.imageViewNasyiatulAisyiah);
        tapakSuciLog = (ImageView) v.findViewById(R.id.imageViewTapakSuci);
        hizbulwathanLog = (ImageView) v.findViewById(R.id.imageViewHizbulWathan);

        judul = (TextView) v.findViewById(R.id.textViewjudul);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/remachinescript.ttf");
        judul.setTypeface(tf);

        aisyiahLog.setOnClickListener(this);
        dikdasmenLog.setOnClickListener(this);
        ipmLog.setOnClickListener(this);
        immLog.setOnClickListener(this);
        pemudaLog.setOnClickListener(this);
        nasyiatulLog.setOnClickListener(this);
        tapakSuciLog.setOnClickListener(this);
        hizbulwathanLog.setOnClickListener(this);

        daftarImage = new int[9];
        daftarImage[0] = R.mipmap.muhammadiyah;
        daftarImage[1] = R.mipmap.aisyiah;
        daftarImage[2] = R.mipmap.dikdasmen;
        daftarImage[3] = R.mipmap.ipm;
        daftarImage[4] = R.mipmap.immnoborder;
        daftarImage[5] = R.mipmap.pemuda;
        daftarImage[6] = R.mipmap.nasyiatul;
        daftarImage[7] = R.mipmap.tapaksuci;
        daftarImage[8] = R.mipmap.hizbulwathan;

        Picasso.with(InfoPersyarikatanActivity.this.getContext()).load(daftarImage[0]).into(muhammadiyahLog);
        Picasso.with(InfoPersyarikatanActivity.this.getContext()).load(daftarImage[1]).into(aisyiahLog);
        Picasso.with(InfoPersyarikatanActivity.this.getContext()).load(daftarImage[2]).into(dikdasmenLog);
        Picasso.with(InfoPersyarikatanActivity.this.getContext()).load(daftarImage[3]).into(ipmLog);
        Picasso.with(InfoPersyarikatanActivity.this.getContext()).load(daftarImage[4]).into(immLog);
        Picasso.with(InfoPersyarikatanActivity.this.getContext()).load(daftarImage[5]).into(pemudaLog);
        Picasso.with(InfoPersyarikatanActivity.this.getContext()).load(daftarImage[6]).into(nasyiatulLog);
        Picasso.with(InfoPersyarikatanActivity.this.getContext()).load(daftarImage[7]).into(tapakSuciLog);
        Picasso.with(InfoPersyarikatanActivity.this.getContext()).load(daftarImage[8]).into(hizbulwathanLog);

        return v;
    }


    @Override
    public void onClick(View v) {
        int num = v.getId();
        Uri uri;
        Intent intent;
        switch (num) {
            case R.id.imageViewAisyiah:
                uri = Uri.parse("http://line.me/ti/p/%40xqa3540r"); // missing 'http://' will cause crashed
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.imageViewDikdasmen:
                uri = Uri.parse("https://www.instagram.com/himaiftelkom/"); // missing 'http://' will cause crashed
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.imageViewIPM:
                uri = Uri.parse("http://line.me/ti/p/%40fns2717v"); // missing 'http://' will cause crashed
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.imageViewIMM:
                uri = Uri.parse("http://line.me/ti/p/%40tnv4574a"); // missing 'http://' will cause crashed
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.imageViewPemuda:
                uri = Uri.parse("http://line.me/ti/p/~@lkk1194u"); // missing 'http://' will cause crashed
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.imageViewNasyiatulAisyiah:
                uri = Uri.parse("http://line.me/ti/p/~@jix3428m"); // missing 'http://' will cause crashed
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.imageViewTapakSuci:
                uri = Uri.parse("http://line.me/ti/p/%40uzo0610j"); // missing 'http://' will cause crashed
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.imageViewHizbulWathan:
                uri = Uri.parse("https://line.me/R/ti/p/%40nin0004x"); // missing 'http://' will cause crashed
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }
}
