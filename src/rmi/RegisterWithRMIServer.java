package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegisterWithRMIServer {

    public static void main(String[] args) {
        try {
            StudentServerInterface obj = new StudentServerInterfaceImpl();
            Registry registry = LocateRegistry.getRegistry();

            

            registry.rebind("StudentServerInterfaceImpl", obj);

            System.out.println("Student server "+obj+" registered. ");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
