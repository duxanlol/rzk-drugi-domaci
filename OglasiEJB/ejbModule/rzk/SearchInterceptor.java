package rzk;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Ogla;

public class SearchInterceptor {


	@EJB
	OglasiStatsBeanLocal osbl;
	
	@AroundInvoke
	public Object incPregledi(InvocationContext ic) throws Exception {
		@SuppressWarnings("unchecked")
		List<Ogla> oglasi = (List<Ogla>) ic.getMethod().invoke(ic.getTarget(),ic.getParameters());
		oglasi.forEach(oglas -> {
			osbl.incPregleda(oglas.getIdOglas());
			System.out.println("Dizem za oglas " + oglas.getIdOglas());
		});
		return ic.proceed();
	}
	
}
