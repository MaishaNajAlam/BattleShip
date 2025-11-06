package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private GameState gameState;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.gameState = new GameState();//gamestate remembers the players turn,also related things

        showSplashScreen();
    }

    public void showSplashScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SplashScreen.fxml"));
        Parent root = loader.load();
        SplashController controller = loader.getController();
        controller.setMainApp(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Battleship Game");
        primaryStage.show();
    }

    public void showPlayer1Placement() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Player1Placement.fxml"));
        Parent root = loader.load();
        Player1PlacementController controller = loader.getController();
        controller.setMainApp(this);
        controller.setGameState(gameState);
        controller.initWithGameState();
        primaryStage.setScene(new Scene(root));
    }

    public void showPlayer2Placement() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Player2Placement.fxml"));
        Parent root = loader.load();
        Player2PlacementController controller = loader.getController();
        controller.setMainApp(this);
        controller.setGameState(gameState);
        controller.initWithGameState(); 
        primaryStage.setScene(new Scene(root));
    }

    public void showBattleScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BattleScene.fxml"));
        Parent root = loader.load();
        BattleController controller = loader.getController();
        controller.setMainApp(this);
        controller.setGameState(gameState);
        controller.initWithGameState(); 
        primaryStage.setScene(new Scene(root));
    }

    public void showGameOver(int winner) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
        Parent root = loader.load();
        GameOverController controller = loader.getController();
        controller.setMainApp(this);
        controller.setWinner(winner);
        primaryStage.setScene(new Scene(root));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
