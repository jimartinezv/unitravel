package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Cama;
import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.servicios.AdministradorServicio;
import co.edu.uniquindio.unitravel.servicios.ServiciosGenerales;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Component
public class DetallesBean implements Serializable {

    @Getter @Setter
    private Caracteristica caracteristica;

    @Getter
    private List<Caracteristica> caracteristicaList;

    @Getter @Setter
    private Cama cama;

    @Getter
    private List<Cama> camaList;

    @Getter @Setter
    private String tipo;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private ServiciosGenerales serviciosGenerales;

    @PostConstruct
    public void inicio(){
        caracteristica= new Caracteristica();
        cama= new Cama();
        caracteristicaList= serviciosGenerales.listarCaracteristicasHoteles();
        camaList= administradorServicio.listarCamas();
    }
    public void registrarCaracteristicaHotel(){
        if(tipo.equals("CAMA")){
            cama.setTipo(caracteristica.getDescripcion());
            administradorServicio.crearCama(cama);
            camaList=administradorServicio.listarCamas();
            cama= new Cama();
            caracteristica=new Caracteristica();
        }else {
            caracteristica.setTipo(tipo);
            administradorServicio.crearCaracteristica(caracteristica);
            caracteristicaList= serviciosGenerales.listarCaracteristicasHoteles();
            caracteristica= new Caracteristica();
        }

    }


}
