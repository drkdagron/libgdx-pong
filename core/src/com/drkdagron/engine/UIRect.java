package com.drkdagron.engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by stemc on 2016-05-01.
 */
public class UIRect extends UIElement {

    protected Texture rect;

    public UIRect(int width, int height, Color c, boolean fill, Vector2 pos)
    {
        super();

        position = pos;

        Pixmap p = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        p.setColor(c);
        if (fill)
            p.fillRectangle(0, 0, width, height);
        else
            p.drawRectangle(0, 0, width, height);
        rect = new Texture(p);
    }

    public void Draw(SpriteBatch sb)
    {
        sb.draw(rect, position.x - (rect.getWidth() / 2), position.y - (rect.getHeight() / 2));
    }
}
