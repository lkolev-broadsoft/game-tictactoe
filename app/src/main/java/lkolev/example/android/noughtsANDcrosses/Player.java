package lkolev.example.android.noughtsANDcrosses;

public class Player {

    private String playerName;
    private String playerToken;
    private int playerPoints;
    private boolean playerTurn;
    private boolean iaPlayer;

    public Player(String name, String token, boolean ai){
        this.playerName = name;
        this.playerPoints = 0;
        this.playerToken = token;

        if(ai){
            this.playerTurn = false;
            this.iaPlayer = true;
        } else{
            this.playerTurn = true;
            this.iaPlayer = false;
        }
    }

    public void setPlayerPoints(int points){
        this.playerPoints = points;
    }

    public void setPlayerToken( String token){
        this.playerToken = token;
    }

    public void setPlayerTurn(boolean playerTurn){
        this.playerTurn = playerTurn;
    }

    public String getPlayerToken(){
        return this.playerToken;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public boolean getPlayerTurn(){
        return playerTurn;
    }

    public int getPlayerPoints(){
        return this.playerPoints;
    }

    public boolean isPlayerHuman(){
        return (!this.iaPlayer);
    }

    public void incrementPlayerPoints(){
        playerPoints++;
    }
}
