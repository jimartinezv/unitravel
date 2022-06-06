package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Administrador;
import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Cliente;
import co.edu.uniquindio.unitravel.entidades.Persona;
import co.edu.uniquindio.unitravel.servicios.ServiciosGenerales;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private Persona persona;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private Boolean autenticado;

    @Getter @Setter
    private int rol;

    @Autowired
    private ServiciosGenerales serviciosGenerales;
    public String login(){
        try {
            persona=serviciosGenerales.login(email,password);
            autenticado=true;
            if(persona instanceof Cliente){
                rol=1;
            }else if(persona instanceof Administrador){
                rol=2;
            }else if(persona instanceof AdministradorHotel){
                rol=3;
            }
            return "index?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("login-bean", fm);


        }
        return null;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        rol=0;
        return "/index?faces-redirect=true";
    }
}
