import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameFrame extends JFrame {
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JPanel containerPanel;
    private Random random = new Random();
    private int correctNumber;

    private int numberOfTries;

    public GameFrame() {
        setTitle("Number Guessing Game");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(4, 1));

        guessField = new JTextField();
        guessButton = new JButton("Guess");
        resultLabel = new JLabel();

        containerPanel.add(new JLabel("Guess a number between 0 and 100"));
        containerPanel.add(guessField);
        containerPanel.add(guessButton);
        containerPanel.add(resultLabel);

        add(containerPanel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        initializeGame();
        setVisible(true);
    }

    private void initializeGame() {
        // Generera ett nytt slumpmässigt nummer för spelet
        correctNumber = random.nextInt(101);
        // Återställ resultatetiketten och panelbakgrunden
        resultLabel.setText("");
        containerPanel.setBackground(Color.WHITE);
        // Återställ antalet försök
        numberOfTries = 0;
    }

    private void handleGuess() {
        try {
            // Hämta gissningen från textfieldet
            int guess = Integer.parseInt(guessField.getText());
            numberOfTries++;
            // Evaluterar gissningen och uppdatera resultlabel
            String result = evaluateGuess(guess, correctNumber);
            resultLabel.setText(result);

            //Om gissningen är korrekt ändras bakgrund till grön
            if (result.equals("Correct!")) {
                containerPanel.setBackground(Color.GREEN);
                // Om spelaren gissat rätt visa en dialogruta med antal försök det tog
                JOptionPane.showMessageDialog(
                        this,
                        "Congratulations! You guessed the correct number in " + numberOfTries + " tries.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                // Starta om spelet efter att spelaren gissat rätt
                initializeGame();
            } else {
                // Om gissningen är fel, ändra panelbakgrunden till röd
                containerPanel.setBackground(Color.RED);
                guessField.setText("");
            }
            // Om användaren inte matar in ett giltigt nummer
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private String evaluateGuess(int guess, int correctNumber) {
        if (guess < correctNumber) {
            return "Wrong! My number is bigger than " + guess + "!";
        } else if (guess > correctNumber) {
            return "Wrong! My number is smaller than " + guess + "!";
        } else {
            return "Correct!";
        }
    }


}