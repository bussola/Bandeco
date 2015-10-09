package com.sherolero.bandeco.sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity implements ConfigurationFragmentCallbacks {
    public String json = "";
    public List<Cardapio> cards;
    Bundle bundle = new Bundle();
    static int diaMes;
    static int diaSemana;
    static int mes;
    static int hora;

    //A ProgressDialog object
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getCardapio();
        getDateAtual();

        //Initialize a LoadViewTask object and call the execute() method
        new LoadViewTask().execute();

    }

    public void getCardapio(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://sherolerobandeco.herokuapp.com/cardapio";
        //String url = "http://192.168.0.101:8080/bandeco";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        json = response;
                        Type listOfObjects = new TypeToken<List<Cardapio>>(){}.getType();
                        Gson gson = new GsonBuilder().create();
                        cards = gson.fromJson(json, listOfObjects);
                        bundle.putString("json", json);
                        CardapioListAdapter.recebeCardapioDaMain(MainActivity.this);

                        progressDialog.dismiss();
                        //initialize the View
                        setContentView(R.layout.activity_main);
                        final TabsLayout tabs = findView(R.id.tabs);
                        //final SlidingTabLayout tabs = findView(R.id.tabs);
                        final ViewPager viewPager = findView(R.id.view_pager);

                        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getResources(), getFragments());
                        viewPager.setAdapter(adapter);
                        viewPager.setCurrentItem(diaSemana - 2);
                        if(diaSemana == 1)
                            viewPager.setCurrentItem(5);
                        tabs.setViewPager(viewPager);
                        tabs.setItemSelected(diaSemana - 2);

                        if(diaSemana == 1)
                            tabs.setItemSelected(5);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CardapioListAdapter.recebeErro();

                progressDialog.dismiss();
                //initialize the View
                setContentView(R.layout.activity_main);
                final TabsLayout tabs = findView(R.id.tabs);
                //final SlidingTabLayout tabs = findView(R.id.tabs);
                final ViewPager viewPager = findView(R.id.view_pager);

                final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getResources(), getFragments());
                viewPager.setAdapter(adapter);
                viewPager.setCurrentItem(diaSemana - 2);
                if(diaSemana == 1)
                    viewPager.setCurrentItem(5);
                tabs.setViewPager(viewPager);
                tabs.setItemSelected(diaSemana-2);

                if(diaSemana == 1)
                    tabs.setItemSelected(5);
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //outState.putInt(ARG_LAST_SCROLL_Y, mScrollableLayout.getScrollY());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saldo_settings:
                Intent myIntent = new Intent(MainActivity.this, SaldoActivity.class);
                MainActivity.this.startActivity(myIntent);
                return true;
            case R.id.action_settings:
                Intent myIntent1 = new Intent(MainActivity.this, SobreActivity.class);
                MainActivity.this.startActivity(myIntent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<BaseFragment> getFragments() {

        final FragmentManager manager = getSupportFragmentManager();
        final List<BaseFragment> list = new ArrayList<>();


        CallSegunda segundaFragment = (CallSegunda) manager.findFragmentByTag(CallSegunda.TAG);
        if (segundaFragment == null) {
            segundaFragment = CallSegunda.newInstance();
        }

        CallTerca tercaFragment = (CallTerca) manager.findFragmentByTag(CallTerca.TAG);
        if (tercaFragment == null) {
            tercaFragment = CallTerca.newInstance();
        }

        CallQuarta quartaFragment = (CallQuarta) manager.findFragmentByTag(CallQuarta.TAG);
        if (quartaFragment == null) {
            quartaFragment = CallQuarta.newInstance();
        }

        CallQuinta quintaFragment = (CallQuinta) manager.findFragmentByTag(CallQuinta.TAG);
        if (quintaFragment == null) {
            quintaFragment = CallQuinta.newInstance();
        }

        CallSexta sextaFragment = (CallSexta) manager.findFragmentByTag(CallSexta.TAG);
        if (sextaFragment == null) {
            sextaFragment = CallSexta.newInstance();
        }

        CallSabado sabadoFragment = (CallSabado) manager.findFragmentByTag(CallSabado.TAG);
        if (sabadoFragment == null) {
            sabadoFragment = CallSabado.newInstance();
        }

        Collections.addAll(list, segundaFragment, tercaFragment, quartaFragment, quintaFragment, sextaFragment,sabadoFragment);

        return list;
    }

    @Override
    public void onFrictionChanged(float friction) {

    }

    @Override
    public void openDialog(float friction) {
//        final ScrollableDialog dialog = ScrollableDialog.newInstance(friction);
//        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void openActivity(Intent intent) {
        startActivity(intent);
    }

    public void getDateAtual(){
        Calendar c = Calendar.getInstance();
        diaMes = c.get(Calendar.DAY_OF_MONTH); // 1 para primeiro do mes
        diaSemana = c.get(Calendar.DAY_OF_WEEK); // 1 para domingo
        mes = c.get(Calendar.MONTH); // 0 para janeiro
        hora = c.get(Calendar.HOUR_OF_DAY);
    }

    static String getOtherDates(Calendar c, int index){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        c.add(Calendar.DATE, index);
        return dateFormat.format(c.getTime());
    }

    //To use the AsyncTask, it must be subclassed
    private class LoadViewTask extends AsyncTask<Void, Integer, Void>
    {
        //Before running code in separate thread
        @Override
        protected void onPreExecute()
        {
            //Create a new progress dialog
            progressDialog = new ProgressDialog(MainActivity.this);
            //Set the progress dialog to display a horizontal progress bar
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //Set the dialog title to 'Loading...'
            progressDialog.setTitle("Loading...");
            //Set the dialog message to 'Loading application View, please wait...'
            progressDialog.setMessage("Loading application View, please wait...");
            //This dialog can't be canceled by pressing the back key
            progressDialog.setCancelable(false);
            //This dialog isn't indeterminate
            progressDialog.setIndeterminate(false);
            //The maximum number of items is 100
            progressDialog.setMax(100);
            //Set the current progress to zero
            progressDialog.setProgress(0);
            //Display the progress dialog
            progressDialog.show();
        }

        //The code to be executed in a background thread.
        @Override
        protected Void doInBackground(Void... params)
        {
            /* This is just a code that delays the thread execution 4 times,
             * during 850 milliseconds and updates the current progress. This
             * is where the code that is going to be executed on a background
             * thread must be placed.
             */
            try
            {
                //Get the current thread's token
                synchronized (this)
                {
                    //Initialize an integer (that will act as a counter) to zero
                    int counter = 0;
                    //While the counter is smaller than four
                    while(counter <= 4)
                    {
                        //Wait 1350 milliseconds
                        this.wait(1450);
                        //Increment the counter
                        counter++;
                        //Set the current progress.
                        //This value is going to be passed to the onProgressUpdate() method.
                        publishProgress(counter*25);
                    }
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        //Update the progress
        @Override
        protected void onProgressUpdate(Integer... values)
        {
            //set the current progress of the progress dialog
            progressDialog.setProgress(values[0]);
        }

        //after executing the code in the thread
        @Override
        protected void onPostExecute(Void result)
        {
            //close the progress dialog
            progressDialog.dismiss();
        }
    }

}
