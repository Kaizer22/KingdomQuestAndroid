package com.example.denis.kingdomquest;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayingActivity extends AppCompatActivity {
    public Story story;
    public Kingdom k = new Kingdom();
    public int choosenVariant = 0;
    public int n = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        final AssetManager am = this.getAssets();


        class SitLoader extends AsyncTask<Void, Void, Void>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                ImageView iv = (ImageView) findViewById(R.id.loading);
                iv.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(Void... args0) {
                story = new Story(am);
                return null;
            }

            @Override
            protected void onPostExecute(Void o) {
                super.onPostExecute(o);

                ImageView iv = (ImageView) findViewById(R.id.loading);
                iv.setVisibility(View.INVISIBLE);
            }
        }


        new SitLoader().execute();

        Intent i = getIntent();
        k.kingdomName = i.getStringExtra("kingdom_name");

        hideButtons();

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

    public void hideButtons(){
        Button b1 = (Button) findViewById(R.id.button1); b1.setVisibility(View.INVISIBLE);
        Button b2 = (Button) findViewById(R.id.button2); b2.setVisibility(View.INVISIBLE);
        Button b3 = (Button) findViewById(R.id.button3); b3.setVisibility(View.INVISIBLE);
        Button b4 = (Button) findViewById(R.id.button4); b4.setVisibility(View.INVISIBLE);
        Button b5 = (Button) findViewById(R.id.button5); b5.setVisibility(View.INVISIBLE);
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

    public void nextKing(){

        Button conf = (Button) findViewById(R.id.confirm_king);
        EditText e = (EditText) findViewById(R.id.editName);
        e.setVisibility(View.VISIBLE);
        conf.setVisibility(View.VISIBLE);
        hideButtons();
        TextView sit = (TextView)findViewById(R.id.situation);
        sit.setText(getString(R.string.next_king_p1) + "\n"
                  + getString(R.string.next_king_p2) + "\n"
                  + getString(R.string.next_king_p3) + "\n"
                  + k.kingName + "? " + getString(R.string.next_king_p4));
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

    public void showToast(String s){
        Toast toast = Toast.makeText(this,s,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void nextSituation(){
        story.situations[n].chooseVariant(choosenVariant,k);
        switch(story.End(k)) {
            case 1:
                updateScreen();
                showToast(getString(R.string.game_over_1));
                nextKing();
                break;
            case 2:
                updateScreen();
                showToast(getString(R.string.game_over_2));
                nextKing();
                break;
            case 3:
                updateScreen();
                showToast(getString(R.string.game_over_3));
                nextKing();
                break;
            case 4:
                updateScreen();
                showToast(getString(R.string.game_over_4));
                nextKing();
                break;
            case 5:
                updateScreen();
                showToast(getString(R.string.game_over_5));
                nextKing();
                break;
            default:
            {
                if (n == (story.situations.length-1)) {
                    n = 0;
                    story.newStory();
                }else
                  n++;
                updateScreen();
            }

        }
    }
}
