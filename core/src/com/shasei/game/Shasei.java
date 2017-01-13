package com.shasei.game;

import java.util.Random;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Shasei extends Game {

	private SpriteBatch batch;
	private Sprite heroSprite;
	private Sprite goalSprite;
	private Sprite backgroundSprite;
	private Sprite winTextSprite;
	private boolean win;

	public void create() {
		batch = new SpriteBatch();
		Random rnd = new Random();

		heroSprite = new Sprite(new Texture(Gdx.files.internal("hero.png")));
		heroSprite.setPosition(20, 20);

		goalSprite = new Sprite(new Texture(Gdx.files.internal("goal.png")));
		goalSprite.setPosition(rnd.nextInt(800 - (int) goalSprite.getWidth()),
				rnd.nextInt(600 - (int) goalSprite.getWidth()));

		backgroundSprite = new Sprite(new Texture(Gdx.files.internal("background.png")));
		backgroundSprite.setPosition(0, 0);

		winTextSprite = new Sprite(new Texture(Gdx.files.internal("win.png")));
		winTextSprite.setPosition(800 / 2 - winTextSprite.getWidth() / 2, 600 / 2 - winTextSprite.getHeight() / 2);

		win = false;
	}

	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		if (!win) {
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				heroSprite.translateX(-100 * dt);
			if (Gdx.input.isKeyPressed(Keys.RIGHT))
				heroSprite.translateX(100 * dt);
			if (Gdx.input.isKeyPressed(Keys.DOWN))
				heroSprite.translateY(-100 * dt);
			if (Gdx.input.isKeyPressed(Keys.UP))
				heroSprite.translateY(100 * dt);
			
			if (Gdx.input.isKeyPressed(Keys.X)) {
				float distanceX = (goalSprite.getX() + goalSprite.getWidth() / 2)
						- (heroSprite.getX() + heroSprite.getWidth() / 2);
				float distanceY = (goalSprite.getY() + goalSprite.getHeight() / 2)
						- (heroSprite.getY() + heroSprite.getHeight() / 2);
				if (Math.abs(distanceX) > Math.abs(distanceY)) {
					if (distanceX > 0)
						heroSprite.translateX(250 * dt);
					else
						heroSprite.translateX(-250 * dt);
				} else {
					if (distanceY > 0)
						heroSprite.translateY(250 * dt);
					else
						heroSprite.translateY(-250 * dt);
				}
			}
		}
		
		Rectangle goalRectangle = goalSprite.getBoundingRectangle();
		Rectangle heroRectangle = heroSprite.getBoundingRectangle();

		if (goalRectangle.contains(heroRectangle))
			win = true;

		Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		backgroundSprite.draw(batch);
		goalSprite.draw(batch);
		heroSprite.draw(batch);
		if (win)
			winTextSprite.draw(batch);
		batch.end();
	}
}