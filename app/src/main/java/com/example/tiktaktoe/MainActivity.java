package com.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int moves=0;

    int scorex=0;
    int scoreo=0;
//    player meaning
    //0-->x
    //1-->o
    int activePlayer=0;
//    state meaning
    //0-->x
    //1-->o
    //2-->blank
    int []activeState={2, 2 , 2, 2, 2, 2, 2, 2, 2};
    int winngingPositions[][]={{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    public void update_moves(){

            moves++;
            if(gameActive){
                TextView total_moves=findViewById(R.id.total_moves);
                total_moves.setText("Total Moves : "+moves);}


    }
public void playerTap(View view){

ImageView img=(ImageView)view;
int tapped=Integer.parseInt(img.getTag().toString());
if(!gameActive){
    gameReset(view);
    moves=0;
}
if(activeState[tapped]==2&&gameActive){
    update_moves();
    activeState[tapped]=activePlayer;
    img.setTranslationY(-1000f);
    if(activePlayer==0){
        activePlayer=1;
        img.setImageResource(R.drawable.x);
        TextView status = findViewById(R.id.status);
        status.setText("O's Turn - Tap to play");
    }
    else{
        activePlayer=0;
        img.setImageResource(R.drawable.o);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }

img.animate().translationYBy(1000f).setDuration(300);}
//winning function

    for(int []winposition:winngingPositions){

         if(activeState[winposition[0]]==activeState[winposition[1]]&&activeState[winposition[0]]==activeState[winposition[2]]&&activeState[winposition[0]]!=2){
//             some one is winner
             String winner="";
             gameActive=false;
             if(activeState[winposition[0]]==0){
                 winner="X Won";
                 scorex+=1;
             }
             else if(activeState[winposition[0]]==1){
                 winner="O Won";
                 scoreo+=1;
             }
//             update winner
             TextView status = findViewById(R.id.status);
             status.setText(winner);
             TextView score_x=findViewById(R.id.x_score);
             score_x.setText("X's Score : "+scorex);
             TextView score_o=findViewById(R.id.o_score);
             score_o.setText("O's Score : "+scoreo);

         }

    }


    boolean emptySquare = false;
    for (int squareState : activeState) {
        if (squareState == 2) {
            emptySquare = true;
            break;
        }
    }
    if (!emptySquare && gameActive) {
        // Game is a draw
        gameActive = false;
        String winnerStr;
        winnerStr = "Draw";
        TextView status = findViewById(R.id.status);
        status.setText(winnerStr);
    }
}

public void gameReset(View view){
    gameActive=true;
    activePlayer=0;
    for(int i=0;i<activeState.length;i++){
        activeState[i]=2;
    }
    ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
    ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    TextView status = findViewById(R.id.status);
    status.setText("X's Turn - Tap to play");
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    danish yousuf bhat
//    jammu and kashmir
}