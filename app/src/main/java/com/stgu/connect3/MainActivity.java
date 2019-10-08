package com.stgu.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView winnerLabel;
    private Button playAgainButton;

    private TextView redsTurn;
    private TextView yellowsTurn;


    private boolean player1 = true;
    private boolean gameFinished = false;

    private int[] score = {0, 0, 0, 0, 0, 0, 0, 0 , 0}; //0: empty, 1: player1, 2: player2


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winnerLabel = findViewById(R.id.winnerLabel);
        playAgainButton = findViewById(R.id.playAgainButton);
        redsTurn = findViewById(R.id.redsTurn);
        yellowsTurn = findViewById(R.id.yellowsTurn);
        yellowsTurn.setVisibility(View.INVISIBLE);


    }

    public void onClick(View view){
        ImageView selectedField = (ImageView) view;

        int index = Integer.parseInt(selectedField.getTag().toString());

        if(score[index] != 0 || gameFinished) return;

        score[index] = player1 ? 1: 2;
        selectedField.setTranslationY(-1500);
        selectedField.setImageResource(player1 ? R.drawable.red : R.drawable.yellow);
        selectedField.animate().translationYBy(1500).rotation(360).setDuration(500);
        if(checkWin()) return;
        player1 = !player1;

        redsTurn.setVisibility(player1 ? View.VISIBLE : View.INVISIBLE);
        yellowsTurn.setVisibility(player1 ? View.INVISIBLE : View.VISIBLE);



    }

    private boolean checkWin(){
        System.out.println(Arrays.toString(score));
        if((score[0] == score[1] && score[1] == score[2] && score[0] != 0) || (score[3] == score[4] && score[4] == score[5] && score[3] != 0) || (score[6] == score[7] && score[7] == score[8] && score[6] != 0) ||
                (score[0] == score[3] && score[3] == score[6] && score[0] != 0) || (score[1] == score[4] && score[4] == score[7] && score[1] != 0) || (score[2] == score[5] && score[5] == score[8] && score[2] != 0) ||
                (score[0] == score[4] && score[4] == score[8] && score[0] != 0) || (score[2] == score[4] && score[4] == score[6] && score[2] != 0)
        ){
            System.out.println("Player1 wins: " + player1);
            gameFinished = true;
            redsTurn.setVisibility(View.INVISIBLE);
            yellowsTurn.setVisibility(View.INVISIBLE);

            String text = player1 ? "Red wins!" : "Yellow wins!";
            winnerLabel.setText(text);
            winnerLabel.setTextColor(player1 ? Color.RED : Color.YELLOW);
            winnerLabel.setVisibility(View.VISIBLE);
            playAgainButton.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }


    public void playAgain(View view){
        for(int i=0; i<score.length; i++) score[i] = 0;

        ConstraintLayout constraintLayout = findViewById(R.id.layout);

        for(int i=0; i<constraintLayout.getChildCount(); i++){
            try {
                ImageView img = (ImageView) constraintLayout.getChildAt(i);
                System.out.println(img.getTag().toString());
                if(!img.getTag().toString().equals("board")) img.setImageResource(0);
            }catch(Exception e) {
                continue;
            }

        }

        gameFinished = false;
        player1 = !player1;

        winnerLabel.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        redsTurn.setVisibility(player1 ? View.VISIBLE : View.INVISIBLE);
        yellowsTurn.setVisibility(player1 ? View.INVISIBLE : View.VISIBLE);
    }
}
