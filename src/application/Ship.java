package application;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    public enum ShipType {
        BATTLESHIP(3), DESTROYER(2), SUBMARINE(1);

        private final int size;
        ShipType(int size) { this.size = size; }
        public int getSize() { return size; }
    }

    private ShipType type;
    private boolean horizontal;
    private List<Cell> cells = new ArrayList<>();
    private int hits;
	private int length;

    public Ship(ShipType type, boolean horizontal) {
        this.type = type;
        this.horizontal = horizontal;
        this.hits = 0;
        
        switch(type) {
        case BATTLESHIP: this.length = 3; break;
        case DESTROYER: this.length = 2; break;
        case SUBMARINE: this.length = 1; break;
    }
    }

    public ShipType getType() { return type; }
    public boolean isHorizontal() { return horizontal; }
    public void setHorizontal(boolean horizontal) { this.horizontal = horizontal; }
    public List<Cell> getCells() { return cells; }

    public void addCell(Cell cell) { cells.add(cell); }

    public boolean isSunk() { return hits >= type.getSize(); }

    public void hit() { hits++; }
}
