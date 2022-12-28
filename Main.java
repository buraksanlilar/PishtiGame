import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import java.util.Formatter;
public class Main {
    private Card[] board = new Card[52];
    private Card[] player = new Card[4];
    private Card[] computer = new Card[4];
    private int DealSize = 8;
    private int FirstDeal = 4;
    private int boardIndex = 0;
    private int gameTurn = 0;
    private int deckIndex = 0;
    private Card[] playerWon = new Card[52];
    private Card[] computerWon = new Card[52];
    private Card played;
    private int StopPoint = 0;
    private int a = 0;
    private int b = 0;
    private int playerpishti = 0;
    private int computerpishti = 0;
    public Card topCard;
    private int playerscore = 0;
    private int computerscore = 0;
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    Main game = new Main();
    game.start();
    
    }
    public void start() {
      System.out.println("please enter your name");
      String name = sc.nextLine();
      DeckOfCards deck1 = new DeckOfCards();
      deck1.shuffle();
      int cut = 0;
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
       Deal(deck1.getCards(),player,computer,board);
       System.out.println("Your hand: ");
       for(int i = 0 ; i < 4;i++) { 
       System.out.print(player[i]+", ");
       }
       System.out.println();
       if(StopPoint!=boardIndex) {
        System.out.println("Current board: "+ board[boardIndex-1]);
       } else {
        System.out.println("Current board: ");
       }
       
       System.out.println("gameturn: "+gameTurn);
       playerTurn();
       computerTurn(board, computer);
       gameTurn++;
      }
      if(gameTurn==48) {
        System.out.println("game is over");
        scorelist();
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
  
    board[boardIndex] = player[index];
    played = player[index];
    player[index] = null;
    boardIndex++;
    play(board, player, index);
    System.out.println("YOU PLAYED: "+played);
    gameTurn++;

      
      
      
    }
    public void Deal(Card[] deck,Card[] player,Card[] computer,Card[] board) {
   //Deals one by one to computer and player from deck
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
        //When BoardIndex-StopPoint == 1 it means there is only two cards on top of the board so it is available for pishti
      }
      StopPoint = boardIndex;
    }
   
    else if(played.getValue() == topCard.getValue() || played.getValue()=="J") {
      System.out.println("YOU WON THE ROUND");
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
  // Try to find pishti
  
  for (int i = 0; i < computer.length; i++) { //null oluyor kazanınca
    if(computer[i]!=null && topCard!=null) {
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
        break;
    }
} 
}
public void scorelist() {
  
  for(int i = 0 ; i<a;i++ ) {
   if(playerWon[i].toString()=="10D") {
   playerscore = playerscore + 3 ;
   }else if(playerWon[i].toString()=="2C") {
   playerscore = playerscore + 3;
   }else {
    playerscore++;
   }
  }
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
  if(playerWon.length>computerWon.length) {
  playerscore+=3;
  } else {
  computerscore+=3;
  }
}
public void addAndSaveScore(String name, int score) {
  // Read the scores from the file into an array
  Score[] scores = new Score[11];
  int numScores = 0;
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

  // Add the new score to the array
  scores[numScores] = new Score(name, score);
  numScores++;

  // Sort the array based on the scores
  for (int i = 0; i < numScores; i++) {
      for (int j = i + 1; j < numScores; j++) {
          if (scores[j].score > scores[i].score) {
              Score temp = scores[i];
              scores[i] = scores[j];
              scores[j] = temp;
          }
      }
  }

  // Write the top 10 scores to the file
  try (Formatter f = new Formatter(new File("scoreboard.txt"))) {
      for (int i = 0; i < 10 && i < numScores; i++) {
          f.format("%s:%d\n", scores[i].name, scores[i].score);
      }
  } catch (IOException e) {
      System.out.println("Error writing to scoreboard.txt: " + e.getMessage());
  }
}


}

