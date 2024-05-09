package gm.inventarios.servicio;

import gm.inventarios.modelo.Producto;
import java.util.List;

public interface IProductoServicio {
    public List<Producto> listarProductos();

    public Producto BuscarProductoPorId(Integer idProducto);

    public Producto guardarProducto(Producto producto);//insertar y actualizar

    public void eliminarProductoPorId(Integer idProducto);

}
