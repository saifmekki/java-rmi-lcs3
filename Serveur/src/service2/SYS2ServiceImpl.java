package service2;

import java.rmi.RemoteException;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import si2.SYS2Impl;
import si2.TransportsInvoice;

public class SYS2ServiceImpl extends UnicastRemoteObject implements SYS2Service {
	
	private SYS2Impl si; 
	public SYS2ServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.si=new SYS2Impl();
	}

	@Override
	public List<TransportsInvoice> getTransportInvoices() throws RemoteException {
		// TODO Auto-generated method stub
		return si.getTransportsInvoices();
	}

	@Override
	public void addTransportInvoice(String clientName, String vehicleType, double amount) throws RemoteException {
		// TODO Auto-generated method stub
		si.addTransportInvoice(clientName, vehicleType, amount);	
	}
}
