package rzk;

import javax.ejb.Local;

@Local
public interface OglasiStatsBeanLocal {
    public void incPregleda(int id); 
    
    public int incJavljanja();
}
