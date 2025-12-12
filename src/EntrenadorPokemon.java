import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrenadorPokemon {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Patrón Memento - Batalla Final");
        ventana.setSize(900, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        PantallaBatalla batalla = new PantallaBatalla();
        ConsolaJuego gameboy = new ConsolaJuego();

        JPanel panelBotonera = new JPanel();
        panelBotonera.setLayout(new GridLayout(1, 5));
        panelBotonera.setPreferredSize(new Dimension(900, 80));

        JButton btnAtacar = crearBoton("ATACAR", new Color(220, 20, 60));
        JButton btnCapturar = crearBoton("CAPTURAR", new Color(148, 0, 211));
        JButton btnGuardar = crearBoton("GUARDAR", new Color(70, 130, 180));
        JButton btnCargar = crearBoton("CARGAR", new Color(60, 179, 113));
        JButton btnReset = crearBoton("REINICIAR", new Color(255, 140, 0));

        btnAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                batalla.atacar();
            }
        });

        btnCapturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                batalla.capturar();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboy.guardarPartida(batalla);
            }
        });

        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboy.cargarPartida(batalla);
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                batalla.reiniciar();
            }
        });

        panelBotonera.add(btnAtacar);
        panelBotonera.add(btnCapturar);
        panelBotonera.add(btnGuardar);
        panelBotonera.add(btnCargar);
        panelBotonera.add(btnReset);

        ventana.add(batalla, BorderLayout.CENTER);
        ventana.add(panelBotonera, BorderLayout.SOUTH);

        ventana.setVisible(true);
    }

    private static JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        return btn;
    }
}