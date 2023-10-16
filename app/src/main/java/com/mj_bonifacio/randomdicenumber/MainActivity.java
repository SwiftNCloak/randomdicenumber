package com.mj_bonifacio.randomdicenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView dicePics;
    Button playB, resetB;
    TableLayout tableLayout;
    TextView currCell, startCell;
    int currPos = 1;
    int roll, moves, highlighted, resourceId;
    String pl, viewId;

    boolean goBack = false;

    Integer[] dices = { R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dicePics = findViewById(R.id.dice);
        playB = findViewById(R.id.playBtn);
        resetB = findViewById(R.id.resetBtn);

        tableLayout = findViewById(R.id.tableLayout);

        startCell = findViewById(R.id.nstart);
        startCell.setBackgroundResource(R.drawable.updated_box);
    }



    public void Play(View play) {
        startCell = findViewById(R.id.nstart);
        int moves = DiceRoll();

        while (moves > 0) {
            if(currPos < 60){
                ChangePosToUnselect();
                currPos++;
                ChangePosToSelect();
            }
            else if(currPos == 60){
                goBack = true;
            }
            if(goBack){
                while (goBack && moves > 0) {
                    ChangePosToUnselect();
                    currPos--;
                    ChangePosToSelect();
                    moves--;
                }
            }
            moves--;
        }
        goBack = false;
        if(currPos == 60){
            Toast.makeText(this,"You win!", Toast.LENGTH_LONG).show();
        }
    }

    public void Reset(View reset) {
        dicePics.setImageResource(dices[0]);
        ChangePosToUnselect();
        currPos = 1;
        ChangePosToSelect();
    }

    public int DiceRoll(){
        Random random = new Random();
        roll = random.nextInt(6);
        dicePics.setImageResource(dices[roll]);
        moves = roll + 1;
        return moves;
    }

    public void ChangePosToUnselect(){
        viewId = "pos" + currPos;
        resourceId = getResources().getIdentifier(viewId, "id", getPackageName());
        currCell = findViewById(resourceId);
        currCell.setBackgroundResource(R.drawable.border_color);
    }

    public void ChangePosToSelect(){
        viewId = "pos" + currPos;
        resourceId = getResources().getIdentifier(viewId, "id", getPackageName());
        currCell = findViewById(resourceId);
        currCell.setBackgroundResource(R.drawable.updated_box);
    }
}