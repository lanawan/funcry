package com.amerticum.funcry.desktop;

import com.amerticum.funcry.Constants;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.amerticum.funcry.FunCry;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name", "aaaaa");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.SCREEN_WIDTH;
		config.height = Constants.SCREEN_HEIGHT;
		config.title = Constants.GAME_TITLE;
		new LwjglApplication(new FunCry(), config);
	}
}
