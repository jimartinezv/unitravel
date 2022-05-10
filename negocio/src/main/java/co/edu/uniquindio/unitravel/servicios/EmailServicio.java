package co.edu.uniquindio.unitravel.servicios;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServicio {

    //@Autowired
    private JavaMailSender sender;

    public Boolean enviarEmail(String asunto, String contenido, String destinatario){

        MimeMessage mensaje= sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mensaje);
        try {
            mimeMessageHelper.setText(contenido,true);
            mimeMessageHelper.setTo(destinatario);
            mimeMessageHelper.setSubject(asunto);
            sender.send(mensaje);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
