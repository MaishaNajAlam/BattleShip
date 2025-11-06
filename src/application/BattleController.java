//package application;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.Node;
//
//public class BattleController {
//
//    private static final int GRID_SIZE = 9;
//    private static final int CELL_SIZE = 40;
//
//    private Main mainApp;
//    private GameState gameState;
//
//    @FXML private GridPane player1BattleGrid;
//    @FXML private GridPane player2BattleGrid;
//    @FXML private Label player1ShipsLabel;
//    @FXML private Label player2ShipsLabel;
//    @FXML private Label player1Label;
//    @FXML private Label player2Label;
//
//    public void setMainApp(Main mainApp) {
//        this.mainApp = mainApp;
//    }
//
//    public void setGameState(GameState gameState) {
//        this.gameState = gameState;
//    }
//
//    public void initWithGameState() {
//        setupGrid(player1BattleGrid, false);
//        setupGrid(player2BattleGrid, true);
//        updateShipLabels();
//        updateBattleHighlight();
//    }
//
//    private void setupGrid(GridPane gridPane, boolean isPlayer2Grid) {
//        gridPane.getChildren().clear();
//        for (int row = 0; row < GRID_SIZE; row++) {
//            for (int col = 0; col < GRID_SIZE; col++) {
//                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
////                cell.setFill(Color.LIGHTBLUE);
//                if (isPlayer2Grid) {
//                    cell.setFill(Color.web("#ff7f7f")); // light reddish
//                } else {
//                    cell.setFill(Color.SKYBLUE);
//                }
//                cell.setStroke(Color.BLACK);
//
//                final int r = row;
//                final int c = col;
//
//                if (isPlayer2Grid) {
//                    // Player 2 grid is target for Player 1 attacks
//                    cell.setOnMouseClicked(e -> handleAttack(2, r, c));
//
//                    // Highlight on hover if attackable
//                    cell.setOnMouseEntered(e -> {
//                        if (canAttackCell(gameState.getPlayer2Board(), r, c)) {
//                            cell.setFill(Color.LIGHTGREEN);
//                        }
//                    });
//                    //cell.setOnMouseExited(e -> resetCellColor(cell, gameState.getPlayer2Board().getCell(r, c)));
//                    if (isPlayer2Grid) {
//                        cell.setOnMouseExited(e -> resetCellColor(cell, gameState.getPlayer2Board().getCell(r, c), true));
//                    } else {
//                        cell.setOnMouseExited(e -> resetCellColor(cell, gameState.getPlayer1Board().getCell(r, c), false));
//                    }
//                } else {
//                    // Player 1 grid is target for Player 2 attacks
//                    cell.setOnMouseClicked(e -> handleAttack(1, r, c));
//
//                    // Highlight on hover if attackable
//                    cell.setOnMouseEntered(e -> {
//                        if (canAttackCell(gameState.getPlayer1Board(), r, c)) {
//                            cell.setFill(Color.LIGHTGREEN);
//                        }
//                    });
//                   // cell.setOnMouseExited(e -> resetCellColor(cell, gameState.getPlayer1Board().getCell(r, c), isPlayer2Grid));
//                    if (isPlayer2Grid) {
//                        cell.setOnMouseExited(e -> resetCellColor(cell, gameState.getPlayer2Board().getCell(r, c), true));
//                    } else {
//                        cell.setOnMouseExited(e -> resetCellColor(cell, gameState.getPlayer1Board().getCell(r, c), false));
//                    }
//
//                }
//
//                gridPane.add(cell, col, row);
//            }
//        }
//        updateGridVisuals();
//    }
//
//    private void resetCellColor(Rectangle cell, Cell cellData, boolean isPlayer2Grid) {
//        if (cellData.isHit()) {
//            if (cellData.hasShip()) {
//                cell.setFill(Color.RED);
//            } else {
//                cell.setFill(Color.GRAY);
//            }
//        } else {
//            if (isPlayer2Grid) {
//                cell.setFill(Color.web("#ff7f7f")); // reddish
//            } else {
//                cell.setFill(Color.SKYBLUE);
//            }
//        }
//    }
//
//
//    private boolean canAttackCell(Board board, int row, int col) {
//        Cell cell = board.getCell(row, col);
//        return !cell.isHit();
//    }
//
//    private void handleAttack(int targetPlayer, int row, int col) {
//        if (gameState.currentPlayer == targetPlayer) {
//            // It's the target player's own board - disallow attack on own board
//            return;
//        }
//
//        Board targetBoard = (targetPlayer == 1) ? gameState.getPlayer1Board() : gameState.getPlayer2Board();
//        Cell cell = targetBoard.getCell(row, col);
//
//        if (cell.isHit()) {
//            // Already attacked
//            return;
//        }
//
//        // Mark hit
//        cell.setHit(true);
//
//        if (cell.hasShip()) {
//            cell.getShip().hit();
//            updateShipLabels();
//        }
//
//        // Check if game over
//        if (targetBoard.allShipsSunk()) {
//            try {
//                mainApp.showGameOver(gameState.currentPlayer);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return;
//        }
//
//        // Switch turn
//        gameState.currentPlayer = (gameState.currentPlayer == 1) ? 2 : 1;
//
//        updateGridVisuals();
//    }
//
//    private void updateGridVisuals() {
//        updateGridVisual(player1BattleGrid, gameState.getPlayer1Board(), 1);
//        updateGridVisual(player2BattleGrid, gameState.getPlayer2Board(), 2);
//        updateBattleHighlight();
//    }
//
////    private void updateGridVisual(GridPane gridPane, Board board, int boardOwner) {
////        for (Node node : gridPane.getChildren()) {
////            if (!(node instanceof Rectangle)) continue;
////            Integer col = GridPane.getColumnIndex(node);
////            Integer row = GridPane.getRowIndex(node);
////            if (row == null) row = 0;
////            if (col == null) col = 0;
////
////            Cell cell = board.getCell(row, col);
////            Rectangle rect = (Rectangle) node;
////
////            if (cell.isHit()) {
////                if (cell.hasShip()) {
////                    rect.setFill(Color.RED);
////                } else {
////                    rect.setFill(Color.GRAY);
////                }
////            } else {
////                rect.setFill(Color.LIGHTBLUE);
////            }
////        }
////    }
////    private void updateGridVisual(GridPane gridPane, Board board, int boardOwner) {
////        boolean isPlayer2Grid = (boardOwner == 2);
////
////        for (Node node : gridPane.getChildren()) {
////            if (!(node instanceof Rectangle)) continue;
////            Integer col = GridPane.getColumnIndex(node);
////            Integer row = GridPane.getRowIndex(node);
////            if (row == null) row = 0;
////            if (col == null) col = 0;
////
////            Cell cell = board.getCell(row, col);
////            Rectangle rect = (Rectangle) node;
////
////            if (cell.isHit()) {
////                if (cell.hasShip()) {
////                    rect.setFill(Color.RED);
////                } else {
////                    rect.setFill(Color.GRAY);
////                }
////            } else {
////                if (isPlayer2Grid) {
////                    rect.setFill(Color.web("#ff7f7f")); // reddish
////                } else {
////                    rect.setFill(Color.SKYBLUE);
////                }
////            }
////        }
////    }
//    
//    private void updateGridVisual(GridPane gridPane, Board board, int boardOwner) {
//        boolean isPlayer2Grid = (boardOwner == 2);
//
//        Color player1Base = Color.web("#87CEEB");  // softer sky blue
//        Color player2Base = Color.web("#F08080");  // light coral
//
//        Color hitColor = Color.web("#B22222");     // firebrick (dark red)
//        Color missColor = Color.web("#DCDCDC");    // gainsboro (light gray)
//
//        for (Node node : gridPane.getChildren()) {
//            if (!(node instanceof Rectangle)) continue;
//            Integer col = GridPane.getColumnIndex(node);
//            Integer row = GridPane.getRowIndex(node);
//            if (row == null) row = 0;
//            if (col == null) col = 0;
//
//            Cell cell = board.getCell(row, col);
//            Rectangle rect = (Rectangle) node;
//
//            if (cell.isHit()) {
//                if (cell.hasShip()) {
//                    rect.setFill(hitColor);
//                } else {
//                    rect.setFill(missColor);
//                }
//            } else {
//                rect.setFill(isPlayer2Grid ? player2Base : player1Base);
//            }
//        }
//    }
//
//
//
//
//    private void updateShipLabels() {
//        int player1ShipsLeft = (int) gameState.getPlayer1Board().getShips().stream().filter(s -> !s.isSunk()).count();
//        int player2ShipsLeft = (int) gameState.getPlayer2Board().getShips().stream().filter(s -> !s.isSunk()).count();
//
//        // Simplified labels
//        player1ShipsLabel.setText("P1 Ships: " + player1ShipsLeft);
//        player2ShipsLabel.setText("P2 Ships: " + player2ShipsLeft);
//
//        player1Label.setText("P1 Board — Turn: P" + gameState.currentPlayer);
//        player2Label.setText("P2 Board — Turn: P" + gameState.currentPlayer);
//    }
//
//    private void updateBattleHighlight() {
//        if (gameState.currentPlayer == 1) {
//            player2Label.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
//            player2BattleGrid.setStyle("-fx-border-color: red; -fx-border-width: 3;");
//
//            player1Label.setStyle("");
//            player1BattleGrid.setStyle("");
//        } else {
//            player1Label.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
//            player1BattleGrid.setStyle("-fx-border-color: red; -fx-border-width: 3;");
//
//            player2Label.setStyle("");
//            player2BattleGrid.setStyle("");
//        }
//    }
//}


