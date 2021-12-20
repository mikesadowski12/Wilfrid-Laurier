package ec.lab;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class ModelImplementation implements ModelI {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Model getModelByName(String modelname) {
		Query query = entityManager.createQuery("select m from Model m where m.name = :modelname ORDER BY m.date DESC");
		query.setParameter("modelname", modelname);

		try {
			Model model = null;
			ArrayList<Model> models = (ArrayList<Model>) query.getResultList();
			
			for (Model m : models) {
				model = m;
			}
			
			return model;
		} catch (EntityNotFoundException notFound) {
			return null;
		}
	}

	@Override
	public void saveModel(Model model) {
		entityManager.merge(model);
	}
}
