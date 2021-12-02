package rzk;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Ogla;

/**
 * Session Bean implementation class OglasiSearchBean
 */
@Stateless
@LocalBean
public class OglasiSearchBean implements OglasiSearchBeanLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
	
    public OglasiSearchBean() {
    	super();
    }
    
    @SuppressWarnings("unchecked")
	public List<Ogla> searchText(String text) {
    	
		Query oglasQuery = em.createNamedQuery("Ogla.findByText");
		oglasQuery.setParameter("text", text);
		List<Ogla> oglas = oglasQuery.getResultList();

		System.out.println("Trazio oglase, ima ih " + oglas.size());
		
		return oglas;
    	
    }

}