package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;

public class BattleController {

    private static final int GRID_SIZE = 9;
    private static final int CELL_SIZE = 40;

    private Main mainApp;
    private GameState gameState;

    @FXML private GridPane player1BattleGrid;
    @FXML private GridPane player2BattleGrid;
    @FXML private Label player1ShipsLabel;
    @FXML private Label player2ShipsLabel;
    @FXML private Label player1Label;
    @FXML private Label player2Label;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void initWithGameState() {
        setupGrid(player1BattleGrid, false);
        setupGrid(player2BattleGrid, true);
        updateShipLabels();
        updateBattleHighlight();
    }

    private void setupGrid(GridPane gridPane, boolean isPlayer2Grid) {
        gridPane.getChildren().clear();
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);

                // Use unified softer base colors here
                if (isPlayer2Grid) {
                    cell.setFill(Color.web("#F08080")); // light coral
                } else {
                    cell.setFill(Color.web("#87CEFA")); // softer sky blue
                }

                cell.setStroke(Color.BLACK);

                final int r = row;
                final int c = col;

                if (isPlayer2Grid) {
                    // Player 2 grid is target for Player 1 attacks
                    cell.setOnMouseClicked(e -> handleAttack(2, r, c));

                    // Highlight on hover if attackable
                    cell.setOnMouseEntered(e -> {
                        if (canAttackCell(gameState.getPlayer2Board(), r, c)) {
                            cell.setFill(Color.LIGHTGREEN);
                        }
                    });
                    cell.setOnMouseExited(e -> resetCellColor(cell, gameState.getPlayer2Board().getCell(r, c), true));
                } else {
                    // Player 1 grid is target for Player 2 attacks
                    cell.setOnMouseClicked(e -> handleAttack(1, r, c));

                    // Highlight on hover if attackable
                    cell.setOnMouseEntered(e -> {
                        if (canAttackCell(gameState.getPlayer1Board(), r, c)) {
                            cell.setFill(Color.LIGHTGREEN);
                        }
                    });
                    cell.setOnMouseExited(e -> resetCellColor(cell, gameState.getPlayer1Board().getCell(r, c), false));
                }

