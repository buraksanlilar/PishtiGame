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
    private Card[] playerWin;
    private Card played;
    private int StopPoint = 0;
    private int a = 0;
    private int playerpishti = 0;
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
    Main game = new Main();
    game.start();
    }
    public void start() {
      DeckOfCards deck1 = new DeckOfCards();
      deck1.shuffle();
      System.out.println("Please choose a number between 1-52 to cut the desk");
      int cut = sc.nextInt();
      deck1.cut(cut);
     
      
      while(gameTurn<48) {
       Deal(deck1.getCards(),player,computer,board);
       for(int i = 0 ; i < 4;i++) {
       System.out.println(player[i]);
       }
       System.out.println("\n"+board[boardIndex-1]);
       System.out.println(gameTurn);
       playTurn();
       gameTurn++;
       computerplay(board, computer);
       gameTurn++;
      }
    }
    public void playTurn() {
      System.out.println("It is your turn please select your card between (0-3): ");
      int index = sc.nextInt();
      PlayForPlayer(player, board, index);
    }
    public void Deal(Card[] deck,Card[] player,Card[] computer,Card[] board) {
   //Deals one by one to computer and player from deck
   if(gameTurn % DealSize == 0) {
    for (int i = 0 ; i<player.length;i++) { //while (gameTurn%DealSize==0) {} ın içine yazcaksın bunu çıkışında
    player[i] = deck[deckIndex];
    deckIndex++;
    computer[i] = deck[deckIndex];
    deckIndex++;
   }
   }
   //Deals to the board in order
  while (boardIndex<FirstDeal) {
  board[boardIndex] = deck[deckIndex];
  deckIndex++;
  boardIndex++;
  
  //The gameTurn defines the order for players to play and cards to deal from the deck.
  }
    }
    public void PlayForPlayer(Card[] player,Card[] board,int index) {
      // dont forget to cath with try cath phrases
      // let the user choose a card from player array
    board[boardIndex] = player[index];
    played = player[index];
    player[index] = null;
    boardIndex++;
    }
    public void play(Card[] board,Card[]player,int index) {
      Card topCard = board[boardIndex-1];
      //This method checks Value of the topCard versus played card if it is equal to each other it makes the listed cards go to the playerWin[] and list there.
     if (played.getValue() == topCard.getValue() && (boardIndex-StopPoint==1)  ) { 
      for(int i = StopPoint; i<boardIndex;i++) {
        
        playerWin[a] = board[i];
        StopPoint = boardIndex;
        board[i]= null;
        a++;
        playerpishti++;
        System.out.println("PİİSSHHTİİ");
        //When BoardIndex-StopPoint == 1 it means there is only two cards on top of the board so it is available for pishti
      }
    if(played.getValue() == topCard.getValue() || played.getValue()=="J") {
    for(int i = StopPoint; i<boardIndex;i++) {
    playerWin[a] = board[i];
    StopPoint = boardIndex;
    board[i]= null;
    a++;
    }
    } else if (played.getValue() != topCard.getValue() || played.getValue() !="J") {
    board[boardIndex] = player[index];
    boardIndex++;
    } 

  }
}
public void computerplay(Card [] board,Card[] computer) { 
  Card topCard = board[boardIndex-1];
  // Try to find pishti
  
  for (int i = 0; i < computer.length; i++) {
    if(computer[i]!=null) {
    if (computer[i].getValue() == topCard.getValue() && (boardIndex-StopPoint)==1) {
        board[boardIndex] = computer[i];
        computer[i] = null;
        boardIndex++;
        System.out.println("PİSHTİİİİ");
        return;
    }
  }
}
  // Try to find a card in the computer's hand with the same value as the top card
  for (int i = 0; i < computer.length; i++) {
      if(computer[i]!=null) {
      if (computer[i].getValue() == topCard.getValue()) {
          board[boardIndex] = computer[i];
          computer[i] = null;
          boardIndex++;
          return;
      }
    }
  }

  // If no matching card is found, try to find a card with a value of "J"
  for (int i = 0; i < computer.length; i++) {
     if(computer[i]!=null) {
      if (computer[i].getValue() == "J") {
          board[boardIndex] = computer[i];
          computer[i] = null;
          boardIndex++;
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

