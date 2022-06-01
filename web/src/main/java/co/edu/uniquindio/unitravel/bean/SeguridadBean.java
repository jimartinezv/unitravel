package co.edu.uniquindio.unitravel.bean;

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

    @Autowired
    private ServiciosGenerales serviciosGenerales;
    public String login(){
        try {
            persona=serviciosGenerales.login(email,password);
            return "index?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("login-bean", fm);


        }
        return null;
    }
}
