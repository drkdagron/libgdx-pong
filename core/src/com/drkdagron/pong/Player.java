package com.drkdagron.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.drkdagron.engine.GameObj;

/**
 * Created by drkdagron on 2016-04-26.
 */
public class Player extends GameObj{
    public Player(String file, Vector2 bounds) {
        super(file);
        this.bounds = bounds;
    }

    public boolean isTop()
    {
        if (position.y > 0)
            return true;

        return false;
    }

    public void Draw(SpriteBatch sb)
    {
        sb.draw(texture, position.x - origin.x, position.y - origin.y);
    }
}
