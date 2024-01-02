package serviceGlobale;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.util.List;

import service.SYS1Service;
import service2.SYS2Service;
import si.Invoice;
import si2.TransportsInvoice;

public class GlobalRevenueServiceImpl extends UnicastRemoteObject implements GlobalRevenueService, Serializable {

    private SYS1Service sys1Service;
    private SYS2Service sys2Service;

    public GlobalRevenueServiceImpl(SYS1Service sys1Service, SYS2Service sys2Service) throws RemoteException {
        super();
        this.sys1Service = sys1Service;
        this.sys2Service = sys2Service;
    }

    @Override
    public double calculateOverallRevenue() throws RemoteException {
        double overallRevenue = 0.0;

        // Calculate revenue from SI1 (Flat file)
        List<Invoice> si1Invoices = sys1Service.getSalesInvoices();
        for (Invoice invoice : si1Invoices) {
            overallRevenue += invoice.getMontantVersée();
        }

        // Calculate revenue from SI2 (Database)
        List<TransportsInvoice> si2Invoices = sys2Service.getTransportInvoices();
        for (TransportsInvoice invoice : si2Invoices) {
            overallRevenue += invoice.getAmount();
        }

        return overallRevenue;
    }
}
