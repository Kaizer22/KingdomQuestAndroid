package com.example.denis.kingdomquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startPlayingActivity(View view){

        Intent i = new Intent(this, PlayingActivity.class);
        EditText b = (EditText) this.findViewById(R.id.kingdom_name);
        i.putExtra("kingdom_name",b.getText().toString());
        startActivity(i);

        finish();
    }


}
