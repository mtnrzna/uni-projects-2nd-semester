package com.company;

public class CellInfos {
    private boolean Player1InIt;
    private boolean Player2InIt;
    private boolean wallInIt;
    private boolean StarInIt;

    public boolean isPlayer1InIt() {
        return Player1InIt;
    }
    public void setPlayer1InIt(boolean player1InIt) {
        Player1InIt = player1InIt;
    }
    public boolean isPlayer2InIt() {
        return Player2InIt;
    }
    public void setPlayer2InIt(boolean player2InIt) {
        Player2InIt = player2InIt;
    }
    public boolean isWallInIt() {
        return wallInIt;
    }
    public void setWallInIt(boolean wallInIt) {
        this.wallInIt = wallInIt;
    }
    public boolean isStarInIt() {
        return StarInIt;
    }
    public void setStarInIt(boolean starInIt) {
        StarInIt = starInIt;
    }
}
