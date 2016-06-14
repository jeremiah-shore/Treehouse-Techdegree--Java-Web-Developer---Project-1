public class GameLauncher {
  
  public static void main(String[] args) {
    Prompter prompter = new Prompter();
    Game game = new Game(prompter);
    
    do {
      game.play();
    } while (prompter.promptForPlayAgain());  
  }
  
}