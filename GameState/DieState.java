/* 
+ Your Team Number: Group 6
+ Member names & IU code:
Tran Phuong Quang Huy - ITCSIU21071
Nguyen Thi Phuong Thao - ITITITIU21214
Nguyen Thi Anh Tho - ITCSIU21236
Bui Nhu Y - ITCSIU21247
+ Purpose: Package GameState: Manage different stages of gameplay: MenuState, WinState, DieState,...
*/
package GameState;

import GameObject.Background;
import Audio.AudioPlayer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.util.HashMap;

public class DieState extends GameState{
    private Background bg;
    private AudioPlayer bgMusic;

    private HashMap<String, AudioPlayer> sfx;

    private BufferedImage head;
    private int currentChoice = 0;
    private String[] options = {"Start","Quit"};

    private Color titleColor;
    private Font titleFont;
    private Font font;
    private Color fontColor1;

    public DieState(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        try{
            bg = new Background("/Resources/Backgrounds/menubg.gif", 1);
            bg.setVector(-0.1,0);
            bgMusic = new AudioPlayer("/Resources/SFX/deadsfx.mp3");
            bgMusic.play();
            sfx = new HashMap<String, AudioPlayer>();
		    sfx.put("jump", new AudioPlayer("/Resources/SFX/jump.mp3"));
		    sfx.put("scratch", new AudioPlayer("/Resources/SFX/scratch.mp3"));


            titleColor = new Color(255,182,43);
            titleFont = new Font("Nordic Light",Font.PLAIN,28);
            font = new Font("valorax",Font.PLAIN,12);
            fontColor1 = new Color(60,78,97);

            head = ImageIO.read(getClass().getResourceAsStream("/Resources/HUD/Hud.gif")).getSubimage(0, 12, 12, 11);
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
        g.drawString("You are Dead",65,70);

        g.setFont(font);
        for (int i=0;i<options.length;i++){
            if (i==currentChoice){
                g.setColor(Color.RED);
            }
            else{
                g.setColor(fontColor1);
            }
            g.drawString(options[i],145,140+i*15);
        }

        if(currentChoice == 0) g.drawImage(head, 130, 130, null);
		else if(currentChoice == 1) g.drawImage(head, 130, 145, null);
    }
    private void select(){
        if (currentChoice == 0){
            sfx.get("jump").play();
            bgMusic.stop();
            gameStateManager.setState(GameStateManager.LEVELONESTATE);
        }
        if (currentChoice == 1){
            System.exit(0);
        }
    }
    public void keyPressed(int k){
        if (k == KeyEvent.VK_ENTER){
            select();
        }
        if (k==KeyEvent.VK_UP){
            
                sfx.get("scratch").play();
				currentChoice=0;
			
        }
        if (k == KeyEvent.VK_DOWN){
            
                sfx.get("scratch").play();
				currentChoice=1;
			
        } 
    }
    public void keyReleased(int k){

    }
}
