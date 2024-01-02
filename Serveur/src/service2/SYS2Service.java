package service2;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.util.List;

import si2.TransportsInvoice;

public interface SYS2Service extends Remote {
	
    List<TransportsInvoice> getTransportInvoices() throws RemoteException;
    public void addTransportInvoice(String clientName, String vehicleType, double amount) throws RemoteException;

}
