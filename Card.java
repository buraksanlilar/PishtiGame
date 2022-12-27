public class Card {
private String value;
private String suit;
public Card(String cardValue,String cardSuit){
value=cardValue;
suit=cardSuit;
}
public String getValue() {
    return value;
}
public void setValue(String value) {
    this.value=value;
}
public String getSuit() {
    return suit;
}
public String toString() {
    return value+suit;
}

}
