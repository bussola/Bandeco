package ru.noties.scrollable.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

//import java.lang.reflect.Type;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

public class MainActivity extends BaseActivity implements ConfigurationFragmentCallbacks {

    static String cardapio_segunda_almoco;
    static String cardapio_segunda_janta;
    static String cardapio_terca_almoco;
    static String cardapio_terca_janta;
    static String cardapio_quarta_almoco;
    static String cardapio_quarta_janta;
    static String cardapio_quinta_almoco;
    static String cardapio_quinta_janta;
    static String cardapio_sexta_almoco;
    static String cardapio_sexta_janta;
    static String cardapio_sabado;

    private static final String ARG_LAST_SCROLL_Y = "arg.LastScrollY";
    private ScrollableLayout mScrollableLayout;

    public String json = "";
    public List<Cardapio> cards;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getCardapio();

        //savedInstanceState.putString("json", json);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View header = findViewById(R.id.header);
        final TabsLayout tabs = findView(R.id.tabs);

        mScrollableLayout = findView(R.id.scrollable_layout);
        mScrollableLayout.setDraggableView(tabs);

        final ViewPager viewPager = findView(R.id.view_pager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getResources(), getFragments());
        viewPager.setAdapter(adapter);

        tabs.setViewPager(viewPager);

        mScrollableLayout.setCanScrollVerticallyDelegate(new CanScrollVerticallyDelegate() {
            @Override
            public boolean canScrollVertically(int direction) {
                return adapter.canScrollVertically(viewPager.getCurrentItem(), direction);
            }
        });

        mScrollableLayout.setOnScrollChangedListener(new OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int y, int oldY, int maxY) {

                final float tabsTranslationY;
                if (y < maxY) {
                    tabsTranslationY = .0F;
                } else {
                    tabsTranslationY = y - maxY;
                }

                tabs.setTranslationY(tabsTranslationY);

                header.setTranslationY(y / 2);
            }
        });

        if (savedInstanceState != null) {
            final int y = savedInstanceState.getInt(ARG_LAST_SCROLL_Y);
            mScrollableLayout.post(new Runnable() {
                @Override
                public void run() {
                    mScrollableLayout.scrollTo(0, y);
                }
            });
        }
    }

    public void getCardapio(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.30:8080/bandeco";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        json = response;
                        Type listOfObjects = new TypeToken<List<Cardapio>>(){}.getType();
                        Gson gson = new GsonBuilder().create();
                        cards = gson.fromJson(json, listOfObjects);
                        bundle.putString("json", json); //Aqui coloca o json no bundle
                        CardapioListAdapter.recebeCardapioDaMain(MainActivity.this);
                        //bundle.putString("json", json);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                json = "Erro";
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_LAST_SCROLL_Y, mScrollableLayout.getScrollY());
        super.onSaveInstanceState(outState);
    }

    private List<BaseFragment> getFragments() {

        final FragmentManager manager = getSupportFragmentManager();
        final ColorRandomizer colorRandomizer = new ColorRandomizer(getResources().getIntArray(R.array.fragment_colors));
        final List<BaseFragment> list = new ArrayList<>();


        CallSegunda segundaFragment = (CallSegunda) manager.findFragmentByTag(CallSegunda.TAG);
        if (segundaFragment == null) {
            segundaFragment = CallSegunda.newInstance();
        }

        CallTerca tercaFragment = (CallTerca) manager.findFragmentByTag(CallTerca.TAG);
        if (tercaFragment == null) {
            tercaFragment = CallTerca.newInstance();
        }

        CallQuarta quartaFragment = (CallQuarta) manager.findFragmentByTag(CallQuarta.TAG);
        if (quartaFragment == null) {
            quartaFragment = CallQuarta.newInstance();
        }

        CallQuinta quintaFragment = (CallQuinta) manager.findFragmentByTag(CallQuinta.TAG);
        if (quintaFragment == null) {
            quintaFragment = CallQuinta.newInstance();
        }

        CallSexta sextaFragment = (CallSexta) manager.findFragmentByTag(CallSexta.TAG);
        if (sextaFragment == null) {
            sextaFragment = CallSexta.newInstance(colorRandomizer.next());
        }

        CallSabado sabadoFragment = (CallSabado) manager.findFragmentByTag(CallSabado.TAG);
        if (sabadoFragment == null) {
            sabadoFragment = CallSabado.newInstance();
        }

        Collections.addAll(list, segundaFragment, tercaFragment, quartaFragment, quintaFragment, sextaFragment,sabadoFragment);

        return list;
    }

    @Override
    public void onFrictionChanged(float friction) {
        mScrollableLayout.setFriction(friction);
    }

    @Override
    public void openDialog(float friction) {
        final ScrollableDialog dialog = ScrollableDialog.newInstance(friction);
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void openActivity(Intent intent) {
        startActivity(intent);
    }
}
