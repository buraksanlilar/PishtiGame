public class masa {
    private Card[] board = new Card[52];
    private Card[] player = new Card[4];
    private Card[] computer = new Card[4];
    private int a=0;
    private int b=0;
    private int count = 0;
    private Card[] InitialDeal(Card[] deck,Card[] playershand) {
    for(int i = 0;i<playershand.length;i++) {
    if(playershand[i]!=null) {
    break;
    }else { 
    count++;
    }
    }
    if(count ==4) {
        for(int i = 0;i<8;i++) {
        player[i] = deck[a];
        a++;
        computer[i] = deck[a];
        a++;
        }
        for(int i=0;i<4;i++) {
        board[i] = deck[a];
        a++;
        }
    }
   return playershand;
    

    }
}

