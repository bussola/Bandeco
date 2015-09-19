package ru.noties.scrollable.sample;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 29.03.2015.
 */
public class CardapioListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private int contador;
    private int index;

    public CardapioListAdapter(Context context, int index) {
        this.mInflater = LayoutInflater.from(context);
        this.index = index;
    }

    List<String> teste = Arrays.asList("sup1", "sup2", "sup3");
    Cardapio c = new Cardapio("segunda", "almoco", teste);

    final Gson gson = new Gson();
// original object instantiation
    Cardapio modelObject = new Cardapio("segunda", "almoco", teste);
// converting an object to json object
    String json = gson.toJson(modelObject);
// converting json to object
    Cardapio modelObject1 = gson.fromJson(json, Cardapio.class);


    List<Cardapio> listaCardapio;

    final String[][] shero = {
            {"almoco segunda1", "alseg2", "alseg3", "alseg4", "alseg5", "alseg6", "alseg7", "alseg8"},
            {"janta segunda", "JS2", "JS3", "JS4", "JS5", "JS6"},
            {"almoco terca", "alter2", "alter3", "alter4", "alter5", "alter6", "alter7", "alter8"},
            {"janta terca", "JantaTerca2", "JantaTerca3", "JantaTerca4", "JantaTerca5", "JantaTerca6"},
            {"almoco quarta", "AlmocoQuarta2", "AlmocoQuarta3", "AlmocoQuarta4", "AlmocoQuarta5", "AlmocoQuarta6", "AlmocoQuarta7", "AlmocoQuarta8"},
            {"janta quarta", "sehro", "lero", "shero", "sehro", "lero", "sehro", "lero", "sehro", "lero"},
            {"almoco quinta", "alseg2", "alseg3", "alseg4", "alseg5", "alseg6"},
            {"janta quinta", "sehro", "lero", "shero", "sehro", "lero"},
            {"almoco sexta", "sehro", "lero", "shero", "sehro", "lero"},
            {"janta sexta", "sehro", "lero", "shero", "sehro", "lero"},
            {"almoco sabado", "sehro", "lero", "shero", "sehro", "lero"},
            {"janta sabado", "sehro", "lero", "shero", "sehro", "lero"},
    };

//    public void adiciona(){
//        for(int i= 0; i < this.shero[i].length-1; i++){
//            String dia = shero[i][]
//            Cardapio p = new Cardapio(shero[i][0], shero[i][1], )
//            for(int j = 0; j < this.shero[i][j].length(); j++){
//                this.listaCardapio.add(new Cardapio())
//            }
//        }
//    }


//    class RequestTask extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... uri) {
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpResponse response;
//            String responseString = null;
//            try {
//                response = httpclient.execute(new HttpGet(uri[0]));
//                StatusLine statusLine = response.getStatusLine();
//                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    response.getEntity().writeTo(out);
//                    responseString = out.toString();
//                    out.close();
//                } else{
//                    //Closes the connection.
//                    response.getEntity().getContent().close();
//                    throw new IOException(statusLine.getReasonPhrase());
//                }
//            } catch (ClientProtocolException e) {
//                //TODO Handle problems..
//            } catch (IOException e) {
//                //TODO Handle problems..
//            }
//            return responseString;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            //Do anything with response..
//        }
//    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0L;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;

        if (convertView == null) {
            view = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        } else {
            view = convertView;
        }

        ((TextView) view).setText(shero[index][position]); //Position se refere a posicao de cada linha do ListView

        return view;
    }

    @Override
    public int getCount() {
        contador = shero[index].length;
        return contador;
    }
}
