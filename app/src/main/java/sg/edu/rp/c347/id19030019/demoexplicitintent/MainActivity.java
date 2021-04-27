package sg.edu.rp.c347.id19030019.demoexplicitintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int requestCodeForSupermanStats = 1;
    int requestCodeForBatmanStats = 2;
    TextView tvSuperman, tvBatman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSuperman = (TextView) findViewById(R.id.textViewSuperman);
        tvBatman = (TextView) findViewById(R.id.textViewBatman);

        //Set listener to handle thr clicking of SUperman Textview
        tvSuperman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create Hero object of strength 100 and technical 60
                Hero superman = new Hero("Superman", 100, 60);
                Intent i = new Intent(MainActivity.this, HeroStatsActivity.class);
                //Put hero object in intent
                i.putExtra("hero", superman);
//                startActivity(i);
                //Start activity with requestCodeForSupermanStats to
                //identify it was started by clicking on Superman
                startActivityForResult(i,requestCodeForSupermanStats);
            }
        });
        //set listener to handle the clicking of Batman textview
        tvBatman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create hero object of strength 60 and technical 90
                Hero batman = new Hero("Batman", 60, 90);
                Intent i = new Intent(MainActivity.this, HeroStatsActivity.class);
                //Put hero object in intent
                i.putExtra("hero",batman);
                //Start activity
//                startActivity(i);
                startActivityForResult(i,requestCodeForBatmanStats);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Only handle when 2nd activity closed normally
        //and data contains something
        if(resultCode == RESULT_OK){
            if(data != null){
                //Get data passed back from 2nd activity
                String like = data.getStringExtra("like");
                String statement = "";
                //If it is activity started by clicking
                //Superman, create corresponding String
                if(requestCode == requestCodeForSupermanStats){
                    statement = "You " + like + " Superman";
                }
                //If 2nd activity started by clicking
                //Batman, create a corresponding String
                if(requestCode == requestCodeForBatmanStats){
                    statement = "You " + like + " Batman";
                }

                Toast.makeText(MainActivity.this, statement, Toast.LENGTH_LONG).show();
            }
        }
    }
}