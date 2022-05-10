package co.edu.uniquindio.unitravel.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@Getter
@Setter
public class EjemploBean implements Serializable {
    private String at1, at2;
    private int t=0;
    public void cambiar(){
        t++;
        String aux=at1;
        at1=at2;
        at2=aux;
    }
}
