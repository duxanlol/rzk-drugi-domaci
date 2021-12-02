package rzk;

import java.util.List;

import javax.ejb.Remote;
import model.Ogla;

@Remote
public interface OglasiBeanRemote {

	String login(String username, String password);

	boolean createOglas(String text);
	
	List<Ogla> searchOglas(String text);
	
	boolean javiSeNaOglas(int idOglas, String text);

}
