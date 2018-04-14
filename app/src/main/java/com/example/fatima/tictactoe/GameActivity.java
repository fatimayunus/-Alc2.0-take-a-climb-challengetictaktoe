package com.example.fatima.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    // creating member variables
    private Button[][] buttons=new Button[3][3];
    private boolean player1Turn=true;
    private  int roundCount;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private int player1Points;
    private int player2Points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //getting references to our text view
        textViewPlayer1=findViewById(R.id.text_view_p1);
        textViewPlayer2=findViewById(R.id.text_view_p2);
        //designing button array with references to our buttons using nested loop
        for (int i =0; i<3; i++){
            for (int j=0; j<3; j++){
                //getting our button reference in a dynamic array

                String buttonID="button_"+i+j;

                int resID=getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }

        }
        //setting click listener to our buttton
        Button buttonReset=findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           resetGame();

            }
        });
    }

    @Override
    public void onClick(View v) {
        //to check if a button that was click contain an empty string
        if (!((Button)v).getText().toString().equals("")) {
            return;
        }
        // check if player1 turn is true set text to "X"
        if (player1Turn) {
            ((Button)v).setText("X");
        }else{
            //if player2 Turns set text to "O"
            ((Button)v).setText("O");

        }
        //increments roundcount
        roundCount++;
        // check if player1 wins or player two or draw
        if (checkForwin()){
            if (player1Turn){
                player1wins();
            }else{
                player2wins();
            }
        } else if (roundCount==9){
            draw();
        } else{
            player1Turn=!player1Turn;
        }

    }
    // check for winner
    private boolean checkForwin(){
        String [][] field=new String [3][3];
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        //checking the rows
        for (int i=0; i<3; i++){
            if (field[i][0].equals(field[i][1])&& field[i][0].equals(field[i][2])
                    && !field[i][1].equals("")){
                return true;
            }

        }
        // checking the column
        for (int i=0; i<3; i++){
            if (field[0][i].equals(field[1][i])&& field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }

        }
        //checking the diagonal

        if (field[0][2].equals(field[1][1])&& field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        if (field[0][0].equals(field[1][1])&& field[0][0].equals(field[2][2])
                && !field[0][2].equals("")){
            return true;
        }
        return false;

    }
    //creating method for player1 wins
    private void player1wins(){
        //increments player1 points
        player1Points++;
        //show toast message
        Toast.makeText(this,"player1 wins!",Toast.LENGTH_SHORT).show();
        //update our text view
        updateTextPoints();
        //to start another round when there is a winner
        resetBoard();
    }
    //creating method for player2 wins
    private void player2wins(){
        player2Points++;
        Toast.makeText(this,"player2 wins!",Toast.LENGTH_SHORT).show();
        updateTextPoints();
        resetBoard();
    }
    //creating method for draw after nine round
    private void draw(){
        Toast.makeText(this, "draw", Toast.LENGTH_SHORT).show();

    }
    private void updateTextPoints(){
        //takes two references and update them
        textViewPlayer1.setText("X player:"+player1Points);
        textViewPlayer2.setText("O player;"+ player2Points);
    }
    //reset all our button to empty string
    private void resetBoard(){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        //player one start the next round again
        player1Turn=true;

    }
    public void resetGame(){
        player1Points++;
        player2Points++;
        updateTextPoints();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundCount);
        outState.putInt("player1Points",  player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount=savedInstanceState.getInt("roundCount");
        player1Points=savedInstanceState.getInt("player1Points");
        player2Points=savedInstanceState.getInt("player2points");
        player1Turn=savedInstanceState.getBoolean("player1Turn");
    }
}

