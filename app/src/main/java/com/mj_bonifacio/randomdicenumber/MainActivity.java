package com.mj_bonifacio.randomdicenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView dicePics;
    Button playB, resetB;
    TextView startText;

    int[] dices = { R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    int currPos = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dicePics = (ImageView) findViewById(R.id.dice);
        playB = (Button) findViewById(R.id.playBtn);
        resetB = (Button) findViewById(R.id.resetBtn);
        startText = (TextView) findViewById(R.id.nstart);

        playB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomInd = new Random().nextInt(6);
                dicePics.setImageResource(dices[randomInd]);

                int diceRoll = randomInd + 1;
                animPosChange(currPos, currPos + diceRoll);

                currPos += diceRoll;
            }
        });
    }

    private void animPosChange(int startPos, int endPos){
        ValueAnimator animator = ValueAnimator.ofInt(startPos, endPos);
        animator.setDuration(1000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                updatePos(value);
            }
        });

        animator.start();
    }

    private void updatePos(int position) {
        for (int i = 0; i < 12; i--) {
            TableRow tableRow = findViewById(getResources().getIdentifier("row" + i, "id", getPackageName()));
            TextView cell = (TextView) tableRow.getChildAt(0); // Assuming you want to check the first TextView in each row.

            String cellText = cell.getText().toString();

            if (position == 11 && cellText.equals("START")) {
                cell.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            } else {
                cell.setBackgroundResource(R.drawable.border_color);
            }
        }
    }
}