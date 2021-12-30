
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
            entry(A3,190),
            entry(B3,180),
            entry(C4,170),
            entry(D4,160),
            entry(E4,150),
            entry(F4,140),
            entry(G4,130),
            entry(A4,120),
            entry(B4,110),
            entry(C5,100),
            entry(D5,90),
            entry(E5,80),
            entry(F5,70),
            entry(G5,60),
            entry(A5,50),
            entry(B5,40),
            entry(C6,30)
        );
    
    private final String NOTE_UNICODE = "\uD834\uDD58";
    
    private final int Y_SHIFT = 20;
    private final int X_SHIFT = 40;
    private final int LL_SPAN = 35;
    
    //private final int[] note = {C4,D4,E4,F4,G4,A4,B4,C5,D5,E5,F5,G5,A5,B5};
    //private final int[] positions = {140,130,120,110,100,90,80,70,60,50,40,30,20,10};
    
    public NotePanel(){
        this.setBackground(Color.WHITE);
        this.setSize(200,195);
        this.setLocation(80, 34);
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
        g2D.drawString("\uD834\uDD1E", 0, 140);
        drawNotes(g2D);
    }

    private void drawStave(Graphics gr) {
        for(int i=0; i<5; i++)
            gr.drawLine(0, (i+3)*Y_SHIFT, this.getWidth(), (i+3)*Y_SHIFT);
    }
    
    public void setNotes(ArrayList<Integer> pitch){
        this.pitch = pitch;
        this.repaint();
    }

    private void drawNotes(Graphics gr) {
        
        /*TEST NOTE ARRAY
        this.pitch.clear();
        this.pitch.add(G4);
        this.pitch.add(C6);
        this.pitch.add(A3);*/
        
        Graphics2D g2D = (Graphics2D)gr;
        Font font = new Font("font\\Bravura.otf", Font.PLAIN, 90);
        g2D.setFont(font);
        int x = 70;
        int shift = 0;
        for(int i=0; i<this.pitch.size(); i++){
            
            //special case for suggested notes
            if (i>=1){
                font = new Font("font\\Bravura.otf", Font.PLAIN, 70);
                g2D.setFont(font);
                shift = 3;
            }
            
            g2D.drawString(NOTE_UNICODE, x+(i*X_SHIFT)+shift,this.notes.get(this.pitch.get(i))-shift);
        
            if(this.pitch.get(i)<D4){
                int ll = (D4-this.pitch.get(i))/2;
                for(int j=0; j<ll; j++){
                    g2D.setStroke(new BasicStroke(2));
                    g2D.drawLine(x+(i*X_SHIFT), (j+8)*Y_SHIFT, x+(i*X_SHIFT)+LL_SPAN, (j+8)*Y_SHIFT);
                }
            } else if(this.pitch.get(i)>G5){
                int ll = (this.pitch.get(i)-G5-1)/2;
                for(int j=0; j<ll; j++){
                    g2D.setStroke(new BasicStroke(2));
                    g2D.drawLine(x+(i*X_SHIFT), (2-j)*Y_SHIFT, x+(i*X_SHIFT)+LL_SPAN, (2-j)*Y_SHIFT);
                }
            }
        }
        
        
    }
}
