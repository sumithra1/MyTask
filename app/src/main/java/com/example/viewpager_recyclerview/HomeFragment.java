    package com.example.viewpager_recyclerview;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.android.volley.toolbox.JsonObjectRequest;
    import com.android.volley.toolbox.StringRequest;
    import com.android.volley.toolbox.Volley;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;
    import java.util.List;


    public class HomeFragment extends Fragment {
        View v;
        private Context context;
        private RecyclerView myrecylerview;
        private List<HomeValues> homeValue;
        private static  String server_url ="https://api.github.com/repositories";
        private RecyclerviewAdapter adapter;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            v= inflater.inflate(R.layout.fragment_home, container, false);
            homeValue = new ArrayList<>();
            adapter = new RecyclerviewAdapter(homeValue,getActivity());

            myrecylerview=(RecyclerView)v.findViewById(R.id.home_recycler);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            myrecylerview.setLayoutManager(llm);
            getData();

    //        String fname= getActivity().getIntent().getStringExtra("name");
    //        String fimg=getActivity().getIntent().getStringExtra("img");
    //        String fedit=getActivity().getIntent().getStringExtra("text");


            return v;
        }

        private void getData() {
            StringRequest stringRequest=new StringRequest(Request.Method.GET, server_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
    //                        JSONObject jsonObject=new JSONObject(response);
                        Log.e("response",response.toString());

                        JSONArray array= new JSONArray(response);

                        for (int i=0; i<array.length(); i++){
                            Log.e("Array check",array.getJSONObject(i).get("name").toString());
                            String name =array.getJSONObject(i).get("name").toString();
                            String id =array.getJSONObject(i).get("id").toString();
                            //String img =array.getJSONObject(i).get("avatar_url").toString();
                            String img=array.getJSONObject(i).getJSONObject("owner").get("avatar_url").toString();
                            Log.e("avatar_url",img);

                            HomeValues ld=new HomeValues(id,name,img);
                            homeValue.add(ld);
                            if(i == 10)
                                break;
                        }
                        myrecylerview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);

        }


    }