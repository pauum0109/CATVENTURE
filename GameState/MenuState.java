package GameState;

import GameObject.Background;
import java.awt.*;
import java.awt.event.*;

public class MenuState extends GameState{
    private Background bg;

    private int currentChoice = 0;
    private String[] options = {"Start","Guide","Quit"};

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        try{
            bg = new Background("/Resources/Backgrounds/MenuBackground4.jpg", 1);
            bg.setVector(-0.1,0);

            titleColor = new Color(128,0,0);
            titleFont = new Font("Century Gothic",Font.PLAIN,28);
            font = new Font("Arial",Font.PLAIN,12);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init(){

    }
    public void update(){
        bg.update();
    }
    public void draw(Graphics2D g){
        bg.draw(g);
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("CATVENGER",140,100);

        g.setFont(font);
        for (int i=0;i<options.length;i++){
            if (i==currentChoice){
                g.setColor(Color.BLACK);
            }
            else{
                g.setColor(Color.RED);
            }
            g.drawString(options[i],200,140+i*15);
        }
    }
    private void select(){
        if (currentChoice == 0){
            gameStateManager.setState(GameStateManager.LEVELONESTATE);
        }
        if (currentChoice == 1){

        }
        if (currentChoice == 2){
            System.exit(0);
        }
    }
    public void keyPressed(int k){
        if (k == KeyEvent.VK_ENTER){
            select();
        }
        if (k==KeyEvent.VK_UP){
            currentChoice--;
            if (currentChoice == -1){
                currentChoice = options.length-1;
            }
        }
        if (k == KeyEvent.VK_DOWN){
            currentChoice++;
            if (currentChoice == options.length){
                currentChoice = 0;
            }
        } 
    }
    public void keyReleased(int k){

    }
}
