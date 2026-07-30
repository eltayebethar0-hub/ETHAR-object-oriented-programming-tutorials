package Tutorial_10;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class QuizBattleGUI extends JFrame {

    private static final int INITIAL_PLAYER_HP = 100;
    private static final int INITIAL_BOSS_HP = 100;
    private static final int BOSS_DAMAGE = 20;
    private static final int PLAYER_DAMAGE = 10;
    private static final int SCORE_REWARD = 10;

    private static final Color BG_DARK = new Color(24, 25, 38);
    private static final Color PANEL_BG = new Color(36, 39, 58);
    private static final Color ACCENT_BLUE = new Color(138, 173, 244);
    private static final Color BOSS_RED = new Color(237, 135, 150);
    private static final Color PLAYER_GREEN = new Color(166, 218, 149);
    private static final Color TEXT_WHITE = new Color(202, 211, 245);

    private int playerHP = INITIAL_PLAYER_HP;
    private int bossHP = INITIAL_BOSS_HP;
    private int score = 0;
    private int currentQuestionIndex = 0;
    private List<Questions> questions;

    private JProgressBar playerHpBar;
    private JProgressBar bossHpBar;
    private JLabel scoreLabel;
    private JLabel bossStatusLabel;
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JPanel mainContainer;

    public QuizBattleGUI() {
        setTitle("Java OOP Arena: Code Boss Showdown");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        questions = Questions.getQuestionBank();

        initUI();
        loadNextQuestion();
    }

    private void initUI() {
        mainContainer = new JPanel(new BorderLayout(15, 15));
        mainContainer.setBackground(BG_DARK);
        mainContainer.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel battlePanel = new JPanel(new GridLayout(1, 2, 20, 0));
        battlePanel.setOpaque(false);

        JPanel playerCard = createCardPanel("HERO PROGRAMMER");
        playerHpBar = createHealthBar(INITIAL_PLAYER_HP, PLAYER_GREEN);
        scoreLabel = new JLabel("SCORE: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        scoreLabel.setForeground(ACCENT_BLUE);
        
        playerCard.add(playerHpBar, BorderLayout.CENTER);
        playerCard.add(scoreLabel, BorderLayout.SOUTH);

        JPanel bossCard = createCardPanel("THE CODE BOSS");
        bossHpBar = createHealthBar(INITIAL_BOSS_HP, BOSS_RED);
        bossStatusLabel = new JLabel("STATUS: ANGRY", SwingConstants.CENTER);
        bossStatusLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        bossStatusLabel.setForeground(BOSS_RED);

        bossCard.add(bossHpBar, BorderLayout.CENTER);
        bossCard.add(bossStatusLabel, BorderLayout.SOUTH);

        battlePanel.add(playerCard);
        battlePanel.add(bossCard);

        JPanel questionCard = new JPanel(new BorderLayout());
        questionCard.setBackground(PANEL_BG);
        questionCard.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_BLUE, 2),
            new EmptyBorder(25, 20, 25, 20)
        ));

        questionLabel = new JLabel("Question prompt will appear here...", SwingConstants.CENTER);
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        questionLabel.setForeground(TEXT_WHITE);
        questionCard.add(questionLabel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 12, 12));
        optionsPanel.setOpaque(false);
        optionsPanel.setPreferredSize(new Dimension(800, 140));

        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            final int choice = i;
            JButton btn = new JButton();
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            btn.setFocusPainted(false);
            btn.setBackground(PANEL_BG);
            btn.setForeground(TEXT_WHITE);
            btn.setBorder(BorderFactory.createLineBorder(ACCENT_BLUE, 1));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            btn.addActionListener(e -> processAnswer(choice));
            optionButtons[i] = btn;
            optionsPanel.add(btn);
        }

        mainContainer.add(battlePanel, BorderLayout.NORTH);
        mainContainer.add(questionCard, BorderLayout.CENTER);
        mainContainer.add(optionsPanel, BorderLayout.SOUTH);

        add(mainContainer);
    }

    private JPanel createCardPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(PANEL_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(69, 71, 90), 1),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        titleLabel.setForeground(TEXT_WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);

        return panel;
    }

    private JProgressBar createHealthBar(int max, Color color) {
        JProgressBar bar = new JProgressBar(0, max);
        bar.setValue(max);
        bar.setStringPainted(true);
        bar.setFont(new Font("Monospaced", Font.BOLD, 14));
        bar.setForeground(color);
        bar.setBackground(new Color(18, 18, 24));
        bar.setBorder(BorderFactory.createEmptyBorder());
        bar.setPreferredSize(new Dimension(200, 25));
        bar.setString("HP: " + max + " / " + max);
        return bar;
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            questions = Questions.getQuestionBank();
            currentQuestionIndex = 0;
        }

        Questions q = questions.get(currentQuestionIndex);
        questionLabel.setText("<html><div style='text-align: center;'>" + q.getQuestionText() + "</div></html>");

        String[] options = q.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText((char) ('A' + i) + ") " + options[i]);
            optionButtons[i].setEnabled(true);
        }
    }

    private void processAnswer(int selectedIndex) {
        Questions currentQuestion = questions.get(currentQuestionIndex);

        if (currentQuestion.isCorrect(selectedIndex)) {
            bossHP = Math.max(0, bossHP - BOSS_DAMAGE);
            score += SCORE_REWARD;
            flashFeedback(BOSS_RED);
        } else {
            playerHP = Math.max(0, playerHP - PLAYER_DAMAGE);
            flashFeedback(new Color(243, 139, 168));
        }

        updateUIState();

        if (bossHP <= 0) {
            triggerEndGame(true);
        } else if (playerHP <= 0) {
            triggerEndGame(false);
        } else {
            currentQuestionIndex++;
            loadNextQuestion();
        }
    }

    private void updateUIState() {
        playerHpBar.setValue(playerHP);
        playerHpBar.setString("HP: " + playerHP + " / " + INITIAL_PLAYER_HP);

        bossHpBar.setValue(bossHP);
        bossHpBar.setString("HP: " + bossHP + " / " + INITIAL_BOSS_HP);

        scoreLabel.setText("SCORE: " + score);

        if (bossHP <= 30) {
            bossStatusLabel.setText("STATUS: CRITICAL!");
        } else if (bossHP <= 60) {
            bossStatusLabel.setText("STATUS: ENRAGED");
        } else {
            bossStatusLabel.setText("STATUS: ANGRY");
        }
    }

    private void flashFeedback(Color flashColor) {
        Color originalBg = mainContainer.getBackground();
        mainContainer.setBackground(flashColor.darker().darker());

        Timer timer = new Timer(150, e -> mainContainer.setBackground(originalBg));
        timer.setRepeats(false);
        timer.start();
    }

    private void triggerEndGame(boolean playerWon) {
        for (JButton btn : optionButtons) {
            btn.setEnabled(false);
        }

        String title = playerWon ? "VICTORY!" : "GAME OVER";
        String message = playerWon 
            ? "Congratulations! You defeated the Code Boss!\nFinal Score: " + score
            : "You were defeated by the Code Boss!\nFinal Score: " + score;

        int choice = JOptionPane.showOptionDialog(
            this,
            message + "\n\nWould you like to play again?",
            title,
            JOptionPane.YES_NO_OPTION,
            playerWon ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE,
            null,
            new String[]{"Play Again", "Exit"},
            "Play Again"
        );

        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        playerHP = INITIAL_PLAYER_HP;
        bossHP = INITIAL_BOSS_HP;
        score = 0;
        currentQuestionIndex = 0;
        questions = Questions.getQuestionBank();

        updateUIState();
        loadNextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizBattleGUI gui = new QuizBattleGUI();
            gui.setVisible(true);
        });
    }
}