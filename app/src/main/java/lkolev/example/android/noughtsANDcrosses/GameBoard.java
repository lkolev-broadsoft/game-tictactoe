package lkolev.example.android.noughtsANDcrosses;


import android.widget.Button;

public class GameBoard {

    private int roundCount;
    Player player1 = new Player("Player 1", "X", false);
    Player player2 = new Player("Player 2", "O", true);
    private String [][] gameBoard;
    private int boardSize = 3;


    public GameBoard(int boardSize){
        this.gameBoard = new String [boardSize][boardSize];
        this.boardSize = boardSize;
    }

    public void updateGameBoard(Button buttons[][]){
        for(int i=0; i<this.boardSize;i++){
            for(int j=0;j<this.boardSize;j++){
               gameBoard[i][j] = buttons[i][j].getText().toString();
            }
        }
    }

    public boolean checkForWin(){
        for(int k =0; k < this.boardSize; k++){
            if(gameBoard[k][0].equals(gameBoard[k][1])
                    && gameBoard[k][0].equals(gameBoard[k][2])
                    && !gameBoard[k][0].equals("")){
                return true;
            }
        }

        for(int k =0; k < this.boardSize; k++){
            if(gameBoard[0][k].equals(gameBoard[1][k])
                    && gameBoard[0][k].equals(gameBoard[2][k])
                    && !gameBoard[0][k].equals("")){
                return true;
            }
        }

        if(gameBoard[0][0].equals(gameBoard[1][1])
                && gameBoard[0][0].equals(gameBoard[2][2])
                && !gameBoard[0][0].equals("")){
            return true;
        }

        if(gameBoard[0][2].equals(gameBoard[1][1])
                && gameBoard[0][2].equals(gameBoard[2][0])
                && !gameBoard[0][2].equals("")){
            return true;
        }

        return false;
    }

    public void resetBoard(){
        player1.setPlayerTurn(true);
        roundCount = 0;
        for(int i = 0; i < this.boardSize; i++){
            for(int j =0; j < this.boardSize; j++){
                gameBoard[i][j] = "";
            }
        }
    }

    void resetGame(){
        player1.setPlayerPoints(0);
        player2.setPlayerPoints(0);
        resetBoard();
    }

    public void playerWins(Player player){
        player.incrementPlayerPoints();
        resetBoard();
    }

    void incrementRoundCount(){
        roundCount++;
    }

    int getRoundCount(){
        return roundCount;
    }

    void setRoundCount(int rounds){
        this.roundCount= rounds;
    }

    public boolean checkPlayerTurn(Player player){
        if (player.getPlayerTurn()){
            return true;
        } else {
            return false;
        }
    }

    public void computerMove(){
        if(player2.getPlayerTurn()){
            
        }
    }


}
