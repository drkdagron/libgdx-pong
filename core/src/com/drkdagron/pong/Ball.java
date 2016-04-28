package com.drkdagron.pong;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.drkdagron.engine.GameObj;

import java.util.Random;

/**
 * Created by stemc on 2016-04-27.
 */
public class Ball extends GameObj {

    protected Vector2 direction = new Vector2(0,0);
    protected float speed;

    public Ball(String file, Vector2 bounds)
    {
        super(file);
        this.bounds = bounds;

        ResetBall();
    }

    public void Draw(SpriteBatch sb)
    {
        sb.draw(texture, position.x - origin.x, position.y - origin.y);
    }

    public void Update(float elapsedTime)
    {
        position.add(direction);

        if (position.y + origin.y > 400) //hit top
        {
            direction = new Vector2(direction.x, -direction.y);
        }
        else if (position.y - origin.y < -400) //hit bottom
        {
            direction = new Vector2(direction.x, -direction.y);
        }
        else if (position.x - origin.x < -240) // hit left
        {
            direction = new Vector2(-direction.x, direction.y);
        }
        else if (position.x + origin.x > 240) // hit right
        {
            direction = new Vector2(-direction.x, direction.y);
        }
    }

    public void ResetBall()
    {
        Random rnd = new Random();
        float x = (rnd.nextFloat() * 2) - 1;
        float y = (rnd.nextFloat() * 2) - 1;
        direction = new Vector2(x, y);
        direction = direction.nor();
    }
}
