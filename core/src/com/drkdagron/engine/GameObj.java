package com.drkdagron.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObj extends Entity{
    public void Move(Vector2 v)
    {
        position.x += v.x; position.y += v.y;
    }
    protected Texture texture;
    protected Vector2 origin;
    protected Vector2 bounds;
    public void setBounds(Vector2 b)
    {
        bounds = b;
    }

    public GameObj(Pixmap pix)
    {
        texture = new Texture(pix);
        origin = new Vector2(texture.getWidth() / 2, texture.getHeight() / 2);
        position = new Vector2(0,0);
        rotation = 0;
    }
    public GameObj(String file)
    {
        texture = new Texture(Gdx.files.internal(file));
        origin = new Vector2(texture.getWidth() / 2, texture.getHeight() / 2);
        position = new Vector2(0,0);
        rotation = 0;
    }

    public void Draw(SpriteBatch sb)
    {
        sb.draw(texture, position.x - origin.x, position.y - origin.y);
    }

    public float Top()
    {
        return position.y + origin.y;
    }
    public float Bottom()
    {
        return position.y - origin.y;
    }
    public float Left()
    {
        return position.x - origin.x;
    }
    public float Right()
    {
        return position.x + origin.x;
    }

    public Rectangle getCollRect()
    {
        return new Rectangle(position.x - origin.x, position.y + origin.y, texture.getWidth(), texture.getHeight());
    }
}
