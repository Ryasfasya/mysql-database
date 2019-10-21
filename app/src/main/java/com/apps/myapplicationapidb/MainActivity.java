package com.apps.myapplicationapidb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPegawai;
    private PegawaiAdapter adapter;
    private ArrayList<Pegawai> daftarPegawai;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPegawai = findViewById(R.id.rv_pegawai);
        adapter = new PegawaiAdapter(this);
        daftarPegawai = new ArrayList<>();
        gson = new Gson();
        ambilData();

        LinearLayoutManager lm = new LinearLayoutManager(this);
        DividerItemDecoration divider = new DividerItemDecoration(this,lm.getOrientation());

        rvPegawai.setLayoutManager(lm);
        rvPegawai.setAdapter(adapter);
        rvPegawai.addItemDecoration(divider);

    }
    private void ambilData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.82.2/api-mysql/tampilsemuapgw.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PegawaiResult result = gson.fromJson(response,PegawaiResult.class);
                daftarPegawai = result.getResult();
                adapter.setDaftarPegawai(daftarPegawai);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(stringRequest);
    }

    public void aksiTambah(View view) {
        String url = "http://192.168.82.2/api-mysql/tambahpgw.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responapp", "hasil:");
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameter = new HashMap<>();
                parameter.put("name", "Siva");
                parameter.put("position", "Programer");
                parameter.put("salary", "5000");
                return parameter;
            }
        };
        queue.add(stringRequest);
    }
}
