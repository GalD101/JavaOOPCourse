package objects;

import animations.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Game;
import utils.RandomSingleton;

import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle collisionRectangle;
    private Color color;
    private double speed;

    public Paddle(KeyboardSensor keyboard, Rectangle collisionRectangle, Color color) {
        this.keyboard = keyboard;
        this.collisionRectangle = new Rectangle(collisionRectangle.getUpperLeft(), collisionRectangle.getLowerRight());
        this.color = color == null ? Color.WHITE : color;
        this.speed = RandomSingleton.getInstance().nextBoolean() ? 5 : -5;
    }

    public void setCollisionRectangle(Rectangle collisionRectangle) {
        this.collisionRectangle = new Rectangle(collisionRectangle.getUpperLeft(), collisionRectangle.getLowerRight());
    }

    public void moveLeft() {
        this.speed = -1 * Math.abs(this.speed);
        move();
    }

    public void moveRight() {
        this.speed = Math.abs(this.speed);
        move();
    }

    public void move() {
        double newUpperLeft = this.getCollisionRectangle().getUpperLeft().getX() + this.speed;
        double newLowerRight = this.getCollisionRectangle().getLowerRight().getX() + this.speed;
        this.setCollisionRectangle(new Rectangle(new Point(newUpperLeft, this.getCollisionRectangle().getUpperLeft().getY()),
                new Point(newLowerRight, this.getCollisionRectangle().getLowerRight().getY())));
    }

    // Sprite
    public void timePassed() {
        // Check if the left or right keys are pressed and move the paddle accordingly use KeyboardSensor
        boolean moveRight = this.speed > 0;
        boolean moveLeft = this.speed < 0;
        // Check if there is a collision with the right/left block and invert the velocity
        // TODO: use constants instead of plain numbers
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
        // The paddle moves in a circular fashion, so that when it reaches the edge of the screen it moves to the other side
    }

    // Sprite
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawRectangle((int) this.collisionRectangle.getUpperLeft().getX(), (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(), (int) this.collisionRectangle.getHeight());
        d.fillRectangle((int) this.collisionRectangle.getUpperLeft().getX(), (int) this.collisionRectangle.getUpperLeft().getY(),
                (int) this.collisionRectangle.getWidth(), (int) this.collisionRectangle.getHeight());
    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.collisionRectangle.getUpperLeft(), this.collisionRectangle.getLowerRight());
    }

    // Collidable
    // TODO: Check weird behavior when ball collides with side of paddle
    // Answer: The issue is that the ball can collide from inside the paddle
    // Why does the ball gets inside the paddle? It is supposed to bounce when it hits the left or right side of the paddle
    // Right now it doesnt do that - I need to figure out why
    // It happens because the lines are moving.
    // Idea check if the collision point is between the two lines
    // Problem - I cant since it uses another function that checks if the line is intersecting with the point :(
    // New idea - Make it so that only collision from outside are possible
    // LAZY IDEA BECAUSE NOTHING ELSE WORKS - JUST MAKE THE PADDLE VERY THIN AND IT WON'T CAUSE ANY ISSUES
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // should be the same logic as block
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

    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
