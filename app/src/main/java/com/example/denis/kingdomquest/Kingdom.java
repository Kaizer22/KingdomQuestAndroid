package com.example.denis.kingdomquest;

/**
 * Created by denis on 28.01.17.
 */

public class Kingdom {
    public int Gold = 50;
    public int Population = 0;
    public int Sat = 0;
    public int Army = 0;
    public int Age = 0;
    public String kingName;
    public String kingdomName;

    public void newKing(){
        this.Gold = (int)(100* Math.random());
        this.Age = (int)(10 + (20*Math.random()));
        this.Population = (int)(10+90*Math.random());
        this.Army = (int)(this.Population/6+(this.Population/2*Math.random()));
        this.Sat = (int)(30 *Math.random());
    }

}
