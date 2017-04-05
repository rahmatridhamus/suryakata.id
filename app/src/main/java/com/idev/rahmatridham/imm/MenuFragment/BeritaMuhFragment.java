package com.idev.rahmatridham.imm.MenuFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
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
import com.idev.rahmatridham.himaifofficialapps.R;
import com.idev.rahmatridham.imm.BeritaDetailActivity;
import com.idev.rahmatridham.imm.ListAdapter.BeritaMuhammadiyahAdapter;
import com.idev.rahmatridham.imm.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeritaMuhFragment extends Fragment implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemLongClickListener {
    ListView listView;
    BeritaMuhammadiyahAdapter adapter;
    ArrayList<NewsModel> newsArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public BeritaMuhFragment() {
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
        swipeRefreshLayout.setColorSchemeResources(
                R.color.lightBlue,
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );

        adapter = new BeritaMuhammadiyahAdapter(BeritaMuhFragment.this.getContext(), newsArrayList);

//        getAllNews("getAllNews.php");

        swipeRefreshLayout.setOnRefreshListener(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        newsArrayList.clear();
                                        getAllNews("news");

                                    }
                                }
        );
        return v;
    }

    void addRecentNews() {
        newsArrayList.add(new NewsModel("123", "Kader Muhammadiyah Kabupaten Bandung berjurnalis", "KABUPATEN BANDUNG- Tiga Sekolah swasta Muhammadiyah dari beberapa sekolah Muhammadiyah di Kabupaten Bandung, yakni SMA 2 Muhammadiyah Majalaya, SMK Muhammadiya Majalaya dan SMA Muhammadiyah 5 Rancaekek. Mengikuti pelatihan jurnalistik yang dihadiri sebagian siswa setiap sekolahnya masing-masing yang diadakan oleh IMM (ikatan mahasiswa muhammadiyah) Kabupaten Bandung. Di gedung SMA 2 Muhammadiyah Majalaya,Minggu (5/3/2017) dengan pemateri masih dari keluarga besar muhammadiyah itu sendiri ( PWM) jawa barat, \"kelik serta Firman\".\n" +
                "\n" +
                "Pelatihan ini sebagai upaya meningkatkan kualitas kader muhammadiyah kabupaten bandung, terutama dalam bidang IPTEK (ilmu teknologi informasi), agar bisa lebih mengerti dan memahami  akan pentingnya suatu informasi dan kegiatan ini memiliki visi akan terciptanya kader-kader muhammadiyah dalam bidang jurnalis. Pelatihan ini merupakan program rutin yang di adakan satu kali dalam seminggu. \n" +
                "\n" +
                "Pelatihan ini merupakan yang kedua kalinya, yang sebelumnya diadakan di gedung SMA Muhammadiyah 5 Rancaekek. Tetapi dalam kegiatan ini SMK Muhammadiyah Majalaya tidak bisa ikut serta, di jalankan ada kegiatan mendadak di sekolahnya.", "IPM Majalaya dan Rancaekek", "Fitri", "https://s11.postimg.org/k3rp97dyr/resmi.jpg", "2016-11-25 08:45:17"));

    }

    private void getAllNews(String url) {

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getResources().getString(R.string.urlApi) + url + "?apikey=" + getResources().getString(R.string.key),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject result = new JSONObject(response);
                            JSONObject objRes = result.getJSONObject("result");
                            JSONArray jsonArray = objRes.getJSONArray("data");
                            NewsModel newsModel;
                            addRecentNews();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                newsModel = new NewsModel(object.optString("id"), object.optString("title"), object.optString("desc"), object.optString("source"), object.optString("author"), object.optString("img"), object.optString("created_at"));
                                newsArrayList.add(newsModel);
                            }

                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);


                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(BeritaMuhFragment.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(BeritaMuhFragment.this.getContext(), "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);

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
                    Toast.makeText(BeritaMuhFragment.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
        NewsModel newsModel = newsArrayList.get(position);
        Intent intent = new Intent(BeritaMuhFragment.this.getContext(), BeritaDetailActivity.class);

        intent.putExtra("urlfoto", newsModel.getImg());
        intent.putExtra("title", newsModel.getTitle());
        intent.putExtra("desc", newsModel.getDesc());
        intent.putExtra("source", newsModel.getSource());
        intent.putExtra("writer", newsModel.getAuthor());

        startActivity(intent);
    }

    @Override
    public void onRefresh() {
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.detach(this).attach(this).commit();
        newsArrayList.clear();
        getAllNews("news");

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
