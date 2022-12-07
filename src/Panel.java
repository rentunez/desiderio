import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.sql.SQLOutput;
import java.util.List;

public class Panel extends JPanel {
    List<Rectangulo>rectangulos;
    List<Circulo>circulos;
    List<Elipse>elipses;
    List<Linea>lineas;
    List<Texto>textos;

    public Panel (
            List<Rectangulo>rectangulos,
            List<Circulo>circulos,
            List<Elipse>elipses,
            List<Linea>lineas,
            List<Texto>textos
    ){
        this.rectangulos=rectangulos;
        this.circulos=circulos;
        this.elipses=elipses;
        this.lineas=lineas;
        this.textos=textos;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D graficos = (Graphics2D) g;
        dibujar(graficos);
    }

    public void dibujar(Graphics2D graficos){
        dibujarRectangulos(graficos);
        dibujarCirculos(graficos);
        dibujarElipses(graficos);
        dibujarLineas(graficos);
        dibujarTextos(graficos);
    }

    private void dibujarRectangulos(Graphics2D pincel) {
        for(Rectangulo r:rectangulos){
            Color color=crearColor(r.getFill());
            pincel.setColor(color);
            pincel.fillRect(r.getX(),r.getY(),r.getWidth(),r.getHeight());
            pincel.setStroke(new BasicStroke(r.getStrokeWidth()));
            Color colorBorde=crearColor(r.getStroke());
            pincel.setColor(colorBorde);
            pincel.drawRect(r.getX(),r.getY(),r.getWidth(),r.getHeight());

        }
    }

    private void dibujarCirculos(Graphics2D pincel) {
        for(Circulo c:circulos){
            Color color=crearColor(c.getFill());
            pincel.setColor(color);
            pincel.fillOval(c.getCx()-c.getR(),c.getCy()-c.getR(),c.getR()*2,c.getR()*2);
            pincel.setStroke(new BasicStroke(c.getStrokeWidth()));
            Color colorBorde=crearColor(c.getStroke());
            pincel.setColor(colorBorde);
            pincel.drawOval(c.getCx()-c.getR(),c.getCy()-c.getR(),c.getR()*2,c.getR()*2);


        }
    }

    private void dibujarElipses(Graphics2D pincel){
        for(Elipse e:elipses){
            Color color=crearColor(e.getFill());
            pincel.setColor(color);
            pincel.fillOval(e.getCx()-e.getRx(),e.getCy()-e.getRy(),e.getRx()*2,e.getRy()*2);
            pincel.setStroke(new BasicStroke(e.getStrokeWidth()));
            Color colorBorde=crearColor(e.getStroke());
            pincel.setColor(colorBorde);
            pincel.drawOval(e.getCx()-e.getRx(),e.getCy()-e.getRy(),e.getRx()*2,e.getRy()*2);
        }
    }

    private void dibujarLineas(Graphics2D pincel){
        for (Linea l:lineas){
            Color color=crearColor(l.getStroke());
            pincel.setColor(color);
            pincel.setStroke(new BasicStroke(l.getStrokeWidth()));
            pincel.drawLine(l.getX1(),l.getY1(),l.getX2(),l.getY2());
        }
    }

    private void dibujarTextos(Graphics2D pincel){
        for (Texto t:textos){
            Font font=new Font(t.getFontfamily(),Font.PLAIN,t.getFontsize());
            pincel.setFont(font);
            pincel.drawString(t.getTexto(),t.getX(),t.getY());
        }
    }
    private Color crearColor(String fill) {
        if(fill.contains("#")){
            return Color.decode(fill.trim());
        }
        return  Color.GREEN;
    }


}
