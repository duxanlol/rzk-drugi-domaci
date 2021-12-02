package rzk;

import java.util.List;

import javax.ejb.Local;

import model.Ogla;

@Local
public interface OglasiSearchBeanLocal {

	
	List<Ogla> searchText(String text);
}
