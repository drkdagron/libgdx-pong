package com.drkdagron.engine;

/**
 * Created by stemc on 2016-05-01.
 */
public class UIElement extends Entity {
    protected boolean touched;
    public boolean getTouched() { return touched; }
    public void setTouched(boolean t) { touched = t; }

    public UIElement()
    {}
}
