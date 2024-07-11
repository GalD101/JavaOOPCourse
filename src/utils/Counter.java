package utils;

public class Counter {
    private int value;

    public Counter() {
        this.value = 0;
    }

    public Counter(int value) {
        this.value = value;
    }

    // add number to current count.
    public void increase(int number) {
        this.value += number;
    }

    // subtract number from current count.
    public void decrease(int number) {
        this.value -= number;
    }

    // get current count.
    public int getValue() {
        return this.value;
    }
}
