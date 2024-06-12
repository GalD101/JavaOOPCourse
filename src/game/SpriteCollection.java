package game;

import biuoop.DrawSurface;
import objects.Sprite;

public class SpriteCollection {
    private java.util.List<Sprite> spriteList;

    public SpriteCollection() {
        this.spriteList = new java.util.ArrayList<Sprite>();
    }

    public void addSprite(Sprite s) {
        if (s == null) {
            return;
        }
        this.spriteList.add(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite s : this.spriteList) {
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.spriteList) {
            s.drawOn(d);
        }
    }
}
