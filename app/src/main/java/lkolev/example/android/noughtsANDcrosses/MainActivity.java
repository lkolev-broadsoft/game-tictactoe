package lkolev.example.android.noughtsANDcrosses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private TextView textViewPlayer1Points;
    private TextView textViewPlayer2Points;
    private GameBoard gameBoard = new GameBoard(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1Points = findViewById(R.id.text_view_player1);
        textViewPlayer2Points = findViewById(R.id.text_view_player2);

        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                String buttonID = "button_" + i + j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resourceID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        gameBoard.updateGameBoard(buttons);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (gameBoard.checkPlayerTurn(gameBoard.player1)) {
            ((Button) v).setText(gameBoard.player1.getPlayerToken());
        } else {
            ((Button) v).setText(gameBoard.player2.getPlayerToken());
        }

        gameBoard.updateGameBoard(buttons);
        gameBoard.incrementRoundCount();


        if(gameBoard.checkForWin()){
            if(gameBoard.checkPlayerTurn(gameBoard.player1)){
                player1Wins();
            } else {
                player2Wins();
            }
        } else if(gameBoard.getRoundCount() == 9){
            draw();
        } else {
            gameBoard.player1.setPlayerTurn(!gameBoard.player1.getPlayerTurn());
        }
    }


    private void player1Wins(){
        gameBoard.playerWins(gameBoard.player1);
        Toast.makeText(this,"Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        gameBoard.resetBoard();
        resetBoardUI();
    }

    private void player2Wins(){
        gameBoard.playerWins(gameBoard.player2);
        Toast.makeText(this,"Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        gameBoard.resetBoard();
        resetBoardUI();

    }

    private void draw(){
        Toast.makeText(this,"Draw!", Toast.LENGTH_SHORT).show();
        gameBoard.resetBoard();
        resetBoardUI();
    }

    private void updatePointsText() {
        textViewPlayer1Points.setText("Player 1: " + gameBoard.player1.getPlayerPoints());
        textViewPlayer2Points.setText("Player 2: " + gameBoard.player2.getPlayerPoints());
    }

    private void resetBoardUI() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    private void resetGame(){
        gameBoard.resetGame();
        updatePointsText();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", gameBoard.getRoundCount());
        outState.putInt("player1Points", gameBoard.player1.getPlayerPoints());
        outState.putInt("player2Points", gameBoard.player2.getPlayerPoints());
        outState.putBoolean("player1Turn", gameBoard.player1.getPlayerTurn());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gameBoard.setRoundCount(savedInstanceState.getInt("roundCount"));
        gameBoard.player1.setPlayerPoints(savedInstanceState.getInt("player1Points"));
        gameBoard.player2.setPlayerPoints(savedInstanceState.getInt("player2Points"));
        gameBoard.player1.setPlayerTurn(savedInstanceState.getBoolean("player1Turn"));
    }
}
