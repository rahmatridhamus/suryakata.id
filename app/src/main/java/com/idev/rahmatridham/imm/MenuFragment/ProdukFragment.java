package com.idev.rahmatridham.imm.MenuFragment;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.idev.rahmatridham.imm.ListAdapter.ProdukAdapter;
import com.idev.rahmatridham.imm.ProdukDetailActivity;
import com.idev.rahmatridham.imm.R;
import com.idev.rahmatridham.imm.model.ProdukModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProdukFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView listView;
    ProdukAdapter adapter;
    ArrayList<ProdukModel> newsArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ProdukFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_news, container, false);
        newsArrayList = new ArrayList<>();
        listView = (ListView) v.findViewById(R.id.listViewEdu);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);

        adapter = new ProdukAdapter(ProdukFragment.this.getContext(), newsArrayList);
//        getAllNews("getAllFJB.php");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.lightBlue,
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
//        listView.setOnItemLongClickListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        newsArrayList.clear();

                                        getAllNews("product");

                                    }
                                }
        );
//        adapter.notifyDataSetChanged();
        return v;
    }

    @Override
    public void onRefresh() {
        newsArrayList.clear();
        getAllNews("product");

    }


    private void getAllNews(String url) {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getResources().getString(R.string.urlApi) + url + "?apikey=" + getResources().getString(R.string.key),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
//                            newsArrayList = new ArrayList<>();
                            JSONObject result = new JSONObject(response);
                            JSONObject resSet = result.getJSONObject("result");
                            JSONArray jsonArray = resSet.getJSONArray("data");
                            ProdukModel model;

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                model = new ProdukModel(object.optString("id"), object.optString("img"), object.optString("name"), object.optString("desc"), object.optString("price"),"082240219493");
                                newsArrayList.add(model);
                            }

                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(ProdukFragment.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(ProdukFragment.this.getContext(), "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                try {
                    //Adding parameters to request

                    //returning parameter
                    return params;
                } catch (Exception e) {
                    Toast.makeText(ProdukFragment.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    return params;
                }
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProdukModel newsModel = newsArrayList.get(position);
        Intent intent = new Intent(ProdukFragment.this.getContext(), ProdukDetailActivity.class);
        intent.putExtra("urlfoto", newsModel.getImg());
        intent.putExtra("name", newsModel.getName());
        intent.putExtra("desc", newsModel.getDesc());
        intent.putExtra("price", newsModel.getPrice());
        intent.putExtra("contact", newsModel.getContact());

        startActivity(intent);
//        Toast.makeText(view.getContext(), newsModel.getDesc(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id) {
        final ProdukModel model = newsArrayList.get(position);

        new AlertDialog.Builder(view.getContext())
                .setTitle("Hubungi PIC")
                .setMessage("Barang " + model.getName() + ", oleh " + "Amal Usaha Muhammadiyah" + "\n\n Kontak PIC: " + "082240219493")
                .setPositiveButton("Hubungi", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + "082240219493"));
//                                Toast.makeText(mContext, "asfsf", Toast.LENGTH_SHORT).show();
                        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        view.getContext().startActivity(intent);
                    }
                })
                .show();
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Opsi");
        menu.add(0, v.getId(), 0, "Hubungi Pelanggan");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Hubungi Pelanggan") {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + "082240219493"));
            startActivity(intent);
        } else {
            return false;
        }
        return true;
    }
}
