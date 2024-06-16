// 322558297 Gal Dali

package objects;

import animations.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Game;
import utils.RandomSingleton;

import static game.GameSettings.IS_FUN_MODE;
import static game.GameSettings.PADDLE_REGION_ONE;
import static game.GameSettings.PADDLE_REGION_TWO;
import static game.GameSettings.PADDLE_REGION_THREE;
import static game.GameSettings.PADDLE_REGION_FOUR;
import static game.GameSettings.PADDLE_REGION_FIVE;
import static game.GameSettings.PADDLE_SPEED;
import static game.GameSettings.PADDLE_FILL_COLOR;
import static game.GameSettings.PADDLE_BORDER_COLOR;
import static game.GameSettings.SCREEN_WIDTH;
import static game.GameSettings.MAIN_BLOCKS_HEIGHT;
import static game.GameSettings.BALL_SPEED;

/**
 * The Collidable interface represents objects that can participate in collisions.
 * Each Collidable object has a "collision shape" represented as a Rectangle object.
 * When a collision occurs, the Collidable object is notified, and it returns the new velocity expected after the hit.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle collisionRectangle;
    private double speed;

    /**
     * Constructs a new Paddle object.
     *
     * <p>This constructor initializes the Paddle with a keyboard sensor, a collision rectangle.
     * The keyboard sensor is used to control the movement of the Paddle.
     * The collision rectangle defines the size and position of the Paddle.
     * The speed of the Paddle is randomly set to either positive (right) or negative (left).
     *
     * @param keyboard           The keyboard sensor used to control the Paddle
     * @param collisionRectangle The collision rectangle defining the size and position of the Paddle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle collisionRectangle) {
        this.keyboard = keyboard;
        this.collisionRectangle = new Rectangle(collisionRectangle.getUpperLeft(), collisionRectangle.getLowerRight());
        this.speed = RandomSingleton.getInstance().nextBoolean() ? PADDLE_SPEED : -PADDLE_SPEED;
    }

    /**
     * Sets the collision rectangle of this Paddle object.
     *
     * <p>This method takes a Rectangle object as an argument
     * and creates a new Rectangle with the same upper left and lower right points.
     * The new Rectangle is then set as the collision rectangle of this Paddle.
     *
     * @param collisionRectangle The Rectangle object to be set as the collision rectangle of this Paddle
     */
    public void setCollisionRectangle(Rectangle collisionRectangle) {
        this.collisionRectangle = new Rectangle(collisionRectangle.getUpperLeft(), collisionRectangle.getLowerRight());
    }

    /**
     * Moves the Paddle object to the left based on its current speed.
     *
     * <p>This method sets the speed of the Paddle to the negative of its absolute value,
     * ensuring that the Paddle moves to the left,
     * and then calls the move method to update the Paddle's position.
     */
    public void moveLeft() {
        this.speed = -1 * Math.abs(this.speed);
        move();
    }

    /**
     * Moves the Paddle object to the right based on its current speed.
     *
     * <p>This method sets the speed of the Paddle to its absolute value, ensuring that the Paddle moves to the right,
     * and then calls the move method to update the Paddle's position.
     */
    public void moveRight() {
        this.speed = Math.abs(this.speed);
        move();
    }

    /**
     * Moves the Paddle object horizontally based on its current speed.
     *
     * <p>This method calculates the new position of the Paddle by adding the current speed
     * to the x-coordinates of the upper left and lower right points of the Paddle's collision rectangle.
     * The Paddle's collision rectangle is then updated to the new position.
     */
    private void move() {
        double newUpperLeft = this.getCollisionRectangle().getUpperLeft().getX() + this.speed;
        double newLowerRight = this.getCollisionRectangle().getLowerRight().getX() + this.speed;
        this.setCollisionRectangle(new Rectangle(
                new Point(newUpperLeft, this.getCollisionRectangle().getUpperLeft().getY()),
                new Point(newLowerRight, this.getCollisionRectangle().getLowerRight().getY())));
    }

    /**
     * Updates the state of the Paddle object based on the time that has passed.
     *
     * <p>This method is part of the Sprite interface and is called each time unit to update the state of the Paddle.
     * It checks if the left or right keys are pressed and moves the Paddle accordingly.
     * It also checks if the Paddle has collided with the right or left edge of the screen
     * and inverts the Paddle's velocity if a collision has occurred.
     * If the Paddle's speed is positive, it moves the Paddle to the right.
     * If the Paddle's speed is negative, it moves the Paddle to the left.
     * If the Paddle reaches the edge of the screen, it moves to the other side in a circular fashion.
     */
    @Override
    public void timePassed() {
        // Check if the left or right keys are pressed and move the paddle accordingly use KeyboardSensor
        boolean moveRight = this.speed > 0;
        boolean moveLeft = this.speed < 0;

        // Check if there is a collision with the right/left block and invert the velocity
        if (this.getCollisionRectangle().getLowerRight().getX() >= SCREEN_WIDTH - MAIN_BLOCKS_HEIGHT) {
            moveLeft();
            return;
        }
        if (this.getCollisionRectangle().getUpperLeft().getX() <= MAIN_BLOCKS_HEIGHT) {
            moveRight();
            return;
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
            return;
        }
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            return;
        }
        if (moveRight) {
            moveRight();
            return;
        }
        if (moveLeft) {
            moveLeft();
        }
    }

    /**
     * Draws this Paddle object on the given DrawSurface.
     *
     * <p>This method sets the color of the DrawSurface to the color of the Paddle.
     * It then draws and fills a rectangle on the DrawSurface
     * with the same position and size as the Paddle's collision rectangle.
     * The rectangle is drawn with its upper left corner at the upper left point of the Paddle's collision rectangle,
     * and its width and height are the same as the width and height of the Paddle's collision rectangle.
     *
     * @param d The DrawSurface on which this Paddle is to be drawn
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(PADDLE_FILL_COLOR);
        d.fillRectangle(
                (int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(), (int) this.collisionRectangle.getHeight());
        d.setColor(PADDLE_BORDER_COLOR);
        d.drawRectangle(
                (int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(), (int) this.collisionRectangle.getHeight());
    }

    /**
     * Returns a new Rectangle object that represents the collision rectangle of this Paddle object.
     *
     * <p>This method creates a new Rectangle object
     * with the same upper left and lower right points as the Paddle's collision rectangle.
     * This ensures that the returned Rectangle is a copy of the Paddle's collision rectangle,
     * and changes to the returned Rectangle will not affect the Paddle's collision rectangle.
     *
     * @return A new Rectangle object that represents the collision rectangle of this Paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getLowerRight());
    }

    /**
     * Handles the logic for when a collision occurs with this Paddle object.
     *
     * <p>This method takes in the point of collision
     * and the current velocity of the object that collided with the Paddle.
     * It then determines which side of the Paddle the collision occurred on and adjusts the velocity accordingly.
     * If the collision occurred on the left or right side of the Paddle, the x-component of the velocity is reversed.
     * If the collision occurred on the top or bottom of the Paddle, the y-component of the velocity is reversed.
     *
     * @param collisionPoint  The point at which the collision occurred
     * @param currentVelocity The current velocity of the object that collided with the Paddle
     * @return The new velocity of the object after the collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return currentVelocity;
        }

        boolean isOnTop = this.getCollisionRectangle().getTopLine().isPointOnLine(collisionPoint);
        boolean isOnBottom = this.getCollisionRectangle().getBottomLine().isPointOnLine(collisionPoint);
        boolean isOnLeft = this.getCollisionRectangle().getLeftLine().isPointOnLine(collisionPoint);
        boolean isOnRight = this.getCollisionRectangle().getRightLine().isPointOnLine(collisionPoint);

        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        // This (left and right side) never works and the ball tunnels inside the paddle
        if (isOnLeft) {
            newDx = -1 * newDx;
        }

        if (isOnRight) {
            newDx = -1 * newDx;
        }

        if (isOnBottom) {
            newDy = -1 * newDy;
        }

        if (isOnTop) {
            if (IS_FUN_MODE) {
                int collisionRegion = getCollisionRegion(collisionPoint.getX());
                Velocity newVelocity;
                switch (collisionRegion) {
                    case 1:
                        // Ball should bounce back with an angle of
                        // 300 degrees relative to the upwards direction with clockwise rotation (a lot to the left)
                        newVelocity = Velocity.fromAngleAndSpeed(300 - 90, BALL_SPEED);
                        break;
                    case 2:
                        // Ball should bounce back with an angle of
                        // 330 degrees relative to the upwards direction with clockwise rotation (a little to the left)
                        newVelocity = Velocity.fromAngleAndSpeed(330 - 90, BALL_SPEED);
                        break;
                    case 3:
                        // Ball should simply bounce back as expected
                        newDy = -1 * newDy;
                        newVelocity = new Velocity(newDx, newDy);
                        break;
                    case 4:
                        // Ball should bounce back with an angle of
                        // 30 degrees relative to the upwards direction with clockwise rotation (a little to the right)
                        newVelocity = Velocity.fromAngleAndSpeed(30 - 90, BALL_SPEED);
                        break;
                    case 5:
                        // Ball should bounce back with an angle of
                        // 60 degrees relative to the upwards direction with clockwise rotation (a lot to the right)
                        newVelocity = Velocity.fromAngleAndSpeed(60 - 90, BALL_SPEED);
                        break;
                    default:
                        newDy = -1 * newDy;
                        newVelocity = new Velocity(newDx, newDy);
                        break;
                }
                return newVelocity;
            }
            newDy = -1 * newDy;
        }
        return new Velocity(newDx, newDy);
    }

    private int getCollisionRegion(double horizontalPosition) {
        double paddleLeftSideHorizontalValue = this.getCollisionRectangle().getUpperLeft().getX();
        if (paddleLeftSideHorizontalValue <= horizontalPosition
                && horizontalPosition < paddleLeftSideHorizontalValue + PADDLE_REGION_ONE) {
            return 1;
        }
        if (paddleLeftSideHorizontalValue + PADDLE_REGION_ONE <= horizontalPosition
                && horizontalPosition < paddleLeftSideHorizontalValue + PADDLE_REGION_TWO) {
            return 2;
        }
        if (paddleLeftSideHorizontalValue + PADDLE_REGION_TWO <= horizontalPosition
                && horizontalPosition < paddleLeftSideHorizontalValue + PADDLE_REGION_THREE) {
            return 3;
        }
        if (paddleLeftSideHorizontalValue + PADDLE_REGION_THREE <= horizontalPosition
                && horizontalPosition < paddleLeftSideHorizontalValue + PADDLE_REGION_FOUR) {
            return 4;
        }
        if (paddleLeftSideHorizontalValue + PADDLE_REGION_FOUR <= horizontalPosition
                && horizontalPosition <= paddleLeftSideHorizontalValue + PADDLE_REGION_FIVE) {
            return 5;
        }
        return 0;
    }

    /**
     * Adds this Paddle object to the given Game object.
     *
     * <p>This method adds the Paddle object to the game as both a Sprite and a Collidable.
     * As a Sprite, the Paddle can be drawn on the screen and its timePassed method will be called each time unit.
     * As a Collidable, the Paddle can participate in collisions with other Collidable objects.
     *
     * @param game The Game object to which this Paddle is to be added
     */
    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
