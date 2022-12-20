public class Card {
private String value;
private String suit;
public Card(String cardValue,String cardSuit){
value=cardValue;
suit=cardSuit;
}
public String toString() {
    return value+suit;
}
}
