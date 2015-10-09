package com.sherolero.bandeco.sample;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.com) on 29.03.2015.
 */
public class CallQuarta extends BaseFragment implements View.OnClickListener{
    static final String TAG = "tag.CallQuarta";

    private String periodo = "ALMOÇO";
    private boolean ehAlmoco = true;
    String almoco = "ALMOÇO";
    String janta = "JANTA";
    String data;

    public static CallQuarta newInstance() {
        final Bundle bundle = new Bundle();
        final CallQuarta fragment = new CallQuarta();
        fragment.setArguments(bundle);
        return fragment;
    }


    private ListView mListView;
    private TextView txtView_header;
    private Button botao;
    private CardapioListAdapter adapterAlmoco;
    private CardapioListAdapter adapterJanta;
    static String lista_quarta_almoco = "";
    public String lista_quarta_janta = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {
        setHasOptionsMenu(true);

        final View view = inflater.inflate(R.layout.cardapio_botao, parent, false);
        final CardapioListAdapter adapter = new CardapioListAdapter(getActivity(), 4, 0);
        adapterAlmoco = adapter;
        final CardapioListAdapter adapter1 = new CardapioListAdapter(getActivity(), 5, 1);
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
            case 1: //domingo
                data = MainActivity.getOtherDates(c, -4);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 2: //segunda
                data = MainActivity.getOtherDates(c, 2);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 3: //terca
                data = MainActivity.getOtherDates(c, 1);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 4: //quarta
                data = MainActivity.getOtherDates(c, 0);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 5: //quinta
                data = MainActivity.getOtherDates(c, -1);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 6: //sexta
                data = MainActivity.getOtherDates(c, -2);
                txtView_header.setText(periodo + " - " + data);
                break;
            case 7: //sabado
                data = MainActivity.getOtherDates(c, -3);
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
        return r.getString(R.string.fragment_quarta);
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
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        MenuItem itemBlog = menu.add(Menu.NONE, // Group ID
//                Menu.NONE, // Item ID
//                1, // Order
//                R.string.app_name); // Title
//        itemBlog.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); // ShowAsAction
//        itemBlog.setIcon(R.drawable.logo_bandeja); // Icon
//
//        // add your item before calling the super method
//        super.onCreateOptionsMenu(menu,inflater);
//    }


    @Override
    public void onClick(View v) {
        String ver_almoco = getResources().getString(R.string.string_ver_almoco);
        String ver_janta = getResources().getString(R.string.string_ver_janta);
        String palavra = txtView_header.getText().toString();
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