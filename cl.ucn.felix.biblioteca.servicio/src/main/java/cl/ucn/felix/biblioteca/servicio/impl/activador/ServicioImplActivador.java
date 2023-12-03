package cl.ucn.felix.biblioteca.servicio.impl.activador;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import cl.ucn.felix.biblioteca.servicio.api.ServicioInventarioLibro;
import cl.ucn.felix.biblioteca.servicio.api.ServicioInventarioLibroImpl;

public class ServicioImplActivador  implements BundleActivator {

	ServiceRegistration<ServicioInventarioLibro> reg = null;
	
	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		Hashtable<String, Object> props = new Hashtable<String, Object>();
		//props.put("osgi.command.scope", cl.ucn.felix.biblioteca.servicio.api.ServicioInventarioLibroImpl.AMBITO);
		//props.put("osgi.command.function",cl.ucn.felix.biblioteca.servicio.api.ServicioInventarioLibroImpl.FUNCIONES);
		ServicioInventarioLibro servicioInventarioLibro = new ServicioInventarioLibroImpl(context);
		context.registerService(ServicioInventarioLibro.class.getName(),servicioInventarioLibro, props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
