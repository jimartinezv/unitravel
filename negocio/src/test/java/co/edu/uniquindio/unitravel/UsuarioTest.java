package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Cliente;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.repositorios.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {
    @Autowired
    private ClienteRepo usuarioRepo;

    @Test
    public void registrarCliente(){
        Cliente u = new Cliente();
        u.setCedula("1094908238");
        u.setNombre("Jorge Iván");
        u.setApellidos("Martínez Vargas");
        u.setEmail("jimv9200@gmail.com");
        //Guardamos el registro
        usuarioRepo.save(u);

        System.out.println("grabado");
        //Comprobamos que si haya quedado
        Assertions.assertNotNull(u);

    }

    @Test
    public void eliminarCLiente(){
        Cliente u = new Cliente();
        u.setCedula("1094908238");
        u.setNombre("Jorge Iván");
        u.setApellidos("Martínez Vargas");
        u.setEmail("jimv9200@gmail.com");
        //Guardamos el registro
        usuarioRepo.save(u);
        usuarioRepo.delete(u);

        Cliente buscado= usuarioRepo.findById("1094908238").orElse(null);
        System.out.println("grabado");
        //Comprobamos que si haya quedado

        Assertions.assertNull(buscado);
    }

    @Test
    public void updateCliente(){
        Cliente u = new Cliente();
        u.setCedula("1094908238");
        u.setNombre("Jorge Iván");
        u.setApellidos("Martínez Vargas");
        u.setEmail("jimv9200@gmail.com");
        //Guardamos el registro
        usuarioRepo.save(u);
        u.setEmail("jimartinezv@uqvirtual.edu.co");
        usuarioRepo.save(u);
        Assertions.assertEquals("jimartinezv@uqvirtual.edu.co", u.getEmail());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar(){
        Cliente u = new Cliente();
        u.setCedula("1094908238");
        u.setNombre("Jorge Iván");
        u.setApellidos("Martínez Vargas");
        u.setEmail("jimv9200@gmail.com");
        u.setPassword("10029");

        //Guardamos el registro
        usuarioRepo.save(u);
        Cliente u1 = new Cliente();
        u1.setCedula("11094908238");
        u1.setNombre("Jorge Iván");
        u1.setApellidos("Martínez Vargas");
        u1.setEmail("jimv92001@gmail.com");
        u1.setPassword("10029");
        //Guardamos el registro
        usuarioRepo.save(u1);
        Cliente u2 = new Cliente();
        u2.setCedula("12094908238");
        u2.setNombre("Jorge Iván");
        u2.setApellidos("Martínez Vargas");
        u2.setEmail("jimv92002@gmail.com");
        u2.setPassword("10029");
        //Guardamos el registro
        usuarioRepo.save(u2);


        List<Cliente> clientes=usuarioRepo.findAll();
        System.out.println(clientes);
        System.out.println("aqui");
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void loginUsuario(){
        Optional<Cliente> cliente= usuarioRepo.findByPasswordAndEmail("alajj","guti@gmail.com");
        System.out.println(cliente.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void pageableUsuario(){
        Page<Cliente> cliente= usuarioRepo.findAll(PageRequest.of(0,3));
        cliente.get().forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuarioSort(){
        List<Cliente> clientes= usuarioRepo.findAll(Sort.by(Sort.Direction.DESC,"nombre"));
        clientes.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void reservaUsuarioEmail(){
        List<Reserva> reservas = usuarioRepo.reservaCliente("guti@gmail.com");
        reservas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void reservasCliente(){
        List<Object[]> reservas = usuarioRepo.obtenerReservaClientes();
        reservas.forEach(c-> System.out.println(c[0]+"-"+c[1]));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void clienteByTelefono(){
        List<Cliente> clientes = usuarioRepo.obtenerUsuarioTelefono("7495230");
        clientes.forEach(System.out::println);
    }
}
