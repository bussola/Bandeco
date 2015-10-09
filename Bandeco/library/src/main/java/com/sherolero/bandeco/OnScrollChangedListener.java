package com.sherolero.bandeco;

/**
 * Created by Dimitry Ivanov (mail@dimitryivanov.com) on 28.03.2015.
 */
public interface OnScrollChangedListener {

    /**
     * This method will be invoked when scroll state
     * of {@link com.sherolero.bandeco.ScrollableLayout} has changed.
     * @see com.sherolero.bandeco.ScrollableLayout#setOnScrollChangedListener(OnScrollChangedListener)
     * @param y current scroll y
     * @param oldY previous scroll y
     * @param maxY maximum scroll y (helpful for calculating scroll ratio for e.g. for alpha to be applied)
     */
    void onScrollChanged(int y, int oldY, int maxY);
}
