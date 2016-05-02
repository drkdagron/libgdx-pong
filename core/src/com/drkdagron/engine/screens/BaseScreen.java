package com.drkdagron.engine.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by stemc on 2016-05-01.
 */
public abstract class BaseScreen {
    public ScreenManager manager;
    public String name;

    public BaseScreen(ScreenManager man, String name)
    {
        this.manager = man;
        this.name = name;
    }

    public abstract void Init();
    public abstract void Draw(SpriteBatch sb);
    public abstract void Update(float elapsedTime);
}
