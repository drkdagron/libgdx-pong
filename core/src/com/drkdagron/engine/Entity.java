package com.drkdagron.engine;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by stemc on 2016-05-01.
 */
public class Entity {
    protected Vector2 position;
    public void setPosition(Vector2 v) { position = v; }
    public Vector2 getPosition() { return position;}

    protected float rotation;
    public void setRotation(float r) { rotation = r; }
    public float getRotation() { return rotation; }

    public Entity(Vector2 p, float r)
    {
        position = p;
        rotation = r;
    }
    public Entity(Vector2 p)
    {
        position = p;
        rotation = 0;
    }
    public Entity()
    {
        position = new Vector2(0,0);
        rotation = 0;
    }
}
