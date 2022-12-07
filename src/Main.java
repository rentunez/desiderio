import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        * Desarrollar un aplicacion en java que muestre en
        * una ventana la imagen almacenada en un archivo SVG
        * el programa debe recibir desde la linea de comandos
        * un URL. como prueba se pueden usar archivos en el servidor
        * las figuras que debe de mostrar son
        * 1. rectangle con sus atributos x, y width, height, stroke-widht
        *  y stroke-height
        * 2. cicle con sus atributos cx, cy, r, fill, stroke width
        * 3. line con sus atributos x1,y1,x2 y y2, stroke y stroke-width
        * 4. text con sus atributos x, y, font-family, font-size
        * */
        URL url = new URL("file:///C:/Users/HP/Downloads/figuras.svg.svg");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        main2 handler= new main2();
        try (var br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            SAXParser saxParser = saxParserFactory.newSAXParser();
           saxParser.parse(url.openStream(),handler);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        List<Rectangulo>rectangulos=handler.getRectangulos();
        List<Circulo>circulos=handler.getCirculos();
        List<Elipse>elipses=handler.getElipses();
        List<Linea>lineas=handler.getLineas();
        List<Texto>textos=handler.getTextos();

        Panel panel= new Panel(
                rectangulos,circulos,elipses,lineas,textos
        );



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Ventana window=new Ventana();
                window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                window.add(panel);
                window.setSize(1000,1000);
                window.setVisible(true);
            }
        });
    }
}