package application;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int SIZE = 9;

    private Cell[][] grid;
    private List<Ship> ships;

    public Board() {
        grid = new Cell[SIZE][SIZE];
        for(int r=0; r<SIZE; r++) {
            for(int c=0; c<SIZE; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
        ships = new ArrayList<>();
    }

    public boolean placeShip(Ship ship, int startRow, int startCol, boolean horizontal) {
        int size = ship.getType().getSize();

        if (horizontal) {
            if (startCol + size > SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (grid[startRow][startCol + i].hasShip()) return false;
            }
            for (int i = 0; i < size; i++) {
                grid[startRow][startCol + i].setShip(ship);
                ship.addCell(grid[startRow][startCol + i]);
            }
        } else {
            if (startRow + size > SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (grid[startRow + i][startCol].hasShip()) return false;
            }
            for (int i = 0; i < size; i++) {
                grid[startRow + i][startCol].setShip(ship);
                ship.addCell(grid[startRow + i][startCol]);
            }
        }
        ship.setHorizontal(horizontal);
        ships.add(ship);
        return true;
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) return null;
        return grid[row][col];
    }

    public boolean allShipsSunk() {
        return ships.stream().allMatch(Ship::isSunk);
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void clearShips() {
        ships.clear();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Cell cell = getCell(row, col);
                cell.setShip(null);
                cell.setHit(false);  // reset hit status
            }
        }
    }



}
