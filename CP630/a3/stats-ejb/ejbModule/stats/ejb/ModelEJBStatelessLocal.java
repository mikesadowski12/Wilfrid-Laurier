package stats.ejb;

import ec.jpa.model.Model;

public interface ModelEJBStatelessLocal {
    Model getModelByName(String modelname);	
	void saveModel(Model model);
}
