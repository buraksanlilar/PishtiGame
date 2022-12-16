import java.util.Scanner;
public class Pishti {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Deck deck1 = new Deck();
    deck1.setDeck();
    deck1.shuffle();
    System.out.println("before");
    deck1.getDeck();
    System.out.println("after the cut");
    deck1.cut(1);
    deck1.getDeck();
   


    }
}
