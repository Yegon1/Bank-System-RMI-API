/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface Account extends Remote {
    double getBalance() throws RemoteException;
    void deposit(double amount) throws RemoteException;
    void withdraw(double amount) throws RemoteException;
    int getTransactionId() throws RemoteException;
    Date getTransactionTime() throws RemoteException;
}

