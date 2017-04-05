package com.idev.rahmatridham.imm;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.idev.rahmatridham.himaifofficialapps.R;
import com.squareup.picasso.Picasso;

public class BeritaDetailActivity extends AppCompatActivity {
    ImageView foto;
    TextView judul,desc,source, writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        foto = (ImageView) findViewById(R.id.imageViewDetFoto);
        judul = (TextView) findViewById(R.id.textViewContactUs);
        desc = (TextView) findViewById(R.id.textViewDetDesc);
        source = (TextView) findViewById(R.id.textViewDetSW);
        writer = (TextView) findViewById(R.id.textViewDetSWriter);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.yellow));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.grey));

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            collapsingToolbar.setTitle((String) b.get("title"));
            desc.setText(Html.fromHtml((String) b.get("desc")));
            source.setText("Source: "+(String) b.get("source"));
            writer.setText("Written by "+(String) b.get("writer"));
            Picasso.with(this.getApplicationContext()).load((String) b.get("urlfoto")).into(foto);
        }
    }
}
