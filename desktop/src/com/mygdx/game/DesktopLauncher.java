package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import settings.GameSettings;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(GameSettings.FPS.amount);
		config.setIdleFPS(GameSettings.FPS.amount);
		config.useVsync(true);
		config.setWindowedMode(GameSettings.SCR_WIDTH.amount, GameSettings.SCR_HEIGHT.amount);
		config.setTitle("JArkanoid");
		new Lwjgl3Application(new Jarkanoid(), config);
	}
}
