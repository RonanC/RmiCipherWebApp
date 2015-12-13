package ie.gmit.sw.cipher;

import java.rmi.Remote;
import java.rmi.RemoteException;

// this is the remote interface
public interface VigenereBreaker extends Remote {
	public String[] crack(String cipherText, int maxKeyLength) throws RemoteException;
	public String encrypt(String plainText, String key) throws RemoteException;
	public String decrypt(String cipherText, String key) throws RemoteException;
}
