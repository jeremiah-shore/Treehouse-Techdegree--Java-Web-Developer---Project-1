import java.io.Console;

public class Prompter {
  private Console mConsole = System.console();
  
  public void clearConsoleOutput() {
    System.out.print("\033[H\033[2J"); // This may only work from the Treehouse workspace console, please let me know if this causes issues
  }
  
  //INPUT PROMPTS
  public boolean promptForPlayAgain() {
    String input = mConsole.readLine("\nDo you want to play another game? Y/N ").toUpperCase();
    if (input.charAt(0) == 'Y') {
      return true;
    } else if (input.charAt(0) == 'N') {
      return false;
    } else {
      System.out.println("Umm, I don't quite know what that means. Please try again. ");
      return promptForPlayAgain();
    }
  }
  
  public String promptForItem() {
    return mConsole.readLine("What is the name of the items going in the jar? ");
  }
  
  public int promptForMaxCapacity() {
    return Integer.parseInt(mConsole.readLine("How many can fit in the jar? "));
    //todo: validate that the number is a valid integer that is greater than one
  }
  
  public int promptForNumberOfPlayers() {
    return Integer.parseInt(mConsole.readLine("How many players are guessing? "));
    //todo: validate min of 1 and possibly place a reasonable limit
  }
  
  public String promptForPlayerName(int playerNumber) {
    return mConsole.readLine("\nPLAYER %d, please enter your name. ", playerNumber);
  }
  
  public void promptToBeginGuessing(Jar jar, String playerName) {
    System.out.printf("\nRemember, you should be guessing between 1 to %d %s. 1 and %d are both valid guesses.",
                      jar.getMaxCapacity(), jar.getItemName(), jar.getMaxCapacity());
    mConsole.readLine("\n%s, are you ready to begin guessing? (press ENTER to continue)\n\n", playerName);
  }
  
  public int promptForGuess(Jar jar) {
    //receive guess from player and validate it
    int playerGuess = Integer.parseInt(mConsole.readLine("Guess: "));
    if (playerGuess < 1 || playerGuess > jar.getMaxCapacity()) {
      System.out.printf("Your guess was not valid because it was outside of the possible range of 1 to %d %s. This counts as a guess; please try again.", 
                        jar.getMaxCapacity(), jar.getItemName());
      return playerGuess; //the guess was not in the valid range; return early because feedback on low/high is not needed
    }
    //provide player feedback on their guess
    if (playerGuess > jar.getItemCount()) {
      System.out.println("Your guess was a bit high...");
    } else if (playerGuess < jar.getItemCount()) {
      System.out.println("Your guess was a bit low...");
    }
    return playerGuess;
  }
  
  //DISPLAY PROMPTS
  public void displayAdminInterface() {
    System.out.println("ADMINISTRATOR");
    System.out.println("=============");
  }
  
  public void displayPlayerInterface() {
    System.out.println("PLAYER");
    System.out.println("=======");
  }
  
  public void displayGameRules(Jar jar) {
    //Player is prompted with game rules
    System.out.printf("Your goal is to guess how many %s are in the jar.\n", jar.getItemName());
    System.out.printf("The jar will never be empty; your guess should be between 1 and %d.\n", jar.getMaxCapacity());
    System.out.printf("1 and %d are both valid guesses.\n", jar.getMaxCapacity());
  }
  
  public void displayGameResult(Jar jar, int guessCount) {
    mConsole.readLine("\nCongratulations! You guessed that there are %d %s in the jar! It took you %d guess(es) to get it right.\n\n", 
                     jar.getItemCount(), jar.getItemName(), guessCount);
  }
  
  public void displayGameWinner(String winningPlayer, int winningScore) {
    System.out.println("\n\nWell played everyone! The guessing is complete.");
    System.out.printf("The winner is %s, and it took them a total of %d guess(es) to win.", winningPlayer, winningScore);
  }
}