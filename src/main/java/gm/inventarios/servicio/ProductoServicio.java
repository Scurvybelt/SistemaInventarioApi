package gm.inventarios.servicio;

import gm.inventarios.modelo.Producto;
import gm.inventarios.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Con esto ya participa en la fabrica de Spring
public class ProductoServicio implements  IProductoServicio{

    @Autowired//inyectar una propiedad
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> listarProductos() {
        return this.productoRepositorio.findAll();//este metodo regresa todos los objetos de tipo Producto de la base de datos
    }

    @Override
    public Producto BuscarProductoPorId(Integer idProducto) {
        Producto producto = this.productoRepositorio.findById(idProducto).orElse(null);//este metodo regresa un objeto de tipo Producto de la base de datos
        return producto;
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        //Ssve busca si el Producto ya existe en la basWe y si existe le hace update de lo contrario lo inserta
        return this.productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProductoPorId(Integer idProducto) {
        this.productoRepositorio.deleteById(idProducto);//este metodo elimina un objeto de tipo Producto de la base de datos
    }
}
