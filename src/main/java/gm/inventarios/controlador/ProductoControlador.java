package gm.inventarios.controlador;

import gm.inventarios.excepcion.RecursoNoEncontradoExcepcion;
import gm.inventarios.modelo.Producto;
import gm.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/inventario-app
@RequestMapping("/inventario-app")
@CrossOrigin(value = "http://localhost:4200")//para que angular pueda consumir los servicios de spring
public class ProductoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class); //para que se muestren los mensajes en la consola

    @Autowired
    private ProductoServicio productoServicio;

    //http://localhost:8080/inventario-app/productos
    @GetMapping("/productos")
    public List<Producto> obteberProductos(){
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtebidos: "+productos);
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos;
    }

    @PostMapping("/productos")
    public Producto guardarProducto(@RequestBody Producto producto){
        logger.info("Producto guardado: "+producto);
        return this.productoServicio.guardarProducto(producto);
    }
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id){
        Producto producto = this.productoServicio.BuscarProductoPorId(id);
        if(producto != null){
            return ResponseEntity.ok(producto);
        }else{
            throw new RecursoNoEncontradoExcepcion("No se encontro el Id: "+id);
        }
    }
    //Actualizar
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto productoRecibido){
        Producto producto = this.productoServicio.BuscarProductoPorId(id);
        producto.setDescripcion(productoRecibido.getDescripcion());//seteas la descripcion del producto que buscaste
        producto.setPrecio(productoRecibido.getPrecio());//seteas el precio del producto que buscaste
        producto.setExistencia(productoRecibido.getExistencia());//seteas la existencia del producto que buscaste
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    //Eliminar
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int id){
        Producto producto = this.productoServicio.BuscarProductoPorId(id);
        if(producto == null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el Id: "+id);
        }
        this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);

    }


}
