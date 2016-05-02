package com.drkdagron.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.drkdagron.engine.GameObj;
import com.drkdagron.engine.UIText;
import com.drkdagron.engine.screens.BaseScreen;
import com.drkdagron.engine.screens.ScreenManager;

/**
 * Created by stemc on 2016-05-01.
 */
public class GameScreen extends BaseScreen {

    OrthographicCamera gameCam;
    Player[] players;
    Ball ball;
    GameObj centerLine;
    Texture scoreBall;

    UIText font;
    UIText instuct;
    boolean single = false;

    boolean active = false;
    boolean gameOver = false;
    boolean touched = false;

    public GameScreen(ScreenManager man)
    {
        super(man, "Game");
    }

    public void Init()
    {
        gameCam = new OrthographicCamera(480, 800);
        players = new Player[2];
        players[0] = new Player("oriPaddle.png", new Vector2(480, 800));
        players[0].setPosition(new Vector2(0, 330));
        players[1] = new Player("oriPaddle.png", new Vector2(480, 800));
        players[1].setPosition(new Vector2(0, -330));

        Pixmap tmp = new Pixmap(480,10, Pixmap.Format.RGBA8888);
        tmp.setColor(com.badlogic.gdx.graphics.Color.WHITE);
        tmp.fillRectangle(0, 0, 480, 10);
        centerLine = new GameObj(tmp);

        scoreBall = new Texture(Gdx.files.internal("oriBallScore.png"));

        //font = new UIText("kenvector.ttf", "Touch screen to start", 24, new Vector2(0, -200));
        instuct = new UIText("kenvector.ttf", "Touch above line for\nSingle Player\n\n\n\n\nTouch below line for\nMulti Player", 24, new Vector2(0, 210));

        ball = new Ball("oriBall.png", new Vector2(0,0));
    }

    @Override
    public void Draw(SpriteBatch sb) {

        sb.setProjectionMatrix(gameCam.combined);
        sb.begin();
        centerLine.Draw(sb);

        for (int i= 0; i < players.length; i++)
        {
            players[i].Draw(sb);
            for (int j = 0; j < players[i].Score; j++)
            {
                if (players[i].isTop())
                    sb.draw(scoreBall, (-220 + (scoreBall.getWidth() * j)) - (scoreBall.getWidth() / 2), 35 - (scoreBall.getHeight() / 2));
                else
                    sb.draw(scoreBall, (-220 + (scoreBall.getWidth() * j)) - (scoreBall.getWidth() / 2), -35 - (scoreBall.getHeight() / 2));
            }
        }


        if (active == false)
        {
            //font.Draw(sb);
            instuct.Draw(sb);
        }
        else
        {
            ball.Draw(sb);
        }

        sb.end();
    }

    @Override
    public void Update(float elapsedTime) {

        if (Gdx.input.isTouched() && active == false && touched == false) {
            if (getTouch(0).y <= 400)
            {
                //single
                single = true;
            }
            else
            {
                //multi
                single = false;
            }

            active = true;
            ResetGame();
        }
        if (active) {
            Gdx.app.debug("UPDATE", "GAME");

            Vector2 t0 = getTouch(0);
            Vector2 t1 = getTouch(1);
            if (Gdx.input.isTouched()) {
                touched = true;
                if (Gdx.input.isTouched(0)) {
                    if (t0.y < 400) {
                        players[0].setPosition(new Vector2(t0.x - 240, players[0].getPosition().y));
                    } else if (t0.y > 400) {
                        players[1].setPosition(new Vector2(t0.x - 240, players[1].getPosition().y));
                    }
                }
                if (single == false) {
                    if (Gdx.input.isTouched(1)) {
                        if (t1.y < 400) {
                            players[0].setPosition(new Vector2(t1.x - 200, players[0].getPosition().y));
                        } else if (t1.y > 400) {
                            players[1].setPosition(new Vector2(t1.x - 200, players[1].getPosition().y));
                        }
                    }
                }
                else
                {
                    if (ball.getPosition().x < players[0].getPosition().x)
                        players[0].setPosition(new Vector2(players[0].getPosition().x - 5, players[0].getPosition().y));
                    else
                        players[0].setPosition(new Vector2(players[0].getPosition().x + 5, players[0].getPosition().y));
                }
            }

            for (int i = 0; i < players.length; i++) {
                players[i].Update(elapsedTime);

                if (players[i].Score == 5)
                {
                    active = false;
                }
            }
            ball.Update(elapsedTime, players);
        }
        if (!Gdx.input.isTouched())
            touched = false;

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

    public void ResetGame()
    {
        for (int i= 0; i < players.length; i++)
        {
            players[i].Score = 0;
            if (players[i].isTop())
            {
                players[i].setPosition(new Vector2(0, 330));
            }
            else
            {
                players[i].setPosition(new Vector2(0, -330));
            }
        }

        ball.resetBall();
    }
}
