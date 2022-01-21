package com.clef_game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import jm.JMC;


/**
 * 
 * @author alessia lombarda
 * @author andrea valota
 * 
 */

public class Game extends javax.swing.JFrame implements JMC {
    
    private int bpm;
    private int level;
    private Clef lastClef;
    private int clefChanges; //number of NOTES between a clef change
    private int lastIndex;
    
    private int points = 0;
    private int generatedNotes = 0;
    private int error = 0;
    private boolean incorrect = false;
    private boolean correct = false;
        
    private final ArrayList<Integer> pitch;
    private final ArrayList<Clef> clef;
    private final ArrayList<String> accidental;
    
    private final NotePanel np;
    private Timer timer;
    
    long start;
    long end;
    
    private final String[] ACCIDENTALS = {"NATURAL","SHARP","FLAT","DOUBLE_SHARP","DOUBLE_FLAT"};
    private final int[] NOTES = {E3,F3,G3,A3,B3,C4,D4,E4,F4,G4,A4,B4,C5,D5,E5,F5,G5,A5,B5,C6,D6,E6,F6};
    private final int BASEG = 9; //index of G4 in array NOTES 
    private final int LEVELUP = 10; //number of points necessary to level up
    private final int MAX_ERRORS = 5; //maximum number of errors before game over
    
    
    final MidiChannel[] mc;

    /**
     * Creates new form Game
     * @param level The level the game starts from
     */
    public Game(int level, int bpm) {
        //check level value
        if(level<1||level>10){
            level = 1;
        }
        
        //set class attributes
        this.level = level;
        this.clefChanges = 11-this.level;
        this.bpm = bpm;
        
        this.pitch = new ArrayList<>();
        this.clef = new ArrayList<>();
        this.accidental = new ArrayList<>();
        
        this.lastClef = new Clef("TREBLE");
        
        //inizialization of NotePanel needed to draw the game interface
        this.np = new NotePanel();
        np.setBackground(Color.WHITE);
        add(this.np);
        np.setSize(300,195);
        np.setLocation(30, 34);
        np.setVisible(true);
           
        initComponents();
        
        //set background color
        this.getContentPane().setBackground(new Color(102,153,255));
        
        level_label.setText(Integer.toString(this.level));
        bpm_label.setText(Integer.toString(this.bpm));
        
        //binding between keyboard buttons and digital piano buttons
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
        
        Synthesizer synth;
        try {
            
            synth = MidiSystem.getSynthesizer();
            synth.open();
            mc = synth.getChannels();
            Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
            synth.loadInstrument(instr[90]);
        
        } catch (MidiUnavailableException ex) {
            JOptionPane.showMessageDialog(this, "Errore MIDI", "ERRORE", JOptionPane.ERROR_MESSAGE);
        }
        
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
        setTitle("Clef Game");
        setLocation(new java.awt.Point(550, 150));
        setMinimumSize(new java.awt.Dimension(385, 480));
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
        c_button.setName("c"); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel1.setText("Score:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 50, 20);

        score_label.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        score_label.setText("0");
        getContentPane().add(score_label);
        score_label.setBounds(80, 10, 30, 20);

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel2.setText("BPM:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(280, 10, 40, 20);

        bpm_label.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        getContentPane().add(bpm_label);
        bpm_label.setBounds(320, 10, 30, 20);

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel3.setText("Level:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(150, 10, 40, 20);

        level_label.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        level_label.setText("1");
        getContentPane().add(level_label);
        level_label.setBounds(200, 10, 30, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void e_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_e_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(E4,300);
        checkNote(E4);
    }//GEN-LAST:event_e_buttonActionPerformed

    private void eb_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eb_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(EF4,300);
        checkNote(EF4);
    }//GEN-LAST:event_eb_buttonActionPerformed

    private void c_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(C4,300);
        checkNote(C4);
    }//GEN-LAST:event_c_buttonActionPerformed

    private void d_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(D4,300);    
        checkNote(D4);
    }//GEN-LAST:event_d_buttonActionPerformed

    private void db_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_db_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(DF4,300);
        checkNote(DF4);
    }//GEN-LAST:event_db_buttonActionPerformed

