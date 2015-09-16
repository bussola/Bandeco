package ru.noties.scrollable.sample;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 29.03.2015.
 */
public class CallQuinta extends BaseFragment {

    static final String TAG = "tag.CallQuinta";

    public static CallQuinta newInstance(int color) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_COLOR, color);

        final CallQuinta fragment = new CallQuinta();
        fragment.setArguments(bundle);
        return fragment;
    }


    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {
        final View view = inflater.inflate(R.layout.cardapio_duplo, parent, false);
        final CardapioListAdapter adapter = new CardapioListAdapter(getActivity(), 6);
        final CardapioListAdapter adapter1 = new CardapioListAdapter(getActivity(), 7);

        mListView = findView(view, R.id.list_view1);
        mListView.setAdapter(adapter);

        mListView = findView(view, R.id.list_view2);
        mListView.setAdapter(adapter1);
        return view;
    }

    @Override
    public CharSequence getTitle(Resources r) {
        return r.getString(R.string.fragment_quinta);
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