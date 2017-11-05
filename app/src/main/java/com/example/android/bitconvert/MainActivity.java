package com.example.android.bitconvert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    // Will show the strings for "dollar value", "naira value" and "pound value" that holds the results
    TextView NairaExchange;
    TextView DollarExchange;
    TextView PoundsExchange;
    // URL of object to be parsed
    String JsonURL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC&tsyms=USD,GBP,NGN";
    // This will hold the results
    double naira_data = 0.0;
    double dollar_data = 0.0;
    double pounds_data = 0.0;

    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing cardview to be clickable and to open the convert activity
        CardView cardView = (CardView) findViewById(R.id.crypto_cardview);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConvertActivity.class);
                intent.putExtra("Naira_Value", naira_data);
                intent.putExtra("Dollar_Value", dollar_data);
                intent.putExtra("Pounds_Value", pounds_data);

                startActivity(intent);
            }
        });

            // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        // Casts results into the TextViews found within the main layout XML with corresponding id
        NairaExchange = (TextView) findViewById(R.id.naira_exchange_rate);
        DollarExchange = (TextView) findViewById(R.id.dollar_exchange_rate);
        PoundsExchange = (TextView) findViewById(R.id.pounds_exchange_rate);

        // Creating the JsonObjectRequest class called obreq, passing required parameters:
        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("BTC");
                            // Retrieves the strings labeled "NGN", "GBP" and "USD" from
                            //the response JSON Object
                            //and converts them into javascript objects
                             naira_data = obj.getDouble("NGN");
                             dollar_data = obj.getDouble("USD");
                             pounds_data = obj.getDouble("GBP");

                            //Change data collected to string before showing on the
                            //TextViews
                            String naira_value = naira_data + " NGN";
                            String dollar_value = dollar_data + " USD";
                            String pounds_value = pounds_data + " GBP";


                            // Adds the value strings to the TextViews
                            NairaExchange.setText(naira_value);
                            DollarExchange.setText(dollar_value);
                            PoundsExchange.setText(pounds_value);
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                        //Prints a toast message about the error to the user
                        Toast.makeText(MainActivity.this, "Unable to fetch data." +
                                " Please check your internet connection", Toast.LENGTH_LONG).show();
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(obreq);
    }
}