    private void f_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(F4,300);
        checkNote(F4);
    }//GEN-LAST:event_f_buttonActionPerformed

    private void gb_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gb_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(GF4,300);
        checkNote(GF4);
    }//GEN-LAST:event_gb_buttonActionPerformed

    private void g_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(G4,300);
        checkNote(G4);
    }//GEN-LAST:event_g_buttonActionPerformed

    private void ab_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ab_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(AF4,300);
        checkNote(AF4);
    }//GEN-LAST:event_ab_buttonActionPerformed

    private void a_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(A4,300);
        checkNote(A4);
    }//GEN-LAST:event_a_buttonActionPerformed

    private void bb_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bb_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(BF4,300);
        checkNote(BF4);
    }//GEN-LAST:event_bb_buttonActionPerformed

    private void b_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_buttonActionPerformed
        mc[0].allSoundOff();
        mc[0].noteOn(B4,300);
        checkNote(B4);
    }//GEN-LAST:event_b_buttonActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
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

    /**
     * This method check if the note selected by the user is correct and calls addScore if correct and addError otherwise
     * @param note The user input note
     */
    private void checkNote(int note) {
        int i;
        //look for current note index in NOTES array
        for(i=0; i<this.NOTES.length; i++){
            if(this.pitch.get(0)==this.NOTES[i])
                break;
        }
        
        //compute note value in relation to the correct clef
        int shiftedNote = this.NOTES[i+this.clef.get(0).getShiftFromTreble()];
        
        //modify note value in relation to accidentals
        switch(this.accidental.get(0)){
            
            case "SHARP":
                            shiftedNote++;
                            break;
            case "FLAT":
                            shiftedNote--;
                            break;
            case "DOUBLE_SHARP":
                            shiftedNote+=2;
                            break;
            case "DOUBLE_FLAT":
                            shiftedNote-=2;
                            break;
        }
        
        //check if user input note is correct without considering octave
        if (((shiftedNote%12)==(note%12)) && !this.correct){
            this.correct = true;
            addScore();
        }else{
            this.incorrect = true;
            addError();
        }
    }
    
    /**
     * This method increment the score points, handle the levelup procedure and the win message
     */
    private void addScore() {
        
        this.points++;
        
        this.end = System.currentTimeMillis();
        long elapsedTime = end - start;
        if(elapsedTime <= 60000/(bpm*2)){
            this.points++;
        }    
            
        score_label.setText(Integer.toString(this.points));
        
        
        //check if score points has reached levelup value and reset points, labels, otherwise generate next note
        if(this.points >= this.LEVELUP){
            this.timer.cancel();
            this.points = 0;
            this.generatedNotes = 0;
            score_label.setText(Integer.toString(this.points));
            this.level++;
            this.clefChanges = 11 - this.level;
            
            //check if user wins
            if(this.level>=11){
                JOptionPane.showMessageDialog(this, "YOU WIN!");
                this.dispose();
                new Main_menu().setVisible(true);
            }
            
            JOptionPane.showMessageDialog(this, "LEVEL UP!");
            
            level_label.setText(Integer.toString(this.level));
            
            if(this.level==5 || this.level==9){
                this.bpm+=10;
                bpm_label.setText(Integer.toString(this.bpm));
            }
            
            //generate first notes of the new level
            generateFirstNote();
        }     
    }

    /**
     * This method increase the error count and handles the game over
     */
    private void addError() {
        this.error++;
        
        if(this.error>=MAX_ERRORS){
            this.timer.cancel();
            JOptionPane.showMessageDialog(this, "GAME OVER");
            this.dispose();
            new Main_menu().setVisible(true);
        }  
    }
    
    /**
     * This method generate the first notes of the level and associated clefs and accidentals; 
     * the number of generated notes varies with respect to the level
     */
    private void generateFirstNote() {     
               
        this.pitch.clear();
        this.clef.clear();
        this.accidental.clear();
        
        this.correct = false;
        this.incorrect = false;
        
        //define how many notes to generate w.r.t. the level: 
        //level 1-4: 3 notes
        //level 5-8: 2 notes
        //level 9-10: 1 note
        int numNote = 3 - (this.level-1)/4; 
        int grades = this.level; //maximum interval between notes
        
        //add first clef
        this.clef.add(this.lastClef);
        
        //set first note
        switch(this.lastClef.getName()){
            case "FRENCH":  this.pitch.add(E4);
                            this.lastIndex = BASEG-2;
                            break;  
            case "TREBLE":  this.pitch.add(G4);
                            this.lastIndex = BASEG;
                            break;
                                
            case "SOPRANO": this.pitch.add(E4);
                            this.lastIndex = BASEG-2;
                            break;
            case "MEZZO_SOPRANO":
                            this.pitch.add(G4);
                            this.lastIndex = BASEG;
                            break;
            case "ALTO":    this.pitch.add(B4);
                            this.lastIndex = BASEG+2;
                            break;
            case "TENOR":   this.pitch.add(D5);
                            this.lastIndex = BASEG+4;
                            break;
            case "BARITONE_C":  
                            this.pitch.add(F5);
                            this.lastIndex = BASEG+6;
                            break;
                                    
            case "BARITONE_F":
                            this.pitch.add(B4);
                            this.lastIndex = BASEG+2;
                            break;
            case "BASS":    this.pitch.add(D5);
                            this.lastIndex = BASEG+4;
                            break;
            case "SUBBASS": this.pitch.add(F5);
                            this.lastIndex = BASEG+6;
                            break;                                            
        }
        
        //set first accidental
        this.accidental.add("NATURAL");
        
        //generate needed remaining notes
        for(int i=0; i<numNote-1; i++){         
            generateIndex(grades);
            this.pitch.add(this.NOTES[this.lastIndex]);
            this.clef.add(this.lastClef);
            
            //Single accidentals are allowed from level 5, double accidentals from level 9
            Random r = new Random();
            
            if(this.level<=4){
                this.accidental.add("NATURAL");
            }else if(this.level>4 && this.level<9){
                int high = 3;
                int res = r.nextInt(high);
                this.accidental.add(this.ACCIDENTALS[res]);
            } else {
                int high = this.ACCIDENTALS.length;
                int res = r.nextInt(high);
                this.accidental.add(this.ACCIDENTALS[res]);
            }
            
        }
        //passes the notes to the notepanel component
        this.np.setNotes(this.pitch, this.clef, this.accidental);
        
        this.generatedNotes += numNote;

        this.timer = new Timer();
        this.timer.schedule(new TimerTask() { 
            @Override
            public void run() {
                if(!incorrect && !correct){
                    addError();
                }
                generateNote();
                start = System.currentTimeMillis();
            }
        }, 2000, 60000/bpm); 
    }
    
    /**
     * This method generate step by step the required random notes;
     * if required this method handles the clef change
     */
    private void generateNote(){
         
        int grades = this.level;
        
        this.pitch.remove(0);
        this.clef.remove(0);
        this.accidental.remove(0);
        
        //clef change every 11-level notes
        if((this.generatedNotes%this.clefChanges)==0){
            //extract randomly new clef
            Clef c = Clef.getRandomClef();
            
            while(c.equals(this.lastClef)){
                c = Clef.getRandomClef();
            }
            this.lastClef = c;
            
            //get correct lastIndex
            switch(this.lastClef.getName()){
                case "FRENCH":  this.lastIndex = BASEG-2;
                                break;  
                case "TREBLE":  this.lastIndex = BASEG;
                                break;
                                
                case "SOPRANO": 
                                this.lastIndex = BASEG-2;
                                break;
                case "MEZZO_SOPRANO":
                                this.lastIndex = BASEG;
                                break;
                case "ALTO":    this.lastIndex = BASEG+2;
                                break;
                case "TENOR":   this.lastIndex = BASEG+4;
                                break;
                case "BARITONE_C":  
                                this.lastIndex = BASEG+6;
                                break;
                                    
                case "BARITONE_F":
                                this.lastIndex = BASEG+2;
                                break;
                case "BASS":    this.lastIndex = BASEG+4;
                                break;
                case "SUBBASS":   
                                this.lastIndex = BASEG+6;
                                break;                                            
            }          
            
        }
        
        //generate notes w.r.t. new clwf
        generateIndex(grades);
        this.pitch.add(this.NOTES[lastIndex]);
        this.clef.add(this.lastClef);
        
        //set accidentals:
        //level 1-4: natural notes only
        //level 5-8: single accidentals allowed 
        //level 1-4: double accidentals allowed too
        if(this.level<=4){
            this.accidental.add("NATURAL");
        }else if(this.level>4 && this.level<9){
            Random r = new Random();
            int high = 3;
            int res = r.nextInt(high);
            this.accidental.add(this.ACCIDENTALS[res]);
        } else {
            Random r = new Random();
            int high = this.ACCIDENTALS.length;
            int res = r.nextInt(high);
            this.accidental.add(this.ACCIDENTALS[res]);
        }

        this.np.setNotes(this.pitch, this.clef, this.accidental);
        this.generatedNotes++;
        this.correct = false;
        this.incorrect = false;
    }
    
    /**
     * This method generate and set the index corresponding to the note to generate;
     * it chooses notes among the ones in array NOTES
     * @param grades The allowed interval between notes
     */
    private void generateIndex(int grades) {
        Random r = new Random();
        int low = -grades;
        int high = grades+1;
        int res = r.nextInt(high-low)+low;
        this.lastIndex = lastIndex+res;
        
        //first and last three notes are not allowed; they're only used to handle clef changes
        if(this.lastIndex<3){
            this.lastIndex = 3;
        }
            
        if(this.lastIndex > this.NOTES.length-4){
            this.lastIndex = this.NOTES.length-4;
        }
        
    }
    
    private class NoteAction extends AbstractAction {

        int note; //the note corresponding to the pressed button

        NoteAction(int note) {
            this.note=note;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            mc[0].allSoundOff();
            mc[0].noteOn(note,300);
            checkNote(note);
        }
    }
}
