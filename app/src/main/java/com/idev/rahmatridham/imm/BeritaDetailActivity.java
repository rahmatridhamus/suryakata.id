package com.idev.rahmatridham.imm;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.idev.rahmatridham.imm.model.NewsModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class BeritaDetailActivity extends AppCompatActivity {
    ImageView foto, shareBut;
    TextView judul, desc, source, writer;
    NewsModel model;
    String urlToShare = "http://suryakata.id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_detail);

        foto = (ImageView) findViewById(R.id.imageViewDetFoto);
//        shareBut = (ImageView) findViewById(R.id.iconToShare);
//        shareBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shareIt();
//            }
//        });

        judul = (TextView) findViewById(R.id.textViewContactUs);
        desc = (TextView) findViewById(R.id.textViewDetDesc);
        source = (TextView) findViewById(R.id.textViewDetSW);
        writer = (TextView) findViewById(R.id.textViewDetSWriter);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.yellow));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.grey));

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            model = new NewsModel((String) b.get("id"), (String) b.get("title"), (String) b.get("desc"), (String) b.get("source"), (String) b.get("author"), (String) b.get("img"), (String) b.get("date"));
            collapsingToolbar.setTitle(model.getTitle());
            desc.setText(Html.fromHtml(model.getDesc()));
            source.setText("Source: " + model.getSource());
            writer.setText("Written by " + model.getAuthor());
            Picasso.with(this.getApplicationContext()).load(model.getImg()).into(foto);
        }
        shareIt();
    }

    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    private void shareIt() {
//sharing implementation here

        ImageView twitter, facebook, whatsapp, line, instagram, copy;
        twitter = (ImageView) findViewById(R.id.twitter);
        facebook = (ImageView) findViewById(R.id.facebook);
        whatsapp = (ImageView) findViewById(R.id.whatsapp);
        line = (ImageView) findViewById(R.id.line);
        instagram = (ImageView) findViewById(R.id.instagram);
        copy = (ImageView) findViewById(R.id.copies);

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BeritaDetailActivity.this, "preparing...", Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(model.getImg()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.setPackage("com.twitter.android");
                        intent.putExtra(Intent.EXTRA_TEXT, urlToShare + "\n" + model.getTitle());

                        if (getLocalBitmapUri(bitmap) != null) {
                            intent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            intent.setType("image/*");
                        }

                        try {
                            startActivity(intent);
                        } catch (android.content.ActivityNotFoundException ex) {
                            ex.printStackTrace();
                            Toast.makeText(BeritaDetailActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Toast.makeText(BeritaDetailActivity.this, "preparing...", Toast.LENGTH_SHORT).show();
                Picasso.with(getApplicationContext()).load(model.getImg()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
// intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
                        intent.putExtra(Intent.EXTRA_TITLE, model.getTitle());
                        intent.putExtra(Intent.EXTRA_TEXT, urlToShare + "\n" + model.getTitle());

// See if official Facebook app is found
                        boolean facebookAppFound = false;
                        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
                        for (ResolveInfo info : matches) {
                            if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                                intent.setPackage(info.activityInfo.packageName);
                                facebookAppFound = true;
                                break;
                            }
                        }

// As fallback, launch sharer.php in a browser
                        if (!facebookAppFound) {
                            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                        }

                        startActivity(intent);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });


            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm = getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "YOUR TEXT HERE";

                    PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    //Check if package exists or not. If not then code
                    //in catch block will be called
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, urlToShare + "\n" + model.getTitle());
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(v.getContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLineInstalled()) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setClassName(PACKAGE_NAME, CLASS_NAME);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, model.getTitle() + "\n" + urlToShare);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(v.getContext(), "LINE not Installed", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Picasso.with(getApplicationContext()).load(model.getImg()).into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("image/*");
                            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                            shareIntent.putExtra(Intent.EXTRA_TITLE, model.getTitle());
                            shareIntent.setPackage("com.instagram.android");
                            startActivity(shareIntent);

                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });


                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Instagram not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", model.getTitle()+"\n"+urlToShare);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(BeritaDetailActivity.this, "text copied", Toast.LENGTH_SHORT).show();
            }
        });


    }

    static final int REQUEST_ACTION_PICK = 1;
    public static final String PACKAGE_NAME = "jp.naver.line.android";
    public static final String CLASS_NAME = "jp.naver.line.android.activity.selectchat.SelectChatActivity";
    private List<ApplicationInfo> m_appList;

    private boolean checkLineInstalled() {
        PackageManager pm = getPackageManager();
        m_appList = pm.getInstalledApplications(0);
        boolean lineInstallFlag = false;
        for (ApplicationInfo ai : m_appList) {
            if (ai.packageName.equals(PACKAGE_NAME)) {
                lineInstallFlag = true;
                break;
            }
        }
        return lineInstallFlag;
    }
}
