package com.example.flycheap;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


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

    @RequiresApi(api = Build.VERSION_CODES.N)
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
            try {
                FlightOffer[] flightOffers = amadeus.shopping.flightOffers.get(Params.with("origin", or).and("destination", des).and("departureDate", "2019-12-24"));
                String vuelos = procesarRespuesta(flightOffers); // Esto va directo al textView
                final TextView textView = (TextView) findViewById(R.id.resultados);
                textView.setText(vuelos);
            } catch (ResponseException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    Funcion auxiliar para ordenar HashMap por value
     */
    public static HashMap<String, Double> sortByValue(HashMap<String, Double> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String procesarRespuesta(FlightOffer[] flightOffers) {
        String prices = new String();
        HashMap<String, Double> dadesVols = new HashMap<>();
        for (int i=0; i<flightOffers.length && i < 10; ++i){ // Mostramos maximo 15 vuelos
            double price = flightOffers[i].getOfferItems()[0].getPricePerAdult().getTotal();

            FlightOffer.FlightSegment details = flightOffers[i].getOfferItems()[0].getServices()[0].getSegments()[0].getFlightSegment();
            String horaSortida = details.getDeparture().getAt().substring(11,16);
            String horaArribada = details.getArrival().getAt().substring(11,16);
            String dadaVol = "SORTIDA: " + horaSortida + "  ARRIBADA: " + horaArribada;
            dadesVols.put(dadaVol,price);


        }
        dadesVols = sortByValue(dadesVols);
        for (Map.Entry<String, Double> entry : dadesVols.entrySet()) {
            prices = prices + entry.getKey() + ", PREU=" + entry.getValue() + "€" + "\n\n";
        }
        return prices;
    }

    private void logMessage(String msg) {
        logMessage(msg, Toast.LENGTH_SHORT);
    }

    private void logMessage(String msg, int duration) {
        Toast.makeText(this, msg, duration).show();
        Log.d(TAG, msg);
    }
}
