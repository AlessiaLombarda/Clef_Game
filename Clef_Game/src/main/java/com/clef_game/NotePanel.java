package com.clef_game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Map;
import static java.util.Map.entry;
import javax.swing.JPanel;
import jm.JMC;

/**
 * 
 * @author Alessia Lombarda
 * @author Andrea Valota
 * 
 */

public class NotePanel extends JPanel implements JMC{
    
    private ArrayList<Integer> pitch;
    private ArrayList<Clef> clef;
    private ArrayList<String> accidental;
    private boolean change = true; //boolean variable used to switch the color of the notes
    
    //binding between notes and corresponding y-position in the panel 
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
    
    private final String NOTE_UNICODE = "\uD834\uDD58"; //unicode character of a generic note
    private final String FLAT_UNICODE = "\u266D";  //unicode character of the flat symbol
    private final String SHARP_UNICODE = "\u266F"; //unicode character of the sharp symbol
    private final String DOUBLE_SHARP_UNICODE = "\uD834\uDD2A"; //unicode character of the double sharp symbol
    private final String DOUBLE_FLAT_UNICODE = "\uD834\uDD2B"; //unicode character of the double flat symbol
    
    private final int Y_SHIFT = 20; //shift between lines
    private final int X_SHIFT = 40; //space after note
    private final int LL_SPAN = 35; //horizontal width of ledger lines 
    
    @Override
    protected void paintComponent(Graphics gr){
              
        super.paintComponent(gr);
        Graphics2D g2D = (Graphics2D)gr;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawStave(g2D);
        drawNotes(g2D);
    }
    
    /**
     * This method is called from Game and set the arraylists for notes, pitches and accidentals;
     * at the end it repaints the panel to draw correct notes
     * @param pitch The Arraylist containing pitches to draw
     * @param clef The Arraylist containing clefs to draw
     * @param accidental The Arraylist containing accidentals to draw
     */
    public void setNotes(ArrayList<Integer> pitch, ArrayList<Clef> clef, ArrayList<String> accidental){
        this.pitch = pitch;
        this.clef = clef;
        this.accidental = accidental;
        this.repaint();
    }
    
    /**
     * This method draws the stave
     * @param gr The Graphics2D oblect
     */
    private void drawStave(Graphics2D g2D) {
        for(int i=0; i<5; i++)
            g2D.drawLine(0, (i+3)*Y_SHIFT, this.getWidth(), (i+3)*Y_SHIFT);
    }
    
    /**
     * This method effectively draws notes, clefs and accidentals on the stave
     * @param gr The Graphics2D object to draw
     */
    private void drawNotes(Graphics2D g2D) {
        
        //font, dimension for first note and clef
        Font font = new Font("font\\Bravura.otf", Font.PLAIN, 100);
        g2D.setFont(font);        
        FontMetrics fm = g2D.getFontMetrics();
        
        int x = 0; //x-position: it will be incremented through for loop to define correct x-position of each symbol drawn
        
        int shift_hints = 0; //offset for small suggested notes
        int shift_clef_hints = 0; //offset for small suggested clefs
        
        for(int i=0; i<this.pitch.size(); i++){
            //special case for suggested notes
            if (i>=1){
                font = new Font("font\\Bravura.otf", Font.PLAIN, 70);
                g2D.setFont(font);
                fm = g2D.getFontMetrics();
                
                shift_hints = 3; //shift on x and y for smaller notes used as hints
                shift_clef_hints = 11; //shift on x and y for smaller clefs used as hints
            }   
            
            //if the clef is the same we only increment x; otherwise we draw the new clef and increment x with a larger shift
            if(i>=1 && this.clef.get(i).equals(this.clef.get(i-1))){
                x+=X_SHIFT;
            }else{
                //first clef is drawn here
                g2D.setColor(Color.BLACK);
                g2D.drawString(this.clef.get(i).toString(), x, this.clef.get(i).getShift()-shift_clef_hints);
                x+=fm.stringWidth(this.clef.get(i).toString())+5;
            }
            
            //change notes' color
            if((i%2)==0 ^ change){
                g2D.setColor(Color.RED);
            } else {
                g2D.setColor(Color.BLACK);
            }
            
            //set smaller font for accidentals
            font = new Font("font\\Bravura.otf", Font.PLAIN, 47);
            g2D.setFont(font);
            fm = g2D.getFontMetrics();
            
            //draw accidentals and shifts x position
            switch(this.accidental.get(i)){
                case "SHARP":
                            g2D.drawString(SHARP_UNICODE, x,this.notes.get(this.pitch.get(i))+6);
                            x+= fm.stringWidth(SHARP_UNICODE);
                            break;
                case "FLAT":
                            g2D.drawString(FLAT_UNICODE, x,this.notes.get(this.pitch.get(i))-shift_hints);
                            x+= fm.stringWidth(FLAT_UNICODE);
                            break;
                case "DOUBLE_SHARP":
                            g2D.drawString(DOUBLE_SHARP_UNICODE, x,this.notes.get(this.pitch.get(i))+20);
                            x+= fm.stringWidth(DOUBLE_SHARP_UNICODE);
                            break;
                case "DOUBLE_FLAT":
                            g2D.drawString(DOUBLE_FLAT_UNICODE, x,this.notes.get(this.pitch.get(i)));
                            x+= fm.stringWidth(DOUBLE_FLAT_UNICODE)-2;
                            break;
            }
            
            //set proper font to draw notes; a smaller font will be used for suggestions
            if(i>=1){
                font = new Font("font\\Bravura.otf", Font.PLAIN, 70);
            }else{
                font = new Font("font\\Bravura.otf", Font.PLAIN, 100);
            }
            
            g2D.setFont(font);
            fm = g2D.getFontMetrics();
            
            //draw the note: shift_hints will be 0 in case of first note and 3 otherwise
            g2D.drawString(NOTE_UNICODE, x,this.notes.get(this.pitch.get(i))-shift_hints);
            
            //draws ledger lines if pitch higher than G5 or lower than D4
            if(this.pitch.get(i)<D4){
                //compute number of ledger lines to draw
                int ll = (D4-this.pitch.get(i))/2;
                
                for(int j=0; j<ll; j++){
                    g2D.setStroke(new BasicStroke(2));
                    //used x+3 to separate a bit from the previous ledger line 
                    g2D.drawLine(x+3, (j+8)*Y_SHIFT, x+LL_SPAN-2*shift_hints, (j+8)*Y_SHIFT);
                }
                
            } else if(this.pitch.get(i)>G5){
                
                //compute number of ledger lines to draw
                int ll = (this.pitch.get(i)-G5+1)/3;
                
                for(int j=0; j<ll; j++){
                    g2D.setStroke(new BasicStroke(2));
                    //used x+3 to separate a bit from the previous ledger line
                    g2D.drawLine(x+3, (2-j)*Y_SHIFT, x+LL_SPAN-2*shift_hints, (2-j)*Y_SHIFT);
                }
            }
            
            //x shift to go beyond note
            x+=fm.stringWidth(NOTE_UNICODE);
        }
        
        //switch boolean to change color
        change = !change; 
    }
}
