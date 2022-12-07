import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class main2 extends DefaultHandler {
    List<Rectangulo> rectangulos=new ArrayList<>();
    List<Circulo> circulos=new ArrayList<>();
    List<Elipse> elipses=new ArrayList<>();
    List<Linea> lineas=new ArrayList<>();
    List<Texto> textos=new ArrayList<>();

    Texto texto= new Texto();
    boolean estaEnTexto;
    StringBuilder escritor= new StringBuilder();


    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("rect")){
            Rectangulo r= new Rectangulo();
            r.setX(Double.parseDouble(attributes.getValue("x")));
            r.setY(Double.parseDouble(attributes.getValue("y")));
            r.setWidth(Double.parseDouble(attributes.getValue("width")));
            r.setHeight(Double.parseDouble(attributes.getValue("height")));
            r.setStrokeWidth(Double.parseDouble(attributes.getValue("stroke-width")));
            r.setStroke(attributes.getValue("stroke"));
            r.setFill(attributes.getValue("fill"));
            rectangulos.add(r);
        }
        if (qName.equalsIgnoreCase("circle")){
            Circulo c= new Circulo();
            c.setCx(Double.parseDouble(attributes.getValue("cx")));
            c.setCy(Double.parseDouble(attributes.getValue("cy")));
            c.setR(Double.parseDouble(attributes.getValue("r")));
            c.setStrokeWidth(Double.parseDouble(attributes.getValue("stroke-width")));
            c.setStroke(attributes.getValue("stroke"));
            c.setFill(attributes.getValue("fill"));
            circulos.add(c);
        }
        if (qName.equalsIgnoreCase("ellipse")){
            Elipse e= new Elipse();
            e.setCx(Double.parseDouble(attributes.getValue("cx")));
            e.setCy(Double.parseDouble(attributes.getValue("cy")));
            e.setRx(Double.parseDouble(attributes.getValue("rx")));
            e.setRy(Double.parseDouble(attributes.getValue("ry")));
            e.setStrokeWidth(Double.parseDouble(attributes.getValue("stroke-width")));
            e.setStroke(attributes.getValue("stroke"));
            e.setFill(attributes.getValue("fill"));
            elipses.add(e);
        }
        if(qName.equalsIgnoreCase("line")){
            Linea l= new Linea();
            l.setX1(Double.parseDouble(attributes.getValue("x1")));
            l.setX2(Double.parseDouble(attributes.getValue("x2")));
            l.setY1(Double.parseDouble(attributes.getValue("y1")));
            l.setY2(Double.parseDouble(attributes.getValue("y2")));
            l.setStrokeWidth(Double.parseDouble(attributes.getValue("stroke-width")));
            l.setStroke(attributes.getValue("stroke"));
            lineas.add(l);
        }
        if (qName.equalsIgnoreCase("text")){
            estaEnTexto=true;
            texto=new Texto();
            texto.setX(Double.parseDouble(attributes.getValue("x")));
            texto.setY(Double.parseDouble(attributes.getValue("y")));
            texto.setFontsize(Double.parseDouble(attributes.getValue("font-size")));
            texto.setFontfamily(attributes.getValue("font-family,"));
            textos.add(texto);

        }


    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        estaEnTexto=false;
        texto.setTexto(escritor.toString());
        escritor=new StringBuilder();
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        escritor.append(ch,start,length);
    }

    public List<Rectangulo> getRectangulos() {
        return rectangulos;
    }

    public List<Circulo> getCirculos() {
        return circulos;
    }

    public List<Elipse> getElipses() {
        return elipses;
    }

    public List<Linea> getLineas() {
        return lineas;
    }

    public List<Texto> getTextos() {
        return textos;
    }
}