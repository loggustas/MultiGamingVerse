package main;

import Inputs.KeyBoardListener;
import Inputs.MyMouseListener;

import javax.swing.JPanel;
import java.awt.*;    //using Color and Graphics

public class GameScreen extends JPanel {   //allows to draw on the window
    private Game game;
    private Dimension size;
    public int width, height;

    private MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;

    public GameScreen(Game game) {
        this.game = game;
        this.width = 880;
        this.height = 640;

        setPanelSize();

    }
    public void initInputs() {
        this.myMouseListener = new MyMouseListener(this.game);
        this.keyBoardListener = new KeyBoardListener(this.game);

        this.addMouseListener(this.myMouseListener);         //adding listeners
        this.addMouseMotionListener(this.myMouseListener);
        this.addKeyListener(keyBoardListener);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(width, height);

        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }

    public void paintComponent(Graphics g) {   //graphics class creates graphic objects
        super.paintComponent(g);   //does all graphic calculations

        this.game.getRender().render(g);

    }

}
