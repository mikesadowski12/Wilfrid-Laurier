package ec.lab;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ec.lab.SBStatefulRemote1;
import ec.lab.SBStatelessRemote1;


public class EJBClient {
	public static void main(String[] args) throws NamingException {
		
		SBStatelessRemote1 sbstateless = (SBStatelessRemote1) InitialContext
				.doLookup("ec-ejb/SBStateless1!ec.lab.SBStatelessRemote1");
		System.out.println(sbstateless.getSBType());
		System.out.println(sbstateless.getPrediction(60));

		SBStatefulRemote1 sbstateful = (SBStatefulRemote1) InitialContext
				.doLookup("ec-ejb/SBStateful1!ec.lab.SBStatefulRemote1");
		System.out.println(sbstateful.getSBType());
		System.out.println(sbstateful.Predict(80));
		System.out.println(sbstateful.getCounter());

		System.out.println("RMI done!");
	}
}


//SBStatefulRemote sbstateful = (SBStatefulRemote) InitialContext
//.doLookup("ec-mdb-entity/SBStateful!ec.lab.SBStatefulRemote");
//
//System.out.println(sbstateful.Predict(60, "admin"));
