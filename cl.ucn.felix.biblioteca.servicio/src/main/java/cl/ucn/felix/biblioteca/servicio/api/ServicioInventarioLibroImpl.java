package cl.ucn.felix.biblioteca.servicio.api;

import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import cl.ucn.felix.biblioteca.api.ExcepcionLibroExistente;
import cl.ucn.felix.biblioteca.api.ExcepcionLibroInvalido;
import cl.ucn.felix.biblioteca.api.ExcepcionLibroNoEncontrado;
import cl.ucn.felix.biblioteca.api.Inventario;
import cl.ucn.felix.biblioteca.api.Inventario.CriterioBusqueda;
import cl.ucn.felix.biblioteca.api.Libro;
import cl.ucn.felix.biblioteca.api.LibroMutable;

public class ServicioInventarioLibroImpl implements ServicioInventarioLibro{

	private String sesion;
	private BundleContext contexto;
	
	
	public ServicioInventarioLibroImpl(BundleContext contexto) {
		
		this.contexto = contexto;
	}

		
	@Override
	public String login(String username, String password) throws ExcepcionCredencialInvalida {
		// TODO Auto-generated method stub
		if ("admin".equals(username) && "admin".equals(password)) {
			
			this.sesion = Long.toString(System.currentTimeMillis());
			return this.sesion;
		}
		throw new ExcepcionCredencialInvalida(username);
	}

	@Override
	public void logout(String sesion) throws ExcepcionSesionNoValidaTiempoEjecucion {
		// TODO Auto-generated method stub
		chequearSesion(sesion);
		this.sesion = null;
	}

	@Override
	public boolean sesionEsValida(String sesion) {
		// TODO Auto-generated method stub
		return this.sesion != null && this.sesion.equals(sesion);
	}
	
	protected void chequearSesion(String sesion) throws ExcepcionSesionNoValidaTiempoEjecucion {
		
		if (!sesionEsValida(sesion)) {
			throw new ExcepcionSesionNoValidaTiempoEjecucion(sesion);
		}
	}

	@Override
	public Set<String> obtenerGrupos(String sesion)  {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void adicionarLibro(String sesion, String isbn, String titulo, String autor, String categoria) throws ExcepcionSesionNoValidaTiempoEjecucion, ExcepcionLibroExistente, ExcepcionLibroInvalido {
		// TODO Auto-generated method stub
		this.chequearSesion(sesion);
		Inventario servicio = buscarLibroEnInventario();
		LibroMutable libro = servicio.crearLibro(isbn);
		libro.setAutor(autor);
		libro.setTitulo(titulo);
		libro.setCategoria(categoria);
		servicio.guardarLibro(libro);
	}

	@Override
	public void modificarCategoriaLibro(String sesion, String isbn, String categoria) throws ExcepcionSesionNoValidaTiempoEjecucion, ExcepcionLibroNoEncontrado, ExcepcionLibroInvalido {
		// TODO Auto-generated method stub
		this.chequearSesion(sesion);
		Inventario inventario = buscarLibroEnInventario();
		LibroMutable libro = inventario.cargarLibroParaEdicion(isbn);
		libro.setCategoria(categoria);
		inventario.guardarLibro(libro);
	}

	@Override
	public void removerLibro(String sesion, String isbn) throws ExcepcionSesionNoValidaTiempoEjecucion, ExcepcionLibroNoEncontrado {
		// TODO Auto-generated method stub
		this.chequearSesion(sesion);
		Inventario inventario = buscarLibroEnInventario();
		inventario.removerLibro(isbn);
	}

	@Override
	public Libro obtenerLibro(String sesion, String isbn) throws ExcepcionLibroNoEncontrado , ExcepcionSesionNoValidaTiempoEjecucion{
		// TODO Auto-generated method stub
		this.chequearSesion(sesion);
		Inventario inventario = buscarLibroEnInventario();
		return inventario.cargarLibro(isbn);
	}

	@Override
	public Set<String> buscarLibrosPorCategoria(String sesion, String categoriaLike) throws ExcepcionSesionNoValidaTiempoEjecucion {
		// TODO Auto-generated method stub
		this.chequearSesion(sesion);
		Inventario inventario = buscarLibroEnInventario();
		return inventario.buscarLibros(Map.of(CriterioBusqueda.CATEGORIA_LIKE, categoriaLike));
	}

	@Override
	public Set<String> buscarLibrosPorAutor(String session, String autorLike) throws ExcepcionSesionNoValidaTiempoEjecucion {
		// TODO Auto-generated method stub
		this.chequearSesion(sesion);
		Inventario inventario = buscarLibroEnInventario();
		return inventario.buscarLibros(Map.of(CriterioBusqueda.AUTOR_LIKE, autorLike));
	}

	@Override
	public Set<String> buscarLibrosPorTitulo(String sesion, String tituloLike) throws ExcepcionSesionNoValidaTiempoEjecucion {
		// TODO Auto-generated method stub
		this.chequearSesion(sesion);
		Inventario inventario = buscarLibroEnInventario();
		return inventario.buscarLibros(Map.of(CriterioBusqueda.TITULO_LIKE, tituloLike));
	}

	private Inventario buscarLibroEnInventario() throws ExcepcionSesionNoValidaTiempoEjecucion {
		
		String nombre = Inventario.class.getName();
		ServiceReference<?> ref = this.contexto.getServiceReference(nombre);
		if (ref == null) {
			throw new ExcepcionSesionNoValidaTiempoEjecucion(nombre);
		}
		return (Inventario) this.contexto.getService(ref);
 		
	}
	
}
