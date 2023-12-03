package cl.ucn.felix.biblioteca.servicio.api;

import java.util.Set;

import cl.ucn.felix.biblioteca.api.ExcepcionLibroExistente;
import cl.ucn.felix.biblioteca.api.ExcepcionLibroInvalido;
import cl.ucn.felix.biblioteca.api.ExcepcionLibroNoEncontrado;
import cl.ucn.felix.biblioteca.api.Libro;

public interface ServicioInventarioLibro extends Autenticacion{

	Set<String> obtenerGrupos(String sesion) throws ExcepcionSesionNoValidaTiempoEjecucion;
	void adicionarLibro(String sesion, String isbn, String titulo, String autor, String categoria) throws ExcepcionLibroExistente, ExcepcionLibroInvalido, ExcepcionSesionNoValidaTiempoEjecucion;
	void modificarCategoriaLibro(String sesion, String isbn, String categoria) throws ExcepcionLibroNoEncontrado, ExcepcionLibroInvalido, ExcepcionSesionNoValidaTiempoEjecucion;
	void removerLibro(String sesion, String isbn) throws ExcepcionLibroNoEncontrado, ExcepcionSesionNoValidaTiempoEjecucion;
	Libro obtenerLibro(String sesion, String isbn) throws ExcepcionLibroNoEncontrado, ExcepcionSesionNoValidaTiempoEjecucion;
	Set<String> buscarLibrosPorCategoria(String sesion, String categoriaLike) throws ExcepcionSesionNoValidaTiempoEjecucion;
	Set<String> buscarLibrosPorAutor(String session, String autorLike) throws ExcepcionSesionNoValidaTiempoEjecucion;
	Set<String> buscarLibrosPorTitulo(String sesion, String tituloLike) throws ExcepcionSesionNoValidaTiempoEjecucion;
	
}
