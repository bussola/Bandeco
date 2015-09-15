package ru.noties.scrollable.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 29.03.2015.
 */
public class CardapioListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private int contador;
    private int index;

    final String[][] lista = {
            {"almoco segunda", "2", "3", "4", "5", "6", "janta segunda", "sehro", "lero"},
            {"almoco terca", "sehro", "lero", "janta terca", "sehro", "lero"},
            {"almoco quarta", "sehro", "lero", "janta quarta", "sehro", "lero"},
            {"almoco quinta", "sehro", "lero", "janta quinta", "sehro", "lero"},
            {"almoco sexta", "sehro", "lero", "janta sexta", "sehro", "lero"},
            {"almoco sabado", "sehro", "lero", "janta sabado", "sehro", "lero"},
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
