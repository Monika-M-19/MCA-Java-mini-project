# 🎮 Tic Tac Toe — Java Swing

A desktop-based **Tic Tac Toe game** developed using **Java Swing** as an MCA mini project.

The application provides an interactive graphical interface with **Player vs Player** and **Player vs Computer** modes, score tracking, persistent scores, and visual feedback for winning moves.

## ✨ Features

- 🎮 Player vs Player mode
- 🤖 Player vs Computer mode
- 🏠 Interactive home screen
- ▶️ Start Game option
- 🔄 Replay / restart the current game
- 🏆 Automatic winner detection
- 🤝 Draw detection
- 📊 X, O and Draw score tracking
- 💾 Persistent score storage using file handling
- 🔁 Reset Score option
- 🔀 Switch between PvP and PvC modes
- ✨ Winning-line visual indication
- 🎨 Custom graphical interface
- 🧠 Computer move-selection logic
- 🖥️ Desktop GUI application

## 🛠️ Technologies Used

- **Java**
- **Java Swing**
- **AWT**
- **File Handling**
- **CardLayout**
- **Event Handling**

## 📂 Project Structure

```text
MCA-Java-mini-project/
│
├── TicTacToeDesign.java
├── bg.png
├── scores.txt
└── README.md
```

> `scores.txt` is created automatically by the application when scores are saved.

## 🎯 Game Modes

### 👥 Player vs Player

Two players can play against each other by taking turns as **X** and **O**.

### 🤖 Player vs Computer

The player competes against the computer.

The computer checks for possible winning moves and blocking moves before selecting an available position. It also prioritizes the center, corners, and sides when choosing a move.

## 💾 Score Management

The application maintains scores for:

- ❌ X Wins
- ⭕ O Wins
- 🤝 Draws

Scores are saved locally using a `scores.txt` file and loaded when the application starts.

## 🎮 Controls

| Button | Function |
|---|---|
| **Start Game** | Opens the game board |
| **Replay** | Starts a new round |
| **Reset Score** | Clears all stored scores |
| **Switch Mode** | Switches between PvP and PvC |

## ▶️ How to Run

### Prerequisites

- **JDK (Java Development Kit)**
- Any Java-compatible IDE such as:
  - IntelliJ IDEA
  - Eclipse
  - NetBeans
  - VS Code

### Steps

1. Clone the repository:

```bash
git clone https://github.com/Monika-M-19/MCA-Java-mini-project.git
```

2. Open the project in your preferred Java IDE.

3. Make sure `bg.png` is located in the same folder as `TicTacToeDesign.java`.

4. Compile the program:

```bash
javac TicTacToeDesign.java
```

5. Run the application:

```bash
java TicTacToeDesign
```

## 📸 Screenshots

Screenshots can be added here to demonstrate:

- 🏠 Home screen
- 🎮 Game board
- 👥 Player vs Player mode
- 🤖 Player vs Computer mode
- 🏆 Winning result
- 📊 Score display

## 🚀 Future Improvements

Some possible improvements for future versions:

- 🧠 Implement a stronger AI using the **Minimax algorithm**
- 🎚️ Add multiple difficulty levels
- 🎨 Add additional themes and customization
- 🔊 Add sound effects
- 🏅 Add player statistics and game history
- 🌐 Develop an online multiplayer version
- 📱 Create a mobile version

## 🎓 Project Information

**Project Type:** MCA Mini Project  
**Application Type:** Desktop Application  
**Programming Language:** Java  
**GUI Framework:** Java Swing

## 👩‍💻 Developer

**Monika M**

MCA Student | Web Developer | Building Real-World Projects

🔗 **GitHub:** [Monika-M-19](https://github.com/Monika-M-19)
