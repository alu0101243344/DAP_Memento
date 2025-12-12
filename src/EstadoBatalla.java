public class EstadoBatalla implements IMemento {
    private final int hpMewtwo;
    private final String mensaje;
    private final boolean capturado;

    public EstadoBatalla(int hpMewtwo, String mensaje, boolean capturado) {
        this.hpMewtwo = hpMewtwo;
        this.mensaje = mensaje;
        this.capturado = capturado;
    }

    public int getHpMewtwo() {
        return hpMewtwo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public boolean isCapturado() {
        return capturado;
    }
}