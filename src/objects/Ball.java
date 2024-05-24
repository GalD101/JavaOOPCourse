package objects;

import animations.Velocity;
import biuoop.DrawSurface;

public class Ball {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;

    // constructors
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    public Ball(double centerX, double centerY, int r, java.awt.Color color) {
        this(new Point(centerX, centerY), r, color);
    }

    // accessors
    public int getX() {
        if (this.center == null) {
            return 0; // TODO: handle this
        }
        return (int) Math.round(this.center.getX());
    }

    public int getY() {
        if (this.center == null) {
            return 0; // TODO: handle this
        }
        return (int) Math.round(this.center.getY());
    }

    // return the radius of the ball
    public int getSize() {
        return this.r;
    }

    public java.awt.Color getColor() {
        return this.color;
    }

    public Velocity getVelocity() {
        return new Velocity(this.velocity.getDx(), this.velocity.getDy());
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }
}
