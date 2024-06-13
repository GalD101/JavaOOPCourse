import game.Game;

/**
 * The Ass3Game class serves as the entry point for the application.
 * It contains the main method which creates a new Game instance, initializes it, and starts the game loop.
 */
public class Ass3Game {

    /**
     * The main entry point for the application.
     * This method is responsible for creating a new Game instance, initializing it, and starting the game loop.
     *
     * @param args The command-line arguments. This is currently unused.
     */
    public static void main(String[] args) {
        // Please check out GameSettings.java to modify the game settings
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
