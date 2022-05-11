package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Vuelo;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class VueloBean implements Serializable {

    @Autowired
    private AdministradorServicio administradorServicio;
    @Getter @Setter
    private Vuelo v;

    @PostConstruct
    public void init(){
        v= new Vuelo();
    }

    public void crearVuelo(){

        try {
            v.setCiudadOrigen(administradorServicio.buscarCiudad(66));
            v.setCiudadDestino(administradorServicio.buscarCiudad(50));
            administradorServicio.crearVuelo(v);
            v=new Vuelo();
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "El usuario ha sido creado");
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }catch (Exception e){
            FacesMessage msj= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msj);
        }
    }
}
