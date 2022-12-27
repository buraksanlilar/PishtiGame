import java.util.InputMismatchException;
import java.util.Scanner;
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
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    Main game = new Main();
    game.start();
    }
    public void start() {
      DeckOfCards deck1 = new DeckOfCards();
      deck1.shuffle();
      int cut = 0;
      while(cut < 1 || cut >52) {
        System.out.println("Please choose a number between 1-52 to cut the desk");
        try {
          cut = sc.nextInt();
        } catch (InputMismatchException e) {
          System.out.println("Invalid input");
          sc.nextLine();
          continue;
        }
      }
      
      while(gameTurn<48) {
       Deal(deck1.getCards(),player,computer,board);
       for(int i = 0 ; i < 4;i++) { //bu kısmı oyuncularda yap her oyuncu oynadığında bu gözüksün mantıken
       System.out.println(player[i]);
       }
       System.out.println("Current board: ");
       for (int i = StopPoint; i < boardIndex; i++) {
       System.out.println(board[i]);
       }

       System.out.println("\n"+"gameturn: "+gameTurn);
       playerTurn();
       computerTurn(board, computer);
       gameTurn++;
      }
      if(gameTurn==48) {
        System.out.println("game over");
        for(int i = 0 ; i<a;i++) {
        System.out.println(playerWon[i]);
        }
        
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
     if (played.getValue() == topCard.getValue() && (boardIndex-StopPoint==1)  ) { 
      for(int i = StopPoint; i<boardIndex;i++) {
        playerWon[a] = board[i];
        board[i]= null;
        a++;
        playerpishti++;
        System.out.println("PİİSSHHTİİ");
        //When BoardIndex-StopPoint == 1 it means there is only two cards on top of the board so it is available for pishti
      }
      StopPoint = boardIndex;
    }
   
    else if(played.getValue() == topCard.getValue() || played.getValue()=="J") {
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
  
  for (int i = 0; i < computer.length; i++) {
    if(computer[i]!=null && topCard!=null) {
    if (computer[i].getValue() == topCard.getValue() && (boardIndex-StopPoint)==1 ) {
        board[boardIndex] = computer[i];
        computer[i] = null;
        for(int j = StopPoint;j<=boardIndex;j++) {
       computerWon[b] = board[j];
       board[j] = null;
       b++;
        }
        boardIndex++;
        computerpishti++;
        System.out.println("PİSHTİİİİ");
        return;
    }
  }
}
  // Try to find a card in the computer's hand with the same value as the top card
  for (int i = 0; i < computer.length; i++) {
      if(computer[i]!=null && topCard != null) {
      if (computer[i].getValue() == topCard.getValue()) {
          board[boardIndex] = computer[i];
          computer[i] = null;
          for(int k=StopPoint;k<=boardIndex;k++) {
          computerWon[b]= board[k];
          board[k]=null;
          b++;
          }
          boardIndex++;
          StopPoint = boardIndex;
          return;
      }
    }
  }

  // If no matching card is found, try to find a card with a value of "J"
  for (int i = 0; i < computer.length; i++) {
     if(computer[i]!=null && topCard!=null) {
      if (computer[i].getValue() == "J") {
          board[boardIndex] = computer[i];
          computer[i] = null;
          for(int k=StopPoint;k<=boardIndex;k++) {
            computerWon[b]= board[k];
            board[k]=null;
            b++;
            }
          boardIndex++;
          StopPoint = boardIndex;
          return;
      }
    }
  }

  // If no matching card is found, choose a random card and play it
  while (true) {
    int random = (int)(Math.random() * 4);
    if (computer[random] != null) {
        board[boardIndex] = computer[random];
        computer[random] = null;
        boardIndex++;
        break;
    }
}
}
}

