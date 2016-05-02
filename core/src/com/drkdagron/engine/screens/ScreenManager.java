package com.drkdagron.engine.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.drkdagron.pong.GameScreen;
import java.util.*;

/**
 * Created by stemc on 2016-05-01.
 */
public class ScreenManager {
    BaseScreen[] screens;
    BaseScreen currentScreen;

    public ScreenManager()
    {
        screens = new BaseScreen[10];
    }

    public void addScreen(BaseScreen b)
    {
        Gdx.app.error("SCREENMANAGER", b.toString());
        for (int i= 0; i < screens.length; i++)
        {
            if (screens[i] == null)
            {
                screens[i] = b;
                Gdx.app.error("SCREENMANAGER", screens[i].name);
                return;
            }
        }
    }

    public void setScreen(String n)
    {
        Gdx.app.debug("SEARCH", "STARTING: " + n);
        for (int i= 0; i < screens.length; i++)
        {
            Gdx.app.debug("SEARCH", "ING");
            if (screens[i] != null) {
                if (screens[i].name == n) {
                    Gdx.app.debug("SEARCH", "FOUND SCREEN");
                    currentScreen = screens[i];
                    currentScreen.Init();
                }
            }
        }
    }

    public void Draw(SpriteBatch sb)
    {
        if (currentScreen != null) {
            currentScreen.Draw(sb);
        }
        else {
            Gdx.app.error("UPDATE", "DRAW");
        }
    }

    public void Update(float elapsedTime)
    {
        if (currentScreen != null) {
            currentScreen.Update(elapsedTime);
        }
    }
}
