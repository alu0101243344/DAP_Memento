import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PantallaBatalla extends JPanel {
    private int hpMewtwo;
    private String mensaje;
    private boolean capturado;
    private BufferedImage imgMagikarp;
    private BufferedImage imgMewtwo;
    private BufferedImage imgPokeball;

    public PantallaBatalla() {
        this.reiniciar();
        this.setBackground(Color.WHITE);

        try {
            imgMagikarp = ImageIO.read(new File("doc/magikarp.png"));
            imgMewtwo = ImageIO.read(new File("doc/mewtwo.png"));
            imgPokeball = ImageIO.read(new File("doc/pokeball.png"));
        } catch (IOException e) {
            System.out.println("Imagenes no encontradas.");
        }
    }

    public void atacar() {
        if (capturado) {
            this.mensaje = "Ya has capturado al Pokemon. No puedes atacar.";
            repaint();
            return;
        }

        if (this.hpMewtwo > 50) {
            this.hpMewtwo = 50;
            this.mensaje = "¡Es Super Efectivo! El rival resiste.";
        } else if (this.hpMewtwo > 0) {
            this.hpMewtwo = 0;
            this.mensaje = "¡Es Super Efectivo! Mewtwo se debilito.";
        } else {
            this.mensaje = "El rival ya no puede combatir.";
        }
        repaint();
    }

    public void capturar() {
        if (capturado) return;

        if (this.hpMewtwo == 0) {
            this.mensaje = "No es posible capturar a un Pokemon debilitado.";
        } else if (this.hpMewtwo > 50) {
            this.mensaje = "¡La Pokeball ha fallado! El Pokemon aun esta muy fuerte.";
        } else {
            this.capturado = true;
            this.mensaje = "¡Felicidades! Mewtwo ha sido atrapado con exito.";
        }
        repaint();
    }

    public void reiniciar() {
        this.hpMewtwo = 100;
        this.capturado = false;
        this.mensaje = "¡Mewtwo salvaje aparecio!";
        repaint();
    }

    public IMemento guardar() {
        String msgGuardado = "Partida guardada correctamente.";
        this.mensaje = msgGuardado;
        repaint();
        return new EstadoBatalla(this.hpMewtwo, msgGuardado, this.capturado);
    }

    public void restaurar(IMemento memento) {
        if (memento instanceof EstadoBatalla) {
            EstadoBatalla estado = (EstadoBatalla) memento;
            this.hpMewtwo = estado.getHpMewtwo();
            this.mensaje = "Estado restaurado: " + estado.getMensaje();
            this.capturado = estado.isCapturado();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(248, 248, 248));
        g2.fillRect(0, 0, getWidth(), getHeight() - 100);

        int anchoP = getWidth();
        int altoP = getHeight();

        int xMewtwo = anchoP - 250;
        int yMewtwo = 100;

        int xMagikarp = 100;
        int yMagikarp = altoP - 250;

        if (!capturado && hpMewtwo > 0) {
            dibujarHUD(g2, xMewtwo - 20, yMewtwo - 70, "Mewtwo", 50, hpMewtwo);
        }

        dibujarHUD(g2, xMagikarp + 80, yMagikarp - 50, "Magikarp", 100, 100);

        if (capturado) {
            if (imgPokeball != null) {
                g2.drawImage(imgPokeball, xMewtwo + 40, yMewtwo + 40, 80, 80, this);
            } else {
                g2.setColor(Color.RED);
                g2.fillArc(xMewtwo + 40, yMewtwo + 40, 80, 80, 0, 180);
                g2.setColor(Color.WHITE);
                g2.fillArc(xMewtwo + 40, yMewtwo + 40, 80, 80, 180, 180);
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(3));
                g2.drawOval(xMewtwo + 40, yMewtwo + 40, 80, 80);
                g2.drawLine(xMewtwo + 40, yMewtwo + 80, xMewtwo + 120, yMewtwo + 80);
            }
        } else if (hpMewtwo > 0) {
            if (imgMewtwo != null) {
                g2.drawImage(imgMewtwo, xMewtwo, yMewtwo, 180, 180, this);
            } else {
                g2.setColor(new Color(148, 0, 211));
                g2.fillOval(xMewtwo, yMewtwo, 150, 150);
            }
        }

        if (imgMagikarp != null) {
            g2.drawImage(imgMagikarp, xMagikarp, yMagikarp, 180, 180, this);
        } else {
            g2.setColor(Color.ORANGE);
            g2.fillOval(xMagikarp, yMagikarp, 150, 150);
        }

        g2.setColor(new Color(50, 50, 50));
        g2.fillRoundRect(10, altoP - 90, anchoP - 20, 80, 20, 20);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(10, altoP - 90, anchoP - 20, 80, 20, 20);

        g2.setFont(new Font("Consolas", Font.BOLD, 18));
        g2.drawString(mensaje, 30, altoP - 45);
    }

    private void dibujarHUD(Graphics2D g, int x, int y, String nombre, int nivel, int hp) {
        g.setColor(new Color(255, 255, 240));
        g.fillRoundRect(x, y, 220, 60, 10, 10);
        g.setColor(new Color(80, 80, 80));
        g.setStroke(new BasicStroke(2));
        g.drawRoundRect(x, y, 220, 60, 10, 10);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.drawString(nombre, x + 15, y + 20);
        g.drawString("Nv " + nivel, x + 160, y + 20);

        g.setColor(Color.GRAY);
        g.fillRoundRect(x + 40, y + 35, 150, 10, 5, 5);

        Color c = Color.GREEN;
        if (hp <= 50) c = Color.YELLOW;
        if (hp <= 20) c = Color.RED;

        g.setColor(c);
        int anchoBarra = (int)((hp / 100.0) * 150);
        g.fillRoundRect(x + 40, y + 35, anchoBarra, 10, 5, 5);
    }
}