package com.sherolero.bandeco.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vinicius Busssola
 */
public class CardapioListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private int contador;
    private int index;
    private int periodo;

    static List<Cardapio> cards_CardAdapter;
    static String[][] listaCardapios = new String[12][12];

    public CardapioListAdapter(Context context, int index, int periodo) {
        this.mInflater = LayoutInflater.from(context);
        this.index = index;
        this.periodo = periodo;
    }

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
            if(periodo == 0) {
                view = mInflater.inflate(R.layout.configuracao_cardapio_dia, parent, false);
            }
            else{
                view = mInflater.inflate(R.layout.configuracao_cardapio_noite, parent, false);
            }
        } else {
            view = convertView;
        }

        ((TextView) view).setText(listaCardapios[index][position]); //Position se refere a posicao de cada linha do ListView

        return view;
    }

    @Override
    public int getCount() {
        int i = 0;
        for(int j =0;j<listaCardapios[index].length;j++) {
            if (listaCardapios[index][j] != null) {
                i++;
            }
        }
        contador = i;
        //contador = listaCardapios[index].length;
        return contador;
    }


    static void recebeCardapioDaMain(MainActivity m){
        int i = 0, j = 0;
        cards_CardAdapter = m.cards;
        for(Cardapio c: cards_CardAdapter){
            List<String> st = c.getOpcoes();
            j = 0;
            for(String s: st){
                listaCardapios[i][j] = s.substring(0, 1) + s.substring(1).toLowerCase();
                j++;
            }
            i++;
        }
    }

    static void recebeErro(){
        for(int i=0;i<12;i++) {
            listaCardapios[i][0] = "Sem acesso à internet";
        }
    }
}

