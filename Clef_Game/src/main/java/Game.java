/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alessia lombarda e andrea valota
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import jm.JMC;
import jm.constants.*;
import jm.music.data.*;
import jm.util.Play;
import jm.util.View;

public class Game extends javax.swing.JFrame implements JMC {
    
    private int level=1;
    private int score=0;
    private int bpm;
    private int error=0;
            
    private int currentNote;
    private ArrayList<Integer> pitch = new ArrayList<Integer>();
    
    private int[] easyNotes = {C4,D4,E4,F4,G4,A4,B4,C5,D5,E5,F5,G5,A5,B5};
    private int easyG = 4;
    private int currentIndex;
    
    private int[] mediumNotes = {C4,CS4,DF4,D4,DS4,EF4,E4,F4,FS4,GF4,G4,GS4,AF4,A4,AS4,BF4,B4,C5,CS5,DF5,D5,DS5,EF5,E5,F5,FS5,GF5,G5,GS5,AF5,A5,AS5,BF5,B5};
    private int mediumG = 10;
    //creare difficultNotes con double accidentals
    
    /**
     * Creates new form Game
     */
    public Game(int level) {
        this.level = level;
        
        initComponents();
        
        c_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Q"), "Q");
        c_button.getActionMap().put("Q", new NoteAction(C4));
        
        db_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"), "2");
        db_button.getActionMap().put("2", new NoteAction(DF4));
        
        d_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "W");
        d_button.getActionMap().put("W", new NoteAction(D4));
        
        eb_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"), "3");
        eb_button.getActionMap().put("3", new NoteAction(EF4));
        
