package ec.lr;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.jpa.model.Model;
import stats.ejb.ModelEJBStateless;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

@Stateless
@LocalBean
public class LRStateless implements LRStatelessLocal {
    @EJB
    private ModelEJBStateless modelEJBStateless;

	@Override
	public String prediction() {
		Model model = modelEJBStateless.getModelByName("weka-lr");
    	ByteArrayInputStream bis = new ByteArrayInputStream(model.getObject());
    	ObjectInput in = null;
    	Instances predicationDataSet = null;

    	try {
      	  try {
			in = new ObjectInputStream(bis);
			Classifier cls = (Classifier) in.readObject();

          	predicationDataSet = DataSource.read("data/house_unknown.arff");
          	predicationDataSet.setClassIndex(predicationDataSet.numAttributes()-1);

          	Instance predicationDataInstance = predicationDataSet.lastInstance();
    		double value = cls.classifyInstance(predicationDataInstance);
    		System.out.println(value);

    	    for (int i = 0; i < predicationDataSet.numInstances(); i++) {
    		      double clsLabel = cls.classifyInstance(predicationDataSet.instance(i));
    		      System.out.println("1=" + predicationDataSet.instance(i));
    		      System.out.println("2=" + predicationDataSet.instance(i));
    		      predicationDataSet.instance(i).setClassValue(clsLabel);
		    }

        	String predictionResults = predicationDataSet.toString();

				 	return predictionResults;
      	  } catch (IOException e) {
      		  e.printStackTrace();
      	  } catch (ClassNotFoundException e) {
      		  e.printStackTrace();
      	  } catch (Exception e) {
      		  e.printStackTrace();
      	  }
      	} finally {
      	  try {
      	    if (in != null) {
      	      in.close();
      	    }
      	  } catch (IOException ex) {
      	    // ignore close exception
      	  }
      	}


//    	return predicationDataSet;
    	return "null";
	}
}
