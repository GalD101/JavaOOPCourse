import biuoop.DrawSurface;
import biuoop.GUI;
import objects.Line;
import objects.Point;
import utils.RandomSingleton;

import java.awt.Color;
import java.util.Random;

/**
 * This class is responsible for creating an abstract art drawing using random lines and circles.
 * It uses the biuoop GUI library to create a window and draw on it.
 */
public class AbstractArtDrawing {
    private static final int POINT_RADIUS = 3;
    private Random rand = RandomSingleton.getInstance(); // create a random-number generator
    private final int width = 400;
    private final int height = 300;

    /**
     * Generates a random line within the bounds of the window.
     *
     * @return A Line object representing the random line.
     */
    public Line generateRandLine() {
        double x1 = rand.nextDouble(width) + 1;
        double y1 = rand.nextDouble(height) + 1;
        double x2 = rand.nextDouble(width) + 1;
        double y2 = rand.nextDouble(height) + 1;
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Draws a given line on a given DrawSurface.
     *
     * @param l The line to be drawn.
     * @param d The DrawSurface on which to draw the line.
     */
    public void drawLine(Line l, DrawSurface d) {
        d.drawLine((int) Math.round(l.start().getX()), (int) Math.round(l.start().getY()),
                (int) Math.round(l.end().getX()), (int) Math.round(l.end().getY()));
    }

    /**
     * Draws random lines on a GUI window.
     * The lines are drawn in black, the midpoints of the lines are drawn in blue,
     * the intersection points of the lines are drawn in red, and the segments of lines
     * that create triangles are highlighted in green.
     */
    public void drawRandomLines() {
        // Create a window with the title "Random lines"
        // which is WIDTH pixels wide and HEIGHT pixels high.
        GUI gui = new GUI("Random lines", width, height);
        DrawSurface d = gui.getDrawSurface();

        Line[] lines = new Line[10];

        // 1. Create 10 random black lines:
        for (int i = 0; i < lines.length; ++i) {
            lines[i] = generateRandLine();
            drawLine(lines[i], d);
        }

        // 2. Color the middle point of each line in blue:
        for (int i = 0; i < lines.length; ++i) {
            d.setColor(Color.BLUE);
            Point midpoint = lines[i].middle();
            d.fillCircle((int) Math.round(midpoint.getX()), (int) Math.round(midpoint.getY()), POINT_RADIUS);
        }

        // 3. Draw the intersection points between the lines:
        for (int i = 0; i < lines.length; ++i) {
            for (int j = i + 1; j < lines.length; ++j) {
                Point intersection = lines[i].intersectionWith(lines[j]);
                if (intersection != null) {
                    d.setColor(Color.RED);
                    d.fillCircle((int) Math.round(intersection.getX()),
                            (int) Math.round(intersection.getY()), POINT_RADIUS);
                }
            }
        }

        // 4. Highlight the segments of lines that create triangles in green:
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                for (int k = j + 1; k < lines.length; k++) {
                    // if a line intersects with two other lines, it creates a triangle
                    if ((lines[i].isIntersecting(lines[j], lines[k]))
                            && (lines[j].isIntersecting(lines[i], lines[k]))
                            && (lines[k].isIntersecting(lines[i], lines[j]))) {
                        d.setColor(Color.GREEN);

                        Line triangleLine1 = createLineFromIntersections(lines[i], lines[j], lines[k]);
                        drawLine(triangleLine1, d);

                        Line triangleLine2 = createLineFromIntersections(lines[j], lines[i], lines[k]);
                        drawLine(triangleLine2, d);

                        Line triangleLine3 = createLineFromIntersections(lines[k], lines[i], lines[j]);
                        drawLine(triangleLine3, d);
                    }
                }
            }
        }
        gui.show(d);
    }

    /**
     * Creates a line from the intersection points of three given lines.
     *
     * @param line1 The first line.
     * @param line2 The second line.
     * @param line3 The third line.
     * @return A Line object representing the line created from the intersection points.
     */
    private Line createLineFromIntersections(Line line1, Line line2, Line line3) {
        Point intersectionStart = line1.intersectionWith(line2);
        Point intersectionEnd = line1.intersectionWith(line3);
        return new Line(intersectionStart, intersectionEnd);
    }

    /**
     * The main method that creates an instance of AbstractArtDrawing and calls the drawRandomCircles method.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}
