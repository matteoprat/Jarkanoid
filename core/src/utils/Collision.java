package utils;

import entities.Ball;
import entities.Brick;
import entities.Paddle;

/**
 * A collection of methods that control various collisions
 */
public class Collision {

    /**
     * Check if a specific Ball collides with a specific Brick horizontally.
     * If the collision happens then we move the ball outside the brick, to its left or its right.
     * @param ball the Ball object to check
     * @param brick the Brick object to check
     * @return true if the object have a collision horizontally
     */
    public boolean collideWithBrickHorizontal(Ball ball, Brick brick) {
        boolean collision = (brick.getLeft() > ball.getLeft() && brick.getLeft() <= ball.getRight()) ||
                            (brick.getRight() > ball.getLeft() && brick.getRight() <= ball.getRight());

        if (collision) {
            if (brick.getLeft() > ball.getLeft() && brick.getLeft() <= ball.getRight()) {
                ball.setLeft(brick.getLeft()-ball.getTexture().getWidth());
            } else {
                ball.setLeft(brick.getRight());
            }
        }

        return collision;
    }

    /**
     * Check if a specific Ball collides with a specific Brick vertically.
     * If the collision happens then we move the ball outside the brick, to its bottom or its top.
     * @param ball the Ball object to check
     * @param brick the Brick object to check
     * @return true if the object have a collision vertically
     */
    public boolean collideWithBrickVertical(Ball ball, Brick brick) {
        boolean collision = (brick.getBottom() > ball.getBottom() && brick.getBottom() < ball.getTop()) ||
                            (brick.getTop() < ball.getTop() && brick.getTop() > ball.getBottom());

        if (collision) {
            // Check if it collided with bottom
            if (brick.getBottom() > ball.getBottom() && brick.getBottom() < ball.getTop()) {
                ball.setBottom(brick.getBottom()-ball.getTexture().getHeight());
            } else {
                // if we are here it means it collided with top
                ball.setBottom(brick.getTop());
            }
        }
        return collision;
    }

    /**
     * Calculate the new angle when the ball collide with paddle.
     * @param ball a Ball object
     * @param paddle the Paddle object
     * @return new amount of angle
     */
    public float getBounceAngle(Ball ball, Paddle paddle) {

        int max_angle = 4;

        // The point of collision is the middle of ball position - the middle of paddle position
        float pointOfCollision = ball.getCenterX() - paddle.getCenterX();
        float mid = paddle.getCenterX() + Math.abs(pointOfCollision);
        float new_angle = max_angle * (mid / paddle.getTexture().getWidth());

        if (new_angle > max_angle) {
            new_angle = max_angle;
        }

        if (pointOfCollision <= 0) {
            new_angle = new_angle * -1;
        }

        moveBallOutsidePaddle(ball, paddle);

        return new_angle;
    }


    /**
     * This method move the ball outside paddle.
     * @param ball the Ball Object
     * @param paddle the Paddle Object
     */
    private void moveBallOutsidePaddle(Ball ball, Paddle paddle) {
        // Check if the ball is inside the paddle vertically (only check top)
        if (ball.getBottom() < paddle.getTop() && ball.getTop() > paddle.getTop()) {
            ball.setBottom(paddle.getTop());
        }
        // check if the ball is inside the paddle horizontally (left)
        if (ball.getLeft() < paddle.getLeft() && ball.getRight() > paddle.getLeft()) {
            ball.setLeft(paddle.getLeft()-ball.getTexture().getWidth());
        }
        // check if the ball is inside the paddle horizontally (right)
        if (ball.getRight() > paddle.getRight() && ball.getLeft() < paddle.getRight()) {
            ball.setLeft(paddle.getRight());
        }
    }

}
