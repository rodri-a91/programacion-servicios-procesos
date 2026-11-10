package ceu.dam.psp.examen.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ceu.dam.psp.examen.exceptions.ErrorPagoException;
import ceu.dam.psp.examen.exceptions.NoAutorizadoException;
import ceu.dam.psp.examen.exceptions.NoExisteException;
import ceu.dam.psp.examen.exceptions.UsuarioDuplicadoException;
import ceu.dam.psp.examen.model.Articulo;
import ceu.dam.psp.examen.model.Usuario;


@Service
public class WallapopServiceImpl implements WallapopService {

    private static final Map<String, Usuario> usuarios = new HashMap<>();
    private static final Map<Long, Articulo> articulos = new HashMap<>();
    private static final AtomicLong idGenerador = new AtomicLong(10);

    public WallapopServiceImpl() {
        cargarDatosPrueba();
    }

   

    @Override
    public Usuario crearUsuario(String identificador, String descripcion, String contraseña) throws UsuarioDuplicadoException {
        if (usuarios.containsKey(identificador)) {
            throw new UsuarioDuplicadoException("El usuario ya existe: " + identificador);
        }
        Usuario u = new Usuario();
        u.setIdentificador(identificador);
        u.setDescripcion(descripcion);
        u.setContraseña(contraseña);
        u.setFechaAlta(LocalDate.now());
        u.setFechaUltimoAcceso(LocalDate.now());
        u.setCatalogo(new ArrayList<>());
        usuarios.put(identificador, u);
        return u;
    }

    @Override
    public Usuario autenticarUsuario(String identificador, String contraseña) throws NoAutorizadoException {
        Usuario u = usuarios.get(identificador);
        if (u == null || !u.getContraseña().equals(contraseña)) {
            throw new NoAutorizadoException("Usuario o contraseña incorrectos");
        }
        u.setFechaUltimoAcceso(LocalDate.now());
        return u;
    }

    @Override
    public Usuario consultarUsuario(String identificador) throws NoExisteException {
        Usuario u = usuarios.get(identificador);
        if (u == null) {
            throw new NoExisteException("No existe el usuario: " + identificador);
        }
        return u;
    }

    @Override
    public Usuario crearArticulo(Articulo articulo) {
        long id = idGenerador.getAndIncrement();
        articulo.setCodigo(id);
        articulos.put(id, articulo);

        // Añadir artículo al catálogo del propietario
        Usuario propietario = usuarios.get(articulo.getIdPropietario());
        if (propietario.getCatalogo() == null) {
            propietario.setCatalogo(new ArrayList<>());
        }
        propietario.getCatalogo().add(articulo);

        return propietario;
    }

    @Override
    public List<Articulo> buscarArticulos(String filtroNombre, String filtroDescripcion, LocalDate filtroFechaAltaMinima) {
        return articulos.values().stream()
                .filter(a -> ((filtroNombre == null && filtroDescripcion == null)
                			|| (filtroDescripcion == null && a.getNombre().toLowerCase().contains(filtroNombre.toLowerCase()))
                			|| (filtroNombre == null && a.getDetalles().toLowerCase().contains(filtroDescripcion.toLowerCase()))
                			|| ((a.getNombre().toLowerCase().contains(filtroNombre.toLowerCase())) || a.getDetalles().toLowerCase().contains(filtroDescripcion.toLowerCase()))))
                .filter(a -> (filtroFechaAltaMinima == null || a.getFechaPublicacion().isAfter(filtroFechaAltaMinima.minusDays(1))))
                .filter(a -> a.getEstado() == 0) // Solo disponibles
                .collect(Collectors.toList());
    }

    @Override
    public Articulo consultarArticulo(Long codigo) throws NoExisteException {
        Articulo a = articulos.get(codigo);
        if (a == null) {
            throw new NoExisteException("No existe el artículo con código: " + codigo);
        }
        return a;
    }

    @Override
    public void comprarArticulo(Long codigo) throws ErrorPagoException {
        Articulo a = articulos.get(codigo);
        if (a == null || a.getEstado() != 0) {
            throw new ErrorPagoException("El artículo no está disponible para la compra");
        }
        a.setEstado(1); // Vendido
    }
    
    
    private void cargarDatosPrueba() {
        try {
            // Crear usuarios de prueba
            Usuario u1 = crearUsuario("juan123", "Juan Pérez", "1234");
            Usuario u2 = crearUsuario("maria456", "María López", "abcd");

            // --- Artículos de Juan ---
            crearArticulo(new Articulo() {{
                setNombre("Bicicleta de montaña");
                setDetalles("Ruedas 26 pulgadas, color rojo");
                setFechaPublicacion(LocalDate.now().minusDays(10));
                setPrecio(150.0);
                setCategoria("Deporte");
                setIdPropietario(u1.getIdentificador());
                setEstado(0); // Disponible
            }});

            crearArticulo(new Articulo() {{
                setNombre("Raqueta de tenis");
                setDetalles("Marca Wilson, usada 2 meses");
                setFechaPublicacion(LocalDate.now().minusDays(8));
                setPrecio(40.0);
                setCategoria("Deporte");
                setIdPropietario(u1.getIdentificador());
                setEstado(0);
            }});

            crearArticulo(new Articulo() {{
                setNombre("Patines en línea");
                setDetalles("Talla 42, color negro");
                setFechaPublicacion(LocalDate.now().minusDays(5));
                setPrecio(60.0);
                setCategoria("Deporte");
                setIdPropietario(u1.getIdentificador());
                setEstado(0);
            }});

            // --- Artículos de María ---
            crearArticulo(new Articulo() {{
                setNombre("Teléfono móvil");
                setDetalles("Android, 64GB, negro");
                setFechaPublicacion(LocalDate.now().minusDays(7));
                setPrecio(200.0);
                setCategoria("Electrónica");
                setIdPropietario(u2.getIdentificador());
                setEstado(0);
            }});

            crearArticulo(new Articulo() {{
                setNombre("Tablet");
                setDetalles("10 pulgadas, funda incluida");
                setFechaPublicacion(LocalDate.now().minusDays(4));
                setPrecio(180.0);
                setCategoria("Electrónica");
                setIdPropietario(u2.getIdentificador());
                setEstado(0);
            }});

            crearArticulo(new Articulo() {{
                setNombre("Auriculares Bluetooth");
                setDetalles("Color blanco, poco uso");
                setFechaPublicacion(LocalDate.now().minusDays(2));
                setPrecio(50.0);
                setCategoria("Electrónica");
                setIdPropietario(u2.getIdentificador());
                setEstado(0);
            }});

        } catch (UsuarioDuplicadoException e) {
            // Nunca debería pasar en datos de prueba
            e.printStackTrace();
        }
    }



	@Override
	public String consultarApiKey() {
		return "ANOMIA";
	}
}
