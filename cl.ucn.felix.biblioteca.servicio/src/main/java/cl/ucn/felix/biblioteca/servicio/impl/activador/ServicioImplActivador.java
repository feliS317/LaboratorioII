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
		ServicioInventarioLibro servicioInventarioLibro = new ServicioInventarioLibroImpl(context);
		reg = (ServiceRegistration<ServicioInventarioLibro>) context.registerService(ServicioInventarioLibro.class.getName(),servicioInventarioLibro, props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		if(reg != null) {
			reg.unregister();
		}
	}

}
