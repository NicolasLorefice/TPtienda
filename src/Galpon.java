import interfases.Descuentos;
import interfases.Vender;
import interfases.Comprar;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class Galpon implements Vender , Comprar {

    private int cantidadMax;
    private int almacenamiento;
    private double caja;

    private Map<String, Productos> galpon;

    public Galpon(){

    }
    public Galpon(int cantidadMax) {
        this.galpon = new HashMap<>();
        this.cantidadMax = cantidadMax;
        this.almacenamiento = 0;
    }
    public void ingresarProducto(Productos produ){
        if(this.cantidadMax> almacenamiento+produ.cant){
        galpon.put(produ.idProd,produ);
        this.almacenamiento = this.almacenamiento + produ.cant;
        }
    }
    public void eliminarProducto(String idprodu){
        if (galpon.containsKey(idprodu)){
            this.almacenamiento = this.almacenamiento - this.galpon.get(idprodu).cant;
            this.galpon.remove(idprodu);
        }
        }


     public boolean hayLugar(int cant){
         return this.almacenamiento + cant < this.cantidadMax;
     }
    public int mostrarCantidad(){
        return this.almacenamiento;
    }

    public int getCantidadMax() {
        return cantidadMax;
    }

    public int cantidadDeProds() {
        return galpon.size();
    }

    public void setCaja(double caja) {
        this.caja = caja;
    }
    public Productos getProducto(String id){
        return this.galpon.get(id);
    }

    public double getCaja() {
        return caja;
    }

    public String mostrarProductos(){
        String value = "";
        for (String key : galpon.keySet()) {
             value += (galpon.get(key).getIdProd() + " " + (galpon.get(key).getDescripcionProd() + " " + (galpon.get(key).getCant()))+" "+galpon.get(key).getPrecioUnidad()+"\n");
        }
        return value;

    }
    public void controlarStock(){
        for (String key : galpon.keySet()) {
            if (galpon.get(key).getCant() <= 0){
                this.galpon.remove(key);
            }
        }
    }

public void reponerProductos(String id, int cant){
    double total=0;
        this.galpon.get(id).setCant(this.galpon.get(id).getCant()+cant);
        total += (cant+this.galpon.get(id).getPrecioCosto());
        if(total<this.caja){this.caja -=(this.galpon.get(id).getPrecioCosto())*cant;}

}
    @Override
    public void comprar(int cant, double precio) {
            double total=0;
            total += cant*precio;
            if(total<this.caja){this.caja -= total;}
    }
    public Productos existencia(String id){
      return this.galpon.get(id);
    }
    @Override
    public String vender(Map<String, Integer> ticket){//TODO mostrar todos los items seleccionados para vender
        if (ticket.size()>3 || ticket.isEmpty()) {
            return "No se pueden comprar mas de 3 productos, tampoco 0";
        }
        String ticketFinal = "";
        double total=0;
        for (String i : ticket.keySet()) {
            galpon.get(i).setCant(galpon.get(i).getCant() - ticket.get(i));
            caja += galpon.get(i).getPrecioVentaConDescuento() * ticket.get(i);
            total+=galpon.get(i).getPrecioVentaConDescuento() * ticket.get(i);
            ticketFinal += i + " " + galpon.get(i).getDescripcionProd() + " " + ticket.get(i) + " " + galpon.get(i).getPrecioVentaConDescuento()+"\n";
        }
        ticketFinal+="Total: "+total;
        controlarStock();
        return ticketFinal;
    }

    public boolean haystock(String id,int cant){
        return galpon.get(id).getCant() > cant;
    }
    public String aplicarDescuento(double descuento, String id){
       return galpon.get(id).setPorcentajeDescuento(descuento);
    }

    public int getStock(String id){
       return galpon.get(id).cant;
    }



}



