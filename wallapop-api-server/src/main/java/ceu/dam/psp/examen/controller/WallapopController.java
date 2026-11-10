package ceu.dam.psp.examen.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.psp.examen.dto.request.ArticuloCreateRequestDto;
import ceu.dam.psp.examen.dto.request.FiltroRequestDto;
import ceu.dam.psp.examen.dto.request.UserCreateRequestDto;
import ceu.dam.psp.examen.dto.request.UserLoginRequestDto;
import ceu.dam.psp.examen.dto.response.ArticuloResponseDto;
import ceu.dam.psp.examen.dto.response.UserResponseDto;
import ceu.dam.psp.examen.exceptions.ErrorPagoException;
import ceu.dam.psp.examen.exceptions.NoAutorizadoException;
import ceu.dam.psp.examen.exceptions.NoExisteException;
import ceu.dam.psp.examen.exceptions.UsuarioDuplicadoException;
import ceu.dam.psp.examen.model.Articulo;
import ceu.dam.psp.examen.model.Usuario;
import ceu.dam.psp.examen.service.WallapopService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallapop")

public class WallapopController {
	final WallapopService service;
	final ModelMapper mapper;

	public WallapopController(WallapopService service) {
		this.service = service;
		this.mapper = new ModelMapper();
	}

	@PostMapping("/usuarios")
	public UserResponseDto crearUsuario(@Valid @RequestBody UserCreateRequestDto user)
			throws UsuarioDuplicadoException {

		user.setContraseña(DigestUtils.sha512_224Hex(user.getContraseña()));
		Usuario userCreado = service.crearUsuario(user.getIdentificador(), user.getDescripcion(), user.getContraseña());

		return mapper.map(userCreado, UserResponseDto.class);
	}

	@PostMapping("/login")
	public UserResponseDto autenticarUsuario(@Valid @RequestBody UserLoginRequestDto user)
			throws NoAutorizadoException {
		user.setContraseña(DigestUtils.sha512_224Hex(user.getContraseña()));
		Usuario userLogin = service.autenticarUsuario(user.getIdentificador(), user.getContraseña());

		return mapper.map(userLogin, UserResponseDto.class);
	}

	@GetMapping("/usuarios/{identificador}")
	public UserResponseDto consultarUsuario(@PathVariable String identificador) throws NoExisteException {
		Usuario user = service.consultarUsuario(identificador);
		return mapper.map(user, UserResponseDto.class);
	}

	@PostMapping("/articulos")
	@Operation(summary = "", description = "")
	public UserResponseDto crearArticulo(@Valid @RequestBody ArticuloCreateRequestDto articulo) throws NoExisteException {
		Usuario user = service.consultarUsuario(articulo.getIdPropietario());

		Articulo art = mapper.map(articulo, Articulo.class);
		art.setFechaPublicacion(LocalDate.now());
		art.setEstado(0);
		user = service.crearArticulo(art);

		return mapper.map(user, UserResponseDto.class);
	}

	@GetMapping("articulos/buscar")
	public List<ArticuloResponseDto> buscarArticulos(@RequestParam(required = false) FiltroRequestDto filtro,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate filtroFechaAltaMinima) {

		List<Articulo> articulos = service.buscarArticulos(filtro.getFiltroNombre(), filtro.getFiltroDescripcion(),
				filtroFechaAltaMinima);
		return articulos.stream().map(a -> mapper.map(a, ArticuloResponseDto.class)).toList();
	}

	@GetMapping("articulos/{codigo}")
	public ArticuloResponseDto consultarArticulo(@PathVariable Long codigo) throws NoExisteException {
		Articulo articulo = service.consultarArticulo(codigo);
		return mapper.map(articulo, ArticuloResponseDto.class);
	}

	@PutMapping("articulos/{codigo}/comprar")
	public void comprarArticulo(@PathVariable Long codigo) throws ErrorPagoException {
		service.comprarArticulo(codigo);

	}

}
