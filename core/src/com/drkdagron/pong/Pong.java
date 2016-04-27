package com.drkdagron.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Pong extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	OrthographicCamera gameCam;
	Player[] players;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		gameCam = new OrthographicCamera(480, 800);
		players = new Player[2];
		players[0] = new Player("pblue.png", new Vector2(480, 800));
		players[0].setPosition(new Vector2(0, 370));
		players[1] = new Player("pred.png", new Vector2(480, 800));
		players[1].setPosition(new Vector2(0, -370));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Update(Gdx.graphics.getDeltaTime());

		batch.setProjectionMatrix(gameCam.combined);
		batch.begin();
		for (int i= 0; i < players.length; i++)
		{
			players[i].Draw(batch);
		}
		batch.end();
	}

	public void Update(float elapsedTime)
	{
		Vector2 t0 = getTouch(0);
		Vector2 t1 = getTouch(1);
		if (Gdx.input.isTouched())
		{
			if (Gdx.input.isTouched(0))
			{
				if (t0.y < 400)
				{
					players[0].setPosition(new Vector2(t0.x - 200, players[0].getPosition().y));
				}
				else if (t0.y > 400)
				{
					players[1].setPosition(new Vector2(t0.x - 200, players[1].getPosition().y));
				}
			}
			if (Gdx.input.isTouched(1))
			{
				if (t1.y < 400)
				{
					players[0].setPosition(new Vector2(t1.x - 200, players[0].getPosition().y));
				}
				else if (t1.y > 400)
				{
					players[1].setPosition(new Vector2(t1.x - 200, players[1].getPosition().y));
				}
			}
		}
	}

	public Vector2 getTouch(int id)
	{
		Vector2 start = new Vector2(0,0);
		Vector2 viewport = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Vector2 scale = new Vector2(0,0);
		if (Gdx.input.isTouched(id)) {
			start = new Vector2(Gdx.input.getX(id), Gdx.input.getY(id));
			scale = new Vector2((start.x / viewport.x) * 480, (start.y / viewport.y) * 800);
			Gdx.app.error("CAM", scale.toString());
		}
		return scale;
	}

}
