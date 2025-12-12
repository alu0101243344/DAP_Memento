public class ConsolaJuego {
    private IMemento puntoGuardado;

    public void guardarPartida(PantallaBatalla batalla) {
        this.puntoGuardado = batalla.guardar();
    }

    public void cargarPartida(PantallaBatalla batalla) {
        if (this.puntoGuardado != null) {
            batalla.restaurar(this.puntoGuardado);
        }
    }
}
