import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import java.util.Formatter;
public class Main {
  //The card array for where the cards will play
    private Card[] board = new Card[52]; 
    private Card[] player = new Card[4]; 
    private Card[] computer = new Card[4];
    //When the game ends player and computer cards will list up here
    private Card[] playerWon = new Card[52];
    private Card[] computerWon = new Card[52];
    //This is for the deal cards every 8 gameturn to players
    private int DealSize = 8;
    //First deal of the game
    private int FirstDeal = 4;
    //This is for to list board arrays upcoming card.
    private int boardIndex = 0;
    //This defines Game Turn 
    private int gameTurn = 0;
    //Cards are listed from the deck and it shows that.
    private int deckIndex = 0; 
    //This is to compare players played card with top of the board card.
    private Card played;
    //StopPoint is for when someone wins the tour. Won cards should listed somewhere and this integer defines startpoint of it.
    private int StopPoint = 0;
    //start point for playerWon and computerWon arrays.
    private int a = 0;
    private int b = 0;
    //pishti counter for player and computer
    private int playerpishti = 0;
    private int computerpishti = 0;
    //This shows the topcard of the board
    public Card topCard;
    //Scores are listed in these
    private int playerscore = 0;
    private int computerscore = 0;
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    Main game = new Main();
    System.out.println("WELCOME TO PISHTI");
    game.start();
    
    }
    public void start() {
      System.out.println("please enter your name");
      String name = sc.nextLine();
      DeckOfCards deck1 = new DeckOfCards();
      deck1.shuffle();
      
      int cut = 0;
      //User should enter a appropriate value it makes that.
      //If user enters a inappropriate value like string it will make you choose a number again.
      while(cut < 1 || cut >52) {
        System.out.println("Please choose a number between 1-52 to cut the desk");
        try {
          cut = sc.nextInt();
        } catch (InputMismatchException e) {
          System.out.println("Invalid input You need to enter a integer number betwwen 1-52 ");
          sc.nextLine();
          continue;
        }
      
    }
      while(gameTurn<48) {
      //The game continues until gameTurn turns into 48.
       Deal(deck1.getCards(),player,computer,board);
       System.out.println("Your hand: ");
       for(int i = 0 ; i < 4;i++) { 
       System.out.print(player[i]+", ");
       }
       System.out.println();
       //This is for to not print null to the top of the board
       if(StopPoint!=boardIndex) {
        System.out.println("Current board: "+ board[boardIndex-1]);
       } else {
        System.out.println("Current board: ");
       }
       System.out.println("gameturn: "+gameTurn);
       //the game will be played by turns
       playerTurn();
       computerTurn(board, computer);
       gameTurn++;
      }
      if(gameTurn==48) {
        System.out.println("game is over");
        
        scorelist(); 
        //scores are calculated.
        if(computerscore > playerscore) {
        System.out.println("COMPUTER HAS WON THE GAME !!! YOU LOST  HERE İS THE COMPUTER'S SCORE: "+computerscore + "\n" + "YOUR SCORE: " + playerscore);
        }else {
          System.out.println("YOU WON THE GAME HURRAY! HERE İS YOUR SCORE: "+playerscore);
        }
        addAndSaveScore(name,playerscore);
 
        
      }
      
    }
    public void playerTurn() {
      int index = -1;
      //This will ask you the select a integer number between 0-3
      //If you enter invalid input like a string it will give you a message and make you enter again
    while (index < 0 || index >= player.length || player[index] == null) {
        System.out.println("It is your turn please select your card between (0-3)");
        try {
            index = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();
            continue;
        }
        if (index < 0 || index >= player.length || player[index] == null) {
            System.out.println("This card has been played before or is not in your hand.");
        }
    }
    //If the index you choosed is between 0-3 
    //It makes it play to top of the deck and empties your index card
  
    board[boardIndex] = player[index];
    played = player[index];
    player[index] = null;
    boardIndex++;
    play(board, player, index);
    System.out.println("YOU PLAYED: "+played);
    gameTurn++;

      
      
      
    }
    public void Deal(Card[] deck,Card[] player,Card[] computer,Card[] board) {
   //Deals one by one to computer and player from deck when gameturn is dividable by 8
   if(gameTurn % DealSize == 0) {
    for (int i = 0 ; i<player.length;i++) { 
    player[i] = deck[deckIndex];
    deckIndex++;
    computer[i] = deck[deckIndex];
    deckIndex++;
   }
   }
   //Deals to the board in order
  while (boardIndex < FirstDeal) {
  board[boardIndex] = deck[deckIndex];
  deckIndex++;
  boardIndex++;
  
  //The gameTurn defines the order for players to play and cards to deal from the deck.
  }
    }
    public void play(Card[] board,Card[]player,int index) {
      topCard = board[boardIndex-2];
      //This method checks Value of the topCard versus played card if it is equal to each other it makes the listed cards go to the playerWin[] and list there.
     if(topCard!=null) {
     if (played.getValue() == topCard.getValue() && (boardIndex-StopPoint)==2  ) { 
      System.out.println("YOU MADE PİSTHİ CONGRATS BRO");
      for(int i = StopPoint; i<boardIndex;i++) {
        playerWon[a] = board[i];
        board[i]= null;
        a++;
        playerpishti++;
        //This is special statement for pishti
        //When BoardIndex-StopPoint == 1 it means there is only two cards on top of the board so it is available for pishti
        //PlayerWon[] list the cards won
      }
      StopPoint = boardIndex;
      //This makes next board index start from the where cards are gone
    }
   
    else if(played.getValue() == topCard.getValue() || played.getValue()=="J") {
      System.out.println("YOU WON THE ROUND");
      //It checks your played card and compare it to the top of the board
    for(int i = StopPoint; i<boardIndex;i++) {
    playerWon[a] = board[i];
    board[i]= null;
    a++;
    }
    StopPoint = boardIndex;
    } }
   else  {
    
    return;
    } 
  
  }

