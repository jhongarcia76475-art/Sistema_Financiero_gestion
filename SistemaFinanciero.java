
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
        double razonLiquidez = activoCorriente / pasivoCorriente;
        double pruebaAcida = (activoCorriente - inventario) / pasivoCorriente;
        double ratioCaja = efectivo / pasivoCorriente;

        // INDICADORES DE ENDEUDAMIENTO
        double endeudamientoTotal = pasivoTotal / activoTotal;
        double concentracionCP = pasivoCorriente / pasivoTotal;
        double endeudamientoFinanciero = pasivoTotal / patrimonio;
        double apalancamiento = activoTotal / patrimonio;
        double endeudamientoCP = pasivoCorriente / activoTotal;
        double coberturaInteres = utilidad / intereses;
        double ratioDeuda = pasivoTotal / activoTotal;
        double deudaPatrimonio = pasivoTotal / patrimonio;

        // INDICADORES DE RENTABILIDAD
        double margenNeto = utilidad / ingresos;
        double roa = utilidad / activoTotal;
        double roe = utilidad / patrimonio;
        double facturacionActivos = ingresos / activoTotal;
        double rendimientoActivos = roa;
        double rendimientoPatrimonio = roe;

        // INDICADORES DE INVENTARIO
        double rotacionInventario = costos / inventario;
        double diasInventario = 365 / rotacionInventario;

        // INDICADORES DE CREDITO
        double volumenVentas = ingresos;
        double diasVentasCredito = 365 / (ingresos / cxc);

        // LA IDENTIDAD DUPONT
        double dupont = margenNeto * facturacionActivos * apalancamiento;

        // MEDIDAS DEL VALOR DEL MERCADO
        double periodoInventario = diasInventario;
        double periodoCobro = 365 / (ingresos / cxc);
        double periodoPago = 365 / (costos / cxp);
        double cicloOperativo = periodoInventario + periodoCobro;
        double cicloCaja = cicloOperativo - periodoPago;

        //
    }

    //validacion para la division
    public static double dividir(double numerador, double denominador, String mensaje) {
        if (denominador == 0) {
            System.out.println("⚠️ No se puede calcular " + mensaje + " (división por cero)");
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
                System.out.println("⚠️ No se permiten valores negativos");
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