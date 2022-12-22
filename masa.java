public class masa {
    private Card[] board = new Card[52];
    private Card[] player = new Card[4];
    private Card[] computer = new Card[4];
    private int boardIndex = 0;
    private int gameTurn = 0;
    private int deckIndex = 0;
    private int playerIndex = 0;
    private int computerIndex = 0;
    public void Deal(Card[] deck,Card[] player,Card[] computer,Card[] board) {
   for (int i = 0 ; i<player.length;i++) {
    player[i] = deck[deckIndex];
    deckIndex++;
    computer[i] = deck[deckIndex];
    deckIndex++;
   }
  while (boardIndex<4) {
  board[boardIndex] = deck[deckIndex];
  deckIndex++;
  boardIndex++;
  }
    }
    public void PlayCard(Card[] deck,Card[] player,Card[] board,int index) {
    board[boardIndex] = player[index];
    player[index] = null;
    gameTurn++;
    boardIndex++;
    if(gameTurn%8==0) {
      for(int i = 0; i < player.length;i++) {
      player[i] = deck[deckIndex];
      deckIndex++;
      computer[i] = deck[deckIndex];
      deckIndex++;
      }
    }
    }

    
}

