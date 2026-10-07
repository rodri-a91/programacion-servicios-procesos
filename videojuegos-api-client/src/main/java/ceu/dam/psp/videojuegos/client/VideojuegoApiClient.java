package ceu.dam.psp.videojuegos.client;

import java.util.List;

import ceu.dam.psp.videojuegos.exceptions.ApiException;
import ceu.dam.psp.videojuegos.exceptions.NotFoundException;
import ceu.dam.psp.videojuegos.model.Videojuego;

public interface VideojuegoApiClient {
	
	/** Tiene que devolver el videojuego con el ID indicado. Si no existe, lanzará 
	 * NotFoundException. Si hay cualquier otro error, lanzará ApiException
	 * @param id
	 * @return
	 * @throws NotFoundException
	 * @throws ApiException
	 */
	public Videojuego findById(String id) throws NotFoundException, ApiException;
	
	
	/** Tiene que devolver la lista de videojuegos que tengan el año de publicación
	 * indicado. Si no hay ninguno, lanzará NotFoundException. Si hay cualquier
	 * otro error, lanzará ApiException
	 * @param año
	 * @return
	 * @throws NotFoundException
	 * @throws ApiException
	 */
	public List<Videojuego> findByAñoPublicacion(Integer año) throws NotFoundException, ApiException;
	
	
	/** Debe crear el videojuego recibido. Devolverá el ID generado. 
	 * Si hay cualquier error, lanzará ApiException
	 * @param videojuego
	 * @return
	 * @throws ApiException
	 */
	public String create(Videojuego videojuego) throws ApiException;
	
	
	/** Debe actualizar el videojuego indicado. Si no existe, lanzará NotFoundException.
	 * Si hay cualquier otro error, lanzará ApiException
	 * @param videojuego
	 * @return
	 * @throws NotFoundException
	 * @throws ApiException
	 */
	public void update(Videojuego videojuego) throws NotFoundException, ApiException;
	
	
	/** Debe borrar el videojuego con ID indicado. Si no existe, lanzará NotFoundException.
	 * Si hay cualquier otro error, lanzará ApiException
	 * @param id
	 * @return
	 * @throws NotFoundException
	 * @throws ApiException
	 */
	public void delete(String id) throws NotFoundException, ApiException;
	
	
}
