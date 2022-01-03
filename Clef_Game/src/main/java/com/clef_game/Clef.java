/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clef_game;

import java.util.Random;

/**
 *
 * @author lomba
 */
class Clef {

    //private final String[] names = {"FRENCH", "TREBLE", "SOPRANO", "MEZZO_SOPRANO", "ALTO", "TENOR", "BARITONE_C", "BARITONE_F", "BASS", "SUBBASS"};
    private String unicode;
    private String name;
    private int shift;
    private int shiftFromTreble;
    
    public Clef(String clef){
        this.name = clef;
        switch(name){
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
    }
    
    public static Clef getRandomClef() {
        
        String[] names = {"FRENCH", "TREBLE", "SOPRANO", "MEZZO_SOPRANO", "ALTO", "TENOR", "BARITONE_C", "BARITONE_F", "BASS", "SUBBASS"};
        
        Random r = new Random();
        int low = 0;
        int high = 10;
        int res = r.nextInt(high-low)+low;
        String s = names[res];
        return new Clef(s);   
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getShiftFromTreble(){
        return this.shiftFromTreble;
    }
    
    @Override
    public String toString(){
        return this.unicode;
    }
    
    @Override
    public boolean equals(Object j){
        if(j instanceof Clef){
            return this.getName().equals(((Clef)j).getName());
        } else {
            throw new IllegalArgumentException();
        }
    }        
    
    int getShift() {
        return this.shift;
    }
    
}
