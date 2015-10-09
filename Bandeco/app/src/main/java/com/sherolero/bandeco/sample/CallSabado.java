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
public class CallSabado extends BaseFragment{
    static final String TAG = "tag.CallSabado";

    private String periodo = "ALMOÇO";
    private boolean ehAlmoco = true;
    String almoco = "ALMOÇO";

    public static CallSabado newInstance() {
        final Bundle bundle = new Bundle();
        final CallSabado fragment = new CallSabado();
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
        final CardapioListAdapter adapter = new CardapioListAdapter(getActivity(), 10, 0);
        adapterAlmoco = adapter;
        final CardapioListAdapter adapter1 = new CardapioListAdapter(getActivity(), 11, 1);
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
        String data;
        switch (MainActivity.diaSemana){
            case 1:
                data = MainActivity.getOtherDates(c, -1);
                txtView_header.setText(almoco + " - " + data);
                break;
            case 2:
                data = MainActivity.getOtherDates(c, 5);
                txtView_header.setText(almoco + " - " + data);
                break;
            case 3:
                data = MainActivity.getOtherDates(c, 4);
                txtView_header.setText(almoco + " - " + data);
                break;
            case 4:
                data = MainActivity.getOtherDates(c, 3);
                txtView_header.setText(almoco + " - " + data);
                break;
            case 5:
                data = MainActivity.getOtherDates(c, 2);
                txtView_header.setText(almoco + " - " + data);
                break;
            case 6:
                data = MainActivity.getOtherDates(c, 1);
                txtView_header.setText(almoco + " - " + data);
                break;
            case 7:
                data = MainActivity.getOtherDates(c, 0);
                txtView_header.setText(almoco + " - " + data);
                break;
        }

        botao = (Button)view.findViewById(R.id.botao);
        botao.setText("");
        //botao.setOnClickListener(this);

        return view;
    }

    @Override
    public CharSequence getTitle(Resources r) {
        return r.getString(R.string.fragment_sabado);
    }

    @Override
    public String getSelfTag() {
        return TAG;
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return true;
    }


//    @Override
//    public void onClick(View v) {
//        String palavra = txtView_header.getText().toString();
//        String ver_almoco = getResources().getString(R.string.string_ver_almoco);
//        String ver_janta = getResources().getString(R.string.string_ver_janta);
//        String almoco = getResources().getString(R.string.string_almoco);
//        String janta = getResources().getString(R.string.string_janta);
//        if (v.getId() == R.id.botao){
//            if (palavra.substring(0,1).toLowerCase().equals("j")){ //Caso Almoco
//                txtView_header.setText(almoco + " - " + dataCorreta);
//                //txtView_header.setBackgroundColor(Color.WHITE);
//                //txtView_header.setTextColor(Color.BLACK);
//                botao.setText(ver_janta);
//                mListView.setAdapter(adapterAlmoco);
//                //mListView.setBackgroundColor(getResources().getColor(R.color.color_1));
//            }
//            else { //Caso Janta
//                txtView_header.setText(almoco + " - " + dataCorreta);
//                //txtView_header.setBackgroundColor(Color.BLACK);
//                //txtView_header.setTextColor(Color.WHITE);
//                botao.setText(ver_almoco);
//                mListView.setAdapter(adapterJanta);
//                //mListView.setBackgroundColor(Color.BLACK);
//            }
//        }
//    }


}