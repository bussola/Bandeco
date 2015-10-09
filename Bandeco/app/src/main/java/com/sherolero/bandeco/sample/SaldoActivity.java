package com.sherolero.bandeco.sample;

/**
 * Created by Vinicius on 24/09/2015.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;


public class SaldoActivity extends Activity implements View.OnClickListener {

    private String json = "";
    private String saldoTotal = "";
    private String saldo = "";

    private EditText nUSP;
    private EditText senha;
    private Button botao;

    //A ProgressDialog object
    private ProgressDialog progressDialog;

    String nUSPString = "";
    String senhaString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

        TextView textViewPrimeiro =(TextView)findViewById(R.id.textPrimeiro);
        textViewPrimeiro.setClickable(true);
        textViewPrimeiro.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://uspdigital.usp.br/rucard/primeiroAcesso'> Primeiro Acesso </a>";
        textViewPrimeiro.setText(Html.fromHtml(text));

        TextView textViewEsqueci =(TextView)findViewById(R.id.textEsqueci);
        textViewEsqueci.setClickable(true);
        textViewEsqueci.setMovementMethod(LinkMovementMethod.getInstance());
        String text1 = "<a href='https://uspdigital.usp.br/rucard/esqueciSenha'> Esqueci a senha </a>";
        textViewEsqueci.setText(Html.fromHtml(text1));

        botao = (Button)findViewById(R.id.button_saldo);
        botao.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.saldo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.saldo_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        nUSP = (EditText)findViewById(R.id.editText_nUSP);
        nUSPString = nUSP.getText().toString();
        senha = (EditText)findViewById(R.id.editText_senha);
        senhaString = senha.getText().toString();
        if (v.getId() == R.id.button_saldo) {
            if(nUSPString.equals("")){
                AlertDialog alertDialog = new AlertDialog.Builder(SaldoActivity.this).create();
                alertDialog.setTitle("Número USP em branco");
                alertDialog.setMessage("Preencha o campo Número USP");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            else if(senhaString.equals("")){
                AlertDialog alertDialog = new AlertDialog.Builder(SaldoActivity.this).create();
                alertDialog.setTitle("Senha em branco");
                alertDialog.setMessage("Preencha o campo senha");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            else {
                getSaldo(nUSPString, senhaString);
                //Initialize a LoadViewTask object and call the execute() method
                new LoadViewTask().execute();
            }
        }
    }

    public void getSaldo(String uUSP, String uSenha){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://sherolerobandeco.herokuapp.com/get_saldo?login=" + uUSP + "&senha=" + uSenha;
        //String url = "http://sherolerobandeco.herokuapp.com/get_saldo?login=8521552&senha=Acess01!";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        json = response;
                        saldo = json;

                        progressDialog.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(SaldoActivity.this).create();
                        alertDialog.setTitle("Seu Saldo:");
                        switch (saldo){
                            case "":
                                alertDialog.setMessage("Erro de conexão");
                                break;
                            case "-2":
                                alertDialog.setMessage("Erro de conexão");
                                break;
                            case "-1":
                                alertDialog.setMessage("Usuario nao cadastrado ou senha incorreta");
                                break;
                            default:
                                alertDialog.setMessage("Voce tem " + saldo + " créditos");
                                break;
                        }
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                json = error.toString();
                AlertDialog alertDialog = new AlertDialog.Builder(SaldoActivity.this).create();
                alertDialog.setTitle("Erro:");
                alertDialog.setMessage("Aconteceu algo inesperado. Verifique sua internet e tente novamente.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    //To use the AsyncTask, it must be subclassed
    private class LoadViewTask extends AsyncTask<Void, Integer, Void>
    {
        //Before running code in separate thread
        @Override
        protected void onPreExecute()
        {
            //Create a new progress dialog
            progressDialog = new ProgressDialog(SaldoActivity.this);
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
                        //Wait 950 milliseconds
                        this.wait(1250);
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