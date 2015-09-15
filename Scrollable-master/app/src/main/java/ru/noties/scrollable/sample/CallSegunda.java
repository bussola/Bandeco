package ru.noties.scrollable.sample;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.ru) on 29.03.2015.
 */
public class CallSegunda extends BaseFragment {

    static final String TAG = "tag.ListViewFragment";

    public static CallSegunda newInstance(int color) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_COLOR, color);

        final CallSegunda fragment = new CallSegunda();
        fragment.setArguments(bundle);
        return fragment;
    }

    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {
        final View view = inflater.inflate(R.layout.cardapio, parent, false);
        final CardapioListAdapter adapter = new CardapioListAdapter(getActivity(), 0);

        mListView = findView(view, R.id.list_view);
        mListView.setAdapter(adapter);
        return view;
    }

    @Override
    public CharSequence getTitle(Resources r) {
        return r.getString(R.string.fragment_segunda);
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