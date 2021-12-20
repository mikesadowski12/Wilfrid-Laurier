package ec.lab;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import weka.core.Attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.DenseInstance;

@RestController
public class ModelController {
	// TODO FIX THISSSSSSSSSSSSSSSSSS
    private static  Instance ToDataInstance(String p) throws Exception {
		ArrayList<Attribute> houseatts = new ArrayList<Attribute>();
		houseatts.add(new Attribute("houseSize"));
		houseatts.add(new Attribute("lotSize"));
		houseatts.add(new Attribute("bedrooms"));
		houseatts.add(new Attribute("granite"));
		houseatts.add(new Attribute("bathroom"));
		houseatts.add(new Attribute("sellingPrice"));
		Instances prdictdata = new Instances("HousePredict", houseatts, 0);

		double[] prevals = { 3198, 9669, 5, 1, 1, 0 };

		String[] arrOfStr = p.split(",");

		for (int i = 0; i < prevals.length - 1; i++) {
			prevals[i] = Double.valueOf(arrOfStr[i]);
		}
		prdictdata.add(new DenseInstance(1, prevals));
		prdictdata.setClassIndex(prdictdata.numAttributes() - 1);
		Instance predicationDataInstance = prdictdata.lastInstance();
        
       return predicationDataInstance;    
    }
	
	@Autowired
	private ModelI modeli;
    
	@RequestMapping("/prediction/{prediction}")
	String predict(@PathVariable String prediction) throws IOException {
		Model model = modeli.getModelByName("weka-lr");

		if (model != null) {
			ByteArrayInputStream bis = new ByteArrayInputStream(((ec.lab.Model) model).getObject());
			ObjectInput in = new ObjectInputStream(bis);

			try {
				Classifier cls = (Classifier) in.readObject();
				Instance predicationDataInstance = ModelController.ToDataInstance(prediction);

				return "{\"value\": " + cls.classifyInstance(predicationDataInstance) + "}";
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return prediction;
	}
}
