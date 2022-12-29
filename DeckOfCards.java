public class DeckOfCards{
//This class is for to define cards and list them into the deck and their special methods
private static Card[] deck;
private int current;
private int CARDNUMBER = 52;
public DeckOfCards() {
//Every card defines into the deck in order
String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
String[] suits = {"S","C","H","D"};
deck = new Card[CARDNUMBER];
current =0;
for(int i=0;i<deck.length;i++) {
    deck [i] = new Card(values[i%13],suits[i/13]);
    
}
}
public void shuffle() {
//shuffle method for cards to shuffle
current = 0;
//It pull a random number between 0-52 and define it to a integer
//Defines the first deck of card to temp
//then assigns a random card from the deck to first card of the deck.
//It continues until the all cards are out and the deck is shuffled.
for(int i = 0;i<deck.length;i++) {
int random = (int)(Math.random()*52);
Card temp = deck[i];
deck[i]=deck[random];
deck[random]=temp;
}
}
public void cut(int c) {
  //This is the cut method. We create two new card arrays where the deck has been cut.
  //List the deck into top and bottom from cutpoint
  //Then we make it upside down and define deck top to the bottom with newDeck array.
    Card [] top = new Card[c];
    Card [] bot = new Card[deck.length-c];
    for (int i = 0; i < c; i++) {
        top[i] = deck[i];
    }
      for (int i = 0; i < bot.length; i++) {
        bot[i] = deck[c + i];
      }
     Card[] newDeck = new Card[deck.length];
     for (int i = 0; i<bot.length;i++) {
    newDeck[i]=bot[i];
     }
     for (int i = 0 ; i<top.length;i++) {
    newDeck[bot.length+i]=top[i];
     }
     deck = newDeck;
     }
     public Card[] getCards() { //We will need to use the created deck later
      return deck;
     }
     public void showCards() {
      for(int i = 0 ; i < 52 ; i++) {
        System.out.println(deck[i]);
      }
     }
    }