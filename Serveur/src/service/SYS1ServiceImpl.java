package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import si.Invoice;
import si.SYS1Impl;

public class SYS1ServiceImpl extends UnicastRemoteObject implements SYS1Service{
	
	private SYS1Impl si ;
	
	public SYS1ServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.si = new SYS1Impl();
	}
	
	
	@Override
	public List<Invoice> getSalesInvoices() throws RemoteException {
		
		return si.getSalesInvoices();
	}


	@Override
	public void addInvoice(long id, String name, double price) throws RemoteException {
		si.addInvoice(id, name, price);
	}

	
	
	
}
