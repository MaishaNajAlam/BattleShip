package application;

public class GameState {

    public static int currentPlayer = 1;
	public Board player1Board = new Board();
    private Board player2Board = new Board();

    private boolean player1Ready = false;
    private boolean player2Ready = false;

    public Board getPlayer1Board() { return player1Board; }
    public Board getPlayer2Board() { return player2Board; }

    public boolean isPlayer1Ready() { return player1Ready; }
    public void setPlayer1Ready(boolean ready) { this.player1Ready = ready; }

    public boolean isPlayer2Ready() { return player2Ready; }
    public void setPlayer2Ready(boolean ready) { this.player2Ready = ready; }
}
