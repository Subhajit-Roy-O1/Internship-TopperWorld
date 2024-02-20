import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int lowerBound = 1;
    private int upperBound = 100;
    private int randomNumber;
    private int maxAttempts = 10;
    private int attempts = 0;

    private JTextField guessTextField;
    private JLabel feedbackLabel;
    private JButton guessButton;
    private JLabel geussAttempt;
    private JTextField name;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        initializeComponents();
        generateRandomNumber();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void initializeComponents() {
        guessTextField = new JTextField();
        feedbackLabel = new JLabel("Try to guess the number between " + lowerBound + " and " + upperBound);
        guessButton = new JButton("Guess");
        geussAttempt = new JLabel("Attemps : "+ attempts);
        name = new JTextField("Subhajit Roy @TopperWorld");

        add(feedbackLabel);
        add(guessTextField);
        add(guessButton);
        add(geussAttempt);
        add(name);
        name.setHorizontalAlignment(JTextField.CENTER);
        name.setBackground(Color.YELLOW);
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessTextField.getText());
            
            attempts++;
            geussAttempt.setText("Attempts : "+ attempts);

            if (userGuess < lowerBound || userGuess > upperBound) {
                feedbackLabel.setText("Please enter a valid guess within the specified range.");
            } else {
                if (userGuess == randomNumber) {
                    displayWinMessage();
                } else if (userGuess < randomNumber) {
                    feedbackLabel.setText("Too low! Try again.");
                } else {
                    feedbackLabel.setText("Too high! Try again.");
                }

                if (attempts == maxAttempts) {
                    displayLoseMessage();
                }
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    private void displayWinMessage() {
        feedbackLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
        guessTextField.setEnabled(false);
        guessButton.setEnabled(false);
       
        
    }

    private void displayLoseMessage() {
        feedbackLabel.setText("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
        guessTextField.setEnabled(false);
        guessButton.setEnabled(false);
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI().setVisible(true);
            }
        });
    }
}
