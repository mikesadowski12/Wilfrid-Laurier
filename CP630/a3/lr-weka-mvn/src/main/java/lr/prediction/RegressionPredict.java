package lr.prediction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class RegressionPredict {
	public static void main(String[] args) throws Exception {
		String linearRegressionModelFileName = args[1];
		String predictionFileName = args[3];
		String outputFileName = args[5];
		
		Instances predicationDataSet = DataSource.read("data/" + predictionFileName);
		predicationDataSet.setClassIndex(predicationDataSet.numAttributes()-1);
		
		Classifier cls = (Classifier) weka.core.SerializationHelper.read("model/" + linearRegressionModelFileName);
		
		Instance predicationDataInstance = predicationDataSet.lastInstance();
		double value = cls.classifyInstance(predicationDataInstance);
		System.out.println(value);
		
	    for (int i = 0; i < predicationDataSet.numInstances(); i++) {
	      double clsLabel = cls.classifyInstance(predicationDataSet.instance(i));
	      System.out.println(predicationDataSet.instance(i));
	      predicationDataSet.instance(i).setClassValue(clsLabel);
	    }
	    String predictionResults = predicationDataSet.toString();
	    System.out.println(predictionResults);
	    
	    final String DIR_NAME = "model";
	    File directory = new File(DIR_NAME);
	    File file = new File(outputFileName);
		
	    if (!directory.exists()) {
    		directory.mkdir();
	    }
	    
	    try {
	    	file.createNewFile();
	    	FileOutputStream fileOut = new FileOutputStream(DIR_NAME + "/" + outputFileName);
	    	ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    	out.writeObject(predictionResults);
	    	out.close();
	    	fileOut.close();
	    	System.out.printf("Data is saved in " + DIR_NAME + "/" + outputFileName);
	    } catch (IOException i) {
	    	i.printStackTrace();
	    }
	}
}
