public class Card {
//This is the single card class I will define them later in the deck
private String value;
private String suit;
public Card(String cardValue,String cardSuit){
value=cardValue;
suit=cardSuit;
}//getValue method to use it later when calculating score
public String getValue() {
    return value;
}
public void setValue(String value) {
    this.value=value;
}
public String getSuit() {
    return suit;
}
public String toString() {  //It turns the card into a String
    return value+suit;
}

}
