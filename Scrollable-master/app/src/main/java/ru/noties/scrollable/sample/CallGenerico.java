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
 * Created by Vinicius on 21/09/2015.
 */
public class CallGenerico extends BaseFragment implements View.OnClickListener{

    private static int shero;
    static final String TAG_segunda = "tag.segCall";
    static final String TAG_terca = "tag.terCall";
    static final String TAG_quarta = "tag.quaCall";
    static final String TAG_quinta = "tag.quiCall";
    static final String TAG_sexta = "tag.sexCall";
    static final String TAG_sabado = "tag.sabCall";

    public static CallGenerico newInstance(int aux) {
        final CallGenerico fragment = new CallGenerico();
        return fragment;
    }


    private ListView mListView;
    private TextView txtView_header;
    private Button botao;
    public CardapioListAdapter adapterAlmoco;
    public CardapioListAdapter adapterJanta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {

        final View view = inflater.inflate(R.layout.cardapio_botao, parent, false);


        if(shero == 0) { //Segunda
            final CardapioListAdapter adapter_almoco = new CardapioListAdapter(getActivity(), 0, 0);
            adapterAlmoco = adapter_almoco;
            final CardapioListAdapter adapter_janta = new CardapioListAdapter(getActivity(), 1, 1);
            adapterJanta = adapter_janta;
            mListView = findView(view, R.id.list_view1);
            mListView.setAdapter(adapter_almoco);
        }
        else if(shero == 1){ //Terca
            final CardapioListAdapter adapter_almoco = new CardapioListAdapter(getActivity(), 2, 0);
            adapterAlmoco = adapter_almoco;
            final CardapioListAdapter adapter_janta = new CardapioListAdapter(getActivity(), 3, 1);
            adapterJanta = adapter_janta;
            mListView = findView(view, R.id.list_view1);
            mListView.setAdapter(adapter_almoco);
        }
        else if(shero == 2){ //Quarta
            final CardapioListAdapter adapter_almoco = new CardapioListAdapter(getActivity(), 4, 0);
            adapterAlmoco = adapter_almoco;
            final CardapioListAdapter adapter_janta = new CardapioListAdapter(getActivity(), 5, 1);
            adapterJanta = adapter_janta;
            mListView = findView(view, R.id.list_view1);
            mListView.setAdapter(adapter_almoco);
        }
        else if(shero == 3){ //Quinta
            final CardapioListAdapter adapter_almoco = new CardapioListAdapter(getActivity(), 6, 0);
            adapterAlmoco = adapter_almoco;
            final CardapioListAdapter adapter_janta = new CardapioListAdapter(getActivity(), 7, 1);
            adapterJanta = adapter_janta;
            mListView = findView(view, R.id.list_view1);
            mListView.setAdapter(adapter_almoco);
        }
        else if(shero == 4){ //Sexta
            final CardapioListAdapter adapter_almoco = new CardapioListAdapter(getActivity(), 8, 0);
            adapterAlmoco = adapter_almoco;
            final CardapioListAdapter adapter_janta = new CardapioListAdapter(getActivity(), 9, 1);
            adapterJanta = adapter_janta;
            mListView = findView(view, R.id.list_view1);
            mListView.setAdapter(adapter_almoco);
        }
        else if(shero == 5){ //Sabado
            final CardapioListAdapter adapter_almoco = new CardapioListAdapter(getActivity(), 10, 0);
            adapterAlmoco = adapter_almoco;
            final CardapioListAdapter adapter_janta = new CardapioListAdapter(getActivity(), 11, 1);
            adapterJanta = adapter_janta;
            mListView = findView(view, R.id.list_view1);
            mListView.setAdapter(adapter_almoco);
        }


        txtView_header=(TextView)view.findViewById(R.id.header_Almoco_Janta);
        botao = (Button)view.findViewById(R.id.botao);
        botao.setOnClickListener(this);

        return view;
    }

    @Override
    public CharSequence getTitle(Resources r) {
        CharSequence cs = r.getString(R.string.fragment_quarta);
        if(shero == 0) //Segunda
            cs = r.getString(R.string.fragment_segunda);
        else if(shero == 1) //Terca
            cs = r.getString(R.string.fragment_terca);
        else if(shero == 2) //Quarta
            cs = r.getString(R.string.fragment_quarta);
        else if(shero == 3) //Quinta
            cs = r.getString(R.string.fragment_quinta);
        else if(shero == 4) //Sexta
            cs = r.getString(R.string.fragment_sexta);
        else if(shero == 5) //Sabado
            cs = r.getString(R.string.fragment_sabado);
        return cs;
    }

    @Override
    public String getSelfTag() {
        String aux = "";
        if(shero == 0) //Segunda
            aux = TAG_segunda;
        else if(shero == 1) //Terca
            aux = TAG_terca;
        else if(shero == 2) //Quarta
            aux = TAG_quarta;
        else if(shero == 3) //Quinta
            aux = TAG_quinta;
        else if(shero == 4) //Sexta
            aux = TAG_sexta;
        else if(shero == 5) //Sabado
            aux = TAG_sabado;
        return aux;
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