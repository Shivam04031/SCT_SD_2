import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class NumberGuessingGame extends JFrame {

    private int targetNumber;
    private int attempts;

    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;

    public NumberGuessingGame() {
        // Generate the first random number
        startNewGame();

        // Window settings
        setTitle("Number Guessing Game");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Number Guessing Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Instructions
        JLabel instructionLabel = new JLabel(
                "Guess a number between 1 and 100:"
        );
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input field
        guessField = new JTextField();
        guessField.setMaximumSize(new Dimension(200, 30));
        guessField.setHorizontalAlignment(JTextField.CENTER);

        // Submit button
        JButton guessButton = new JButton("Submit Guess");
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Feedback label
        feedbackLabel = new JLabel(
                "Enter your guess and click Submit Guess."
        );
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Attempts label
        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // New Game button
        JButton newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action for Submit Guess button
        guessButton.addActionListener(e -> checkGuess());

        // Allow pressing Enter to submit a guess
        guessField.addActionListener(e -> checkGuess());

        // Add action for New Game button
        newGameButton.addActionListener(e -> startNewGame());

        // Add components to panel
        panel.add(Box.createVerticalStrut(20));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(instructionLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(guessField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(guessButton);
        panel.add(Box.createVerticalStrut(15));
        panel.add(feedbackLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(attemptsLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(newGameButton);

        // Add panel to window
        add(panel);

        // Display window
        setVisible(true);
    }

    private void checkGuess() {
        String input = guessField.getText();

        try {
            int guess = Integer.parseInt(input);

            if (guess < 1 || guess > 100) {
                feedbackLabel.setText(
                        "Please enter a number between 1 and 100."
                );
                return;
            }

            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);

            if (guess < targetNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (guess > targetNumber) {
                feedbackLabel.setText("Too high! Try again.");
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Congratulations! You guessed the number in "
                                + attempts + " attempts!",
                        "You Win!",
                        JOptionPane.INFORMATION_MESSAGE
                );

                startNewGame();
            }

        } catch (NumberFormatException e) {
            feedbackLabel.setText(
                    "Please enter a valid whole number."
            );
        }
    }

    private void startNewGame() {
        Random random = new Random();

        targetNumber = random.nextInt(100) + 1;
        attempts = 0;

        if (guessField != null) {
            guessField.setText("");
        }

        if (feedbackLabel != null) {
            feedbackLabel.setText(
                    "Enter your guess and click Submit Guess."
            );
        }

        if (attemptsLabel != null) {
            attemptsLabel.setText("Attempts: 0");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NumberGuessingGame();
        });
    }
}