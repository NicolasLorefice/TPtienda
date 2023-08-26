import java.util.*;

import enums.tipoAplicacion;
import enums.tipoEnvase;


public class ElevenStore
{
    public static void main(String[] args) {
        String nombreTienda = "EleveNStore";
        double caja = 0;
        int capacidadStock = 5000;
        Map<String, Integer> ticket = new HashMap<String, Integer>();
        String id = "";
        String descripcion = "";
        int cant = 0;
        double precioCosto = 0;
        double precioUnidad = 0;
        boolean alcoholica;
        boolean prodDisponible = true;
        boolean tiendaAbierta = false;
        double descuento = 0;
        String fecha;
        tipoAplicacion[] aplicacion = tipoAplicacion.values();
        Scanner scanner = new Scanner(System.in);
        Galpon galpon = new Galpon(capacidadStock);
        System.out.println("Bienvenido a tienda EleveNStore");
        System.out.println("Ingrese el monto de la caja al inicio del dia");
        caja = Double.parseDouble(scanner.nextLine());
        galpon.setCaja(caja);

        //CREACION DE OBJETOS
        PBebidas bebida1 = new PBebidas("AC123", 30, "COCACOLA", 250, 225, true, 0, false);
        PBebidas bebida2 = new PBebidas("AC423", 50, "QUILMES", 200, 180, true, 5.7, true);
        PBebidas bebida3 = new PBebidas("AC456", 23, "REDLABEL", 600, 580, true, 40, true);
        PLimpieza limpieza1 = new PLimpieza("AZ123", 40, "LAVANDINA", 120, 100, true, tipoAplicacion.PISO);
        PLimpieza limpieza2 = new PLimpieza("AZ420", 80, "JAVON", 50, 40, true, tipoAplicacion.BANIO);
        PLimpieza limpieza3 = new PLimpieza("AZ566", 45, "TRAPO DE PISO", 40, 32, true, tipoAplicacion.PISO);
        PEnvasados envasado1 = new PEnvasados("AB789", 70, "ATUN", 100, 85, true, tipoEnvase.METAL, false, true);
        PEnvasados envasado2 = new PEnvasados("AB999", 90, "GALLETITAS", 45, 38, true, tipoEnvase.PLASTICO, false, true);
        PEnvasados envasado3 = new PEnvasados("AB768", 100, "OBLEAS", 35, 30, true, tipoEnvase.PLASTICO, false, true);
        //INGRESO AL STOCK
        galpon.ingresarProducto(bebida1);
        galpon.ingresarProducto(bebida2);
        galpon.ingresarProducto(bebida3);
        galpon.ingresarProducto(limpieza1);
        galpon.ingresarProducto(limpieza2);
        galpon.ingresarProducto(limpieza3);
        galpon.ingresarProducto(envasado1);
        galpon.ingresarProducto(envasado2);
        galpon.ingresarProducto(envasado3);


        //  TIEDA
        while (!tiendaAbierta) {//Ingreso de datos / compras

            System.out.println("Que accion desea realizar? 1-Comprar 2-Vender 3-Terminar dia");

            switch (scanner.nextLine()) {
                case "1": {
                    System.out.println("Que clase de producto desea comprar? 1-Limpieza 2-Bebidas 3-Envasados");
                    switch (scanner.nextLine()) {
                        case "1": {
                            PLimpieza prod = new PLimpieza();

                            System.out.println("Ingrese el codigo del producto a comprar");
                            id = scanner.nextLine();
                            while (!prod.esIdValido(id)) {
                                System.out.println("El id del producto no es válido, ingréselo nuevamente.");
                                id = scanner.nextLine();
                            }
                            if (galpon.existencia(id) == null) {
                                prod.setIdProd(id);
                                System.out.println("El id se registró con éxito: " + prod.idProd);
                            } else {
                                System.out.println("El producto ya existe, igrese la cantidad que desea agregar al stock");
                                cant = scanner.nextInt();
                                scanner.nextLine();
                                while (!galpon.hayLugar(cant)) {
                                    System.out.println("Error! El producto exede la pacacidad de almacenamiento");
                                    System.out.println("Ingrese la cantidad de productos a ingresar");
                                    cant = scanner.nextInt();
                                    scanner.nextLine();
                                }
                                galpon.reponerProductos(id, cant);
                                System.out.println("Total en caja luego de la operacion" + galpon.getCaja());
                                break;
                            }
                            System.out.println("Ingrese el nombre del producto");
                            descripcion = scanner.nextLine();
                            prod.setDescripcionProd(descripcion);
                            System.out.println("Cantidad a ingresar.");
                            cant = scanner.nextInt();
                            scanner.nextLine();
                            while (!galpon.hayLugar(cant)) {
                                System.out.println("Error! El producto exede la pacacidad de almacenamiento");
                                System.out.println("Ingrese la cantidad de productos a ingresar");
                                cant = scanner.nextInt();
                                scanner.nextLine();
                                prod.setCant(cant);
                            }
                            prod.setCant(cant);
                            System.out.println("Que tiepo de producto de limpieza es? 1-BANIO 2-PISO 3- ROPA 4-MULTIUSO");
                            prod.setTipoAplicacion(scanner.nextInt() - 1);
                            scanner.nextLine();
                            System.out.println(prod.getAplicacion());
                            System.out.println("Ingrese el precio del producto");
                            precioCosto = scanner.nextDouble();
                            prod.setPrecioCosto(precioCosto);
                            System.out.println("Ingrese el precio de venta al publico");
                            precioUnidad = scanner.nextDouble();
                            while (!prod.gananciaApta(precioUnidad)) {
                                System.out.println("El precio ingresado es invalido, intentelo nuevamente");
                                precioUnidad = scanner.nextDouble();
                            }
                            galpon.ingresarProducto(prod);
                            System.out.println("Producto agregado con exito");
                            scanner.nextLine();
                            galpon.comprar(prod.cant, prod.precioCosto);

                            break;
                        }
                        case "2": {
                            PBebidas prod = new PBebidas();

                            System.out.println("Ingrese el codigo del producto a comprar");
                            id = scanner.nextLine();
                            while (!prod.esIdValido(id)) {
                                System.out.println("El id del producto no es válido, ingréselo nuevamente.");
                                id = scanner.nextLine();
                            }
                            prod.setIdProd(id);
                            if (galpon.existencia(id) == null) {
                                prod.setIdProd(id);
                                System.out.println("El id se registró con éxito: " + prod.idProd);
                            } else {
                                System.out.println("El producto ya existe, igrese la cantidad que desea agregar al stock");
                                cant = scanner.nextInt();
                                scanner.nextLine();
                                while (!galpon.hayLugar(cant)) {
                                    System.out.println("Error! El producto exede la pacacidad de almacenamiento");
                                    System.out.println("Ingrese la cantidad de productos a ingresar");
                                    cant = scanner.nextInt();
                                    scanner.nextLine();
                                }
                                galpon.reponerProductos(id, cant);
                                break;
                            }
                            System.out.println("Ingrese el nombre del producto");
                            descripcion = scanner.nextLine();
                            prod.setDescripcionProd(descripcion);
                            System.out.println("cantidad a ingresar.");
                            cant = scanner.nextInt();
                            scanner.nextLine();
                            while (!galpon.hayLugar(cant)) {
                                System.out.println("Error! El producto exede la pacacidad de almacenamiento");
                                System.out.println("Ingrese la cantidad de productos a ingresar");
                                cant = scanner.nextInt();
                                scanner.nextLine();
                                prod.setCant(cant);
                            }
                            prod.setCant(cant);
                            System.out.println("Es importado? 1-SI 2-NO");
                            prod.setImportado((scanner.nextInt() == 1));
                            scanner.nextLine();
                            System.out.println("Es alcoholica? 1-SI 2-NO");
                            prod.setAlcoholica(prod.esAlcoholica(scanner.nextInt()));
                            scanner.nextLine();
                            if (prod.isAlcoholica()) {
                                System.out.println("Ingrese el porcentaje alcoholico");
                                prod.setGradAlcoholica(scanner.nextDouble());
                            }
                            prod.setCant(cant);
                            System.out.println("Ingrese el precio del producto");
                            precioCosto = scanner.nextDouble();
                            prod.setPrecioCosto(precioCosto);
                            System.out.println("Ingrese el precio de venta al publico");
                            precioUnidad = scanner.nextDouble();
                            while (!prod.gananciaApta(precioUnidad)) {
                                System.out.println("El precio ingresado es invalido, intentelo nuevamente");
                                precioUnidad = scanner.nextDouble();
                            }
                            System.out.println("El precio ah sido ingresado correctamente");
                            scanner.nextLine();
                            System.out.println("Ingrese la fecha de vencimiento (DD-MM-YYYY)");
                            ((PBebidas) prod).setFechas(scanner.nextLine());
                            System.out.println("Ingrese las calorias");
                            ((PBebidas) prod).setCalorias(scanner.nextInt());
                            scanner.nextLine();
                            System.out.println("las calorias son: " + ((PBebidas) prod).getCalorias());
                            galpon.ingresarProducto(prod);
                            System.out.println("Producto agregado con exito");
                            galpon.comprar(prod.cant, prod.precioCosto);


                            break;
                        }
                        case "3": {
                            PEnvasados prod = new PEnvasados();
                            System.out.println("Ingrese el codigo del producto a comprar");
                            id = scanner.nextLine();
                            while (!prod.esIdValido(id)) {
                                System.out.println("El id del producto no es válido, ingréselo nuevamente.");
                                id = scanner.nextLine();
                            }
                            if (galpon.existencia(id) == null) {
                                prod.setIdProd(id);
                                System.out.println("El id se registró con éxito: " + prod.idProd);
                            } else {
                                System.out.println("El producto ya existe, igrese la cantidad que desea agregar al stock");
                                cant = scanner.nextInt();
                                scanner.nextLine();
                                while (!galpon.hayLugar(cant)) {
                                    System.out.println("Error! El producto exede la pacacidad de almacenamiento");
                                    System.out.println("Ingrese la cantidad de productos a ingresar");
                                    cant = scanner.nextInt();
                                    scanner.nextLine();
                                }
                                galpon.reponerProductos(id, cant);
                                break;
                            }
                            System.out.println("Ingrese el nombre del producto");
                            descripcion = scanner.nextLine();
                            prod.setDescripcionProd(descripcion);
                            System.out.println("Cantidad a ingresar.");
                            cant = scanner.nextInt();
                            scanner.nextLine();
                            while (!galpon.hayLugar(cant)) {
                                System.out.println("Error! El producto exede la pacacidad de almacenamiento");
                                System.out.println("Ingrese la cantidad de productos a ingresar");
                                cant = scanner.nextInt();
                                scanner.nextLine();
                                prod.setCant(cant);
                            }
                            prod.setCant(cant);
                            System.out.println("Ingrese el tipo de envase 1-PLASTICO 2-VIDRIO 3-METAL");
                            prod.setTipoEnvase(scanner.nextInt() - 1);
                            scanner.nextLine();
                            System.out.println("Ingrese el precio del producto");
                            precioCosto = scanner.nextDouble();
                            prod.setPrecioCosto(precioCosto);
                            scanner.nextLine();
                            System.out.println("Ingrese el precio de venta al publico");
                            precioUnidad = scanner.nextDouble();

                            scanner.nextLine();
                            while (!prod.gananciaApta(precioUnidad)) {
                                System.out.println("El precio ingresado es invalido, intentelo nuevamente");
                                precioUnidad = scanner.nextDouble();
                                scanner.nextLine();
                            }
                            System.out.println("Ingrese la fecha de vencimiento (DD-MM-YYYY)");
                            ((PEnvasados) prod).setFechas(scanner.nextLine());
                            System.out.println("Ingrese las calorias");
                            ((PEnvasados) prod).setCalorias(scanner.nextInt());
                            System.out.println("Es importado? 1-SI 2-NO");
                            prod.esImportada(scanner.nextInt());
                            scanner.nextLine();
                            prod.setPrecioCosto(precioCosto);


                            System.out.println("El precio a sido ingresado correctamente");
                            galpon.ingresarProducto(prod);
                            galpon.comprar(cant, precioCosto);
                            System.out.println("Producto agregado con exito");
                            break;
                        }

                    }
                    break;
                }

                case "2": {
                    while (true) {
                        System.out.println("Eliga el codigo del producto que desea vender " + "\n");
                        System.out.println("Listado de productos " + "\n" + galpon.mostrarProductos());
                        System.out.println("Ingrese el codigo del producto que desea vender");
                        id = scanner.nextLine();
                        while (galpon.existencia(id) == null) {
                            System.out.println("El id del producto no es válido, ingréselo nuevamente.");
                            id = scanner.nextLine();
                        }
                        System.out.println("Ingrese la cantidad: ");
                        cant = scanner.nextInt();
                        scanner.nextLine();
                        while (cant > 10) {
                            System.out.println("Exede el maximo de unidades");
                            System.out.println("Ingrese una cantidad igual o menor que 10");
                            cant = scanner.nextInt();
                            scanner.nextLine();
                        }
                        if (galpon.haystock(id, cant)) {
                            ticket.put(id, cant);
                        } else if (galpon.getStock(id) < 1) {
                            System.out.println("No hay stock del producto " + id);
                        } else {
                            System.out.println("Hay menos stock del producto " + id);
                            System.out.println("Se han agregado " + galpon.getStock(id) + " a la compra");
                            ticket.put(id, galpon.getStock(id));
                        }
                        System.out.println("Desea aplicar un descuento? 1-SI 2-NO");
                        if (scanner.nextInt() == 1) {
                            System.out.println("Ingrese el procentaje de descuento");
                            descuento = scanner.nextInt();
                            scanner.nextLine();
                        }
                        String error;
                      do   {
                            System.out.println("Ingrese el procentaje de descuento");
                            descuento = scanner.nextInt();
                            scanner.nextLine();
                            error = galpon.aplicarDescuento(descuento, id);

                        }while (error != "Descuento aplicado");

                    System.out.println("Resto en caja " + galpon.getCaja());
                    System.out.println("Seguir comprando? 1-SI 2-NO");
                    if (scanner.nextInt() == 2 || ticket.size() == 3) {
                        scanner.nextLine();
                        break;
                    }
                }
                }
                System.out.println("Ticket");
                System.out.println(galpon.vender(ticket));
                break;

                case "3": {
                    System.out.println("Dia Finalizado");
                    System.out.println("El valance en caja es de : $" + galpon.getCaja());
                    tiendaAbierta = true;
                    break;
                }
            }


        }
    }
}









