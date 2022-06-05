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
import utils.Collision;
import utils.LevelMap;

import java.util.ArrayList;
import java.util.List;

public class Jarkanoid extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Texture bg;
	private Paddle paddle;
	private LevelMap level;
	private List<Ball> balls;
	private Texture ballImg;
	private int lives = 3;
	private int currentLevel = 1;
	private int score = 0;
	private final Collision collisionHandler = new Collision();
	
	@Override
	public void create () {
		level = new LevelMap();
		level.populateLevel(currentLevel);
		batch = new SpriteBatch();
		img = new Texture("graphics/background.png");
		bg = new Texture("graphics/bg_level0.png");
		ballImg = new Texture("graphics/ball.png");
		paddle = new Paddle();
		balls = new ArrayList<>();
		balls.add(new Ball(ballImg));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		if (balls.size() == 0) {
			lives--;
			if (lives > 0) {
				balls.add(new Ball(ballImg));
				paddle.startPaddle();
			}
		}
		moveBalls();

		batch.begin();
		batch.draw(bg, GameSettings.MARGIN_LEFT.amount, GameSettings.MARGIN_BOTTOM.amount);
		batch.draw(img, 0, 0);
		for (Ball ball : balls) {
			batch.draw(ball.getTexture(), ball.getLeft(), ball.getBottom());
		}
		for (Brick brick : level.getBrickList()) {
			batch.draw(brick.getTexture(), brick.getLeft(), brick.getBottom());
		}
		batch.draw(paddle.getTexture(), paddle.getLeft(), paddle.getBottom());
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
				ball.bounceOnPaddle(collisionHandler.getBounceAngle(ball, paddle));
			}
			// check collisions with bricks
			Brick brickToRemove = null;
			for (Brick brick : level.getBrickList()) {
				// If there is no collision, continue to next brick
				if (!ball.getRect().overlaps(brick.getRect())) {
					continue;
				}

				// check where the ball collide
				boolean collideX = collisionHandler.collideWithBrickHorizontal(ball, brick);
				boolean collideY = collisionHandler.collideWithBrickVertical(ball, brick);

				ball.applyCollision(collideX, collideY);

				brickToRemove = brick;
				break;
			}
			if (brickToRemove != null) {
				brickToRemove.decreaseLife();
				if (brickToRemove.getLife() == 0) {
					//score += brickToRemove.getScore();
					level.removeBrick(brickToRemove);
				}
			}
		}
		for (Ball ball : toDelete) {
			balls.remove(ball);
		}
	}

}
