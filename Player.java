public class Player {
    private String playername;
    public Card[] player;
    private int playerscore;
    private Card[] PLayerWin;
    public Player (String playername,int handnumber,int playerscore,Card[] player) {
    this.player = new Card[4];
    this.playername= playername;
    this.playerscore=playerscore;

    }
}
