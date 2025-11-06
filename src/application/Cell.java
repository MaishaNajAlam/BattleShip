package application;

public class Cell {
    private int row, col;
    private boolean hasShip;
    private boolean hit;
    private Ship ship;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.hasShip = false;
        this.hit = false;
        this.ship = null;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public boolean hasShip() {
        return this.ship != null;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
        this.hasShip = true;
    }
    public Ship getShip() { return ship; }

    public boolean isHit() { return hit; }
    public void setHit(boolean hit) { this.hit = hit; }
}
