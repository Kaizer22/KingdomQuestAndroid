package com.example.denis.kingdomquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayingActivity extends AppCompatActivity {
    public Story story = new Story();
    public Kingdom k = new Kingdom();
    public int choosenVariant = 0;
    public int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        Intent i = getIntent();
        k.kingdomName = i.getStringExtra("kingdom_name");
        nextKing();
        TextView sit = (TextView)findViewById(R.id.situation);
        //sit.setText(story.situations[n].text);
        createButtons(story.situations[n]);


    }

    public void nextKing(){
        story.newStory();
        k.newKing();
        //+ввод имени
    }

    public void chooseOne(View view){
        choosenVariant = 1;
        nextSituation();
    }

    public void chooseTwo(View view){
        choosenVariant = 2;
        nextSituation();
    }

    public void chooseThree(View view){
        choosenVariant = 3;
        nextSituation();
    }

    public void chooseFour(View view){
        choosenVariant = 4;
        nextSituation();
    }

    public void chooseFive(View view){
        choosenVariant = 5;
        nextSituation();
    }

    public void createButtons( Situation s){

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

        }
    }
}
