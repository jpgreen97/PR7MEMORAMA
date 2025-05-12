public class TarjetaBandera extends Tarjeta {
    private ImageIcon imagen;

    public TarjetaBandera(String rutaImagen) {
        this.imagen = new ImageIcon(rutaImagen);
    }

    @Override
    public ImageIcon getImagen() {
        return imagen;
    }
}
