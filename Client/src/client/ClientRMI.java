package client;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

import service.SYS1Service;
import service2.SYS2Service;
import serviceGlobale.GlobalRevenueService;
import si.Invoice;
import si2.TransportsInvoice;

public class ClientRMI {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choisir sys1 sys2 ou recette:");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("sys1")) {
                SYS1Service sys1Stub = (SYS1Service) Naming.lookup("rmi://localhost:1090/SI");
                handleSYS1(sys1Stub, scanner);
            } else if (choice.equalsIgnoreCase("sys2")) {
                SYS2Service sys2Stub = (SYS2Service) Naming.lookup("rmi://localhost:1090/SI");
                handleSYS2(sys2Stub, scanner);
            }
            else if (choice.equalsIgnoreCase("recette")) {
            	GlobalRevenueService GrStub = (GlobalRevenueService) Naming.lookup("rmi://localhost:1090/GR");
            	System.out.println("Le Revenue globale : "+GrStub.calculateOverallRevenue());
            }
            else {
                System.out.println("Choix invalide.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleSYS1(SYS1Service si1Stub, Scanner scanner) {
        try {
            System.out.println("Welcome sys1!");
            List<Invoice> invoices = si1Stub.getSalesInvoices();
            for (Invoice invoice : invoices) {
                System.out.println(invoice.toString());
            }

            System.out.println("Vous voullez ajouter une facture? (oui/non)");
            String response = scanner.next();

            if (response.equalsIgnoreCase("oui")) {
                System.out.println("Code:");
                Long code = scanner.nextLong();
                System.out.println("Nom:");
                String name = scanner.next();
                System.out.println("Montant:");

             // Check if the next token is a valid double
             while (!scanner.hasNextDouble()) {
                 System.out.println("Entrée non valide. Veuillez saisir un numéro valide.");
                 scanner.next(); // Consume the invalid input
             }

             double amount = scanner.nextDouble();
                si1Stub.addInvoice(code, name, amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleSYS2(SYS2Service sys2Stub, Scanner scanner) {
        try {
            System.out.println("Welcome sys2!");
            List<TransportsInvoice> invoices = sys2Stub.getTransportInvoices();

            for (TransportsInvoice invoice : invoices) {
                System.out.println(invoice.toString());
            }

            System.out.println("Vous voullez ajouter une facture de vente de transport? (oui/non)");
            String response = scanner.next();

            if (response.equalsIgnoreCase("oui")) {
                System.out.println("Nom:");
                String name = scanner.next();
                System.out.println("Type:");
                String type = scanner.next();
                System.out.println("Montant:");
                double amount = scanner.nextDouble();

                sys2Stub.addTransportInvoice(name, type, amount);
                System.out.println("Ajouter avec succés");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
