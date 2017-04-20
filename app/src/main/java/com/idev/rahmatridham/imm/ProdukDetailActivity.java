package com.idev.rahmatridham.imm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class ProdukDetailActivity extends AppCompatActivity {
    ImageView foto, callBut;
    TextView judul, desc, source, writer;
    String contact;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_detail);

        foto = (ImageView) findViewById(R.id.imageViewDetFoto);
        callBut = (ImageView) findViewById(R.id.iconToCall);
        callBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + contact));
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
        judul = (TextView) findViewById(R.id.textViewContactUs);
        desc = (TextView) findViewById(R.id.textViewDetDesc);
        source = (TextView) findViewById(R.id.textViewDetSW);
        writer = (TextView) findViewById(R.id.textViewDetSWriter);
        appBarLayout = (AppBarLayout) findViewById(R.id.MyAppbar);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.yellow));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.grey));

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            collapsingToolbar.setTitle((String) b.get("name"));
            desc.setText(Html.fromHtml((String) b.get("desc")));
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
//            source.setText("IDR " + format.format(Double.valueOf((String) b.get("price"))).substring(1));
            source.setText("Price: IDR. " + format.format(Double.valueOf((String) b.get("price"))).substring(1));
            contact = (String) b.get("contact");
            Picasso.with(this.getApplicationContext()).load((String) b.get("urlfoto")).into(foto);
        }
    }


}
