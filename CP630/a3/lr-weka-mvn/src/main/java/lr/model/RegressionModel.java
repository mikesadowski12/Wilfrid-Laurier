package lr.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;

public class RegressionModel {
	private static void TrainAndGenerateModel(String[] args) throws Exception {
		String trainingDataSetFileName = args[1];
		String outputFileName = args[3];
		
		Instances trainingDataSet = DataSource.read("data/" + trainingDataSetFileName);
		trainingDataSet.setClassIndex(trainingDataSet.numAttributes()-1);
		
		LinearRegression cls = new LinearRegression();
		SelectedTag method = new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION);
		cls.setAttributeSelectionMethod(method);
		
		cls.buildClassifier(trainingDataSet);
		System.out.println(cls);
		
		final String DIR_NAME = "model";
		File directory = new File(DIR_NAME);
	    if (!directory.exists()) {
    		directory.mkdir();
	    }

		// https://stackoverflow.com/questions/9620683/java-fileoutputstream-create-file-if-not-exists
		final String FILE_NAME = DIR_NAME + "/" + outputFileName;
		File file = new File(FILE_NAME);
		file.createNewFile();

		weka.core.SerializationHelper.write(FILE_NAME, cls);		
	}
	
	private static void TestModel(String[] args) throws Exception {
		String linearRegressionModelFileName = args[1];
		String trainingDataSetFileName = args[3];
		String testingDataSetFileName = args[5];
		String outputFileName = args[7];
		
		Instances trainingDataSet = DataSource.read("data/" + trainingDataSetFileName);
		Instances testingDataSet = DataSource.read("data/" + testingDataSetFileName);
		
		trainingDataSet.setClassIndex(trainingDataSet.numAttributes()-1);
		testingDataSet.setClassIndex(testingDataSet.numAttributes()-1);
		
		LinearRegression cls = new LinearRegression();
		SelectedTag method = new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION);
		cls.setAttributeSelectionMethod(method);
		
		cls.buildClassifier(testingDataSet);
		
		Evaluation eval = new Evaluation(testingDataSet);
		eval.evaluateModel(cls, trainingDataSet);
		
		System.out.println("Linear Regression Model Evaluation (" + testingDataSetFileName + ")");
		String testingDataSetEval = eval.toSummaryString();
		System.out.println(testingDataSetEval);
		
		final String DIR_NAME = "model";
		final String FILE_NAME = DIR_NAME + "/" + linearRegressionModelFileName;
		Classifier cls1 = (Classifier) weka.core.SerializationHelper.read(FILE_NAME);
		eval.evaluateModel(cls1, trainingDataSet);
		
		System.out.println("Linear Regression Model Evaluation (" + linearRegressionModelFileName + ")");
		String linearRegressionModelEval = eval.toSummaryString();
		System.out.println(linearRegressionModelEval);
		
	    File directory = new File(DIR_NAME);
	    File file = new File(outputFileName);
		
	    if (!directory.exists()) {
    		directory.mkdir();
	    }
	    
	    try {
	    	file.createNewFile();
	    	FileOutputStream fileOut = new FileOutputStream(DIR_NAME + "/" + outputFileName);
	    	ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    	out.writeObject(testingDataSetEval + linearRegressionModelEval);
	    	out.close();
	    	fileOut.close();
	    	System.out.printf("Data is saved in " + DIR_NAME + "/" + outputFileName);
	    } catch (IOException i) {
	    	i.printStackTrace();
	    }
	}
	
	public static void main(String[] args) throws Exception {
		if (args[0].equals("-m")) {
			TestModel(args);
		} else {
			TrainAndGenerateModel(args);
		}
	}
}
