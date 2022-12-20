public class masa {
    private Card[] board = new Card[52];
    private Card[] player = new Card[4];
    private Card[] computer = new Card[4];
    private int count = 0;
    private void InitialDeal(Card[] deck,Card[] player,Card[] computer,Card[] board) {
   int deckIndex = 0;
   int playerIndex = 0;
   int computerIndex = 0;
   int boardIndex = 0;
   while(playerIndex < 4 && computerIndex < 4) {
    player[playerIndex] = deck[deckIndex];
    deckIndex++;
    computer[computerIndex] = deck[deckIndex];
    deckIndex++;
    playerIndex++;
    computerIndex++;
   }
  while (boardIndex<4) {
board[boardIndex] = deck[deckIndex];
deckIndex++;
boardIndex++;
  }
    }
}