public void computerTurn(Card [] board,Card[] computer) { 
  topCard = board[boardIndex-1];
  // First try to find if there is pishti
  for (int i = 0; i < computer.length; i++) { 
    //Check the top card is not null and your hand is not null
    if(computer[i]!=null && topCard!=null) {
      //If the one of your cards equal to the top of the board and if there is one card top of the board play it and make pishti
    if (computer[i].getValue() == topCard.getValue() && (boardIndex-StopPoint)==1 ) {
        board[boardIndex] = computer[i];
        System.out.println("COMPUTER PLAYED: "+computer[i]);
        computer[i] = null;
        for(int j = StopPoint;j<=boardIndex;j++) {
       computerWon[b] = board[j];
       board[j] = null;
       b++;
        } 
        boardIndex++;
        StopPoint= boardIndex;
        computerpishti++;
        System.out.println("COMPUTER MADE PİSHTİİİİ");
        return;
    }
  }
}
  // Try to find a card in the computer's hand with the same value as the top card
  for (int i = 0; i < computer.length; i++) { 
      if(computer[i]!=null && topCard != null) {
      if (computer[i].getValue() == topCard.getValue()) {
          board[boardIndex] = computer[i];
          System.out.println("COMPUTER PLAYED: "+computer[i]);
          computer[i] = null;
          for(int k=StopPoint;k<=boardIndex;k++) {
          computerWon[b]= board[k];
          board[k]=null;
          b++;
          }
          boardIndex++;
          StopPoint = boardIndex;
          System.out.println("COMPUTER WON THE ROUND");
          return;
      }
    }
  }

  // If no matching card is found, try to find a card with a value of "J"
  for (int i = 0; i < computer.length; i++) {
     if(computer[i]!=null && topCard!=null) {
      if (computer[i].getValue() == "J") {
          board[boardIndex] = computer[i];
          System.out.println("COMPUTER PLAYED: "+computer[i]);
          computer[i] = null;
          for(int k=StopPoint;k<=boardIndex;k++) {
            computerWon[b]= board[k];
            board[k]=null;
            b++;
            }
            
          boardIndex++;
          StopPoint = boardIndex;
          System.out.println("COMPUTER WON THE ROUND");
          return;
      }
    }
  }

  // If no matching card is found, choose a random card and play it
  while (true) {
    int random = (int)(Math.random() * 4);
    if (computer[random] != null) {
        board[boardIndex] = computer[random];
        System.out.println("COMPUTER PLAYED: "+computer[random]);
        computer[random] = null;
        boardIndex++;
        //The cards are not won so StopPoint is not equal to boardIndex.
        break;
    }
} 
}
public void scorelist() {
  //check the playerWon array and turn them into string to compare special cards which points are 3
  //Normal cards are 1 points so if you cannot find special card add 1 points to playerscore for normal cards.
  for(int i = 0 ; i<a;i++ ) {
   if(playerWon[i].toString()=="10D") {
   playerscore = playerscore + 3 ;
   }else if(playerWon[i].toString()=="2C") {
   playerscore = playerscore + 3;
   }else {
    playerscore++;
   }
  }
  //Calculate the pishti score and add it to the playerscore if player had no pishtis you add 0 so it means nothing.
  playerscore=(playerpishti*10)+playerscore;

for(int i = 0 ; i<b;i++ ) {
   if(computerWon[i].toString()=="10D") {
   computerscore+=3;
   }else if(computerWon[i].toString()=="2C") {
   computerscore+=3;
   }else {
    computerscore++;
   }
  }
  computerscore=(computerpishti*10)+computerscore;
  //if one player's won cards are more than other one.
  //The player who has more cards gets extra 3 points.
  if(playerWon.length>computerWon.length) {
  playerscore+=3;
  } else {
  computerscore+=3;
  }
}
public void addAndSaveScore(String name, int score) {
  //Read the scores from the file into an array.
  // create scores array to compare playersscore with other entries.
  Score[] scores = new Score[11];
  int numScores = 0;
  //Create a new file and read it.
  try (Scanner sc = new Scanner(new File("scoreboard.txt"))) {
      while (sc.hasNextLine() && numScores < scores.length) {
          String line = sc.nextLine();
          String[] parts = line.split(":");
          String n = parts[0];
          int s = Integer.parseInt(parts[1]);
          scores[numScores] = new Score(n, s);
          numScores++;
      }
  } catch (IOException e) {
      System.out.println("Error reading from scoreboard.txt: " + e.getMessage());
  }

  //Add the new score to the array
  scores[numScores] = new Score(name, score);
  numScores++;

  //Sort the array based on the scores
  for (int i = 0; i < numScores; i++) {
      for (int j = i + 1; j < numScores; j++) {
          if (scores[j].score > scores[i].score) {
              Score temp = scores[i];
              scores[i] = scores[j];
              scores[j] = temp;
          }
      }
  }

  //Write the top 10 scores to the file
  try (Formatter f = new Formatter(new File("scoreboard.txt"))) {
      for (int i = 0; i < 10 && i < numScores; i++) {
          f.format("%s:%d\n", scores[i].name, scores[i].score);
      }
  } catch (IOException e) {
      System.out.println("Error writing to scoreboard.txt: " + e.getMessage());
  }
}


}

