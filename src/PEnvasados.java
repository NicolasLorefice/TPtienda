import enums.tipoEnvase;
import interfases.Calorias;
import interfases.Descuentos;
import interfases.Fechas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PEnvasados extends Productos implements Fechas, Calorias {
    private tipoEnvase envase;
    private LocalDate fechaVto;
    boolean importado;
    boolean comestible;
    double calorias;
    private LocalDate fecha;

    public PEnvasados(){
    }

    public PEnvasados(String idProd, int cant, String descripcionProd, double precioUnidad, double precioCosto, boolean disponible, tipoEnvase envase, boolean importado, boolean comestible) {
        super(idProd, cant, descripcionProd, precioUnidad, precioCosto, disponible);
        this.envase = envase;
        this.importado = importado;
        this.comestible = comestible;
    }

    @Override
    public boolean esIdValido(String id) {
        String regex = "AB...";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);

        if (matcher.matches()) {
            return true;
        } else {
            return false;

        }
    }

    @Override

    public boolean gananciaApta(double precioUnidad) {
        System.out.println(precioUnidad);
        System.out.println(this.precioCosto);
        if(precioUnidad>this.precioCosto &&  (precioUnidad<(this.precioCosto*(1.20)))){
            return true;
        }
        return false;
    }

    @Override
    public double getPrecioUnidad() {
        if(this.importado == true){
            return this.precioUnidad*1.10;
        }
        return this.precioUnidad;
    }

    public void setTipoEnvase(int i) {
        switch (i){
            case 1:this.envase = tipoEnvase.PLASTICO;
                break;
            case 2:this.envase = tipoEnvase.VIDRIO;
                break;
            case 3:this.envase = tipoEnvase.METAL;
                break;

        }
    }
    public void esImportada(int i){
        if(i == 1){
            this.precioUnidad= this.precioUnidad*1.10;
        }

    }
    @Override
    public String getFechaVto() {
        return this.fecha.toString();
    }


    @Override
    public void setFechas(String fechaVto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaVto = LocalDate.parse(fechaVto, formatter);
    }

    @Override
    public double getCalorias() {
        return this.calorias;
    }

    @Override
    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

}




