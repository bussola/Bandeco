package com.sherolero.bandeco.sample;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;


/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.com) on 29.03.2015.
 */
public class CallTerca extends BaseFragment implements View.OnClickListener{
    static final String TAG = "tag.CallTerca";

    private String periodo = "ALMOÇO";
    private boolean ehAlmoco = true;
    private String dataCorreta;
    String almoco = "ALMOÇO";
    String janta = "JANTA";
    String data;

    public static CallTerca newInstance() {
        final Bundle bundle = new Bundle();
        final CallTerca fragment = new CallTerca();
        fragment.setArguments(bundle);
        return fragment;
    }


    private ListView mListView;
    private TextView txtView_header;
    private Button botao;
    private CardapioListAdapter adapterAlmoco;
    private CardapioListAdapter adapterJanta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {

        final View view = inflater.inflate(R.layout.cardapio_botao, parent, false);
        final CardapioListAdapter adapter = new CardapioListAdapter(getActivity(), 2, 0);
        adapterAlmoco = adapter;
        final CardapioListAdapter adapter1 = new CardapioListAdapter(getActivity(), 3, 1);
        adapterJanta = adapter1;

        mListView = findView(view, R.id.list_view_cardapio);
        mListView.setAdapter(adapter);
        if(MainActivity.hora > 14){
            ehAlmoco = false;
            mListView.setAdapter(adapter1); // Abre na Janta
            periodo = "JANTA";
        }

        txtView_header=(TextView)view.findViewById(R.id.header_Almoco_Janta);

        Calendar c = Calendar.getInstance();
        switch (MainActivity.diaSemana){
            case 1:
                data = MainActivity.getOtherDates(c, -5);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 2:
                data = MainActivity.getOtherDates(c, 1);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 3:
                data = MainActivity.getOtherDates(c, 0);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 4:
                data = MainActivity.getOtherDates(c, -1);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 5:
                data = MainActivity.getOtherDates(c, -2);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 6:
                data = MainActivity.getOtherDates(c, -3);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 7:
                data = MainActivity.getOtherDates(c, -4);
                txtView_header.setText(periodo + " - " + data);
                break;
        }

        botao = (Button)view.findViewById(R.id.botao);
        if(!ehAlmoco)
            botao.setText(getResources().getString(R.string.string_ver_almoco));
        botao.setOnClickListener(this);

        return view;
    }

    @Override
    public CharSequence getTitle(Resources r) {
        return r.getString(R.string.fragment_terca);
    }

    @Override
    public String getSelfTag() {
        return TAG;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return true;
    }

    @Override
    public void onClick(View v) {
        String palavra = txtView_header.getText().toString();
        String ver_almoco = getResources().getString(R.string.string_ver_almoco);
        String ver_janta = getResources().getString(R.string.string_ver_janta);
        if (v.getId() == R.id.botao){
            if (palavra.substring(0,1).toLowerCase().equals("j")){ //Caso Almoco
                txtView_header.setText(almoco + " - " + data);
                botao.setText(ver_janta);
                mListView.setAdapter(adapterAlmoco);
            }
            else { //Caso Janta
                txtView_header.setText(janta + " - " + data);
                botao.setText(ver_almoco);
                mListView.setAdapter(adapterJanta);
            }
        }
    }


}