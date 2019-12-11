package com.example.flycheap;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.FlightOffer;
import com.amadeus.resources.Location;
import com.amadeus.shopping.FlightOffers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String CLIENT_ID = "lRHCrMLUTG0HYNVRlcrXLhv2mWAXvo5q";
    private static final String SECRET_ID = "6ByzdfPxfDRlELZ3";

    ArrayList<String> airports = new ArrayList<>();


    AutoCompleteTextView origenes = null;
    AutoCompleteTextView destinos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    protected void onStart() {
        super.onStart();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, processAirports());

        origenes = (AutoCompleteTextView) this.findViewById(R.id.campoOrigen);
        origenes.setAdapter(arrayAdapter);
        origenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                origenes.showDropDown();
            }
        });


        destinos = (AutoCompleteTextView) this.findViewById(R.id.campoDestino);
        destinos.setAdapter(arrayAdapter);
        destinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                destinos.showDropDown();
            }
        });

    }

    public ArrayList<String> processAirports() {

        JSONArray auxAirports = null;
        ArrayList<String> muestraAero = new ArrayList<>();

        InputStream ap = getResources().openRawResource(R.raw.airports);
        BufferedReader in = new BufferedReader(new InputStreamReader(ap));
        String inputLine;
        StringBuffer response = new StringBuffer();
        try {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine + "\n");
            }
            in.close();
            String aux = response.toString();
            auxAirports = new JSONArray(aux);

        }catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(auxAirports != null){

            for (int i = 0; i<auxAirports.length(); i++){
                try {
                    JSONObject aux = auxAirports.getJSONObject(i);
                    airports.add(aux.getString("code"));
                    muestraAero.add(aux.getString("code") + ": "+ aux.getString("name"));
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
        return muestraAero;
    }

    public void amadeus_call(View view){



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Amadeus amadeus = Amadeus
                .builder(CLIENT_ID, SECRET_ID)
                .build();

        String or = origenes.getText().toString();
        String des = destinos.getText().toString();

        if(or.length() > 3 && des.length() > 3) {
            or = or.substring(0,3);
            des = des.substring(0,3);
            logMessage(or + "  " + des);
            try {
                FlightOffer[] flightOffers = amadeus.shopping.flightOffers.get(Params.with("origin", or).and("destination", des).and("departureDate", "2019-12-24"));

                procesarRespuesta(flightOffers); //TODO esto puede devolver un string que se va al texteview

                if(flightOffers != null){
                    logMessage(flightOffers[0].getOfferItems()[0].getPrice().toString());
                }
            } catch (ResponseException e) {
                e.printStackTrace();
            }
        }
    }

    private void procesarRespuesta(FlightOffer[] flightOffers) {
        //TODO: rellenar esta funcion

    }

    private void logMessage(String msg) {
        logMessage(msg, Toast.LENGTH_SHORT);
    }

    private void logMessage(String msg, int duration) {
        Toast.makeText(this, msg, duration).show();
        Log.d(TAG, msg);
    }
}
