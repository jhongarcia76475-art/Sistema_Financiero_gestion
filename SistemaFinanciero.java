
import java.util.Scanner;

public class SistemaFinanciero {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== INGRESO DE DATOS FINANCIEROS DE SU EMPRESA ===");

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

        /* -en esta parte se declararon las variables para realizar los calculos 
        de los indicadores */

        double utilidad = ingresos - costos - gastos;

        //INDICADORES DE LIQUIDEZ
        double capitalTrabajo = activoCorriente - pasivoCorriente;
        double razonLiquidez = dividir(activoCorriente, pasivoCorriente, "Razón de liquidez");
        double pruebaAcida = dividir((activoCorriente - inventario), pasivoCorriente, "Prueba ácida");
        double ratioCaja = dividir(efectivo, pasivoCorriente, "Ratio de caja");

        // INDICADORES DE ENDEUDAMIENTO
        double endeudamientoTotal = dividir(pasivoTotal, activoTotal, "Endeudamiento total");
        double concentracionCP = dividir(pasivoCorriente, pasivoTotal, "Concentración corto plazo");
        double endeudamientoFinanciero = dividir(pasivoTotal, patrimonio, "Endeudamiento financiero");
        double apalancamiento = dividir(activoTotal, patrimonio, "Apalancamiento");
        double endeudamientoCP = dividir(pasivoCorriente, activoTotal, "Endeudamiento corto plazo");
        double coberturaInteres = dividir(utilidad, intereses, "Cobertura de interés");
        double ratioDeuda = dividir(pasivoTotal, activoTotal, "Ratio total deuda");
        double deudaPatrimonio = dividir(pasivoTotal, patrimonio, "Deuda patrimonio");

        // INDICADORES DE RENTABILIDAD
        double margenNeto = dividir(utilidad, ingresos, "Margen neto");
        double roa = dividir(utilidad, activoTotal, "ROA");
        double roe = dividir(utilidad, patrimonio, "ROE");
        double facturacionActivos = dividir(ingresos, activoTotal, "Facturación activos");

        // INDICADORES DE INVENTARIO
        double rotacionInventario = dividir(costos, inventario, "Rotación inventario");
        double diasInventario = dividir(365, rotacionInventario, "Días inventario");

        // INDICADORES DE CREDITO
        double rotacionCartera = dividir(ingresos, cxc, "Rotación cartera");
        double diasVentasCredito = dividir(365, rotacionCartera, "Días ventas crédito");

        // LA IDENTIDAD DUPONT
        double dupont = margenNeto * facturacionActivos * apalancamiento;

        // MEDIDAS DEL VALOR DEL MERCADO
        double periodoInventario = diasInventario;
        double periodoCobro = diasVentasCredito;
        double periodoPago = dividir(365, dividir(costos, cxp, "Rotación pagos"), "Periodo pago");
        double cicloOperativo = periodoInventario + periodoCobro;
        double cicloCaja = cicloOperativo - periodoPago;;
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
            System.out.println(nombre + ": " + valor);
        }
    }
    
}