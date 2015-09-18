package ru.noties.scrollable.sample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.*;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request;
import com.android.volley.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
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

//perai.. tem que colocar aqui.. isso que nao manjo



    List<Cardapio> listaCardapio;

    final String[][] shero = {
            {"almoco segunda1", "alseg2", "alseg3", "alseg4", "alseg5", "alseg6", "alseg7", "alseg8"},
            {"janta segunda", "JS2", "JS3", "JS4", "JS5", "JS6"},
            {"almoco terca", "alter2", "alter3", "alter4", "alter5", "alter6", "alter7", "alter8"},
            {"janta terca", "JantaTerca2", "JantaTerca3", "JantaTerca4", "JantaTerca5", "JantaTerca6"},
            {"almoco quarta", "AlmocoQuarta2", "AlmocoQuarta3", "AlmocoQuarta4", "AlmocoQuarta5", "AlmocoQuarta6"},
            {"janta quarta", "sehro", "lero", "shero", "sehro", "lero"},
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
