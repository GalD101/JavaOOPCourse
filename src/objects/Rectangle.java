package objects;

import java.awt.*;
import java.util.Random;

import utils.RandomSingleton;

// Create a rectangle class that is defined by the upper left point and the lower right point.
public class Rectangle {
    private Point upperLeft;
    private Point lowerRight;
    private Line topLine;
    private Line bottomLine;
    private Line leftLine;
    private Line rightLine;
    private Color color;

    // Constructs a rectangle object with the given upper left and lower right points.
    public Rectangle(Point upperLeft, Point lowerRight, Color color) {
        if (upperLeft == null || lowerRight == null) {
            throw new IllegalArgumentException("The upper left and lower right points must not be null.");
        }
        if (upperLeft.getX() > lowerRight.getX() || upperLeft.getY() > lowerRight.getY()) {
            throw new IllegalArgumentException("The upper left point must be above and to the left of the lower right point.");
        }
        if (upperLeft.equals(lowerRight)) {
            throw new IllegalArgumentException("The upper left and lower right points must not be the same.");
        }
        if (upperLeft.getX() == lowerRight.getX() || upperLeft.getY() == lowerRight.getY()) {
            throw new IllegalArgumentException("The rectangle must have a positive area.");
        }
        if (upperLeft.getX() < 0 || upperLeft.getY() < 0 || lowerRight.getX() < 0 || lowerRight.getY() < 0) {
            throw new IllegalArgumentException("The rectangle must have non-negative coordinates.");
        }
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.lowerRight = new Point(lowerRight.getX(), lowerRight.getY());
        this.color = color == null ? Color.BLACK : color;
    }

    public Rectangle(Point upperLeft, Point lowerRight) {
        this(upperLeft, lowerRight, null);
    }

    // TODO: LOOK AT THIS!
//    public Rectangle(Line line1, Line line2) {
//        if (line1 == null || line2 == null) {
//            throw new IllegalArgumentException("The lines must not be null.");
//        }
//        if (line1.isIntersecting(line2)) {
//            throw new IllegalArgumentException("The lines must not intersect.");
//        }
//
//        if (!line1.isParallel(line2)) {
//            throw new IllegalArgumentException("The lines must be parallel.");
//        }
//
    // Maybe to do later
//        if (line1.isVertical() && line2.isVertical()) {
//            // These are the left and right lines
//            if (line1.middle().getX())) {
//                throw new IllegalArgumentException("The lines must not be the same.");
//            }
//        } else {
//            // These are the top and bottom lines
//        }
//    }

    // Returns the width of the rectangle.
    public double getWidth() {
        return this.lowerRight.getX() - this.upperLeft.getX();
    }

    // Returns the height of the rectangle.
    public double getHeight() {
        return this.lowerRight.getY() - this.upperLeft.getY();
    }

    // Returns the upper left point of the rectangle.
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    // Returns the lower right point of the rectangle.
    public Point getLowerRight() {
        return new Point(this.lowerRight.getX(), this.lowerRight.getY());
    }

    // Returns the area of the rectangle.
    public double getArea() {
        return this.getWidth() * this.getHeight();
    }

    // Returns the perimeter of the rectangle.
    public double getPerimeter() {
        return 2 * (this.getWidth() + this.getHeight());
    }

    public Line getTopLine() {
        return new Line(this.upperLeft, new Point(this.lowerRight.getX(), this.upperLeft.getY()));
    }

    public Line getBottomLine() {
        return new Line(new Point(this.upperLeft.getX(), this.lowerRight.getY()), this.lowerRight);
    }

    public Line getLeftLine() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX(), this.lowerRight.getY()));
    }

    public Line getRightLine() {
        return new Line(new Point(this.lowerRight.getX(), this.upperLeft.getY()), this.lowerRight);
    }

    // Checks if the given point is inside the rectangle.
    public boolean isInside(Point point) {
        return point.getX() > this.upperLeft.getX() && point.getX() < this.lowerRight.getX()
                && point.getY() > this.upperLeft.getY() && point.getY() < this.lowerRight.getY();
    }

    // Returns the color of the rectangle.
    public Color getColor() {
        if (this.color == null) {
            return Color.BLACK;
        }
        return this.color;
    }

    // Sets the color of the rectangle.
    public void setColor(Color color) {
        this.color = color;
    }

    // Generate random coordinates inside the rectangle
