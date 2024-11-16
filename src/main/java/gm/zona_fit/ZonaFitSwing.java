package gm.zona_fit;

import com.formdev.flatlaf.FlatDarculaLaf;
import gm.zona_fit.gui.ZonaFitForma;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

//@SpringBootApplication
public class ZonaFitSwing {
    public static void main(String[] args) {
//        Configuramos el modo oscuro
        FlatDarculaLaf.setup();
        //        Instacia de spring
        ConfigurableApplicationContext contextsSpring =
                new SpringApplicationBuilder(ZonaFitSwing.class)
                        .headless(false)
                        .web(WebApplicationType.NONE)
                        .run(args);
//        hay que crear un objeto de swing
        SwingUtilities.invokeLater(() -> {
            ZonaFitForma zonafitForma = contextsSpring.getBean(ZonaFitForma.class);
            zonafitForma.setVisible(true);
        });
    }
}
