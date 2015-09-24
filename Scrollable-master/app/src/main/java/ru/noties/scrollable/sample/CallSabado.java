package ru.noties.scrollable.sample;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 29.03.2015.
 */
public class CallSabado extends BaseFragment {

    static final String TAG = "tag.CallSabado";

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

        //json = getArguments().getString("json");

        txtView_header=(TextView)view.findViewById(R.id.header_Almoco_Janta);
        botao = (Button)view.findViewById(R.id.botao);
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


}