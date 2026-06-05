
import java.util.Scanner;

public class SistemaFinanciero {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== INGRESO DE DATOS FINANCIEROS ===");

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
        
    }
}