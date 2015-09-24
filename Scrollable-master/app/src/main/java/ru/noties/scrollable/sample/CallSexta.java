package ru.noties.scrollable.sample;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 29.03.2015.
 */
public class CallSexta extends BaseFragment implements View.OnClickListener{
    int periodo = 0;

    static final String TAG = "tag.CallSexta";

    public static CallSexta newInstance(int color) {
        final Bundle bundle = new Bundle();
        final CallSexta fragment = new CallSexta();
        fragment.setArguments(bundle);
        return fragment;

    }


    private ListView mListView;
    private TextView txtView_header;
    private Button botao;
    private CardapioListAdapter adapterAlmoco;
    private CardapioListAdapter adapterJanta;
    private String json = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {

        final View view = inflater.inflate(R.layout.cardapio_botao, parent, false);
        final CardapioListAdapter adapter = new CardapioListAdapter(getActivity(), 8, 0);
        adapterAlmoco = adapter;
        final CardapioListAdapter adapter1 = new CardapioListAdapter(getActivity(), 9, 1);
        adapterJanta = adapter1;


        mListView = findView(view, R.id.list_view_cardapio);
        mListView.setAdapter(adapter);

        //json = getArguments().getString("json");

        txtView_header=(TextView)view.findViewById(R.id.header_Almoco_Janta);
        botao = (Button)view.findViewById(R.id.botao);
        botao.setOnClickListener(this);

        return view;
    }

    @Override
    public CharSequence getTitle(Resources r) {
        return r.getString(R.string.fragment_sexta);
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
        String almoco = getResources().getString(R.string.string_almoco);
        String janta = getResources().getString(R.string.string_janta);
        if (v.getId() == R.id.botao){
            if (palavra.equals(janta)){ //Caso Almoco
                txtView_header.setText(almoco);
                txtView_header.setBackgroundColor(Color.WHITE);
                txtView_header.setTextColor(Color.BLACK);
                botao.setText(ver_janta);
                mListView.setAdapter(adapterAlmoco);
                mListView.setBackgroundColor(Color.WHITE);
            }
            else { //Caso Janta
                txtView_header.setText(janta);
                txtView_header.setBackgroundColor(Color.BLACK);
                txtView_header.setTextColor(Color.WHITE);
                botao.setText(ver_almoco);
                mListView.setAdapter(adapterJanta);
                mListView.setBackgroundColor(Color.BLACK);
            }
        }
    }


}