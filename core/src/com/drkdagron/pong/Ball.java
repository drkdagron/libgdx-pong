package com.drkdagron.pong;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.drkdagron.engine.GameObj;

import java.util.Random;

public class Ball extends GameObj {

    protected Vector2 direction = new Vector2(0,0);
    protected float speed = 4;
    public void addSpeed()
    {
        speed += 1.25f;
        if (speed > 40)
            speed = 4;
    }

    public Ball(String file, Vector2 bounds)
    {
        super(file);
        this.bounds = bounds;

        resetBall();
    }

    public void Update(float elapsedTime, Player[] players)
    {
        Vector2 dirSpd = new Vector2(direction.x * speed, direction.y * speed);
        Vector2 stepSpd = new Vector2(dirSpd.x * 0.2f, dirSpd.y * 0.2f);

        for (int i= 0; i < 5; i++) {
            position.add(stepSpd);
            if (position.y + origin.y > 450) //hit top
            {
                resetBall();
                players[1].Score++;
                return;
            } else if (position.y - origin.y < -450) //hit bottom
            {
                resetBall();
                players[0].Score++;
                return;
            } else if (position.x - origin.x < -240) // hit left
            {
                position.x = -240 + origin.x;
                direction = new Vector2(-direction.x, direction.y);
                return;
            } else if (position.x + origin.x > 240) // hit right
            {
                position.x = 240 - origin.x;
                direction = new Vector2(-direction.x, direction.y);
                return;
            }

            for (int j= 0; j < players.length; j++)
            {
                if (Left() < players[j].Right() && Right() > players[j].Left() && Top() > players[j].Bottom() && Bottom() < players[j].Top() )
                {
                    Vector2 e = new Vector2(getPosition().x - players[j].getPosition().x, getPosition().y - players[j].getPosition().y);
                    e.nor();
                    while (Left() < players[j].Right() && Right() > players[j].Left() && Top() > players[j].Bottom() && Bottom() < players[j].Top() )
                    {
                        position.add(e);
                    }

                    direction = e;
                    addSpeed();
                    return;
                }
            }
        }
    }

    public void resetBall()
    {
        position = new Vector2(0,0);
        Random rnd = new Random();
        float x = (rnd.nextFloat() * 2) - 1;
        float y = (rnd.nextFloat() * 2) - 1;
        if (y < 0.2 && y > 0)
        {
            y = 0.2f;
        }
        else if (y > -0.2 && y < 0)
        {
            y = -0.2f;
        }

        direction = new Vector2(x, y);
        direction = direction.nor();
        speed = 5;
    }
}
