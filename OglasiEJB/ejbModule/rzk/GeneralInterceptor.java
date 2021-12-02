package rzk;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class GeneralInterceptor {
	
	@AroundInvoke
	public Object general(InvocationContext ic) throws Exception {
		System.out.println("Iz stateful bean-a se zove " + ic.getMethod().getName());
		return ic.proceed();
	}
}
