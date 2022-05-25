package co.edu.uniquindio.unitravel.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Scope("session")
@Component
public class SeguridadBean implements Serializable {
}
