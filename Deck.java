import java.util.Random;
public class Deck {
    Random rd = new Random();
    private String [] deck = new String [52];
    private String [] values = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    private String [] suits = {"S","C","H","D"};
    public void getDeck () {
    for(int i=0;i<deck.length;i++) {
        deck [i] =values[i%13] + suits[i/13];
        System.out.println(deck[i]);
    }
   
}
public void shuffle() {
for (int i =0 ; i<deck.length;i++) {
    String temp = deck[i];
    int random= (int)rd.nextInt(deck.length);
    deck[i]= deck[random];
    deck[random]=temp;
    System.out.println(deck[i]);
}

}
  
   
}
