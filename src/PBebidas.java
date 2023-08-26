import interfases.Calorias;
import interfases.Fechas;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import enums.tipoEnvase;
import java.time.LocalDate;

public class PBebidas extends Productos  implements  Calorias, Fechas {
    private boolean alcoholica;
    private double gradAlcoholica;
    private tipoEnvase envase;
    private double calorias;
    private LocalDate fechaVto;
    boolean importado;


    public PBebidas() {

    }

    public PBebidas(String idProd, int cant, String descripcionProd, double precioUnidad, double precioCosto,
                    boolean disponible, double gradAlcoholica, boolean alcoholica) {
        super(idProd, cant, descripcionProd, precioUnidad, precioCosto, disponible);
        this.idProd = idProd;
        esIdValido(this.idProd);
        this.alcoholica = alcoholica;
        if (this.alcoholica){this.gradAlcoholica = gradAlcoholica;}
        else this.gradAlcoholica= 0;
    }

    @Override
      public boolean esIdValido(String id) {
        String regex = "AC...";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);

        return matcher.matches();
    }


    public boolean isAlcoholica() {
        return alcoholica;
    }

    public void setAlcoholica(boolean alcoholica) {
        this.alcoholica = alcoholica;
    }

    public double getGradAlcoholica() {
        return gradAlcoholica;
    }

    public void setGradAlcoholica(double gradAlcoholica) {
        if (this.alcoholica){this.gradAlcoholica = gradAlcoholica;};
    }



    @Override
    public boolean gananciaApta(double precioUnidad) {
        if(precioUnidad>this.precioCosto  &&  (precioUnidad<(this.precioCosto*(1.15)))){
            return true;
        }
        else if(precioUnidad>(this.precioCosto*(1.15))){
            return false;
        }
        else return false;
    }
    public boolean esAlcoholica(int i){
        return i == 1;
    }
    public boolean esImportada(int i){
        if(i == 1){
            this.precioUnidad= this.precioUnidad*1.10;
            return true;}
        else return false;
    }


    public boolean isImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    @Override
    public double getCalorias() {
        return this.calorias;
    }
    @Override
    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    @Override
    public String getFechaVto() {
        return this.fechaVto.toString();
    }

    @Override
    public void setFechas(String fechaVto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaVto = LocalDate.parse(fechaVto, formatter);
    }
    @Override
    public double getPrecioUnidad() {
        if(this.importado == true){
            return this.precioUnidad*1.10;
        }
        return this.precioUnidad;
    }



}
