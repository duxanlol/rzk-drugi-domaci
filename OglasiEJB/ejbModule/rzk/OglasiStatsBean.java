package rzk;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Ogla;

/**
 * Session Bean implementation class OglasiStatsBean
 */
@Singleton
@LocalBean
public class OglasiStatsBean implements OglasiStatsBeanLocal {

    /**
     * Default constructor. 
     */
	
	int javljanja;
	HashMap<Integer, Integer> pregleda;
	
	@PersistenceContext
	EntityManager em;
	
	private Timer tm;
	@Resource
	TimerService ts;
	
    public OglasiStatsBean() {
        javljanja = 0;
        pregleda = new HashMap<Integer, Integer>();
    }

    public void incPregleda(int id) {
    	pregleda.putIfAbsent(id, 0);
    	pregleda.put(id, pregleda.get(id) + 1);
    }
    
    public int incJavljanja() {
    	return ++javljanja; 
    }
    @Schedule(minute="0", hour="0", persistent = false)
    public void scheduleIspisJavljanja() {
    	System.out.println("Danas je bilo " + javljanja);
    	javljanja = 0;
    }
    
    @Timeout
	public void updateDB() {
    	System.out.println("Zovem update.");
		for (Entry<Integer, Integer> entry : pregleda.entrySet()) {
			Ogla o = em.find(Ogla.class, entry.getKey());
			o.setBrojPregleda(o.getBrojPregleda() + entry.getValue());
			em.merge(o);
		}
		pregleda.clear(); 
	}
    @PostConstruct
    public void startTimer() {
    	TimerConfig config = new TimerConfig();
    	config.setPersistent(false);
    	int minuta = 1; //1 zbog testiranja inace 15.
    	tm = ts.createIntervalTimer(0, TimeUnit.MINUTES.toMillis(minuta) , config);
    }
    
    public void cancelTimer() {
    	tm.cancel();
    }
}
