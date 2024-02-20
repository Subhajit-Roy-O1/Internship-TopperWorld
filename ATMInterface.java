import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ATMInterface extends JFrame {
    private Map<Integer, Integer> accounts; // Map of account numbers and PINs
    private Map<Integer, Double> balances; // Map of account numbers and balances

    private JTextField accountNumberField;
    private JPasswordField pinField;
    private JButton loginButton;

    private JButton withdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;
    private JButton exitButton;

    public ATMInterface() {
        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        initializeComponents();

        accounts = new HashMap<>();
        accounts.put(123456, 1234); // Example account with account number 123456 and PIN 1234
        balances = new HashMap<>();
        balances.put(123456, 1000.0); // Initial balance for the example account

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
    }

    private void initializeComponents() {
        JPanel loginPanel = new JPanel(new GridLayout(3, 1));
        loginPanel.add(new JLabel("Account Number:"));
        accountNumberField = new JTextField();
        loginPanel.add(accountNumberField);
        loginPanel.add(new JLabel("PIN:"));
        pinField = new JPasswordField();
        loginPanel.add(pinField);
        loginButton = new JButton("Login");
        loginPanel.add(loginButton);
        add(loginPanel);

        JPanel transactionPanel = new JPanel(new GridLayout(4, 1));
        withdrawButton = new JButton("Withdraw");
        transactionPanel.add(withdrawButton);
        depositButton = new JButton("Deposit");
        transactionPanel.add(depositButton);
        checkBalanceButton = new JButton("Check Balance");
        transactionPanel.add(checkBalanceButton);
        exitButton = new JButton("Exit");
        transactionPanel.add(exitButton);
        add(transactionPanel);
    }

    private void authenticateUser() {
        int accountNumber = Integer.parseInt(accountNumberField.getText());
        int pin = Integer.parseInt(new String(pinField.getPassword()));
        if (accounts.containsKey(accountNumber) && accounts.get(accountNumber) == pin) {
            JOptionPane.showMessageDialog(this, "Login successful.");
            enableTransactions();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid account number or PIN.");
        }
    }

    private void enableTransactions() {
        withdrawButton.setEnabled(true);
        depositButton.setEnabled(true);
        checkBalanceButton.setEnabled(true);
    }

    private void withdraw() {
        int accountNumber = Integer.parseInt(accountNumberField.getText());
        double balance = balances.get(accountNumber);
        double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter amount to withdraw:"));
        if (amount > balance) {
            JOptionPane.showMessageDialog(this, "Insufficient balance.");
        } else {
            balances.put(accountNumber, balance - amount);
            JOptionPane.showMessageDialog(this, "Withdrawal successful. New balance: " + balances.get(accountNumber));
        }
    }

    private void deposit() {
        int accountNumber = Integer.parseInt(accountNumberField.getText());
        double balance = balances.get(accountNumber);
        double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter amount to deposit:"));
        balances.put(accountNumber, balance + amount);
        JOptionPane.showMessageDialog(this, "Deposit successful. New balance: " + balances.get(accountNumber));
    }

    private void checkBalance() {
        int accountNumber = Integer.parseInt(accountNumberField.getText());
        JOptionPane.showMessageDialog(this, "Current balance: " + balances.get(accountNumber));
    }

    private void exit() {
        JOptionPane.showMessageDialog(this, "Thank you for using the ATM. Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface().setVisible(true);
            }
        });
    }
}
