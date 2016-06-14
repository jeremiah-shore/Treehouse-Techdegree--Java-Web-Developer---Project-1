import java.io.Console;

public class Game {
  /**** MEMBER VARIABLES ****/
  
  private Prompter mPrompter;
  private Jar mJar;
  private int mNumberOfPlayers;
  private String[] mPlayerNames;
  private String mWinningPlayer;
  private int mWinningScore;
  
  /**** CONSTRUCTORS ****/
  
  public Game(Prompter prompter) {
    mPrompter = prompter;
    mNumberOfPlayers = 1; //default minimum amount
    mWinningScore = 0;
  }
  
  /**** METHODS ****/
  
  public void play() {
    //GAME SETUP
    adminSetup();
    mPrompter.displayPlayerInterface();
    mPrompter.displayGameRules(mJar);
    obtainPlayerNames();
    
    //GAME BEGINS
    for (String player : mPlayerNames) {
      int playerGuess = 0;
      int guessCount = 0;
      mPrompter.clearConsoleOutput();
      mPrompter.promptToBeginGuessing(mJar, player);
      
      //core game loop
      while (playerGuess != mJar.getItemCount()) {
        playerGuess = mPrompter.promptForGuess(mJar);
        guessCount++;
      }
      
      //game is over for current player
      if (mWinningScore == 0 || guessCount < mWinningScore) { //if new best score
        mWinningScore = guessCount;
        mWinningPlayer = player;
      } else if (guessCount == mWinningScore) { //if there is a tie
        mWinningPlayer = mWinningPlayer + " and " + player;
      }
      mPrompter.displayGameResult(mJar, guessCount);
    }
    
    //if multiple players, the winner is presented once all have guessed correctly
    if (mPlayerNames.length > 1) { 
      mPrompter.displayGameWinner(mWinningPlayer, mWinningScore);
    }
  }
  
  /**** HELPER METHODS ****/

  public void adminSetup() { //Administrator configures game specifications and the game is prepared
    mPrompter.displayAdminInterface();
    mNumberOfPlayers = mPrompter.promptForNumberOfPlayers();
    mJar = new Jar(mPrompter.promptForItem(), mPrompter.promptForMaxCapacity());
    mJar.fill();
  }
  
  public void obtainPlayerNames() {
    mPlayerNames = new String[mNumberOfPlayers];
    for (int x = 0; x < mPlayerNames.length; x++) {
      mPlayerNames[x] = mPrompter.promptForPlayerName(x + 1); //+1 becuase otherwise it will start with PLAYER 0
    }
  }

}