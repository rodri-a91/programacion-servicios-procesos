package ceu.dam.psp.videojuegos.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

import ceu.dam.psp.videojuegos.exceptions.ApiException;
import ceu.dam.psp.videojuegos.exceptions.NotFoundException;
import ceu.dam.psp.videojuegos.model.Videojuego;

public class VideojuegoApiClientImpl implements VideojuegoApiClient{
	
	private String urlBase;
	private HttpClient httpClient;

	
	
	public VideojuegoApiClientImpl(String uuidUrl) { 
		urlBase = "https://crudcrud.com/api/" + uuidUrl + "/";
		httpClient = HttpClient.newHttpClient();
		// El constructor recibe el identificador que ha generado crudcrud.com para nuestro API y construye la URL base
	}
	
//	Tiene que devolver el videojuego con el ID indicado. Si no existe, lanzará 
//	 * NotFoundException. Si hay cualquier otro error, lanzará ApiException
	@Override
	public Videojuego findById(String id) throws NotFoundException, ApiException {
		HttpRequest request = HttpRequest.newBuilder(URI.create(urlBase + "/" + id)).GET().build();
		return null;
	}

	@Override
	public List<Videojuego> findByAñoPublicacion(Integer año) throws NotFoundException, ApiException {
		return null;
	}

	@Override
	public String create(Videojuego videojuego) throws ApiException {
		return null;
	}

	@Override
	public void update(Videojuego videojuego) throws NotFoundException, ApiException {
	}

	@Override
	public void delete(String id) throws NotFoundException, ApiException {
	}

}
