/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// BankServer.java
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Bankserver {
    public static void main(String[] args) {
        try {
            Account bankAccount = new BankAccount();

            // Create registry
            LocateRegistry.createRegistry(1099);

            // Bind the remote object to the registry
            Naming.rebind("BankAccount", bankAccount);

            System.out.println("BankAccount server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

