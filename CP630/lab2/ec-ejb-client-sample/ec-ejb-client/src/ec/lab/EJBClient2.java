package ec.lab;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ec.lab.SBStatefulRemote1;

public class EJBClient2 {
	public static void main(String[] args) throws NamingException {
		
		SBStatefulRemote1 sbstateful = (SBStatefulRemote1) InitialContext
		.doLookup("ec-ejb/SBStateful1!ec.lab.SBStatefulRemote1");
		
		System.out.println(sbstateful.Predict(70, "admin"));
		System.out.println(sbstateful.Predict(60, "guest"));
		System.out.println("RMI done!");
	}
}
