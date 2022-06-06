package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Silla;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import co.edu.uniquindio.unitravel.servicios.ServiciosGenerales;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class VueloBean implements Serializable {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ServiciosGenerales serviciosGenerales;
    @Getter @Setter
    private Vuelo v;

    @Getter @Setter
    private List<Ciudad> ciudadList;

    @Getter @Setter
    private Ciudad ciudadDestino, ciudadOrigen;

    @Getter @Setter
    private Silla silla;

    @Getter @Setter
    private List<Silla> sillaList;


    @PostConstruct
    public void init(){

        v= new Vuelo();
        silla=new Silla();
        sillaList= new ArrayList<>();
        ciudadList=serviciosGenerales.listarCiudades();
    }

    public void anadirSilla(){
        sillaList.add(silla);
        silla=new Silla();


    }

    public void crearVuelo(){

        try {

            administradorServicio.crearVuelo(v);
            sillaList.forEach(s-> { s.setVuelo(v); administradorServicio.crearSilla(s);        });
            v=new Vuelo();
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "El usuario ha sido creado");
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }catch (Exception e){
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }
    }
}
