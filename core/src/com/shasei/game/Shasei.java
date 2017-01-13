package com.shasei.game;

import java.util.Random;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shasei extends Game {

	private SpriteBatch batch;
	private Texture heroTexture;
	private float heroX, heroY;

	private Texture goalTexture;
	private float goalX, goalY;
	private Texture backgroungTexture;

	private Texture winMessage;
	private boolean win;

	public void create() {
		batch = new SpriteBatch();
		Random rnd = new Random();
		heroTexture = new Texture(Gdx.files.internal("assets/hero.png"));
		heroX = 20;
		heroY = 20;
		goalTexture = new Texture(Gdx.files.internal("assets/goal.png"));
		goalX = rnd.nextInt(800 - goalTexture.getWidth());
		goalY = rnd.nextInt(600 - goalTexture.getHeight());
		backgroungTexture = new Texture(Gdx.files.internal("assets/background.png"));
		winMessage = new Texture(Gdx.files.internal("assets/win.png"));
		win = false;
	}

	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		if (!win) {
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				heroX -= 100 * dt;
			if (Gdx.input.isKeyPressed(Keys.RIGHT))
				heroX += 100 * dt;
			if (Gdx.input.isKeyPressed(Keys.DOWN))
				heroY -= 100 * dt;
			if (Gdx.input.isKeyPressed(Keys.UP))
				heroY += 100 * dt;
			if (Gdx.input.isKeyPressed(Keys.X)) {
				float distanceX = (goalX + goalTexture.getWidth() / 2) - (heroX + heroTexture.getWidth() / 2);
				float distanceY = (goalY + goalTexture.getHeight() / 2) - (heroY + heroTexture.getHeight() / 2);
				if (Math.abs(distanceX) > Math.abs(distanceY)) {
					if (distanceX > 0)
						heroX += 250 * dt;
					else
						heroX -= 250 * dt;
				} else {
					if (distanceY > 0)
						heroY += 250 * dt;
					else
						heroY -= 250 * dt;
				}
			}
		}
		if ((heroX > goalX) && (heroX + heroTexture.getWidth() < goalX + goalTexture.getWidth()) && (heroY > goalY)
				&& (heroY + heroTexture.getHeight() < goalY + goalTexture.getHeight()))
			win = true;
		Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(backgroungTexture, 0, 0);
		batch.draw(goalTexture, goalX, goalY);
		batch.draw(heroTexture, heroX, heroY);
		if (win)
			batch.draw(winMessage, 800 / 2 - winMessage.getWidth() / 2, 600 / 2 - winMessage.getHeight() / 2);
		batch.end();
	}
}