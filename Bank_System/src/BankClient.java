import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Date;

public class BankClient extends JFrame {
    private Account bankAccount;
    private JTextField amountField;
    private JTextArea balanceArea;

    public BankClient() {
        try {
            // Lookup the remote object
            bankAccount = (Account) Naming.lookup("rmi://localhost/BankAccount");

            initializeUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeUI() {
        setTitle("Bank Account Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        balanceArea = new JTextArea(10, 30);
        balanceArea.setEditable(false);
        

        // Set layout
        setLayout(new FlowLayout());

        // Add components to the frame
        add(amountLabel);
        add(amountField);
        add(depositButton);
        add(withdrawButton);
        add(new JScrollPane(balanceArea));

        // Add action listeners
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction(true);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performTransaction(false);
            }
        });
        
          // Disable JFrame resizing
        setResizable(false);

        // Display the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performTransaction(boolean isDeposit) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (isDeposit) {
                bankAccount.deposit(amount);
            } else {
                bankAccount.withdraw(amount);
            }
            updateBalance();
        } catch (NumberFormatException | RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input or remote exception", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBalance() {
        try {
            double currentBalance = bankAccount.getBalance();
            int transactionId = bankAccount.getTransactionId();
            Date transactionTime = bankAccount.getTransactionTime();

            balanceArea.setText("Current Balance: " + currentBalance + "\n" +
                                "Transaction ID: " + transactionId + "\n" +
                                "Transaction Time: " + transactionTime);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankClient();
            }
        });
    }
}
