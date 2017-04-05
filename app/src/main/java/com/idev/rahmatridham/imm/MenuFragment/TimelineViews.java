package com.idev.rahmatridham.imm.MenuFragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.idev.rahmatridham.imm.ListAdapter.AgendaAcaraAdapter;
import com.idev.rahmatridham.himaifofficialapps.R;
import com.idev.rahmatridham.imm.Orientation;
import com.idev.rahmatridham.imm.model.TimelineModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineViews extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private AgendaAcaraAdapter mTimelineAdapter;
    private List<TimelineModel> timelineModelArrayList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Orientation mOrientation;


    public TimelineViews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_timeline_views, container, false);

        timelineModelArrayList = new ArrayList<>();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.lightBlue,
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );


        TextView judul = (TextView) v.findViewById(R.id.textTimeline);

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/remachinescript.ttf");
        judul.setTypeface(tf);

        mOrientation = Orientation.vertical;
        mTimelineAdapter = new AgendaAcaraAdapter(timelineModelArrayList, mOrientation,TimelineViews.this.getContext());

        mRecyclerView.setAdapter(mTimelineAdapter);
        getAllTimeline("timeline");


        return v;
    }

    private LinearLayoutManager getLinearLayoutManager() {

        if (mOrientation == Orientation.horizontal) {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TimelineViews.this.getContext(), LinearLayoutManager.HORIZONTAL, false);
            return linearLayoutManager;
        } else {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TimelineViews.this.getContext());
            return linearLayoutManager;
        }

    }

    private void getAllTimeline(String url) {
//        final ProgressDialog loading = ProgressDialog.show(this.getContext(), "Fetching Data", "Please wait...", false, false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getResources().getString(R.string.urlApi) + url+"?apikey="+getResources().getString(R.string.key),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject result = new JSONObject(response);
                            JSONObject resSet = result.getJSONObject("result");
                            JSONObject upcoming = resSet.getJSONObject("upcoming");
                            TimelineModel timelineModel;
                            JSONArray today = resSet.getJSONArray("today");
                            for (int i = 0; i < today.length(); i++) {
                                JSONObject object = today.getJSONObject(i);
                                timelineModel = new TimelineModel(object.optString("id"),object.optString("title"), object.optString("desc"), object.optString("division"), object.optString("date"), "Today");
                                timelineModelArrayList.add(timelineModel);
                            }
                            JSONArray jsonArray = upcoming.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                timelineModel = new TimelineModel(object.optString("id"),object.optString("title"), object.optString("desc"), object.optString("division"), object.optString("date") ,object.optString("status"));
                                timelineModelArrayList.add(timelineModel);
                            }
//                            Collections.reverse(timelineModelArrayList);
                            mTimelineAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(TimelineViews.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        error.printStackTrace();
                        Toast.makeText(TimelineViews.this.getContext(), "erroring: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                        loading.dismiss();

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
                    Toast.makeText(TimelineViews.this.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    return params;
                }
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onRefresh() {
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.detach(this).attach(this).commit();
        timelineModelArrayList.clear();
        getAllTimeline("timeline");

    }
}
