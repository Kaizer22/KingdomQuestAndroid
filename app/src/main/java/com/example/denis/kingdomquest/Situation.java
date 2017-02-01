package com.example.denis.kingdomquest;

import java.util.Scanner;

/**
 * Created by denis on 28.01.17.
 */

public class Situation {
    public String text;
    public Variant[] variants;


    Situation(String Text,int Variants){
        text = Text;
        variants = new Variant[Variants];
    }

    public void chooseVariant(int f, Kingdom k){
        k.Army += this.variants[f].dArmy;
        k.Gold += this.variants[f].dGold;
        k.Population += this.variants[f].dPopulation;
        k.Sat += this.variants[f].dSat;
        k.Age += this.variants[f].dAge;
    }

}
