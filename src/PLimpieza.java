import enums.tipoAplicacion;
import interfases.Descuentos;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PLimpieza extends Productos implements Descuentos {
    tipoAplicacion aplicacion;
    private double precioConDescuento;

    public PLimpieza() {

    }

    @Override
    public boolean esIdValido(String id) {
        String regex = "AZ...";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override//separar bien las cosas con parentesis

    public boolean gananciaApta(double precioUnidad) {
        double ganancias = precioUnidad - precioCosto;
        if ((tipoAplicacion.BANIO == this.aplicacion || tipoAplicacion.PISO ==
                this.aplicacion) && precioUnidad > (this.precioCosto * 1.10) && (precioUnidad < (this.precioCosto * 1.25)
                && ganancias > 0))
            return true;
        else
            return ((tipoAplicacion.MULTIUSO == this.aplicacion || tipoAplicacion.ROPA ==
                    this.aplicacion) && precioUnidad < (this.precioCosto * 1.25) && ganancias > 0);

    }

    public PLimpieza(String idProd, int cant, String descripcionProd, double precioUnidad, double precioCosto,
                     boolean disponible, tipoAplicacion aplicacion) {
        super(idProd, cant, descripcionProd, precioUnidad, precioCosto, disponible);
        this.aplicacion = aplicacion;
    }

    public void setTipoAplicacion(int opcion) {
        switch (opcion + 1) {
            case 1:
                this.aplicacion = tipoAplicacion.BANIO;
                break;
            case 2:
                this.aplicacion = tipoAplicacion.PISO;
                break;
            case 3:
                this.aplicacion = tipoAplicacion.ROPA;
                break;
            case 4:
                this.aplicacion = tipoAplicacion.MULTIUSO;
                break;
        }
    }

    public tipoAplicacion getAplicacion() {
        return aplicacion;
    }


}







