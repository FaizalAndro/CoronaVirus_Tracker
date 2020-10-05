/*
 * Copyright 2020 FAIZAL.DEV. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.faizal.coronavirustracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView cases, tcases, death, tdeath, recover;
    TextView casesW, TcasesW, deathW, TdeathW, recoverW, affectedCountries, critical, active;
    RequestQueue requestQueue;

    PieChart pieChart;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //India
        cases = findViewById(R.id.cases);
        tcases = findViewById(R.id.tcases);
        death = findViewById(R.id.death);
        tdeath = findViewById(R.id.tdeath);
        recover = findViewById(R.id.recover);
        pieChart = findViewById(R.id.piechart);
        //Worldwide
        casesW = findViewById(R.id.tvCases);
        recoverW = findViewById(R.id.tvRecovery);
        critical = findViewById(R.id.tvCritical);
        active = findViewById(R.id.tvActive);
        deathW = findViewById(R.id.tvDeath);
        TcasesW = findViewById(R.id.tvTodayCases);
        TdeathW = findViewById(R.id.tvTodayDeaths);
        affectedCountries = findViewById(R.id.tvCountries);

        swipeRefreshLayout = findViewById(R.id.swipe);


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( MainActivity.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken",newToken);

            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                doYourUpdate();
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://corona.lmao.ninja/v2/countries/india", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String casesComes =  response.getString("cases");
                    String totalCases =  response.getString("todayCases");
                    String deaths =  response.getString("deaths");
                    String totalDeath =  response.getString("todayDeaths");
                    String recovery =  response.getString("recovered");

                    cases.setText(casesComes);
                    tcases.setText(totalCases);
                    death.setText(deaths);
                    tdeath.setText(totalDeath);
                    recover.setText(recovery);

                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "Something went wrong");
            }
        });
        requestQueue.add(jsonObjectRequest);


        //requestQueue1 = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://corona.lmao.ninja/v2/all", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    casesW.setText(jsonObject.getString("cases"));
                    deathW.setText(jsonObject.getString("deaths"));
                    recoverW.setText(jsonObject.getString("recovered"));
                    critical.setText(jsonObject.getString("critical"));
                    active.setText(jsonObject.getString("active"));
                    TcasesW.setText(jsonObject.getString("todayCases"));
                    TdeathW.setText(jsonObject.getString("todayDeaths"));
                    affectedCountries.setText(jsonObject.getString("affectedCountries"));

                        pieChart.addPieSlice(
                                new PieModel(
                                        "Cases",
                                        Integer.parseInt(casesW.getText().toString()),
                                        Color.parseColor("#FFA726")));
                        pieChart.addPieSlice(
                                new PieModel(
                                        "Recovered",
                                        Integer.parseInt(recoverW.getText().toString()),
                                        Color.parseColor("#66BB6A")));
                        pieChart.addPieSlice(
                            new PieModel(
                                    "Death",
                                    Integer.parseInt(deathW.getText().toString()),
                                    Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(
                            new PieModel(
                                    "Active",
                                    Integer.parseInt(deathW.getText().toString()),
                                    Color.parseColor("#29B6F6")));
                        pieChart.startAnimation();




                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void doYourUpdate() {
        // TODO implement a refresh
        swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon
    }
    public void allCountries(View view) {
        startActivity(new Intent(getApplicationContext(), AllCountries.class));
    }

    public void states(View view) {
        startActivity(new Intent(getApplicationContext(), StatesActivity.class));

    }

    public void corona(View view) {
        startActivity(new Intent(getApplicationContext(), Covid.class));


    }

    public void symptom(View view) {
        startActivity(new Intent(getApplicationContext(), Symptoms.class));

    }

    public void mythBusters(View view) {
        startActivity(new Intent(getApplicationContext(), MythsBust.class));

    }

    public void updates(View view) {
        startActivity(new Intent(getApplicationContext(), Updates.class));

    }

    public void helpline(View view) {
        startActivity(new Intent(getApplicationContext(), Helpline.class));

    }

    public void about(View view) {
        startActivity(new Intent(getApplicationContext(), About.class));

    }
}
