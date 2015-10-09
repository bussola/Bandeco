package com.sherolero.bandeco.sample;

import android.content.Intent;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.com) on 29.03.2015.
 */
public interface ConfigurationFragmentCallbacks {
    void onFrictionChanged(float friction);
    void openDialog(float friction);
    void openActivity(Intent intent);
}
