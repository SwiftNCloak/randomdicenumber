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
    int[] boxes = {
            R.id.nstart, R.id.n2, R.id.n3, R.id.n4, R.id.n5, R.id.n6,
            R.id.n7, R.id.n8, R.id.n9, R.id.n10, R.id.n11, R.id.n12,
            R.id.n13, R.id.n14, R.id.n15, R.id.n16, R.id.n17, R.id.n18,
            R.id.n19, R.id.n20, R.id.n21, R.id.n22, R.id.n23, R.id.n24,
            R.id.n25, R.id.n26, R.id.n27, R.id.n28, R.id.n29, R.id.n30,
            R.id.n31, R.id.n32, R.id.n33, R.id.n34, R.id.n35, R.id.n36,
            R.id.n37, R.id.n38, R.id.n39, R.id.n40, R.id.n41, R.id.n42,
            R.id.n43, R.id.n44, R.id.n45, R.id.n46, R.id.n47, R.id.n48,
            R.id.n49, R.id.n50, R.id.n51, R.id.n52, R.id.n53, R.id.n54,
            R.id.n55, R.id.n56, R.id.n57, R.id.n58, R.id.n59, R.id.nend,
    };

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
                for(int i=0; i<60; i++){
                    TextView boxText = (TextView) findViewById(boxes[i]);
                    if (i <= currPos) {
                        boxText.setBackgroundResource(R.drawable.updated_box);
                    } else {
                        boxText.setBackgroundResource(R.drawable.border_color);
                    }
                }
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