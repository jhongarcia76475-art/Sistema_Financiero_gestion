
import java.util.Scanner;

public class SistemaFinanciero {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //introduuccion del nombre del usuario
        System.out.println("== ingrese su nombre ==");
        String nombre = sc.nextLine(); 

        System.out.println(" =========================================================");
        //introduuccion del nombre de la empresa
        System.out.println("Se le solicita que registre el nombre de su empresa:");
        String empresa = sc.nextLine(); 

        System.out.println(" =========================================================");
        System.out.println("=== SEÑOR " + nombre.toUpperCase() + " SE REQUIEREN LOS DATOS DE SU EMPRESA ===");
        System.out.println("=== INGRESE LOS DATOS FINANCIEROS DE LA EMPRESA " + empresa.toUpperCase() + " EN EL ULTIMO AÑO FACTURADO ===");

        double activoCorriente = leerNumero(sc, "Activo Corriente: ");
        double inventario = leerNumero(sc, "Inventario: ");
        double efectivo = leerNumero(sc, "Efectivo: ");
        double pasivoCorriente = leerNumero(sc, "Pasivo Corriente: ");
        double activoTotal = leerNumero(sc, "Activo Total: ");
        double pasivoTotal = leerNumero(sc, "Pasivo Total: ");
        double patrimonio = leerNumero(sc, "Patrimonio: ");
        double ingresos = leerNumero(sc, "Ingresos (Ventas): ");
        double costos = leerNumero(sc, "Costos: ");
        double gastos = leerNumero(sc, "Gastos: ");
        double intereses = leerNumero(sc, "Intereses: ");
        double cxc = leerNumero(sc, "Cuentas por cobrar: ");
        double cxp = leerNumero(sc, "Cuentas por pagar: ");
        System.out.println(" =========================================================");


