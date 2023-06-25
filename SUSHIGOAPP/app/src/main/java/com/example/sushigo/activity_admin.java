package com.example.sushigo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class activity_admin extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String URLSELECT = "http://192.168.1.9/database/select_data.php";
    public static final String URLDELETE = "http://192.168.1.9/database/delete.php";
    public static final String URLINSERT = "http://192.168.1.9/database/insert.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<Data> itemList = new ArrayList<Data>();
    PelangganAdapter adapter;
    LayoutInflater inflater;
    EditText tnama,ttelepon, talamat;
    String vnama, vtelepon, valamat;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list);

        adapter = new PelangganAdapter(activity_admin.this, itemList);
        list.setAdapter(adapter);

        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );
    }

    public void dialogForm(String nama, String telepon, String alamat){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(activity_admin.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_pelanggan, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Form Mahasiswa");

        tnama = (EditText) viewDialog.findViewById(R.id.inNama);
        ttelepon = (EditText) viewDialog.findViewById(R.id.inTelepon);
        talamat = (EditText) viewDialog.findViewById(R.id.inAlamat);

        if (nama.isEmpty()){
            tnama.setText(null);
            ttelepon.setText(null);
            talamat.setText(null);
        }else{
            tnama.setText(nama);
            ttelepon.setText(telepon);
            talamat.setText(alamat);
        }

        dialogForm.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tnama.setText(null);
                ttelepon.setText(null);
                talamat.setText(null);
            }
        });
        dialogForm.show();

    }
    public void simpan(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLINSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(activity_admin.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_admin.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                if (vnama.isEmpty()) {
                    params.put("nama", vnama);
                    params.put("telepon", vtelepon);
                    params.put("alamat", valamat);
                    return params;
                }else{
                    params.put("nama", vnama);
                    params.put("telepon", vtelepon);
                    params.put("alamat", valamat);
                    return params;
                }
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }
    public void hapusData(String id){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLDELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(activity_admin.this, response, Toast.LENGTH_LONG).show();
                        callVolley();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_admin.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("id", id);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();

    }
    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(URLSELECT, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Data item = new Data();

                        item.setNama(obj.getString("nama"));
                        item.setTelepon(obj.getString("telepon"));
                        item.setAlamat(obj.getString("alamat"));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }
}