package ru.noties.scrollable.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

public class MainActivity extends BaseActivity implements ConfigurationFragmentCallbacks {


    private static final String ARG_LAST_SCROLL_Y = "arg.LastScrollY";

    private ScrollableLayout mScrollableLayout;

    JSONObject lero = new JSONObject();

    public String resposta = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getCardapio();

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

    public List<Cardapio> getCardapio(){
        String url = "http://192.168.1.30:8080/bandeco";
        final String fdp = "fdp";

        List<Cardapio> c = new ArrayList<Cardapio>();
        JsonArray j = new JsonArray();
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        resposta = response.toString();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        resposta = error.toString();
                    }
                });

        return c;
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
            segundaFragment = CallSegunda.newInstance(colorRandomizer.next());
        }

        CallTerca tercaFragment
                = (CallTerca) manager.findFragmentByTag(CallTerca.TAG);
        if (tercaFragment == null) {
            tercaFragment = CallTerca.newInstance(colorRandomizer.next());
        }

        CallQuarta quartaFragment
                = (CallQuarta) manager.findFragmentByTag(CallQuarta.TAG);
        if (quartaFragment == null) {
            quartaFragment = CallQuarta.newInstance(colorRandomizer.next());
        }

        CallQuinta quintaFragment
                = (CallQuinta) manager.findFragmentByTag(CallQuinta.TAG);
        if (quintaFragment == null) {
            quintaFragment = CallQuinta.newInstance(colorRandomizer.next());
        }

        CallSexta sextaFragment
                = (CallSexta) manager.findFragmentByTag(CallSexta.TAG);
        if (sextaFragment == null) {
            sextaFragment = CallSexta.newInstance(colorRandomizer.next());
        }

        CallSabado sabadoFragment
                = (CallSabado) manager.findFragmentByTag(CallSabado.TAG);
        if (sabadoFragment == null) {
            sabadoFragment = CallSabado.newInstance(colorRandomizer.next());
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

    private void populateListView(){

    }


}
