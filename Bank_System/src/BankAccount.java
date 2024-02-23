/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class BankAccount extends UnicastRemoteObject implements Account {
    private double balance;
    private int transactionId;
    private Date transactionTime;

    public BankAccount() throws RemoteException {
        // Constructor
        this.balance = 0.0;
        this.transactionId = 0;
        this.transactionTime = null;
    }

    @Override
    public double getBalance() throws RemoteException {
        return balance;
    }

    @Override
    public void deposit(double amount) throws RemoteException {
        balance += amount;
        transactionId++;
        transactionTime = new Date();
        System.out.println("Deposited: " + amount);
    }

    @Override
    public void withdraw(double amount) throws RemoteException {
        if (balance >= amount) {
            balance -= amount;
            transactionId++;
            transactionTime = new Date();
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    public int getTransactionId() throws RemoteException {
        return transactionId;
    }

    @Override
    public Date getTransactionTime() throws RemoteException {
        return transactionTime;
    }
}
