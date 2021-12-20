package ec.lab;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ec.lab.SBSingletonRemote;
import ec.lab.SBStatefulRemote;
import ec.lab.SBStatelessRemote;

public class EJBEarClient {
    public static void main(String[] args) throws NamingException {
        SBStatelessRemote sbstateless = (SBStatelessRemote) InitialContext
                .doLookup("ec-ear/ec-ejb/SBStateless!ec.lab.SBStatelessRemote");
        System.out.println(sbstateless.getSBType());
        System.out.println(sbstateless.getPrediction(70));

        SBStatefulRemote sbstateful = (SBStatefulRemote) InitialContext
                .doLookup("ec-ear/ec-ejb/SBStateful!ec.lab.SBStatefulRemote");
        System.out.println(sbstateful.getSBType());
        System.out.println(sbstateful.Predict(60));
        System.out.println(sbstateful.getCounter());

        SBSingletonRemote sbsingleton = (SBSingletonRemote) InitialContext
                .doLookup("ec-ear/ec-ejb/SBSingleton!ec.lab.SBSingletonRemote");
        System.out.println(sbsingleton.getSBType());
        System.out.println(sbsingleton.getCounter());

        System.out.println("RMI done!");
    }
}