
package com.clef_game;

import java.util.Random;

/**
 * 
 * @author alessia lombarda
 * @author andrea valota
 * 
 */

class Clef {

    private String unicode;
    private String name;
    private int shift; //vertical shift to define note y-position 
    private int shiftFromTreble; //note shift from treble clef to encode notes in new clef 
    
    /** Creates a clef with the specified name
     * 
     * @param clef The clef's name
     */
    public Clef(String clef){
        
        switch(clef){
            case "FRENCH":  this.unicode = "\uD834\uDD1E";
                            this.shift = 160;
                            this.shiftFromTreble = 2;
                            break;
            
            case "TREBLE":  this.unicode = "\uD834\uDD1E";
                            this.shift = 135;
                            this.shiftFromTreble = 0;
                            break;
                                                    
            case "SOPRANO":  this.unicode = "\uD834\uDD21";
                             this.shift = 178;
                             this.shiftFromTreble = -2;
                             break;
                            
            case "MEZZO_SOPRANO":  this.unicode = "\uD834\uDD21";
                                   this.shift = 158;
                                   this.shiftFromTreble = 3;
                                   break;
                            
            case "ALTO":    this.unicode = "\uD834\uDD21";
                            this.shift = 138;
                            this.shiftFromTreble = 1;
                            break;
                            
            case "TENOR":   this.unicode = "\uD834\uDD21";
                            this.shift = 118;
                            this.shiftFromTreble = -1;
                            break;
                            
            case "BARITONE_C":  this.unicode = "\uD834\uDD21";
                                this.shift = 98;
                                this.shiftFromTreble = -3;
                                break;
                            
            case "BARITONE_F":  this.unicode = "\uD834\uDD22";
                                this.shift = 145;
                                this.shiftFromTreble = -3;
                                break;
                            
            case "BASS":  this.unicode = "\uD834\uDD22";
                          this.shift = 125;
                          this.shiftFromTreble = 2;
                          break;
                            
            case "SUBBASS":  this.unicode = "\uD834\uDD22";
                             this.shift = 105;
                             this.shiftFromTreble = 0;
                             break;
                            
            default: throw new IllegalArgumentException();                                   
        }
        
        this.name = clef;
    }
    
    /**
     * This method is used to get a random clef among the ten possible clefs
     * @return The randomly generated clef
     */
    public static Clef getRandomClef() {
        
        String[] names = {"FRENCH", "TREBLE", "SOPRANO", "MEZZO_SOPRANO", "ALTO", "TENOR", "BARITONE_C", "BARITONE_F", "BASS", "SUBBASS"};
        
        Random r = new Random();
        int low = 0;
        int high = 10;
        int res = r.nextInt(high-low)+low;
        String s = names[res];
        return new Clef(s);   
    }
    
    /**
     * This method returns the clef name
     * @return The clef name as a String
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * This method returns the note shift from treble clef to encode notes in new clef 
     * @return The clef shift (number of notes) from notes in treble clef
     */
    public int getShiftFromTreble(){
        return this.shiftFromTreble;
    }
    
    /**
     * This method returns the clef vertical shift to define note y-position 
     * @return The clef vertical shift
     */
    int getShift() {
        return this.shift;
    }
    
    /**
     * This method returns the clef unicode
     * @return The clef unicode
     */
    @Override
    public String toString(){
        return this.unicode;
    }
    
    /**
     * This method returns whether two clefs are equal or not
     * @param j The object to compare with the Clef
     * @return true if the clefs are equals, false otherwise
     * @throws IllegalArgumentExcepion if the oblect isn't a Clef
     */
    @Override
    public boolean equals(Object j) throws IllegalArgumentException{
        if(j instanceof Clef){
            return this.getName().equals(((Clef)j).getName());
        } else {
            throw new IllegalArgumentException();
        }
    }        
    
}
