import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineVotingSystemGUI extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JRadioButton partyARadioButton;
    private JRadioButton partyBRadioButton;
    private JRadioButton partyCRadioButton;
    private JButton submitButton;
    private JButton resultsButton;

    private int votesForPartyA = 0;
    private int votesForPartyB = 0;
    private int votesForPartyC = 0;

    public OnlineVotingSystemGUI() {
        setTitle("Online Voting System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        initializeComponents();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitVote();
            }
        });

        resultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResults();
            }
        });
    }

    private void initializeComponents() {
        // Name input
        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.add(new JLabel("Enter Name:"));
        nameField = new JTextField(20);
        namePanel.add(nameField);
        add(namePanel);

        // Phone input
        JPanel phonePanel = new JPanel(new FlowLayout());
        phonePanel.add(new JLabel("Enter Phone:"));
        phoneField = new JTextField(20);
        phonePanel.add(phoneField);
        add(phonePanel);

        // Vote options
        JPanel votePanel = new JPanel(new GridLayout(3, 1));
        votePanel.add(new JLabel("Cast Your Vote:"));
        partyARadioButton = new JRadioButton("Party A");
        partyBRadioButton = new JRadioButton("Party B");
        partyCRadioButton = new JRadioButton("Party C");
        ButtonGroup group = new ButtonGroup();
        group.add(partyARadioButton);
        group.add(partyBRadioButton);
        group.add(partyCRadioButton);
        votePanel.add(partyARadioButton);
        votePanel.add(partyBRadioButton);
        votePanel.add(partyCRadioButton);
        add(votePanel);

        // Submit button
        JPanel submitPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Submit Your Vote");
        submitPanel.add(submitButton);
        add(submitPanel);

        // Results button
        JPanel resultsPanel = new JPanel(new FlowLayout());
        resultsButton = new JButton("Check Results");
        resultsPanel.add(resultsButton);
        add(resultsPanel);
    }

    private void submitVote() {
        String name = nameField.getText();
        String phone = phoneField.getText();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and phone number.");
            return;
        }

        if (!partyARadioButton.isSelected() && !partyBRadioButton.isSelected() && !partyCRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a party to vote.");
            return;
        }

        if (partyARadioButton.isSelected()) {
            votesForPartyA++;
        } else if (partyBRadioButton.isSelected()) {
            votesForPartyB++;
        } else if (partyCRadioButton.isSelected()) {
            votesForPartyC++;
        }

        JOptionPane.showMessageDialog(this, "Thank you for voting!");
        // Clear fields after voting
        nameField.setText("");
        phoneField.setText("");
        partyARadioButton.setSelected(false);
        partyBRadioButton.setSelected(false);
        partyCRadioButton.setSelected(false);
    }

    private void showResults() {
        JOptionPane.showMessageDialog(this, "Results:\nParty A: " + votesForPartyA + " votes\nParty B: " + votesForPartyB + " votes\nParty C: " + votesForPartyC + " votes");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OnlineVotingSystemGUI().setVisible(true);
            }
        });
    }
}
