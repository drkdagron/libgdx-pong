package com.drkdagron.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by stemc on 2016-05-01.
 */
public class UIText extends Entity {

    protected String text;
    public String getText() { return text; }
    public void setText(String t)
    {
        text = t;
    }

    protected Vector2 origin;

    protected BitmapFont font;
    protected GlyphLayout glyph;

    public UIText(String fileName, String text, int size, Vector2 pos)
    {
        super();
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(fileName));
        FreeTypeFontGenerator.FreeTypeFontParameter par = new FreeTypeFontGenerator.FreeTypeFontParameter();
        par.size = size;
        font = gen.generateFont(par);
        gen.dispose();

        glyph = new GlyphLayout(font, text);
        this.text = text;
        glyph.setText(font, text);

        position = pos;
    }

    public void Draw(SpriteBatch sb)
    {
        font.draw(sb, text, position.x - (glyph.width / 2), position.y - (glyph.height / 2));
    }
}
