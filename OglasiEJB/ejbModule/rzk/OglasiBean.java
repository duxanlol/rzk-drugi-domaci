package rzk;


import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;

/**
 * Session Bean implementation class Session
 */
@Stateful
@LocalBean
@Interceptors(GeneralInterceptor.class)
public class OglasiBean implements OglasiBeanRemote {

    /**
     * Default constructor. 
     * 
     * Korisnički interfejs komunicira sa stateful session bean-om na srednjem sloju aplikacije koji
	   ima metode za logovanje, pretragu oglasa, dodavanje novog oglasa i javljanje na oglas
     * 
     */

	int userId;
	OglasKorisnik user;
	
	@PersistenceContext
	EntityManager em;
	
	@EJB
	OglasiSearchBeanLocal osbl;
	
    public OglasiBean() {
        // TODO Auto-generated constructor stub
    }
    
    
	public String login(String username, String password) {
		//@NamedQuery(name="OglasKorisnik.findByUsername", query="SELECT o FROM OglasKorisnik o WHERE o.username LIKE :username")
		Query q = em.createQuery("SELECT o FROM OglasKorisnik o WHERE o.username LIKE :user AND o.password LIKE :pass");
		q.setParameter("user", username);
		q.setParameter("pass", password);
		List<OglasKorisnik> users = q.getResultList();
		System.out.println(users.size());
		if (!users.isEmpty()) {
			try {

				user = users.get(0);
				return users.get(0).getNickname();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return "";
	}

	
	
	@Override
	public boolean createOglas(String text) {

		if (!text.isBlank()) {
			Ogla oglas = new Ogla();
			oglas.setText(text);
			oglas.setOglasKorisnik(user);
			em.persist(oglas);
			return true;
		}
								
		return false;
	}

	@Interceptors(SearchInterceptor.class)
	public List<Ogla> searchOglas(String text) {
		return osbl.searchText(text);
	}
	@Interceptors(JavljanjeInterceptor.class)
	public boolean javiSeNaOglas(int idOglas, String text) {
		Query oglasQuery = em.createNamedQuery("Ogla.findById");
		oglasQuery.setParameter("idOglas", idOglas);
		Ogla oglas = (Ogla) oglasQuery.getSingleResult();
		if (oglas == null)
			return false;
		OglasPrijava op = new OglasPrijava();
		op.setOgla(oglas);
		op.setOglasKorisnik(user);
		op.setText(text);
		em.persist(op);
		
		return true;
	}
}
