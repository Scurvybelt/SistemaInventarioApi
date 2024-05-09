package gm.inventarios.repositorio;

import gm.inventarios.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
//Esto es lo que permite que puedas hacer operaciones CRUD con los metodos de Spring
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {//Esta interfaz hereda de JpaRepository, que es una interfaz de Spring Data JPA que nos proporciona métodos para realizar operaciones CRUD en la base de datos. y el pasas el modelo y el tipo de datos que es la llave primaria

}
