import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class TicTacToeDesign extends JFrame {

    // ---------- FIELDS ----------
    Cell[] cells = new Cell[9];
    boolean playerX = true;
    boolean vsComputer = false;   // default PvP
    int moveCount = 0;

    int xScore = 0, oScore = 0, drawScore = 0;

    JLabel titleLabel, statusLabel, scoreLabel, modeLabel;
    JButton switchModeButton;

    final String SCORE_FILE = "scores.txt";

    int winPattern = -1;
    Color winLineColor = Color.BLACK;

    BoardPanel boardPanel;

    // Home & CardLayout
    CardLayout cardLayout;
    JPanel mainContainer;
    Image bgImage;

    // ---------- CONSTRUCTOR ----------
    public TicTacToeDesign() {
        setTitle("Tic Tac Toe");
        setSize(420, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // LOAD BACKGROUND IMAGE
        bgImage = new ImageIcon("E:/MCA JAVA MINI PROJECT/TIC TAC TOE/bg.png").getImage();


        // CARDLAYOUT CONTAINER
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        setContentPane(mainContainer);

        // ---------- HOME PANEL ----------
        HomePanel home = new HomePanel();
        mainContainer.add(home, "HOME");

        // ---------- GAME PANEL ----------
        JPanel gameMain = new DarkPanel();
        gameMain.setLayout(new BorderLayout());

        loadScores();

        // ---------- TITLE ----------
        titleLabel = new JLabel("TIC TAC TOE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(255, 255, 255, 30)); // transparent white
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

        // ---------- MODE LABEL ----------
        modeLabel = new JLabel(getModeText(), SwingConstants.CENTER);
        modeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        modeLabel.setForeground(Color.WHITE);

        // ---------- STATUS ----------
        statusLabel = new JLabel("X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setForeground(Color.WHITE);

        // ---------- SCORE ----------
        scoreLabel = new JLabel(getScoreText(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(Color.WHITE);

        // ---------- TOP PANEL ----------
        JPanel top = new JPanel(new GridLayout(4, 1, 1, 0));
        top.setOpaque(false);
        top.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        top.add(titleLabel);
        top.add(modeLabel);
        top.add(statusLabel);
        top.add(scoreLabel);

        gameMain.add(top, BorderLayout.NORTH);

        // ---------- BOARD ----------
        boardPanel = new BoardPanel();
        gameMain.add(boardPanel, BorderLayout.CENTER);

        // ---------- BUTTON BAR ----------
        JPanel buttons = new JPanel();
        buttons.setOpaque(false);

        JButton replay = new JButton("Replay");
        styleButton(replay);
        replay.addActionListener(e -> resetBoard());

        JButton resetScore = new JButton("Reset Score");
        styleButton(resetScore);
        resetScore.addActionListener(e -> resetScores());

        switchModeButton = new JButton("Switch Mode");
        styleButton(switchModeButton);
        switchModeButton.addActionListener(e -> switchMode());

        buttons.add(replay);
        buttons.add(resetScore);
        buttons.add(switchModeButton);

        gameMain.add(buttons, BorderLayout.SOUTH);

        mainContainer.add(gameMain, "GAME");

        // SHOW HOME FIRST
        cardLayout.show(mainContainer, "HOME");

        setVisible(true);
    }

    // ---------- STYLE BUTTON ----------
    void styleButton(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
    }

    // ---------- MODE SWITCH ----------
    void switchMode() {
        vsComputer = !vsComputer;
        xScore = oScore = drawScore = 0;
        saveScores();
        scoreLabel.setText(getScoreText());
        resetBoard();
        modeLabel.setText(getModeText());
    }

    String getModeText() {
        return vsComputer ? "Mode: Player vs Computer" : "Mode: Player vs Player";
    }

    void resetBoard() {
        for (Cell c : cells) {
            c.value = 0;
            c.repaint();
        }
        moveCount = 0;
        winPattern = -1;
        playerX = true;
        statusLabel.setText("X's Turn");
        boardPanel.repaint();
    }

    void resetScores() {
        xScore = oScore = drawScore = 0;
        scoreLabel.setText(getScoreText());
        saveScores();
        resetBoard();
    }

    boolean checkWinner() {
        int[][] wins = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int i = 0; i < wins.length; i++) {
            int a = cells[wins[i][0]].value;
            int b = cells[wins[i][1]].value;
            int c = cells[wins[i][2]].value;

            if (a != 0 && a == b && b == c) {
                winPattern = i;
                winLineColor = (a == 1) ? new Color(66,133,244) : new Color(234,67,53);
                return true;
            }
        }
        return false;
    }

    String getScoreText() {
        return "X : " + xScore + " | O : " + oScore + " | Draws : " + drawScore;
    }

    void saveScores() {
        try (FileWriter fw = new FileWriter(SCORE_FILE)) {
            fw.write(xScore + "\n" + oScore + "\n" + drawScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadScores() {
        try (Scanner sc = new Scanner(new File(SCORE_FILE))) {
            xScore = sc.nextInt();
            oScore = sc.nextInt();
            drawScore = sc.nextInt();
        } catch (Exception e) {
            xScore = oScore = drawScore = 0;
        }
    }

    // ---------- DARK PANEL FOR BOARD BACKGROUND ----------
    class DarkPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(20, 20, 20)); // dark like home
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    // ---------- BOARD PANEL ----------
    class BoardPanel extends JPanel {
        BoardPanel() {
            setLayout(new GridLayout(3, 3));
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            for (int i = 0; i < 9; i++) {
                cells[i] = new Cell();
                add(cells[i]);
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (winPattern == -1) return;

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(8));
            g2.setColor(winLineColor);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int[][] wins = {
                    {0,1,2},{3,4,5},{6,7,8},
                    {0,3,6},{1,4,7},{2,5,8},
                    {0,4,8},{2,4,6}
            };

            Rectangle r1 = cells[wins[winPattern][0]].getBounds();
            Rectangle r2 = cells[wins[winPattern][2]].getBounds();

            g2.drawLine(
                    r1.x + r1.width / 2,
                    r1.y + r1.height / 2,
                    r2.x + r2.width / 2,
                    r2.y + r2.height / 2
            );
        }
    }

    // ---------- CELL ----------
    class Cell extends JButton {
        int value = 0;

        Cell() {
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorder(BorderFactory.createLineBorder(Color.WHITE, 4)); // white grid
            addActionListener(e -> handleMove());
        }

        void handleMove() {
            if (value != 0) return;

            value = playerX ? 1 : 2;
            moveCount++;
            repaint();

            if (checkWinner()) {
                if (playerX) xScore++; else oScore++;
                scoreLabel.setText(getScoreText());
                saveScores();
                boardPanel.repaint();
                JOptionPane.showMessageDialog(null, (playerX ? "X" : "O") + " Wins!");
                resetBoard();
                return;
            }

            if (moveCount == 9) {
                drawScore++;
                scoreLabel.setText(getScoreText());
                saveScores();
                JOptionPane.showMessageDialog(null, "It's a Draw!");
                resetBoard();
                return;
            }

            playerX = !playerX;
            statusLabel.setText(playerX ? "X's Turn" : "O's Turn");

            if (vsComputer && !playerX) {
                Timer t = new Timer(400, e -> computerMove());
                t.setRepeats(false);
                t.start();
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            if (value == 1) {
                g2.setColor(new Color(66,133,244)); // X blue
                g2.setStroke(new BasicStroke(10));
                g2.drawLine(20,20,w-20,h-20);
                g2.drawLine(w-20,20,20,h-20);
            } else if (value == 2) {
                g2.setColor(new Color(234,67,53)); // O red
                g2.setStroke(new BasicStroke(10));
                g2.drawOval(20,20,w-40,h-40);
            }
        }
    }

    // ---------- COMPUTER AI ----------
    void computerMove() {
        int move = findBestMove(2);
        if (move != -1) { playComputerMove(move); return; }

        move = findBestMove(1);
        if (move != -1) { playComputerMove(move); return; }

        if (cells[4].value == 0) { playComputerMove(4); return; }

        int[] corners = {0,2,6,8};
        for (int i : corners)
            if (cells[i].value == 0) { playComputerMove(i); return; }

        int[] sides = {1,3,5,7};
        for (int i : sides)
            if (cells[i].value == 0) { playComputerMove(i); return; }
    }

    int findBestMove(int player) {
        int[][] wins = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] w : wins) {
            int count = 0, empty = -1;
            for (int i : w) {
                if (cells[i].value == player) count++;
                if (cells[i].value == 0) empty = i;
            }
            if (count == 2 && empty != -1) return empty;
        }
        return -1;
    }

    void playComputerMove(int i) {
        cells[i].value = 2;
        cells[i].repaint();
        moveCount++;

        if (checkWinner()) {
            oScore++;
            scoreLabel.setText(getScoreText());
            saveScores();
            boardPanel.repaint();
            JOptionPane.showMessageDialog(null, "Computer Wins!");
            resetBoard();
            return;
        }

        if (moveCount == 9) {
            drawScore++;
            scoreLabel.setText(getScoreText());
            saveScores();
            JOptionPane.showMessageDialog(null, "It's a Draw!");
            resetBoard();
            return;
        }

        playerX = true;
        statusLabel.setText("X's Turn");
    }

    // ---------- HOME PANEL ----------
    class HomePanel extends JPanel {
        HomePanel() {
            setLayout(null); // free placement
            JButton start = new JButton("Start Game");
            start.setFont(new Font("Arial", Font.BOLD, 18));
            start.setBounds(135, 480, 150, 50); // centered
            start.setBackground(Color.WHITE);
            start.setForeground(Color.BLACK);
            start.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            start.setFocusPainted(false);
            start.addActionListener(e -> cardLayout.show(mainContainer, "GAME"));
            add(start);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        new TicTacToeDesign();
    }
}
