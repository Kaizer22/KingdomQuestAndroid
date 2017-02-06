package com.example.denis.kingdomquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayingActivity extends AppCompatActivity {
    public Story story;
    public Kingdom k = new Kingdom();
    public int choosenVariant = 0;
    public int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        Intent i = getIntent();
        k.kingdomName = i.getStringExtra("kingdom_name");
        story = new Story(this);

        Button b1 = (Button) findViewById(R.id.button1); b1.setVisibility(View.INVISIBLE);
        Button b2 = (Button) findViewById(R.id.button2); b2.setVisibility(View.INVISIBLE);
        Button b3 = (Button) findViewById(R.id.button3); b3.setVisibility(View.INVISIBLE);
        Button b4 = (Button) findViewById(R.id.button4); b4.setVisibility(View.INVISIBLE);
        Button b5 = (Button) findViewById(R.id.button5); b5.setVisibility(View.INVISIBLE);


        TextView sit = (TextView)findViewById(R.id.situation);
        sit.setText(getString(R.string.write_name));



    }

    public void updateScreen(){
        TextView sit = (TextView) findViewById(R.id.situation);
        sit.setText(story.situations[n].text);

        TextView king = (TextView) findViewById(R.id.king_text);
        king.setText(getString(R.string.playing_king) + " " + k.kingName);

        TextView kdom = (TextView) findViewById(R.id.kingdom_text);
        kdom.setText(getString(R.string.playing_kingdom) + " " + k.kingdomName);

        TextView gol = (TextView) findViewById(R.id.gold);
        gol.setText(getString(R.string.playing_gold) + " " + k.Gold);

        TextView arm = (TextView) findViewById(R.id.army);
        arm.setText(getString(R.string.playing_army) + " " + k.Army);

        TextView pop = (TextView) findViewById(R.id.population);
        pop.setText(getString(R.string.playing_population) + " " + k.Population);

        TextView age = (TextView) findViewById(R.id.age);
        age.setText(getString(R.string.playing_age) + " " + k.Age);

        TextView sat = (TextView) findViewById(R.id.satisfaction);
        sat.setText(getString(R.string.playing_satisfaction) + " " + k.Sat);

        createButtons(story.situations[n]);
    }

    public void newKing(View view){
        story.newStory();
        k.newKing();
        Button conf = (Button) findViewById(R.id.confirm_king);
        EditText e = (EditText) findViewById(R.id.editName);
        e.setVisibility(View.INVISIBLE);
        conf.setVisibility(View.INVISIBLE);
        k.kingName = e.getText().toString();

        Button b1 = (Button) findViewById(R.id.button1); b1.setVisibility(View.VISIBLE);
        Button b2 = (Button) findViewById(R.id.button2); b2.setVisibility(View.VISIBLE);


        updateScreen();




    }

    public void chooseOne(View view){
        choosenVariant = 0;
        nextSituation();
    }

    public void chooseTwo(View view){
        choosenVariant = 1;
        nextSituation();
    }

    public void chooseThree(View view){
        choosenVariant = 2;
        nextSituation();
    }

    public void chooseFour(View view){
        choosenVariant = 3;
        nextSituation();
    }

    public void chooseFive(View view){
        choosenVariant = 4;
        nextSituation();
    }

    public void createButtons( Situation s){
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        switch (s.variants.length){
            case 2:
                b3.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
                break;
            case 3:
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
                break;
            case 4:
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.INVISIBLE);
                break;
            default:
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
        }

    }

    public void nextSituation(){
        story.situations[n].chooseVariant(choosenVariant,k);
        switch(story.End(k)) {
            case 1:
                System.out.println("Наша страна обнищала! Уже совсем скоро народу будет нечего есть! Я настоятельно \n" +
                        "рекомендую вам отречься от престола, пока разгневанный народ не вышел на улицы.");
                break;
            case 2:
                System.out.println("Народ недоволен! Вы что, не видете этого !? Ваше правление принесло этим людям большие беды!");
                break;
            case 3:
                System.out.println("Лоюди умирают или бегут из страны! Скоро вам будет некем править.");
                break;
            case 4:
                System.out.println("Наш славный король умер! Он мог так много успеть сделать для королевства!");
                break;
            case 5:
                System.out.println("Другое королевство вторглось на нашу территорию! Нам нечем им ответить - наших войск недостаточно!");
                break;
            default:
            {
                if (n == story.situations.length) {
                    n = 0;
                    story.newStory();
                }else
                  n++;
                updateScreen();
            }

        }
    }
}
