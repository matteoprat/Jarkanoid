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
	private SpriteBatch batch;
	private Texture img;
	private Texture bg;
	private Texture ballTexture;
	private Paddle paddle;
	private LevelMap level;
	private List<Ball> balls;
	private int lives = 3;
	private int currentLevel = 1;
	
	@Override
	public void create () {
		level = new LevelMap();
		level.populateLevel(currentLevel);
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
		if (balls.size() == 0) {
			lives--;
			if (lives > 0) {
				balls.add(new Ball());
				paddle = new Paddle();
			}
		}
		moveBalls();

		batch.begin();
		batch.draw(bg, GameSettings.MARGIN_LEFT.amount, GameSettings.MARGIN_BOTTOM.amount);
		batch.draw(img, 0, 0);
		for (Ball ball : balls) {
			batch.draw(ballTexture, ball.getX(), ball.getY());
		}
		for (Brick brick : level.getBrickList()) {
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
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			for (Ball ball : balls) {
				if (ball.getDirection() == 0) {
					ball.launch();
				}
			}
		} else if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			if(!paddle.moveLeft()) {
				return;
			}
			followPaddle('l');
		} else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (!paddle.moveRight()) {
				return;
			}
			followPaddle('r');
		}
	}

	private void followPaddle(char direction) {
		for (Ball ball : balls) {
			if (ball.getDirection() != 0) {
				continue;
			}
			if (direction == 'r') {
				ball.followPaddleRight();
				continue;
			}
			if (direction == 'l') {
				ball.followPaddleLeft();
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
			if (ball.getRect().overlaps(paddle.getRect())) {
				ball.bounceOnPaddle(paddle.getBounceAngle(ball.getX(), ball.getWidth()));
			}
			// check collisions with bricks
			Brick brickToRemove = null;
			for (Brick brick : level.getBrickList()) {
				if (ball.getRect().overlaps(brick.getRect())) {
					brickToRemove = brick;
					break;
				}
			}
			if (brickToRemove != null) {
				level.removeBrick(brickToRemove);
			}
		}
		for (Ball ball : toDelete) {
			balls.remove(ball);
		}
	}
}
