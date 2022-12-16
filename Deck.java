public class Deck {
    private String [] deck = new String [52];
    private String [] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private String [] suits = {"S","C","H","D"};
    public void setDeck () {
    for(int i=0;i<deck.length;i++) {
        deck [i] =values[i%13] + suits[i/13];
        
    }
   
}
public void getDeck() {
    for(int i = 0; i<deck.length;i++) {
        System.out.println(deck[i]);
    }
}
public void shuffle() {
for (int i =0 ; i<deck.length;i++) {
    String temp = deck[i];
    int random= (int)(Math.random()*deck.length);
    deck[i]= deck[random];
    deck[random]=temp;
}
}
public void cut(int c) {
String [] top = new String[deck.length-c];
String [] bot = new String[c];
for (int i = 0; i < c; i++) {
    bot[i] = deck[i];
  }
  for (int i = 0; i < top.length; i++) {
    top[i] = deck[c + i];
  }
 String[] newDeck = new String[deck.length];
 for (int i = 0; i<top.length;i++) {
newDeck[i]=top[i];
 }
 for (int i = 0 ; i<bot.length;i++) {
newDeck[top.length+i]=bot[i];
 }
 deck = newDeck;
  }
}