        e_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "E");
        e_button.getActionMap().put("E", new NoteAction(E4));
        
        f_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "R");
        f_button.getActionMap().put("R", new NoteAction(F4));
        
        gb_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("5"), "5");
        gb_button.getActionMap().put("5", new NoteAction(GF4));
        
        g_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("T"), "T");
        g_button.getActionMap().put("T", new NoteAction(G4));
        
        ab_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("6"), "6");
        ab_button.getActionMap().put("6", new NoteAction(AF4));
        
        a_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Y"), "Y");
        a_button.getActionMap().put("Y", new NoteAction(A4));
        
        bb_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("7"), "7");
        bb_button.getActionMap().put("7", new NoteAction(BF4));
        
        b_button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("U"), "U");
        b_button.getActionMap().put("U", new NoteAction(B4));
        
        generateFirstNote();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eb_button = new javax.swing.JButton();
        e_button = new javax.swing.JButton();
        db_button = new javax.swing.JButton();
        c_button = new javax.swing.JButton();
        d_button = new javax.swing.JButton();
        gb_button = new javax.swing.JButton();
        f_button = new javax.swing.JButton();
        ab_button = new javax.swing.JButton();
        g_button = new javax.swing.JButton();
        bb_button = new javax.swing.JButton();
        a_button = new javax.swing.JButton();
        b_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        score_label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bpm_label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        level_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(385, 480));
        setMinimumSize(new java.awt.Dimension(385, 480));
        setPreferredSize(new java.awt.Dimension(385, 480));
        setResizable(false);
        setSize(new java.awt.Dimension(385, 480));
        getContentPane().setLayout(null);

        eb_button.setBackground(new java.awt.Color(0, 0, 0));
        eb_button.setFocusable(false);
        eb_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eb_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(eb_button);
        eb_button.setBounds(100, 230, 30, 130);

        e_button.setBackground(new java.awt.Color(255, 255, 255));
        e_button.setFocusable(false);
        e_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                e_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(e_button);
        e_button.setBounds(110, 230, 50, 200);

        db_button.setBackground(new java.awt.Color(0, 0, 0));
        db_button.setFocusable(false);
        db_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                db_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(db_button);
        db_button.setBounds(50, 230, 30, 130);

        c_button.setBackground(new java.awt.Color(255, 255, 255));
        c_button.setFocusable(false);
        c_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(c_button);
        c_button.setBounds(10, 230, 50, 200);

        d_button.setBackground(new java.awt.Color(255, 255, 255));
        d_button.setFocusable(false);
        d_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(d_button);
        d_button.setBounds(60, 230, 50, 200);

        gb_button.setBackground(new java.awt.Color(0, 0, 0));
        gb_button.setFocusable(false);
        gb_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gb_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(gb_button);
        gb_button.setBounds(200, 230, 30, 130);

        f_button.setBackground(new java.awt.Color(255, 255, 255));
        f_button.setFocusable(false);
        f_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(f_button);
        f_button.setBounds(160, 230, 50, 200);

        ab_button.setBackground(new java.awt.Color(0, 0, 0));
        ab_button.setFocusable(false);
        ab_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ab_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(ab_button);
        ab_button.setBounds(250, 230, 30, 130);

        g_button.setBackground(new java.awt.Color(255, 255, 255));
        g_button.setFocusable(false);
        g_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(g_button);
        g_button.setBounds(210, 230, 50, 200);

        bb_button.setBackground(new java.awt.Color(0, 0, 0));
        bb_button.setFocusable(false);
        bb_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bb_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(bb_button);
        bb_button.setBounds(300, 230, 30, 130);

        a_button.setBackground(new java.awt.Color(255, 255, 255));
        a_button.setFocusable(false);
        a_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(a_button);
        a_button.setBounds(260, 230, 50, 200);

        b_button.setBackground(new java.awt.Color(255, 255, 255));
        b_button.setFocusable(false);
        b_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(b_button);
        b_button.setBounds(310, 230, 50, 200);

        jLabel1.setText("Score:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 50, 20);

        score_label.setText("0");
        getContentPane().add(score_label);
        score_label.setBounds(80, 10, 30, 20);

        jLabel2.setText("BPM:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(280, 10, 40, 20);
        getContentPane().add(bpm_label);
        bpm_label.setBounds(320, 10, 30, 20);

        jLabel3.setText("Level:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(150, 10, 40, 20);

        level_label.setText("1");
        getContentPane().add(level_label);
        level_label.setBounds(200, 10, 30, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void e_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e_buttonActionPerformed
        playNote(E4);
        checkNote(E4);
    }//GEN-LAST:event_e_buttonActionPerformed

    private void eb_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eb_buttonActionPerformed
        playNote(EF4);
        checkNote(EF4);
    }//GEN-LAST:event_eb_buttonActionPerformed

    private void c_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_buttonActionPerformed
        playNote(C4);
        checkNote(C4);
    }//GEN-LAST:event_c_buttonActionPerformed

    private void d_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_buttonActionPerformed
        playNote(D4);
        checkNote(D4);
    }//GEN-LAST:event_d_buttonActionPerformed

    private void db_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_db_buttonActionPerformed
        playNote(DF4);
        checkNote(DF4);
    }//GEN-LAST:event_db_buttonActionPerformed

    private void f_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_buttonActionPerformed
        playNote(F4);
        checkNote(F4);
    }//GEN-LAST:event_f_buttonActionPerformed

    private void gb_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb_buttonActionPerformed
        playNote(GF4);
        checkNote(GF4);
    }//GEN-LAST:event_gb_buttonActionPerformed

    private void g_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g_buttonActionPerformed
        playNote(G4);
        checkNote(G4);
    }//GEN-LAST:event_g_buttonActionPerformed

    private void ab_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ab_buttonActionPerformed
        playNote(AF4);
        checkNote(AF4);
    }//GEN-LAST:event_ab_buttonActionPerformed

    private void a_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_buttonActionPerformed
        playNote(A4);
        checkNote(A4);
    }//GEN-LAST:event_a_buttonActionPerformed

    private void bb_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bb_buttonActionPerformed
        playNote(BF4);
        checkNote(BF4);
    }//GEN-LAST:event_bb_buttonActionPerformed

    private void b_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_buttonActionPerformed
        playNote(B4);
        checkNote(B4);
    }//GEN-LAST:event_b_buttonActionPerformed

    private void checkNote(int note) {
        System.out.println(note);
        if (currentNote==note){
            addScore();
        }else{
            addError();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*new Game(1).setVisible(true);
                int[] pitch = {C0,B0,F0,A0};
                double[] durations = {C,C,C,C};
                Phrase phrase = new Phrase();
                phrase.addNoteList(pitch,durations);
                View.show(phrase);*/
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton a_button;
    private javax.swing.JButton ab_button;
    private javax.swing.JButton b_button;
    private javax.swing.JButton bb_button;
    private javax.swing.JLabel bpm_label;
    private javax.swing.JButton c_button;
    private javax.swing.JButton d_button;
    private javax.swing.JButton db_button;
    private javax.swing.JButton e_button;
    private javax.swing.JButton eb_button;
    private javax.swing.JButton f_button;
    private javax.swing.JButton g_button;
    private javax.swing.JButton gb_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel level_label;
    private javax.swing.JLabel score_label;
    // End of variables declaration//GEN-END:variables

    private void addScore() {
        
        //this.score++;
        score_label.setText(Integer.toString(++score));
        
        //GENERA NOTA NUOVA SUCCESSIVA
        generateNote();
    }

    private void addError() {
        this.error++;
        //no score sottozero
        //AGGIUNGERE GESTIONE ERRORI
        score_label.setText(Integer.toString(--score));
    }
    
    private void generateFirstNote() {     
        //generazione inizio livello
        /*TEXT AREA
        try {
            Font bravura = Font.createFont(Font.TRUETYPE_FONT, new File("font\\bravura.otf")); 
            bravura = bravura.deriveFont(36f);
            notes_textArea.setFont(bravura);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        
        notes_textArea.setText("\uD834\uDD1E");
        notes_textArea.setText("\uD834\uDD5D");*/
                
        int numNote = 3 - (this.level-1)/4;
        int grades = this.level;
        
        //add first G4
        this.pitch.add(G4);
        
        int[] ourNotes;
        int ourG;
        
        if(this.level<5){
            ourNotes = easyNotes;
            ourG = easyG;
        } else if(this.level<9){
            ourNotes = mediumNotes;
            ourG = mediumG;
        } else {
            //sostituire con difficultNotes
            ourNotes = easyNotes;
            ourG = easyG;
        }
        
        this.currentIndex = ourG;
        
        //sistema per livelli non facili
        for(int i=0; i<numNote-1; i++){         
            generateIndex(grades, ourNotes);
            this.pitch.add(ourNotes[currentIndex]);
        }
    
        this.currentNote = pitch.get(0);
        System.out.println(this.pitch.toString());
    }
    
    private void generateNote(){
        int[] ourNotes;
        
        if(this.level<5)
            ourNotes = easyNotes;
        else if(this.level<9)
            ourNotes = mediumNotes;
        else {
            //sostituire con difficultNotes
            ourNotes = easyNotes;
        }
        
        int grades = this.level;
        this.pitch.remove(0);
    
        generateIndex(grades, ourNotes);
        
        this.pitch.add(ourNotes[currentIndex]);
        this.currentNote = pitch.get(0);
    }

    private void generateIndex(int grades, int[] ourNotes) {
        Random r = new Random();
        int low = -grades;
        int high = grades+1;
        int res = r.nextInt(high-low)+low;
        this.currentIndex = currentIndex+res;
            
        if(this.currentIndex<0){
            this.currentIndex = 0;
        }
            
        if(this.currentIndex>ourNotes.length-1){
            this.currentIndex = ourNotes.length-1;
        }
        
    }
    
    private void playNote(int note){
        Note n = new Note(note, EIGHTH_NOTE);
        Phrase p = new Phrase(n);
        Part part = new Part("piano", PIANO, 1);
        Score score = new Score(part);
        
        part.addPhrase(p);
        score.addPart(part);
        
        Play.midi(score);     
    }
    
    private class NoteAction extends AbstractAction {

        int note;

        NoteAction(int note) {
            this.note=note;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            playNote(note);
            checkNote(note);
        }
    }
}
