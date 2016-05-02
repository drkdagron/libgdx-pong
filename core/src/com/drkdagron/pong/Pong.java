package com.drkdagron.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.drkdagron.engine.GameObj;
import com.drkdagron.engine.UIText;
import com.drkdagron.engine.screens.ScreenManager;

import javafx.scene.paint.Color;

public class Pong extends ApplicationAdapter {
	SpriteBatch batch;

    ScreenManager screen;

	@Override
	public void create () {
		batch = new SpriteBatch();

        screen = new ScreenManager();
        screen.addScreen(new GameScreen(screen));
        screen.setScreen("Game");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float elapsed = Gdx.graphics.getDeltaTime();
        screen.Update(elapsed);
        screen.Draw(batch);
	}
}