//    public Point generateRandomPointInside() {
//        Random rand = RandomSingleton.getInstance();
//        double randomX = rand.nextDouble() * this.getWidth() + this.upperLeft.getX();
//        double randomY = rand.nextDouble() * this.getHeight() + this.upperLeft.getY();
//        return new Point(randomX, randomY);
//    }

    // Generate random coordinates inside the rectangle
    // with a given boundary off the walls of the rectangle.
    public Point generateRandomPointInside(double boundary) {
        Random rand = RandomSingleton.getInstance();
        double randomX = rand.nextDouble() * (this.getWidth() - 2 * boundary) + this.upperLeft.getX() + boundary;
        double randomY = rand.nextDouble() * (this.getHeight() - 2 * boundary) + this.upperLeft.getY() + boundary;
        return new Point(randomX, randomY);
    }

    private Rectangle completeRectangle(Rectangle exclude1, Rectangle exclude2) {
        // This will create a new rectangle that will be
        // the rectangle that completes the two rectangles together
        Point rectUpperLeft = new Point(Math.min(exclude1.getUpperLeft().getX(), exclude2.getUpperLeft().getX()),
                Math.min(exclude1.getUpperLeft().getY(), exclude2.getUpperLeft().getY()));
        Point rectLowerRight = new Point(Math.max(exclude1.getLowerRight().getX(), exclude2.getLowerRight().getX()),
                Math.max(exclude1.getLowerRight().getY(), exclude2.getLowerRight().getY()));
        return new Rectangle(rectUpperLeft, rectLowerRight, null);
    }

    public Point generateRandomPointInside(Rectangle exclude, double boundary) {
        Random rand = RandomSingleton.getInstance();
        int randRectangle = 1 + rand.nextInt(3);
        switch (randRectangle) {
            case 1:
                // Create rectangle 1:
                Point upperLeftRect1 = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY());
                Point lowerRightRect1 = new Point(this.getLowerRight().getX(), exclude.getUpperLeft().getY());
                Rectangle rect1 = new Rectangle(upperLeftRect1, lowerRightRect1);
                return rect1.generateRandomPointInside(boundary);
            case 2:
                // Create rectangle 2:
                Point upperLeftRect2 = new Point(this.getUpperLeft().getX(), exclude.getUpperLeft().getY());
                Point lowerRightRect2 = new Point(exclude.getUpperLeft().getX(), exclude.getLowerRight().getY());
                Rectangle rect2 = new Rectangle(upperLeftRect2, lowerRightRect2);
                return rect2.generateRandomPointInside(boundary);
            case 3:
                // Create rectangle 3:
                Point upperLeftRect3 = new Point(exclude.getLowerRight().getX(), exclude.getUpperLeft().getY());
                Point lowerRightRect3 = new Point(this.getLowerRight().getX(), exclude.getLowerRight().getY());
                Rectangle rect3 = new Rectangle(upperLeftRect3, lowerRightRect3);
                return rect3.generateRandomPointInside(boundary);
//            case 4:
//                // Create rectangle 4:
//                Point upperLeftRect4 = new Point(this.getUpperLeft().getX(), exclude.getLowerRight().getY());
//                Point lowerRightRect4 = new Point(this.getLowerRight().getX(), this.getLowerRight().getY());
//                try {
//                    Rectangle rect4 = new Rectangle(upperLeftRect4, lowerRightRect4);
//                } catch (IllegalArgumentException e) {
//                    return
//                }
//                return rect4.generateRandomPointInside(boundary);
            default:
                return this.generateRandomPointInside(boundary);
        }
    }

    // Generate random coordinates inside the rectangle
    // but exclude the given param rectangles.
    public Point generateRandomPointInside(Rectangle exclude1, Rectangle exclude2, double boundary) {
        // Step 1: Complete the rectangles - check.
        // Step 2: Compute the two extra rectangles - check.
        // Step 3: Choose between creating a random point on either of the rectangles or outside the rectangle - check.
        // Step 4: Implement a function to create a point outside one rectangle - check.

        Random rand = RandomSingleton.getInstance();

        Rectangle completeRectangle = completeRectangle(exclude1, exclude2);
        int randOption = rand.nextInt(3);
        switch (randOption) {
            case 0:
                // Create a point inside one rectangle (Step2):
                Point rectUpperLeft1 = new Point(exclude1.getUpperLeft().getX(), exclude1.getUpperLeft().getY());
                Point rectLowerRight1 = new Point(exclude2.getLowerRight().getX(), exclude2.getUpperLeft().getY());
                Rectangle subdomain1 = new Rectangle(rectUpperLeft1, rectLowerRight1);
                return subdomain1.generateRandomPointInside(boundary);
            case 1:
                // Create a point inside the other rectangle (Step 2):
                Point rectUpperLeft2 = new Point(exclude1.getUpperLeft().getX(), exclude1.getLowerRight().getY());
                Point rectLowerRight2 = new Point(exclude2.getUpperLeft().getX(), exclude2.getLowerRight().getY());
                Rectangle subdomain2 = new Rectangle(rectUpperLeft2, rectLowerRight2);
                return subdomain2.generateRandomPointInside(boundary);
            case 2:
                // Create a point inside this screen without the completed rectangle
                return this.generateRandomPointInside(completeRectangle, boundary);
            default:
                return this.generateRandomPointInside(boundary);
        }
    }
}
