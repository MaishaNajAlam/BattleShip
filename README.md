# Battleship Game - JavaFX

A two-player turn-based Battleship game built with JavaFX, featuring ship placement, battle mechanics, and a complete game flow from start to finish.

---

## Features

* Two-Player Gameplay: Local multiplayer with separate ship placement phases for each player.
* Interactive Ship Placement: Manual placement with rotation controls or automatic randomization.
* Turn-Based Battle System: Players take turns attacking the opponent's grid with visual feedback.
* Game State Management: Persistent state tracking across all game phases.

---

## Architecture

The application follows an MVC (Model-View-Controller) pattern:

* Model: `GameState`, `Board`, `Cell`, `Ship` classes manage game logic and state.
* View: FXML files define declarative UI layouts for each scene.
* Controller: Scene-specific controllers handle user interactions and update the model.

---

## Scene Flow

1. Splash Screen – Entry point with start button.
2. Player 1 Placement – First player places ships on a 9×9 grid.
3. Player 2 Placement – Second player places ships.
4. Battle Scene – Turn-based combat with dual grid display.
5. Game Over – Winner announcement with restart/exit options.

---

## Getting Started

### Prerequisites

* Java 8 or higher with JavaFX support.
* JavaFX SDK (if not bundled with your JDK).

### Running the Application

```bash
# Clone the repository
git clone https://github.com/MaishaNajAlam/BattleShip.git

# Navigate to the project folder
cd BattleshipGame

# Compile and run (ensure JavaFX libraries are in your classpath)
java application.Main
```

---

## How to Play

1. Start Game – Click "Start Game" on the splash screen.
2. Place Ships – Each player places their fleet on the grid:

   * Click cells to place ships manually.
   * Use Rotate to toggle horizontal/vertical orientation.
   * Or click Random for automatic placement.
3. Battle – Players take turns clicking the opponent's grid to attack.

   * Red highlights indicate whose turn it is.
   * The game ends when all of one player's ships are sunk.
4. Game Over – View the winner and choose to restart or exit.

---

## Project Structure

```
src/application/
├── Main.java
├── GameState.java
├── Board.java
├── Cell.java
├── Ship.java
├── SplashScreen.fxml
├── SplashController.java
├── Player1Placement.fxml
├── Player1PlacementController.java
├── Player2Placement.fxml
├── Player2PlacementController.java
├── BattleScene.fxml
├── BattleController.java
├── GameOver.fxml
└── GameOverController.java
```

---

## Technical Details

* Grid Size: 9×9 cells
* UI Framework: JavaFX with FXML-based views
* Navigation: Centralized scene management through `Main.java`
* State Management: Single `GameState` instance shared across all controllers
