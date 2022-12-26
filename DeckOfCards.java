public class DeckOfCards{
private static Card[] deck;
private int current;
private int CARDNUMBER = 52;
public DeckOfCards() {
String[] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
String[] suits = {"S","C","H","D"};
deck = new Card[CARDNUMBER];
current =0;
for(int i=0;i<deck.length;i++) {
    deck [i] = new Card(values[i%13],suits[i/13]);
    
}
}
public void shuffle() {
current = 0;
for(int i = 0;i<deck.length;i++) {
int random = (int)(Math.random()*52);
Card temp = deck[i];
deck[i]=deck[random];
deck[random]=temp;
}
}
public void cut(int c) {
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
     public Card[] getCards() {
      return deck;
     }
     public void showCards() {
      for(int i = 0 ; i < 52 ; i++) {
        System.out.println(deck[i]);
      }
     }
    }