        double utilidad = ingresos - costos - gastos;
            int caso = 0;
        do { 
        System.out.println(" === YA INGRESADOS LOS DATOS QUE DESEAS ENCONTRAR ===");
        System.out.println("1 == INDICADORES DE LIQUIDEZ");
        System.out.println("2 == INDICADORES DE ENDEUDAMIENTO");
        System.out.println("3 == INDICADORES DE RENTABILIDAD");
        System.out.println("4 == INDICADORES DE INVENTARIO");
        System.out.println("5 == INDICADORES DE CREDITO");
        System.out.println("6 == LA IDENTIDAD DUPONT");
        System.out.println("7 == MEDIDAS DEL VALOR DEL MERCADO");
        System.out.println("8 == VARIACION RELATIVA Y ABSOLUTA");
        System.out.println("0 == SALIR");

        caso = sc.nextInt();
            
        
        switch (caso) {
            case 1:
                //INDICADORES DE LIQUIDEZ
                double capitalTrabajo = activoCorriente - pasivoCorriente;
                double razonLiquidez = dividir(activoCorriente, pasivoCorriente, "Razón de liquidez");
                double pruebaAcida = dividir((activoCorriente - inventario), pasivoCorriente, "Prueba ácida");
                double ratioCaja = dividir(efectivo, pasivoCorriente, "Ratio de caja");
                
                System.out.println("\n--- LIQUIDEZ ---");
                mostrar("Capital de trabajo", capitalTrabajo);
                mostrar("Razón de liquidez", razonLiquidez);
                mostrar("Prueba ácida", pruebaAcida);
                mostrar("Ratio de caja", ratioCaja);
                break;
            case 2:
                // INDICADORES DE ENDEUDAMIENTO
                double endeudamientoTotal = dividir(pasivoTotal, activoTotal, "Endeudamiento total");
                double concentracionCP = dividir(pasivoCorriente, pasivoTotal, "Concentración corto plazo");
                double endeudamientoFinanciero = dividir(pasivoTotal, patrimonio, "Endeudamiento financiero");
                double apalancamiento = dividir(activoTotal, patrimonio, "Apalancamiento");
                double endeudamientoCP = dividir(pasivoCorriente, activoTotal, "Endeudamiento corto plazo");
                double coberturaInteres = dividir(utilidad, intereses, "Cobertura de interés");
                double ratioDeuda = dividir(pasivoTotal, activoTotal, "Ratio total deuda");
                double deudaPatrimonio = dividir(pasivoTotal, patrimonio, "Deuda patrimonio");
                
                System.out.println("\n--- ENDEUDAMIENTO ---");
                mostrar("Endeudamiento total", endeudamientoTotal);
                mostrar("Concentración corto plazo", concentracionCP);
                mostrar("Endeudamiento financiero", endeudamientoFinanciero);
                mostrar("Apalancamiento", apalancamiento);
                mostrar("Endeudamiento corto plazo", endeudamientoCP);
                mostrar("Cobertura de interés", coberturaInteres);
                mostrar("Ratio total deuda", ratioDeuda);
                mostrar("Deuda patrimonio", deudaPatrimonio);
                break;
            case 3:
                // INDICADORES DE RENTABILIDAD
                double margenNeto = dividir(utilidad, ingresos, "Margen neto");
                double roa = dividir(utilidad, activoTotal, "ROA");
                double roe = dividir(utilidad, patrimonio, "ROE");
                double facturacionActivos = dividir(ingresos, activoTotal, "Facturación activos");
                
                System.out.println("\n--- RENTABILIDAD ---");
                mostrar("Margen neto", margenNeto);
                mostrar("ROA", roa);
                mostrar("ROE", roe);
                mostrar("Facturación activos", facturacionActivos);
                break;
            case 4:
                // INDICADORES DE INVENTARIO
                double rotacionInventario = dividir(costos, inventario, "Rotación inventario");
                double diasInventario = dividir(365, rotacionInventario, "Días inventario");

                // INDICADORES DE CREDITO NECESARIOS PARA MOSTRAR LOS RESULTADOS DE LOS INDICADORES
                double rotacionCarteraDos = dividir(ingresos, cxc, "Rotación cartera");
                double diasVentasCreditoDos = dividir(365, rotacionCarteraDos, "Días ventas crédito");

                System.out.println("\n--- ACTIVIDAD ---");
                mostrar("Rotación inventario", rotacionInventario);
                mostrar("Días inventario", diasInventario);
                mostrar("Días ventas crédito", diasVentasCreditoDos);

                break;
            case 5:
                // INDICADORES DE INVENTARIO NECESARIOS PARA MOSTRAR LOS RESULTADOS DE LOS INDICADORES
                double rotacionInventarioDos = dividir(costos, inventario, "Rotación inventario");
                double diasInventarioDos = dividir(365, rotacionInventarioDos, "Días inventario");

                // INDICADORES DE CREDITO
                double rotacionCartera = dividir(ingresos, cxc, "Rotación cartera");
                double diasVentasCredito = dividir(365, rotacionCartera, "Días ventas crédito");
                
                System.out.println("\n--- ACTIVIDAD ---");
                mostrar("Rotación inventario", rotacionInventarioDos);
                mostrar("Días inventario", diasInventarioDos);
                mostrar("Días ventas crédito", diasVentasCredito);
                break;
            case 6:
                //INDICADORES NECESARIOS PARA MOSTRAR LOS RESULTADOS DE LOS INDICADORES
                double apalancamientoDos = dividir(activoTotal, patrimonio, "Apalancamiento");
                double margenNetoDos = dividir(utilidad, ingresos, "Margen neto");
                double facturacionActivosDos = dividir(ingresos, activoTotal, "Facturación activos");

                // LA IDENTIDAD DUPONT
                double dupont = margenNetoDos * facturacionActivosDos * apalancamientoDos;
                System.out.println("\n--- DUPONT ---");
                mostrar("Identidad Dupont", dupont);
                break;
            case 7:
                //INDICADORES NECESARIOS PARA MOSTRAR LOS RESULTADOS DE LOS INDICADORES
                double rotacionInventarioTres = dividir(costos, inventario, "Rotación inventario");
                double diasInventarioTres = dividir(365, rotacionInventarioTres, "Días inventario");
                double rotacionCarteraTres = dividir(ingresos, cxc, "Rotación cartera");
                double diasVentasCreditoTres = dividir(365, rotacionCarteraTres, "Días ventas crédito");

                // MEDIDAS DEL VALOR DEL MERCADO
                double periodoInventario = diasInventarioTres;
                double periodoCobro = diasVentasCreditoTres;
                double periodoPago = dividir(365, dividir(costos, cxp, "Rotación pagos"), "Periodo pago");
                double cicloOperativo = periodoInventario + periodoCobro;
                double cicloCaja = cicloOperativo - periodoPago;

                System.out.println("\n--- CICLOS ---");
                mostrar("Periodo inventario", periodoInventario);
                mostrar("Periodo cobro", periodoCobro);
                mostrar("Periodo pago", periodoPago);
                mostrar("Ciclo operativo", cicloOperativo);
                mostrar("Ciclo de caja", cicloCaja);
                break;

            case 8:
                System.out.println("=PARA ESTA OPCION SE LE SOLICITA INGRESAR LOS DATOS FINANCIEROS DE LA EMPRESA " + empresa + " EN EL PENULTIMO AÑO FACTURADO =");
                System.out.println(" ==================================================================================================================================");

                double activoCorrienteDos = leerNumero(sc, "Activo Corriente: ");
                double inventarioDos = leerNumero(sc, "Inventario: ");
                double efectivoDos = leerNumero(sc, "Efectivo: ");
                double pasivoCorrienteDos = leerNumero(sc, "Pasivo Corriente: ");
                double activoTotalDos = leerNumero(sc, "Activo Total: ");
                double pasivoTotalDos = leerNumero(sc, "Pasivo Total: ");
                double patrimonioDos = leerNumero(sc, "Patrimonio: ");
                double ingresosDos = leerNumero(sc, "Ingresos (Ventas): ");
                double costosDos = leerNumero(sc, "Costos: ");
                double gastosDos = leerNumero(sc, "Gastos: ");
                double interesesDos = leerNumero(sc, "Intereses: ");
                double cxcDos = leerNumero(sc, "Cuentas por cobrar: ");
                double cxpDos = leerNumero(sc, "Cuentas por pagar: ");

                variacion("ACTIVO CORRIENTE",activoCorriente,activoCorrienteDos);
                variacion("INVENTARIO",inventario,inventarioDos);
                variacion("EFECTIVO",efectivo, efectivoDos);
                variacion("PASIVO CORRIENTE",pasivoCorriente,pasivoCorrienteDos);
                variacion("ACTIVO TOTAL",activoTotal,activoTotalDos);
                variacion("PASIVO TOTAL",pasivoTotal,pasivoTotalDos);
                variacion("PATRIMONIO",patrimonio,patrimonioDos);
                variacion("INGRESOS",ingresos,ingresosDos);
                variacion("COSTOS",costos,costosDos);
                variacion("GASTOS",gastos,gastosDos);
                variacion("INTERESES",intereses,interesesDos);
                variacion("CUENTAS POR COBRAR",cxc,cxcDos);
                variacion("CUENTAS POR PAGAR",cxp,cxpDos);

                System.out.println(" =========================================================");

                break;

                case 0:
                    System.out.println("HASTA PRONTO");

                    break;
            default:
                System.out.println("¡LA OPCION ES INVALIDA!");
        }
        } while (caso !=0);

        
        
    }

    //validacion para la division
    public static double dividir(double numerador, double denominador, String mensaje) {
        if (denominador == 0) {
            System.out.println(" No se puede calcular " + mensaje + " (división por cero)");
            return Double.NaN;
        }
        return numerador / denominador;
    }

    //validacion de valores
    public static double leerNumero(Scanner sc, String mensaje) {
        double valor;
        do {
            System.out.print(mensaje);
            valor = sc.nextDouble();

            if (valor < 0) {
                System.out.println(" No se permiten valores negativos");
            }

        } while (valor < 0);
        return valor;
    }

    // mostrar datos
    public static void mostrar(String nombre, double valor) {
        if (Double.isNaN(valor)) {
            System.out.println(nombre + ": No calculable");
        } else {
            System.out.println("=======================================================================");
            System.out.println("| "+ nombre + ": " + valor+ " |");
            System.out.println("=======================================================================");
        }
    }
    public static void variacion(String mensaje, double añoDos, double añoUno){
        double variacionAbsoluta = añoDos - añoUno;
        double variacionRelativa = variacionAbsoluta / añoUno; 
        System.out.println("=======================================================================================================");
        System.out.print("| " + mensaje + " | la variacion absoluta: " + variacionAbsoluta);
        System.out.println(" | la variacion relativa: " + variacionRelativa + " |");
        System.out.println("=======================================================================================================");
        
    }
    
}