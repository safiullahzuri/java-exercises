package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class StudentServerInterfaceImpl extends UnicastRemoteObject implements StudentServerInterface{

    private HashMap<String, Double> scores = new HashMap<>();

    protected StudentServerInterfaceImpl() throws RemoteException {
        initializeStudents();
    }

    protected void initializeStudents(){
        scores.put("John", 90.5);
        scores.put("Michael", new Double(100));
        scores.put("Michelle", new Double(98.5));
    }

    @Override
    public double findScore(String name) throws RemoteException {
        Double d = scores.get(name);
        if (d== null){
            System.out.println("Student "+name+" is not found.");
            return -1;
        }else{
            System.out.println("Student "+name+" \'s score is "+d);
            return d;
        }
    }


}
