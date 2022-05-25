package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.*;
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
import java.util.ArrayList;
import java.util.List;

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

    public Vuelo crearVuelo(){
        Vuelo v= new Vuelo();
        v.setAerolinea("AVIANCA");
        v.setCodigo("A4738");

        return v;
    }
    public List<Silla> crearSillas(){
        List<Silla> sillas= new ArrayList<>();
        Silla silla1, silla2, silla3, silla4, silla5, silla6;
        silla1=new Silla();
        silla1.setPrecio(50000);
        silla1.setPosicion("A1");
        administradorServicio.crearSilla(silla1);

        silla2=new Silla();
        silla2.setPrecio(60000);
        silla2.setPosicion("B1");
        administradorServicio.crearSilla(silla1);

        silla3=new Silla();
        silla3.setPrecio(70000);
        silla3.setPosicion("C1");
        administradorServicio.crearSilla(silla1);

        silla4=new Silla();
        silla4.setPrecio(50000);
        silla4.setPosicion("A2");
        administradorServicio.crearSilla(silla1);

        silla5=new Silla();
        silla5.setPrecio(60000);
        silla5.setPosicion("B2");

        administradorServicio.crearSilla(silla1);

        silla6=new Silla();
        silla6.setPrecio(70000);
        silla6.setPosicion("C2");
        administradorServicio.crearSilla(silla1);

        sillas.add(silla1);
        sillas.add(silla2);
        sillas.add(silla3);
        sillas.add(silla4);
        sillas.add(silla5);
        sillas.add(silla6);



        return sillas;
    }

    public Ciudad crearCiudad(){
        Ciudad c= new Ciudad();
        c.setNombre("Nueva Armenia");


        try {



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

    @Test
    //@Sql("classpath:dataset.sql")
    public void crearVueloTest(){
        Vuelo v= crearVuelo();
        try {
            administradorServicio.crearVuelo(v);
            Assertions.assertEquals(administradorServicio.buscarVuelo("A4738").getAerolinea(),"AVIANCA");
        }catch (Exception e){
            System.out.println(e.getMessage()+" error");
        }

    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void actualizarVueloTest(){
        Vuelo v= crearVuelo();
        try {
            administradorServicio.crearVuelo(v);
            v.setAerolinea("AIRES");
            administradorServicio.actualizarVuelo(v);
            Assertions.assertEquals(administradorServicio.buscarVuelo("A4738").getAerolinea(),"AIRES");
        }catch (Exception e){
            System.out.println(e.getMessage()+" error");
        }

    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void eliminarVueloTest(){
        Vuelo v= crearVuelo();
        try {
            administradorServicio.crearVuelo(v);
            administradorServicio.eliminarVuelo("A4738");
            Assertions.assertNull(administradorServicio.buscarVuelo("A4738"));
        }catch (Exception e){
            System.out.println(e.getMessage()+" error");
        }

    }
}
