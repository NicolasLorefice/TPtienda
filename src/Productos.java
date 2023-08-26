import interfases.Descuentos;

abstract class Productos implements Descuentos {
    String idProd;
    private double precioConDescuento;
    int cant;
    String descripcionProd;
    double precioUnidad;
    double precioCosto;
    boolean disponible;

    public Productos() {

    }

    public Productos(String idProd, int cant, String descripcionProd, double precioUnidad,
                     double precioCosto, boolean disponible) {
        this.idProd = idProd;
        this.cant = cant;
        this.descripcionProd = descripcionProd;
        this.precioUnidad = precioUnidad;
        this.precioCosto = precioCosto;
        if (cant != 0) {
            this.disponible = disponible;
        }
    }

    public String getIdProd() {
        return idProd;
    }

    public void setIdProd(String idProd) {
        this.idProd = idProd;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getDescripcionProd() {
        return descripcionProd;
    }

    public void setDescripcionProd(String descripcionProd) {
        this.descripcionProd = descripcionProd;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible() {
        if (cant != 0) {
            this.disponible = true;
        } else this.disponible = false;
    }

    public abstract boolean esIdValido(String id);

    public abstract boolean gananciaApta(double precioUnidad);

    @Override
    public String setPorcentajeDescuento(double porcentajeDescuento) {//TODO devuelve mal el descuento
        double desc;
        if (porcentajeDescuento>=0){
            desc=(this.precioUnidad*(1-porcentajeDescuento/100));
            if(this.precioCosto<desc){
                this.precioConDescuento=desc;
                return "Descuento aplicado";
            }
            else
                return "La ganancia no puede ser menor a 0";
        }
        else
            return "El descuento no puede ser menor a 0";
    }
    @Override
    public double getPrecioVentaConDescuento() {
        return this.precioConDescuento;
    }

}
