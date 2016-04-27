package com.drkdagron.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameObj {
    protected Vector2 position;
    public void setPosition(Vector2 v)
    {
        position = v;
    }
    public Vector2 getPosition() {
        return position;
    }
    public void Move(Vector2 v)
    {
        position.x += v.x; position.y += v.y;
    }
    protected Texture texture;
    protected float rotation;
    protected Vector2 origin;
    protected Vector2 bounds;
    public void setBounds(Vector2 b)
    {
        bounds = b;
    }

    public GameObj(String file)
    {
        texture = new Texture(Gdx.files.internal(file));
        origin = new Vector2(texture.getWidth() / 2, texture.getHeight() / 2);
        position = new Vector2(0,0);
        rotation = 0;
    }
}
