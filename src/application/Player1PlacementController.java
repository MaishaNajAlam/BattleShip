package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;

public class Player1PlacementController {

    private static final int GRID_SIZE = 9;
    private static final int CELL_SIZE = 40;

    private Main mainApp;
    private GameState gameState;

    private boolean isHorizontal = true;
    private Ship.ShipType currentShipType;
    private int shipsPlaced = 0;

    private final Ship.ShipType[] shipsToPlace = {
        Ship.ShipType.BATTLESHIP,
        Ship.ShipType.DESTROYER, Ship.ShipType.DESTROYER,
        Ship.ShipType.SUBMARINE, Ship.ShipType.SUBMARINE, Ship.ShipType.SUBMARINE
    };

    @FXML private GridPane playerGrid;
    @FXML private Button rotateBtn;
    @FXML private Button randomBtn;
    @FXML private Button doneBtn;
    @FXML private ComboBox<String> shipCombo;
    @FXML private Label orientationLabel;
    @FXML private Label tipLabel;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @FXML
    private void initialize() {
        shipCombo.getItems().addAll(
            "Battleship (3)",
            "Destroyer (2)",
            "Submarine (1)"
        );
        shipCombo.getSelectionModel().select(0);
        currentShipType = Ship.ShipType.BATTLESHIP;

        orientationLabel.setText("Orientation: Horizontal");
        tipLabel.setText("Place your ships by clicking grid cells.");

        rotateBtn.setOnAction(e -> toggleOrientation());
        randomBtn.setOnAction(e -> randomizeShips());
        doneBtn.setOnAction(e -> finishPlacement());

        shipCombo.setOnAction(e -> {
            int index = shipCombo.getSelectionModel().getSelectedIndex();
            switch (index) {
                case 0: currentShipType = Ship.ShipType.BATTLESHIP; break;
                case 1: currentShipType = Ship.ShipType.DESTROYER; break;
                case 2: currentShipType = Ship.ShipType.SUBMARINE; break;
            }
        });

        doneBtn.setDisable(true); // disable Done button until all ships placed
    }

    // This is called after gameState is set
    public void initWithGameState() {
        setupGrid();
    }

    private void setupGrid() {
        playerGrid.getChildren().clear();
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                //cell.setFill(Color.LIGHTBLUE);
                cell.setFill(Color.web("#87CEFA"));
                
                cell.setStroke(Color.BLACK);

                final int r = row;
                final int c = col;

                cell.setOnMouseClicked(e -> handlePlaceShipClick(r, c));

                playerGrid.add(cell, col, row);
            }
        }
    }


    private void handlePlaceShipClick(int row, int col) {
        if (shipsPlaced >= shipsToPlace.length) {
            tipLabel.setText("All ships placed! Click 'Done'.");
            doneBtn.setDisable(false);
            return;
        }

        Ship.ShipType type = shipsToPlace[shipsPlaced];
        Ship newShip = new Ship(type, isHorizontal);

        boolean placed = gameState.getPlayer1Board().placeShip(newShip, row, col, isHorizontal);

        if (placed) {
            shipsPlaced++;
            tipLabel.setText("Placed " + type.name() + ". Ships placed: " + shipsPlaced + "/" + shipsToPlace.length);
            updateGridVisual();
            if (shipsPlaced == shipsToPlace.length) {
                tipLabel.setText("All ships placed! Click 'Done'.");
                doneBtn.setDisable(false);
            }
        } else {
            tipLabel.setText("Cannot place ship here.");
        }
    }

//    private void updateGridVisual() {
//        for (Node node : playerGrid.getChildren()) {
//            if (!(node instanceof Rectangle)) continue;
//            Integer col = GridPane.getColumnIndex(node);
//            Integer row = GridPane.getRowIndex(node);
//            if (row == null) row = 0;
//            if (col == null) col = 0;
//
//            Cell cell = gameState.getPlayer1Board().getCell(row, col);
//            Rectangle rect = (Rectangle) node;
//
//            if (cell.hasShip()) {
//                rect.setFill(Color.DARKGRAY);
//            } else {
//                rect.setFill(Color.LIGHTBLUE);
//            }
//        }
//    }
    private void updateGridVisual() {
        int shipCells = 0;
        for (Node node : playerGrid.getChildren()) {
            if (!(node instanceof Rectangle)) continue;
            Integer col = GridPane.getColumnIndex(node);
            Integer row = GridPane.getRowIndex(node);
            if (row == null) row = 0;
            if (col == null) col = 0;

            Cell cell = gameState.getPlayer1Board().getCell(row, col);
            Rectangle rect = (Rectangle) node;

            if (cell.hasShip()) {
                rect.setFill(Color.DARKGRAY);
                shipCells++;
            } else {
                //rect.setFill(Color.LIGHTBLUE);
            	rect.setFill(Color.web("#87CEFA"));
            	
            }
        }
        System.out.println("Ship cells: " + shipCells);
    }


    private void toggleOrientation() {
        isHorizontal = !isHorizontal;
        orientationLabel.setText(isHorizontal ? "Orientation: Horizontal" : "Orientation: Vertical");
    }

    private void randomizeShips() {
        gameState.getPlayer1Board().clearShips();  // clears ships and resets cells
        shipsPlaced = 0;
        tipLabel.setText("Randomizing ship placement...");

        boolean success = true;

        for (Ship.ShipType type : shipsToPlace) {
            int remain = 1;
            int tries = 0;
            while (remain > 0 && tries < 2000) {
                int row = (int) (Math.random() * GRID_SIZE);
                int col = (int) (Math.random() * GRID_SIZE);
                boolean horizontal = Math.random() < 0.5;

                Ship newShip = new Ship(type, horizontal);
                if (gameState.getPlayer1Board().placeShip(newShip, row, col, horizontal)) {
                    remain--;
                    shipsPlaced++;
                }
                tries++;
            }
            if (remain > 0) {
                success = false;
                break;
            }
        }

        if (success) {
            tipLabel.setText("Ships placed randomly! Click 'Done' when ready.");
            doneBtn.setDisable(false);
        } else {
            tipLabel.setText("Failed to place ships randomly. Try again.");
            doneBtn.setDisable(true);
        }

        updateGridVisual();
    }


    private void finishPlacement() {
        if (shipsPlaced < shipsToPlace.length) {
            tipLabel.setText("Place all ships before finishing.");
            return;
        }
        gameState.setPlayer1Ready(true);
        try {
            mainApp.showPlayer2Placement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