                gridPane.add(cell, col, row);
            }
        }
        updateGridVisuals();
    }

    private void resetCellColor(Rectangle cell, Cell cellData, boolean isPlayer2Grid) {
        if (cellData.isHit()) {
            if (cellData.hasShip()) {
                cell.setFill(Color.web("#B22222")); // firebrick (dark red)
            } else {
                cell.setFill(Color.web("#DCDCDC")); // gainsboro (light gray)
            }
        } else {
            if (isPlayer2Grid) {
                cell.setFill(Color.web("#F08080")); // light coral
            } else {
                cell.setFill(Color.web("#87CEEB")); // softer sky blue
            }
        }
    }

    private boolean canAttackCell(Board board, int row, int col) {
        Cell cell = board.getCell(row, col);
        return !cell.isHit();
    }

    private void handleAttack(int targetPlayer, int row, int col) {
        if (gameState.currentPlayer == targetPlayer) {
            // It's the target player's own board - disallow attack on own board
            return;
        }

        Board targetBoard = (targetPlayer == 1) ? gameState.getPlayer1Board() : gameState.getPlayer2Board();
        Cell cell = targetBoard.getCell(row, col);

        if (cell.isHit()) {
            // Already attacked
            return;
        }

        // Mark hit
        cell.setHit(true);

        if (cell.hasShip()) {
            cell.getShip().hit();
            updateShipLabels();
        }

        // Check if game over
        if (targetBoard.allShipsSunk()) {
            try {
                mainApp.showGameOver(gameState.currentPlayer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        // Switch turn
        gameState.currentPlayer = (gameState.currentPlayer == 1) ? 2 : 1;

        updateGridVisuals();
    }

    private void updateGridVisuals() {
        updateGridVisual(player1BattleGrid, gameState.getPlayer1Board(), 1);
        updateGridVisual(player2BattleGrid, gameState.getPlayer2Board(), 2);
        updateBattleHighlight();
    }

    private void updateGridVisual(GridPane gridPane, Board board, int boardOwner) {
        boolean isPlayer2Grid = (boardOwner == 2);

        Color player1Base = Color.web("#87CEEB");  // softer sky blue
        Color player2Base = Color.web("#F08080");  // light coral

        Color hitColor = Color.web("#B22222");     // firebrick (dark red)
        Color missColor = Color.web("#DCDCDC");    // gainsboro (light gray)

        for (Node node : gridPane.getChildren()) {
            if (!(node instanceof Rectangle)) continue;
            Integer col = GridPane.getColumnIndex(node);
            Integer row = GridPane.getRowIndex(node);
            if (row == null) row = 0;
            if (col == null) col = 0;

            Cell cell = board.getCell(row, col);
            Rectangle rect = (Rectangle) node;

            if (cell.isHit()) {
                if (cell.hasShip()) {
                    rect.setFill(hitColor);
                } else {
                    rect.setFill(missColor);
                }
            } else {
                rect.setFill(isPlayer2Grid ? player2Base : player1Base);
            }
        }
    }

    private void updateShipLabels() {
        int player1ShipsLeft = (int) gameState.getPlayer1Board().getShips().stream().filter(s -> !s.isSunk()).count();
        int player2ShipsLeft = (int) gameState.getPlayer2Board().getShips().stream().filter(s -> !s.isSunk()).count();

        // Simplified labels
        player1ShipsLabel.setText("Player1 Ships Left: " + player1ShipsLeft);
        player2ShipsLabel.setText("Player2 Ships Left: " + player2ShipsLeft);

//        player1Label.setText("P1 Board — Turn: P" + gameState.currentPlayer);
//        player2Label.setText("P2 Board — Turn: P" + gameState.currentPlayer);
    }

    private void updateBattleHighlight() {
        if (gameState.currentPlayer == 1) {
            player2Label.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            player2BattleGrid.setStyle("-fx-border-color: red; -fx-border-width: 3;");

            player1Label.setStyle("");
            player1BattleGrid.setStyle("");
        } else {
            player1Label.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            player1BattleGrid.setStyle("-fx-border-color: red; -fx-border-width: 3;");

            player2Label.setStyle("");
            player2BattleGrid.setStyle("");
        }
    }
}
