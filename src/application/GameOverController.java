package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Platform;

public class GameOverController {
    private Main mainApp;

    @FXML private Label winnerLabel;
    @FXML private Button restartBtn;
    @FXML private Button exitBtn;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setWinner(int playerNumber) {
        winnerLabel.setText("Player " + playerNumber + " wins!");
    }

    @FXML
    private void initialize() {
        restartBtn.setOnAction(e -> {
            try {
                mainApp.showSplashScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        exitBtn.setOnAction(e -> {
            Platform.exit();
        });
    }

	
}
