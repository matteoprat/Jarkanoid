package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import entities.Ball;
import entities.Brick;
import entities.Paddle;
import settings.GameSettings;
import utils.LevelMap;

import java.util.ArrayList;
import java.util.List;

public class Jarkanoid extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture bg;
	Texture ballTexture;
	Paddle paddle;
	LevelMap map;
	List<Ball> balls;
	int currentLevel = 1;
	
	@Override
	public void create () {
		map = new LevelMap();
		map.populateLevel(currentLevel);
		System.out.println("Total bricks: "+ map.getBrickCount());
		batch = new SpriteBatch();
		img = new Texture("graphics/background.png");
		bg = new Texture("graphics/bg_level0.png");
		ballTexture = new Texture("graphics/ball.png");
		paddle = new Paddle();
		balls = new ArrayList<>();
		balls.add(new Ball());
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		System.out.println(balls.size());
		moveBalls();

		batch.begin();
		batch.draw(bg, GameSettings.MARGIN_LEFT.amount, GameSettings.MARGIN_BOTTOM.amount);
		batch.draw(img, 0, 0);
		for (Ball ball : balls) {
			batch.draw(ballTexture, ball.getX(), ball.getY());
			System.out.println(ball.getX());
		}
		for (Brick brick : map.getBrickList()) {
			batch.draw(brick.getTexture(), brick.getXPos(), brick.getYPos());
		}
		batch.draw(paddle.getTexture(), paddle.getX(), paddle.getY());
		batch.end();
		keyListener();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	private void keyListener() {
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			paddle.moveLeft();
			for (Ball ball : balls) {
				if (ball.getDirection() == 0) {
					ball.centerOnPaddle(paddle.getX(), paddle.getWidth());
				}
			}
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			paddle.moveRight();
			for (Ball ball : balls) {
				if (ball.getDirection() == 0) {
					ball.centerOnPaddle(paddle.getX(), paddle.getWidth());
				}
			}
		}
	}

	private void moveBalls() {
		List<Ball> toDelete = new ArrayList<>();
		// check ball position
		for (Ball ball : balls) {
			if (ball.getDirection() == 0) {
				continue;
			}
			if (ball.move()) {
				toDelete.add(ball);
				continue;
			}
			// check collision with paddle

			// check collisions with bricks
		}
		for (Ball ball : toDelete) {
			balls.remove(ball);
		}
	}
}
