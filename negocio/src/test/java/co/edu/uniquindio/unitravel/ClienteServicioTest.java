package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Cliente;
import co.edu.uniquindio.unitravel.entidades.Genero;
import co.edu.uniquindio.unitravel.servicios.CiudadServicio;
import co.edu.uniquindio.unitravel.servicios.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional //Garantiza que los datos no sean persistentes en la base de datos
public class ClienteServicioTest {


    @Autowired
    private ClienteServicio clienteServicio;


    @Autowired
    private CiudadServicio ciudadServicio;

    public Cliente crearCliente(){
        Cliente u = new Cliente("1094900","Jorge Iván", "Vargas", "password", "port@gmail.com",null,null, null);
        Map<String, String> telefono= new HashMap<>();
        telefono.put("3215566772","Trabajo");
        telefono.put("6067374637","Casa");
        telefono.put("3219900211","Personal");
        u.setTelefono(telefono);
        Ciudad c= ciudadServicio.obtenerCiudad(66);
        u.setCiudad(c);

        u.setGenero(Genero.MASCULINO);
        return u;
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarClienteTest(){

        try {
            Cliente guardado= clienteServicio.registrarUsuario(crearCliente());
            Assertions.assertNotNull(guardado);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarClienteTest(){

        try {
            Cliente guardado= clienteServicio.registrarUsuario(crearCliente());
            Cliente buscado=clienteServicio.obtenerUsuario(guardado.getCedula());
            Assertions.assertNotNull(buscado);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarClienteTest(){
        Cliente c= crearCliente();

        try {
            clienteServicio.registrarUsuario(c);
            clienteServicio.eliminarUsuario(c.getCedula());
            Cliente e= clienteServicio.obtenerUsuario(c.getCedula());
            Assertions.assertNull(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarClienteTet(){
        Cliente c= crearCliente();

        try {
            clienteServicio.registrarUsuario(c);
            c.setGenero(Genero.FEMENINO);
            c.setEmail("0039@gmail.com");
            clienteServicio.actualizarUsuario(c);
            clienteServicio.listarUsuarios().forEach(System.out::println);
            Assertions.assertEquals("0039@gmail.com", c.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClientesTest(){
        Cliente c= crearCliente();

        try {
            clienteServicio.registrarUsuario(c);
            List<Cliente> cp=clienteServicio.listarUsuarios();
            cp.forEach(System.out::println);
            Assertions.assertEquals(5,clienteServicio.listarUsuarios().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClientesReservaTest(){
        clienteServicio.listarClientesReserva().forEach(System.out::println);
    }
}
