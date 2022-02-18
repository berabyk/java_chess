package chess;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class OpenBoard extends JFrame {
    Component board;
    public OpenBoard(){
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Chess");
        this.setResizable(false);
        board = new BoardGui();
        this.add(board);
        
        this.setLocation(450, 100);
        this.pack();
        this.setVisible(true);
    }
}