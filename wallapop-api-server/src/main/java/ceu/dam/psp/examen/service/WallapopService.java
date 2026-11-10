package ceu.dam.psp.examen.service;

import java.time.LocalDate;
import java.util.List;

import ceu.dam.psp.examen.exceptions.ErrorPagoException;
import ceu.dam.psp.examen.exceptions.NoAutorizadoException;
import ceu.dam.psp.examen.exceptions.NoExisteException;
import ceu.dam.psp.examen.exceptions.UsuarioDuplicadoException;
import ceu.dam.psp.examen.model.Articulo;
import ceu.dam.psp.examen.model.Usuario;

public interface WallapopService {

	/** Crea un usuario en el sistema. La contraseña recibida tiene que venir cifrada en SHA512. 
	 * Si ya hay un usuario con ese identificador, se lanzará UsuarioDuplicadoException */
	Usuario crearUsuario(String identificador, String descripcion, String contraseña) throws UsuarioDuplicadoException;
	
	/** Intenta logar al usuario en el sistema. La contraseña debe venir cifrada en SHA512. 
	 * Si no es correcta o no existe usuario, se lanzará NoAutorizadoException */
	Usuario autenticarUsuario(String identificador, String contraseña) throws NoAutorizadoException;
	
	
	/** Devuelve los datos del usuario con el identificador indicado. Si no existe, se lanzará 
	 * NoExisteException */
	Usuario consultarUsuario(String identificador) throws NoExisteException;
	
	/** Crea en el sistema el artículo recibido. El artículo debe tener indicado el identificador de su propietario. 
	 * Devuelve los datos del usuario propietario completo tras incorporar el nuevo artículo */
	Usuario crearArticulo(Articulo articulo);
	
	/** Devuelve la lista de artículos DISPONIBLES que su nombre contenga el filtro indicado, su descripción contenga el 
	 * filtro indicado, y su fecha de alta sea posterior a la indicada. Ninguno de los parámetros es obligatorio, si no se
	 * recibe, no se aplicará dicho filtro. Si no hay artículos que se ajusten a los filtros se devuelve lista vacía */
	List<Articulo> buscarArticulos(String filtroNombre, String filtroDescripcion, LocalDate filtroFechaAltaMinima);

	
	/** Devuelve el artículo con el código indicado. Si no existe, se lanza NoExisteException */
	Articulo consultarArticulo(Long codigo) throws NoExisteException;
	
	
	/** Cambia el estado del artículo indicado de DISPONIBLE a VENDIDO. Si el artículo indicado no existe o hay
	 * cualquier otro error, se lanzará ErrorPagoException */
	void comprarArticulo(Long codigo) throws ErrorPagoException;
	
	
	/** Devuelve el API KEY configurado para controlar las peticiones al API. */
	String consultarApiKey();
	
	
	
}
