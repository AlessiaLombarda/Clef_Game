
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Map;
import static java.util.Map.entry;
import javax.swing.JPanel;
import static jm.constants.Pitches.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alessia lombarda e andrea valota
 */
public class NotePanel extends JPanel{
    
    private ArrayList<Integer> pitch;
    
    private final Map<Integer,Integer> notes = Map.ofEntries(
            entry(C4,150),
            entry(D4,140),
            entry(E4,130),
            entry(F4,120),
            entry(G4,110),
            entry(A4,100),
            entry(B4,90),
            entry(C5,80),
            entry(D5,70),
            entry(E5,60),
            entry(F5,50),
            entry(G5,40),
            entry(A5,30),
            entry(B5,20));
    
    private final String NOTE_UNICODE = "\uD834\uDD58";
    
    //private final int[] note = {C4,D4,E4,F4,G4,A4,B4,C5,D5,E5,F5,G5,A5,B5};
    //private final int[] positions = {140,130,120,110,100,90,80,70,60,50,40,30,20,10};
    
    public NotePanel(){
        this.setBackground(Color.WHITE);
        this.setSize(200,170);
        this.setLocation(80, 50);
        this.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics gr){
        super.paintComponent(gr);
        Graphics2D g2D = (Graphics2D)gr;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("font\\Bravura.otf", Font.PLAIN, 100);
        g2D.setFont(font);
        drawStave(g2D);
        g2D.drawString("\uD834\uDD1E", 0, 120);
        drawNotes(g2D);
    }

    private void drawStave(Graphics gr) {
        for(int i=0; i<5; i++)
            gr.drawLine(0, (i+2)*20, this.getWidth(), (i+2)*20);
    }
    
    public void setNotes(ArrayList<Integer> pitch){
        this.pitch = pitch;
        this.repaint();
    }

    private void drawNotes(Graphics gr) {
        
        Graphics2D g2D = (Graphics2D)gr;
        Font font = new Font("font\\Bravura.otf", Font.PLAIN, 90);
        g2D.setFont(font);
        int x = 70;
        for(int i=0; i<this.pitch.size(); i++){
            
            g2D.drawString(NOTE_UNICODE, x+(i*40),this.notes.get(this.pitch.get(i)));
        
            if(this.pitch.get(i)<D4){
                System.out.println("Sotto d4");
                int ll = (D4-this.pitch.get(i))/2;
                for(int j=0; j<ll; j++){
                    g2D.setStroke(new BasicStroke(2));
                    g2D.drawLine(x+(i*40)-0, (j+7)*20, x+(i*40)+35, (j+7)*20);
                }
            } else if(this.pitch.get(i)>G5){
                System.out.println("Sopra g5");
                int ll = (this.pitch.get(i)-G5)/2;
                for(int j=0; j<ll; j++){
                    g2D.setStroke(new BasicStroke(2));
                    g2D.drawLine(x+(i*40)-0, (1-j)*20, x+(i*40)+35, (1-j)*20);
                }
            }
        }
        
        
    }
}
