
import java.util.Scanner;

public class SistemaFinanciero {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== INGRESO DE DATOS FINANCIEROS DE SU EMPRESA ===");

        System.out.print("Activo Corriente: ");
        double activoCorriente = sc.nextDouble();

        System.out.print("Inventario: ");
        double inventario = sc.nextDouble();

        System.out.print("Efectivo: ");
        double efectivo = sc.nextDouble();

        System.out.print("Pasivo Corriente: ");
        double pasivoCorriente = sc.nextDouble();

        System.out.print("Activo Total: ");
        double activoTotal = sc.nextDouble();

        System.out.print("Pasivo Total: ");
        double pasivoTotal = sc.nextDouble();

        System.out.print("Patrimonio: ");
        double patrimonio = sc.nextDouble();

        System.out.print("Ingresos (Ventas): ");
        double ingresos = sc.nextDouble();

        System.out.print("Costos: ");
        double costos = sc.nextDouble();

        System.out.print("Gastos: ");
        double gastos = sc.nextDouble();

        System.out.print("Intereses: ");
        double intereses = sc.nextDouble();

        System.out.print("Cuentas por cobrar: ");
        double cxc = sc.nextDouble();

        System.out.print("Cuentas por pagar: ");
        double cxp = sc.nextDouble();

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
    }
    
}