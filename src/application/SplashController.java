package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SplashController {
    private Main mainApp;

    @FXML private Button startGameBtn;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        startGameBtn.setOnAction(e -> {
            try {
                mainApp.showPlayer1Placement();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
