import biuoop.DrawSurface;
import biuoop.GUI;
import objects.Line;
import objects.Point;

import java.awt.*;
import java.util.Random;

public class SimpleGuiExample {
    private static final int POINT_RADIUS = 3;
    private Random rand = new Random(); // create a random-number generator

    public Line generateRandLine() {
        double x1 = rand.nextDouble(400) + 1;
        double y1 = rand.nextDouble(300) + 1;
        double x2 = rand.nextDouble(400) + 1;
        double y2 = rand.nextDouble(300) + 1;
        return new Line(x1, y1, x2, y2);
    }

    public void drawLine(Line l, DrawSurface d) {
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }

    public void drawRandomCircles() {
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
//        for (int i = 0; i < 10; ++i) {
//            int x = rand.nextInt(400) + 1; // get integer in range 1-400
//            int y = rand.nextInt(300) + 1; // get integer in range 1-300
//            int r = 5 * (rand.nextInt(4) + 1); // get integer in 5,10,15,20
//            d.setColor(Color.RED);
//            d.drawLine(x, y, x + 70, y + 40);
//            d.fillCircle(x, y, r);
//        }

        Line[] lines = new Line[10];
        // 1.Create 10 random black lines:
        for (int i = 0; i < 10; ++i) {
            lines[i] = generateRandLine();
            drawLine(lines[i], d);
        }

        // 2. Color the middle point of each line in blue:
        for (int i = 0; i < 10; ++i) {
            d.setColor(Color.BLUE);
            Point midpoint = lines[i].middle();
            d.fillCircle((int) (midpoint.getX()), (int) (midpoint.getY()), POINT_RADIUS);
        }

        // 3. Draw the intersection points between the lines:
        for (int i = 0; i < 10; ++i) {
            for (int j = i + 1; j < 10; ++j) {
                Point intersection = lines[i].intersectionWith(lines[j]);
                if (intersection != null) {
                    d.setColor(Color.RED);
                    d.fillCircle((int) (intersection.getX()), (int) (intersection.getY()), 3);
                }
            }
        }

        // 4. Highlight the segments of lines that create triangles in green:+
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                for (int k = j+1;k<10;k++) {
                    // if a line intersects with two other lines, it creates a triangle
                    if ((lines[i].isIntersecting(lines[j], lines[k]))
                            && (lines[j].isIntersecting(lines[i], lines[k])) &&
                            (lines[k].isIntersecting(lines[i], lines[j]))) {
                        d.setColor(Color.GREEN);

                        Point intersectionStart1 = lines[i].intersectionWith(lines[j]);
                        Point intersectionEnd1 = lines[i].intersectionWith(lines[k]);
                        Line triangleLine1 = new Line(intersectionStart1, intersectionEnd1);
                        drawLine(triangleLine1, d);

                        Point intersectionStart2 = lines[j].intersectionWith(lines[i]);
                        Point intersectionEnd2 = lines[j].intersectionWith(lines[k]);
                        Line triangleLine2 = new Line(intersectionStart2, intersectionEnd2);
                        drawLine(triangleLine2, d);

                        Point intersectionStart3 = lines[k].intersectionWith(lines[i]);
                        Point intersectionEnd3 = lines[k].intersectionWith(lines[j]);
                        Line triangleLine3 = new Line(intersectionStart3, intersectionEnd3);
                        drawLine(triangleLine3, d);
                    }
                }
            }
        }
        gui.show(d);
    }

    public static void main(String[] args) {
        SimpleGuiExample example = new SimpleGuiExample();
        example.drawRandomCircles();
    }
}