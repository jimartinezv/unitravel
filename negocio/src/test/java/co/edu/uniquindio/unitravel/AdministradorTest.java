package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Administrador;
import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Departamento;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional //Garantiza que los datos no sean persistentes en la base de datos
public class AdministradorTest {

    @Autowired
    private AdministradorServicio administradorServicio;


    public AdministradorHotel crearAdminHotel(){
        AdministradorHotel ah= new AdministradorHotel();
        ah.setNombre("Victor Hugo");
        ah.setApellidos("Gonzalez Marquez");
        ah.setEmail("victor@gmail.com");
        ah.setCedula("1098773");
        ah.setPassword("clave");
        return ah;
    }

    public Ciudad crearCiudad(){
        Ciudad c= new Ciudad();
        c.setNombre("Nueva Armenia");
        c.setEstado(1);

        try {
            c.setDepartamento(administradorServicio.consultarDepartamento(63));

            System.out.println(c.getNombre()+c.getDepartamento().getNombre());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return c;
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void loginAdminTest() {
        try {
            Administrador admin= administradorServicio.loginAdmin("jimv9200@gmail.com","019992");

            Assertions.assertEquals(admin.getEmail(),"jimv9200@gmail.com");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearCiudadTest(){

        try{

            Ciudad nueva=crearCiudad();
            System.out.println("qui");
                    administradorServicio.crearCiudad(nueva);
            System.out.println("ya guardo");

            Assertions.assertEquals(nueva.getNombre(),"Nueva Armenia");
        }catch (Exception e){
            System.out.println(e.getMessage()+" ERROR CUIDADO");
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCiudadTest(){
        try{

            Ciudad nueva=crearCiudad();
            administradorServicio.crearCiudad(nueva);
            nueva.setNombre("Nueva Nueva Armenia");
            administradorServicio.modificarCiudad(nueva);

            Assertions.assertEquals(nueva.getNombre(),"Nueva Nueva Armenia");
        }catch (Exception e){
            System.out.println(e.getMessage()+" ERROR");
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCIudades(){
        System.out.println(administradorServicio.listarCiudades());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudadTest() {
        try {
            Ciudad c=administradorServicio.consultarCiudad(66);
            Assertions.assertEquals(c.getNombre(),"Armenia");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCiudadTest(){
        Ciudad c=crearCiudad();
        try{
            administradorServicio.crearCiudad(c);

            administradorServicio.eliminarCiudad(c.getCodigo());


            Assertions.assertNull(administradorServicio.consultarCiudad(c.getCodigo()));

        }catch (Exception e){

            System.out.println(e.getMessage()+" ERROR eliminando");

        }



    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearAdminHotelTest(){
        AdministradorHotel ah= crearAdminHotel();
        try {
            administradorServicio.crearAdministradorHotel(ah);
            Assertions.assertEquals(administradorServicio.buscarAdminHotel(ah.getCedula()).getCedula(), "1098773");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdminHotelTest(){

        try {
            AdministradorHotel ah= crearAdminHotel();
            administradorServicio.crearAdministradorHotel(ah);
            AdministradorHotel ah2=ah;
            ah2.setNombre("Juanito");
            administradorServicio.modificarAdminHotel(ah2);
            Assertions.assertEquals(administradorServicio.buscarAdminHotel(ah.getCedula()).getNombre(), "Juanito");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdminHotelTest(){
        AdministradorHotel ah= crearAdminHotel();
        try {
            administradorServicio.crearAdministradorHotel(ah);
            administradorServicio.eliminarAdminHotel(ah.getCedula());
            Assertions.assertNull(administradorServicio.buscarAdminHotel(ah.getCedula()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarAdminHotelTest(){
        AdministradorHotel ah= crearAdminHotel();
        try {
            administradorServicio.crearAdministradorHotel(ah);
            AdministradorHotel buscado=administradorServicio.buscarAdminHotel(ah.getCedula());
            Assertions.assertEquals(buscado.getNombre(),"Victor Hugo");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
