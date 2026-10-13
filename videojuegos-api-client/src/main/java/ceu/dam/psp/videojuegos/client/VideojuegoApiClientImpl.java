package ceu.dam.psp.videojuegos.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import ceu.dam.psp.videojuegos.exceptions.ApiException;
import ceu.dam.psp.videojuegos.exceptions.NotFoundException;
import ceu.dam.psp.videojuegos.model.Videojuego;
import tools.jackson.databind.ObjectMapper;

public class VideojuegoApiClientImpl implements VideojuegoApiClient {

	private String urlBase;
	private final HttpClient client = HttpClient.newHttpClient();
	private final ObjectMapper mapper = new ObjectMapper();

	public VideojuegoApiClientImpl(String uuidUrl) {
		urlBase = "https://ca82a2abf4224afdc389.free.beeceptor.com/api/" + uuidUrl;
//		httpClient = HttpClient.newHttpClient();
		// El constructor recibe el identificador que ha generado crudcrud.com para
		// nuestro API y construye la URL base
	}

//	Tiene que devolver el videojuego con el ID indicado. Si no existe, lanzará 
//	 * NotFoundException. Si hay cualquier otro error, lanzará ApiException
	@Override
	public Videojuego findById(String id) throws NotFoundException, ApiException {
		try {
			HttpRequest request = HttpRequest.newBuilder(URI.create(urlBase + "/" + id)).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() == 404) {
				throw new NotFoundException("Hubo un problema");
			}
			return mapper.readValue(response.body(), Videojuego.class);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			throw new ApiException("Hubo un problema", e);
		}
	}

	@Override
	public List<Videojuego> findByAñoPublicacion(Integer año) throws NotFoundException, ApiException {
		try {
			HttpRequest request = HttpRequest.newBuilder(URI.create(urlBase + "?añoPublicacion=" + año)).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			return Arrays.asList(mapper.readValue(response.body(), Videojuego[].class));

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String create(Videojuego videojuego) throws ApiException {
		try {
			String videojuegoJson = mapper.writeValueAsString(videojuego);
			HttpRequest request = HttpRequest.newBuilder(URI.create(urlBase))
					.POST(BodyPublishers.ofString(videojuegoJson)).build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			Videojuego videojuegoCreado = mapper.readValue(response.body(), Videojuego.class);
			return videojuegoCreado.getId();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void update(Videojuego videojuego) throws NotFoundException, ApiException {
		findById(videojuego.getId());
		try {
			String videojuegoJson = mapper.writeValueAsString(videojuego);
			HttpRequest request = HttpRequest.newBuilder(URI.create(urlBase))
					.PUT(BodyPublishers.ofString(videojuegoJson)).build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			if (response.statusCode() == 202) {

			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(String id) throws NotFoundException, ApiException {
	}

}
