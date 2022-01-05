package com.clef_game;

//import com.clef_game.Game.Clef;
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
    private ArrayList<Clef> clef;
    private ArrayList<String> accidental;
    
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
    private final String FLAT_UNICODE = "\u266D";
    private final String SHARP_UNICODE = "\u266F";
    private final String DOUBLE_SHARP_UNICODE = "\uD834\uDD2A";
    private final String DOUBLE_FLAT_UNICODE = "\uD834\uDD2B";
    
    private final int Y_SHIFT = 20; //shift between lines
    private final int X_SHIFT = 40; //shift between notes
    private final int CLEF_SHIFT = 70; //offset from clef
    private final int LL_SPAN = 35; //horizontal shift between ledger lines 
    private final int ACCIDENTAL_SHIFT = 13; //horizontal shift between notes and accidentals
    
    
    public NotePanel(){
        this.setBackground(Color.WHITE);
        this.setSize(260,195);
        this.setLocation(50, 34);
        this.setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics gr){
        super.paintComponent(gr);
        Graphics2D g2D = (Graphics2D)gr;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("font\\Bravura.otf", Font.PLAIN, 105);
        g2D.setFont(font);
        drawStave(g2D);
        drawNotes(g2D);
    }

    private void drawStave(Graphics gr) {
        for(int i=0; i<5; i++)
            gr.drawLine(0, (i+3)*Y_SHIFT, this.getWidth(), (i+3)*Y_SHIFT);
    }
    
    public void setNotes(ArrayList<Integer> pitch, ArrayList<Clef> clef, ArrayList<String> accidental){
        this.pitch = pitch;
        this.clef = clef;
        this.accidental = accidental;
        this.repaint();
    }

    private void drawNotes(Graphics gr) {
        
        Graphics2D g2D = (Graphics2D)gr;
        Font font = new Font("font\\Bravura.otf", Font.PLAIN, 100);
        g2D.setFont(font);        
        
        int x = 0;
        
        int shift = 0; //offset for small suggested notes
        int shift_clef = 0;
        int shift_accidental = 0;
        for(int i=0; i<this.pitch.size(); i++){
            
            System.out.println(this.clef.get(i).getName());
            
            //special case for suggested notes
            if (i>=1){
                font = new Font("font\\Bravura.otf", Font.PLAIN, 70);
                g2D.setFont(font);
                x+= 3;
                shift = 3;
                shift_clef = 11;
                shift_accidental = 2;
            }   
            
            if(i>=1 && this.clef.get(i).equals(this.clef.get(i-1))){
                x+=30;
            }else{
                g2D.drawString(this.clef.get(i).toString(), x, this.clef.get(i).getShift()-shift_clef);
                x+=CLEF_SHIFT;
            }
            
            if(this.accidental.get(i).equals("DOUBLE_FLAT")){
                x+=25;
            }else if(!this.accidental.get(i).equals("NATURAL")) {
                x+=15;
            }
            g2D.drawString(NOTE_UNICODE, x,this.notes.get(this.pitch.get(i))-shift);
        
            if(this.pitch.get(i)<D4){
                int ll = (D4-this.pitch.get(i))/2;
                for(int j=0; j<ll; j++){
                    g2D.setStroke(new BasicStroke(2));
                    g2D.drawLine(x+3, (j+8)*Y_SHIFT, x+LL_SPAN-2*shift, (j+8)*Y_SHIFT);
                }
            } else if(this.pitch.get(i)>G5){
                int ll = (this.pitch.get(i)-G5+1)/3;
                for(int j=0; j<ll; j++){
                    g2D.setStroke(new BasicStroke(2));
                    g2D.drawLine(x+3, (2-j)*Y_SHIFT, x+LL_SPAN-2*shift, (2-j)*Y_SHIFT);
                }
            }
            
            font = new Font("font\\Bravura.otf", Font.PLAIN, 50-shift);
            g2D.setFont(font);
            switch(this.accidental.get(i)){
                case "SHARP":
                            g2D.drawString(SHARP_UNICODE, x-ACCIDENTAL_SHIFT-shift_accidental,this.notes.get(this.pitch.get(i))+6);
                            break;
                case "FLAT":
                            //x += 10;
                            g2D.drawString(FLAT_UNICODE, x-ACCIDENTAL_SHIFT-shift_accidental,this.notes.get(this.pitch.get(i))-shift);
                            break;
                case "DOUBLE_SHARP":
                            //x += 10;
                            g2D.drawString(DOUBLE_SHARP_UNICODE, x-ACCIDENTAL_SHIFT-shift_accidental,this.notes.get(this.pitch.get(i))+20);
                            break;
                case "DOUBLE_FLAT":
                            //x += 25;
                            g2D.drawString(DOUBLE_FLAT_UNICODE, x-ACCIDENTAL_SHIFT-shift_accidental-12,this.notes.get(this.pitch.get(i)));
                            break;
            }
            
            x+=X_SHIFT;
        }
        
        
    }
}
