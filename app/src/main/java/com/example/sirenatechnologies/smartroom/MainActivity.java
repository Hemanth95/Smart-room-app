package com.example.sirenatechnologies.smartroom;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.sirenatechnologies.smartroom.R.drawable.nino;

public class MainActivity extends AppCompatActivity {
    socClient soc;
    int data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText ip = findViewById(R.id.ip);
        final ImageButton imageButton = findViewById(R.id.off);
        final Spinner sp =findViewById(R.id.spinner);

         final int[] state = {1};
         final int[] pos=new int[]{0,0,0,0,0,0,0,0,0,0,0};
        final int[] place = new int[1];


        final List<String> lights=new ArrayList<String>();
        lights.add("1st light");
        lights.add("2nd light");
        lights.add("3rd light");
        lights.add("4th light");
        lights.add("5th light");
        lights.add("6th light");
        lights.add("7th light");
        lights.add("8th light");
        lights.add("9th light");
        ArrayAdapter array = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lights);
        final ConstraintLayout con = findViewById(R.id.con);
        array.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(array);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                place[0] =position;
                if(pos[place[0]]==1) {
                    imageButton.setImageResource(R.drawable.lighton);

                    con.setBackgroundResource(R.drawable.nino);

                }
                else if (pos[place[0]]==0){
                    imageButton.setImageResource(R.drawable.lightoff);

                    con.setBackgroundColor(Color.parseColor("#18201b"));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

      imageButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(pos[place[0]]==0) {
                  imageButton.setImageResource(R.drawable.lighton);
                  pos[place[0]] =1;
                  con.setBackgroundResource(R.drawable.nino);
                  data=(place[0]+1)*10+1;
                 soc = new socClient(ip.getText().toString(),data);
                 soc.execute();
              }
              else if (pos[place[0]]==1){
                  imageButton.setImageResource(R.drawable.lightoff);
                  pos[place[0]] =0;
                  con.setBackgroundColor(Color.parseColor("#18201b"));
                  data=(place[0]+1)*10;
                  soc = new socClient(ip.getText().toString(),data);
                  soc.execute();
              }


          }
      });

    }
}
