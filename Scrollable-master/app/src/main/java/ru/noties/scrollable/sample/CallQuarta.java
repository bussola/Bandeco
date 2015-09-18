package ru.noties.scrollable.sample;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;





/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 29.03.2015.
 */
public class CallQuarta extends BaseFragment implements View.OnClickListener{
    int periodo = 0;

    static final String TAG = "tag.CallQuarta";

    public static CallQuarta newInstance(int color) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_COLOR, color);

        final CallQuarta fragment = new CallQuarta();
        fragment.setArguments(bundle);
        return fragment;
    }


    private ListView mListView;
    TextView txtView_header;
    Button botao;
    public CardapioListAdapter adapterAlmoco;
    public CardapioListAdapter adapterJanta;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {

        final View view = inflater.inflate(R.layout.cardapio_botao, parent, false);
        final CardapioListAdapter adapter = new CardapioListAdapter(getActivity(), 4);
        adapterAlmoco = adapter;
        final CardapioListAdapter adapter1 = new CardapioListAdapter(getActivity(), 5);
        adapterJanta = adapter1;

        mListView = findView(view, R.id.list_view1);
        mListView.setAdapter(adapter);


        txtView_header=(TextView)view.findViewById(R.id.header_Almoco_Janta);
        botao = (Button)view.findViewById(R.id.botao);
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

    @Override
    public void onClick(View v) {
        String palavra = txtView_header.getText().toString();
        String almoco = getResources().getString(R.string.string_almoco);
        String janta = getResources().getString(R.string.string_janta);
        if (v.getId() == R.id.botao){
            if (palavra.equals(janta)){
                txtView_header.setText(almoco);
                botao.setText(janta);
                mListView.setAdapter(adapterAlmoco);
            }
            else {
                txtView_header.setText(janta);
                botao.setText(almoco);
                mListView.setAdapter(adapterJanta);
            }
        }
    }


}