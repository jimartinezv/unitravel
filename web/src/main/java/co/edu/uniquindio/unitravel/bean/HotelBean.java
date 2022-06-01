package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.ServiciosGenerales;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class HotelBean implements Serializable {

    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Autowired
    private ServiciosGenerales serviciosGenerales;

    @Getter @Setter
    private Hotel hotel;

    @Getter @Setter
    private Habitacion habitacion;

    @Getter @Setter
    private Direccion direccion;

    @Value("${upload.url}")
    private String urlImagenes;

    private ArrayList<Habitacion> habitaciones;

    private ArrayList<String> imagenes, imagenesHab;


    @Getter
    private List<Ciudad> ciudadList;

    @Getter @Setter
    private List<Caracteristica> caracteristicas;

    @PostConstruct
    public void inicio(){
        hotel= new Hotel();
        habitacion= new Habitacion();
        ciudadList= serviciosGenerales.listarCiudades();
        caracteristicas=serviciosGenerales.listarCaracteristicasHoteles();
        direccion= new Direccion();
        imagenes= new ArrayList<>();
        this.imagenesHab= new ArrayList<>();
        this.habitaciones= new ArrayList<>();

    }

    public void restartHabitacion(){
        habitacion=new Habitacion();
        imagenesHab=new ArrayList<>();
    }

    public void crearHabitacion(){
        try {

            if(imagenesHab.size()>0) {

                habitacion.setFotos(imagenesHab);
                //administradorHotelServicio.crearHabitacion(habitacion);
                habitaciones.add(habitacion);
                habitacion= new Habitacion();
                imagenesHab= new ArrayList<>();

            }else {
                FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Habitación debe tener imagenes");
                FacesContext.getCurrentInstance().addMessage(null,msj);
            }
        }catch (Exception e){
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error creando la habitación");
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }

    }
    public String crearHotel(){
        System.out.println("carac");
        try{
            if(imagenes.size()>0){


            administradorHotelServicio.crearDireccion(direccion);
            hotel.setDireccion(direccion);
            hotel.setFotos(imagenes);
            administradorHotelServicio.crearHotel(hotel);
            direccion.setHotel(hotel);
            administradorHotelServicio.actualizarDireccion(direccion);
            habitaciones.forEach(p-> {
                p.setHotel(hotel);

                try {
                 administradorHotelServicio.crearHabitacion(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            //FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "El Hotel ha sido creado");
            //FacesContext.getCurrentInstance().addMessage(null,msj);
            return "registro_exitoso?faces-redirect=true";
            }else {
                FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Para la creación de el hotel debe tener imagenes");
                FacesContext.getCurrentInstance().addMessage(null,msj);
            }
        }catch (Exception e){
            try {
                administradorHotelServicio.eliminarDireccion(direccion.getCodigo());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }
        return null;

    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen!=null) {
            imagenes.add(nombreImagen);
        }

    }

    public String subirImagen(UploadedFile imagen){
        File archivo= new File(urlImagenes+"/"+imagen.getFileName());
        try {
            OutputStream outputStream= new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(),outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;



    }




    public void subirImagenesHab(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagenHab(imagen);
        if(nombreImagen!=null) {
            imagenesHab.add(nombreImagen);
        }

    }

    public String subirImagenHab(UploadedFile imagen){
        File archivo= new File(urlImagenes+"/"+imagen.getFileName());
        try {
            OutputStream outputStream= new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(),outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;



    }
}
