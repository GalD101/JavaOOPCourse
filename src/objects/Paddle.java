package objects;

import animations.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Game;
import utils.RandomSingleton;

import java.awt.Color;

/**
 * The Collidable interface represents objects that can participate in collisions.
 * Each Collidable object has a "collision shape" represented as a Rectangle object.
 * When a collision occurs, the Collidable object is notified, and it returns the new velocity expected after the hit.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle collisionRectangle;
    private Color color;
    private double speed;

    /**
     * Constructs a new Paddle object.
     *
     * <p>This constructor initializes the Paddle with a keyboard sensor, a collision rectangle, and a color.
     * The keyboard sensor is used to control the movement of the Paddle.
     * The collision rectangle defines the size and position of the Paddle.
     * The color is used when drawing the Paddle on the screen.
     * If the color is null, the Paddle is initialized with a default color of white.
     * The speed of the Paddle is randomly set to either 5 or -5.
     *
     * @param keyboard           The keyboard sensor used to control the Paddle
     * @param collisionRectangle The collision rectangle defining the size and position of the Paddle
     * @param color              The color of the Paddle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle collisionRectangle, Color color) {
        this.keyboard = keyboard;
        this.collisionRectangle = new Rectangle(collisionRectangle.getUpperLeft(), collisionRectangle.getLowerRight());
        this.color = color == null ? Color.WHITE : color;
        // TODO: Use constants from constants class instead of hard coded numbers
        this.speed = RandomSingleton.getInstance().nextBoolean() ? 5 : -5;
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
    public void timePassed() {
        // Check if the left or right keys are pressed and move the paddle accordingly use KeyboardSensor
        boolean moveRight = this.speed > 0;
        boolean moveLeft = this.speed < 0;
        // Check if there is a collision with the right/left block and invert the velocity
        // TODO: use constants instead of plain numbers
        // The paddle moves in a circular fashion,
        // so that when it reaches the edge of the screen it moves to the other side
        if (this.getCollisionRectangle().getLowerRight().getX() >= 800) {
            moveLeft();
            return;
        } else if (this.getCollisionRectangle().getUpperLeft().getX() <= 0) {
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
            return;
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
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawRectangle(
                (int) this.collisionRectangle.getUpperLeft().getX(),
                (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(), (int) this.collisionRectangle.getHeight());
        d.fillRectangle(
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
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getLowerRight());
    }

    // Collidable
    // TODO: Check weird behavior when ball collides with side of paddle
    // Answer: The issue is that the ball can collide from inside the paddle
    // Why does the ball gets inside the paddle?
    // It is supposed to bounce when it hits the left or right side of the paddle
    // Right now it doesnt do that - I need to figure out why
    // It happens because the lines are moving.
    // Idea check if the collision point is between the two lines
    // Problem - I cant since it uses another function that checks if the line is intersecting with the point :(
    // New idea - Make it so that only collision from outside are possible
    // LAZY IDEA BECAUSE NOTHING ELSE WORKS - JUST MAKE THE PADDLE VERY THIN AND IT WON'T CAUSE ANY ISSUES
    // TODO: Unfortunately, the lazy idea won't work because they want me to create a paddle with width so that sucks

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
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // should be the same logic as block
        // TODO: This is too similar to block so change it accordingly
        if (collisionPoint == null || currentVelocity == null) {
            return currentVelocity;
        }

        boolean isOnTop = this.getCollisionRectangle().getTopLine().isPointOnLine(collisionPoint);
        boolean isOnBottom = this.getCollisionRectangle().getBottomLine().isPointOnLine(collisionPoint);
        boolean isOnLeft = this.getCollisionRectangle().getLeftLine().isPointOnLine(collisionPoint);
        boolean isOnRight = this.getCollisionRectangle().getRightLine().isPointOnLine(collisionPoint);

        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();

        if (isOnLeft || isOnRight) {
            newDx = -1 * newDx;
        }
        if (isOnTop || isOnBottom) {
            newDy = -1 * newDy;
        }
        return new Velocity(newDx, newDy);
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
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
