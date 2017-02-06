package com.example.denis.kingdomquest;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by denis on 28.01.17.
 */

public class Story {
    public Situation situations[] = new Situation[11];
    //**                  Variant(int Gold, int Population, int Sat, int Army,int Age)
    Story(Context context){
        try{
            AssetManager am = context.getAssets();
            InputStream in = am.open("test");
            Scanner sc = new Scanner(in);
            int n = 0;
            while((sc.hasNext())  && (n < situations.length)){
                situations[n] = new Situation(sc.nextLine(),sc.nextInt());
                for (int i = 0; i < situations[n].variants.length ; i++){
                    sc.nextLine();
                    situations[n].variants[i] = new Variant(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt());
                }
                sc.nextLine();
                n++;
            }
            sc.close();
            in.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newStory(){
        Situation s;
        int k;
        for (int i = 0; i < this.situations.length ; i++) {
            k = (int)(Math.random()*(this.situations.length-1));
            s = this.situations[k];
            this.situations[k] = this.situations[i];
            this.situations[i] = s;
        }
    }



    public int End(Kingdom k){
        if (k.Gold <= 0){
            return 1;
        }else if(k.Sat <= 0){
            return 2;
        }else if(k.Population < 10){
            return 3;
        }else if(k.Age >= (40+(20*Math.random()))){
            return 4;
        }else if(k.Army < k.Population/6){
            return 5;
        }else
            return 0;
    }


}
