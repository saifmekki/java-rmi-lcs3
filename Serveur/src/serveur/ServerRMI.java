package serveur;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import service.SYS1ServiceImpl;
import service2.SYS2ServiceImpl;
import serviceGlobale.GlobalRevenueServiceImpl;

public class ServerRMI {

    public static void main(String[] args) {
        try {Scanner scanner = new Scanner(System.in);
            System.out.println("Choisir sys1 sys2 ou recette:");
            String choice = scanner.next();
        	LocateRegistry.createRegistry(1090);

            if (choice.equalsIgnoreCase("sys1")) {
                SYS1ServiceImpl sys1 = new SYS1ServiceImpl();
                Naming.rebind("rmi://localhost:1090/SI", sys1);
            } else if (choice.equalsIgnoreCase("sys2")) {
                SYS2ServiceImpl sys2 = new SYS2ServiceImpl();
                Naming.rebind("rmi://localhost:1090/SI", sys2);
            }
            else if (choice.equalsIgnoreCase("recette")) {
            	SYS1ServiceImpl sys1 = new SYS1ServiceImpl();
            	SYS2ServiceImpl sys2 = new SYS2ServiceImpl();
            	GlobalRevenueServiceImpl gr = new GlobalRevenueServiceImpl(sys1, sys2);
            	Naming.rebind("rmi://localhost:1090/GR", gr);
            }
            else {
                System.out.println("Choix invalide.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
