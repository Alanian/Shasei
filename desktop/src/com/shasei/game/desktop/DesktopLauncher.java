package com.shasei.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.shasei.game.Shasei;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Shasei(), config);

		config.title = "Shasei [Alpha 0.08]";
		config.width = 800;
		config.height = 600;
		config.resizable = false;
	}
}
