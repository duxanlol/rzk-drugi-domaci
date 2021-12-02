package rzk;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class JavljanjeInterceptor {

	@EJB
	OglasiStatsBeanLocal osbl;
	
	@AroundInvoke
	public Object incJavljanje(InvocationContext ic) throws Exception {
		System.out.println("povecao javljanja. Sada su " + osbl.incJavljanja());
		return ic.proceed();
	}
}
