package chess;

public class Game{
    public OpenBoard openboard;
    public static void main(String[] args) {
        
            Game game = new Game();
            game.openboard = new OpenBoard();
            game.openboard.setVisible(true);     
    }
}
