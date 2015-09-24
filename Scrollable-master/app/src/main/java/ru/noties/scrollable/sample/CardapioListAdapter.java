package ru.noties.scrollable.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Intent;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 29.03.2015.
 */
public class CardapioListAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private int contador;
    private int index;
    private int periodo;

    static String card_seg_alm = "";
    static String card_seg_jan = "";
    static String card_ter_alm = "";
    static String card_ter_jan = "";
    static String card_qua_alm = "";
    static String card_qua_jan = "";
    static String card_qui_alm = "";
    static String card_qui_jan = "";
    static String card_sex_alm = "";
    static String card_sex_jan = "";
    static String card_sab = "";

    static List<Cardapio> cards_CardAdapter;
    static String[][] listaCardapios = new String[12][12];

    public CardapioListAdapter(Context context, int index, int periodo) {
        this.mInflater = LayoutInflater.from(context);
        this.index = index;
        this.periodo = periodo;
    }
    List<String> teste = Arrays.asList("sup1", "sup2", "sup3");

    //Recebe os cardapios da main

    //depois:
//    for(Cardapio c: cardapios){
//        //vc pega os cardapios 1 por 1
//        for(String s: c.opcoes){
//            //vc pega as opçoes de cada cardapio
//        }
//    }


    final String[][] shero = {
            {"shero", "lero"},
            {card_seg_jan},
            {card_ter_alm},
            {card_ter_jan},
            {card_qua_alm},
            {card_qua_jan},
            {card_qui_alm},
            {card_qui_jan},
            {card_sex_alm},
            {card_sex_jan},
            {card_sab}
    };

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
        contador = listaCardapios[index].length;
        return contador;
    }


    static void recebeCardapioDaMain(MainActivity m){
        int i = 0, j = 0;
        cards_CardAdapter = m.cards;
        for(Cardapio c: cards_CardAdapter){
            List<String> st = c.getOpcoes();
            j = 0;
            for(String s: st){
                listaCardapios[i][j] = s;
                j++;
            }
            i++;
        }
    }
}

