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

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView cases, tcases, death, tdeath, recover;
    TextView casesW, deathW, recoverW;
    RequestQueue requestQueue, requestQueue1;

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
        //Worldwide
        casesW = findViewById(R.id.casesW);
        deathW = findViewById(R.id.deathW);
        recoverW = findViewById(R.id.recoverW);

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




        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://corona.lmao.ninja/countries/india", null, new Response.Listener<JSONObject>() {
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


        requestQueue1 = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, "https://corona.lmao.ninja/all", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String casesWstr =  response.getString("cases");
                    String deathsWStr =  response.getString("deaths");
                    String recoveryWStr =  response.getString("recovered");

                    casesW.setText(casesWstr);
                    deathW.setText(deathsWStr);
                    recoverW.setText(recoveryWStr);
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
        requestQueue.add(jsonObjectRequest1);


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
