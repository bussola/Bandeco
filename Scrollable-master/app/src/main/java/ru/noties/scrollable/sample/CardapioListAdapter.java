package ru.noties.scrollable.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 29.03.2015.
 */
public class CardapioListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private int contador;
    private int index;

    List<String> teste = Arrays.asList("sup1", "sup2", "sup3");
    Cardapio c = new Cardapio("segunda", "almoco", teste);


/*
    final Gson gson = new Gson();
// original object instantiation
    Cardapio modelObject = new Cardapio("segunda", "almoco", teste);
            System.out.println("toJson ---");
            System.out.println("Original Java object : " + modelObject);
// converting an object to json object
    String json = gson.toJson(modelObject);
            System.out.println("Converted JSON string is : " + json);

            System.out.println("fromJson----");
            System.out.println("Original JSON string is : " + json);
// converting json to object
Cardapio modelObject1 = gson.fromJson(json, Cardapio.class);
            System.out.println("Converted Java object : " + modelObject1);
*/



    List<Cardapio> listaCardapio;

    final String[][] lista = {
            {"almoco segunda1", "alseg2", "alseg3", "alseg4", "alseg5", "alseg6"},
            {"janta segunda", "sehro", "lero", "janta terca", "sehro", "lero"},
            {"almoco terca", "sehro", "lero", "janta quarta", "sehro", "lero"},
            {"janta terca", "sehro", "lero", "janta quinta", "sehro", "lero"},
            {"almoco quarta", "sehro", "lero", "janta sexta", "sehro", "lero"},
            {"janta quarta", "sehro", "lero", "janta sabado", "sehro", "lero"},
            {"almoco quinta", "alseg2", "alseg3", "alseg4", "alseg5", "alseg6"},
            {"janta quinta", "sehro", "lero", "janta terca", "sehro", "lero"},
            {"almoco sexta", "sehro", "lero", "janta quarta", "sehro", "lero"},
            {"janta sexta", "sehro", "lero", "janta quinta", "sehro", "lero"},
            {"almoco sabado", "sehro", "lero", "janta sexta", "sehro", "lero"},
            {"janta sabado", "sehro", "lero", "janta sabado", "sehro", "lero"},
    };

    public CardapioListAdapter(Context context, int index) {
        this.mInflater = LayoutInflater.from(context);
        this.index = index;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0L;
    }

    int i = 0;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;

        if (convertView == null) {
            view = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        } else {
            view = convertView;
        }

        if(i < lista[index].length) {
            ((TextView) view).setText(lista[index][i]);
            i++;
        }

        return view;
    }

    @Override
    public int getCount() {
        contador = lista[index].length;
        return contador;
    }
}
