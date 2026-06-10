
import java.io.*;
import java.util.Scanner;

public class SistemaFinanciero {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caso = 0;

        // ===== LOGIN =====
        System.out.println("=================================================");
        System.out.println("|BIENVENIDO AL SISTEMA DE CALCULA PARA ESF Y ERI|");
        System.out.println("=================================================");
        System.out.println("            =====================");
        System.out.println("            | 1. INICIAR SESION |");
        System.out.println("            =====================");
        System.out.println("            | 2. REGISTRARSE    |");
        System.out.println("            =====================");
        int op = sc.nextInt();
        sc.nextLine();

        String usuario = null;

        if (op == 1) usuario = iniciarSesion(sc);
        else if (op == 2) {
            registrarUsuario(sc);
            usuario = iniciarSesion(sc);
        } else return;

        if (usuario == null) {
            System.out.println("ACCESO DENEGADO");
            return;
        }

        System.out.println("BIENVENIDO " + usuario);

        // ===== VARIABLES =====
        String empresa = "";
        double activoCorriente = 0, inventario = 0, efectivo = 0, pasivoCorriente = 0;
        double activoTotal = 0, pasivoTotal = 0, patrimonio = 0;
        double ingresos = 0, costos = 0, gastos = 0, intereses = 0, cxc = 0, cxp = 0;

        // ===== CARGAR DATOS =====
        double[] datos = cargarDatos(usuario);

        if (datos != null) {
            System.out.println(" DATOS CARGADOS CORRECTAMENTE");

            empresa = obtenerEmpresa(usuario);

            activoCorriente = datos[0];
            inventario = datos[1];
            efectivo = datos[2];
            pasivoCorriente = datos[3];
            activoTotal = datos[4];
            pasivoTotal = datos[5];
            patrimonio = datos[6];
            ingresos = datos[7];
            costos = datos[8];
            gastos = datos[9];
            intereses = datos[10];
            cxc = datos[11];
            cxp = datos[12];
            double utilidad = ingresos - costos - gastos;

            do { 
            System.out.println("====================================================");
            System.out.println("| == YA INGRESADOS LOS DATOS QUE DESEAS ENCONTRAR ==|");
            System.out.println("====================================================");
            System.out.println("====================================================");
            System.out.println("| 1 == INDICADORES DE LIQUIDEZ                      |");
            System.out.println("====================================================");
            System.out.println("| 2 == INDICADORES DE ENDEUDAMIENTO                 |");
            System.out.println("====================================================");
            System.out.println("| 3 == INDICADORES DE RENTABILIDAD                  |");
            System.out.println("====================================================");
            System.out.println("| 4 == INDICADORES DE INVENTARIO                    |");
            System.out.println("====================================================");
            System.out.println("| 5 == INDICADORES DE CREDITO                       |");
            System.out.println("====================================================");
            System.out.println("| 6 == LA IDENTIDAD DUPONT                          |");
            System.out.println("====================================================");
            System.out.println("| 7 == MEDIDAS DEL VALOR DEL MERCADO                |");
            System.out.println("====================================================");
            System.out.println("| 8 == VARIACION RELATIVA Y ABSOLUTA                |");
            System.out.println("====================================================");
            System.out.println("| 9 == ACTUALIZAR DATOS                            |");
            System.out.println("====================================================");
            System.out.println("| 0 == SALIR                                        |");
            System.out.println("====================================================");

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
                    System.out.println("==================================================================================================================================");
                    System.out.println("=PARA ESTA OPCION SE LE SOLICITA INGRESAR LOS DATOS FINANCIEROS DE LA EMPRESA " + empresa + " EN EL PENULTIMO AÑO FACTURADO =");
                    System.out.println("==================================================================================================================================");

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
                    case 9:

                        System.out.println("Ingrese los nuevos datos:");

                        System.out.print("Empresa: ");
                        sc.nextLine();
                        String nuevaEmpresa = sc.nextLine();

                        double ac = leerNumero(sc, "Activo Corriente: ");
                        double inv = leerNumero(sc, "Inventario: ");
                        double ef = leerNumero(sc, "Efectivo: ");
                        double pc = leerNumero(sc, "Pasivo Corriente: ");
                        double at = leerNumero(sc, "Activo Total: ");
                        double pt = leerNumero(sc, "Pasivo Total: ");
                        double pat = leerNumero(sc, "Patrimonio: ");
                        double ing = leerNumero(sc, "Ingresos: ");
                        double cos = leerNumero(sc, "Costos: ");
                        double gas = leerNumero(sc, "Gastos: ");
                        double inte = leerNumero(sc, "Intereses: ");
                        double cxcN = leerNumero(sc, "Cuentas por cobrar: ");
                        double cxpN = leerNumero(sc, "Cuentas por pagar: ");

                        actualizarDatos(usuario, nuevaEmpresa, ac, inv, ef, pc, at, pt, pat,
                                ing, cos, gas, inte, cxcN, cxpN);

                        double[] nuevos = cargarDatos(usuario);

                        activoCorriente = nuevos[0];
                        inventario = nuevos[1];
                        efectivo = nuevos[2];
                        pasivoCorriente = nuevos[3];
                        activoTotal = nuevos[4];
                        pasivoTotal = nuevos[5];
                        patrimonio = nuevos[6];
                        ingresos = nuevos[7];
                        costos = nuevos[8];
                        gastos = nuevos[9];
                        intereses = nuevos[10];
                        cxc = nuevos[11];
                        cxp = nuevos[12];

                        empresa = nuevaEmpresa;

                        System.out.println("🔄 Datos actualizados en memoria");

                    break;

                    case 0:
                        System.out.println("HASTA PRONTO");

                        break;
                default:
                    System.out.println("¡LA OPCION ES INVALIDA!");
            }
            } while (caso !=0);

        } else {

            System.out.println("=================================================");
            System.out.println("|BIENVENIDO AL SISTEMA DE CALCULA PARA ESF Y ERI|");
            System.out.println("=================================================");
            //introduuccion del nombre del usuario
            /*System.out.println("===========================");
            System.out.println("| == INGRESE SU NOMBRE == |");
            System.out.println("===========================");
            String nombre = sc.nextLine(); */

            //introduuccion del nombre de la empresa
            System.out.println("==================================");
            System.out.println("|CUAL ES EL NOMBRE DE SU EMPRESA:|");
            System.out.println("==================================");
            empresa = sc.nextLine(); 

            System.out.println("============================================================================================================================================================");
            System.out.println("=== SEÑOR INGRESE LOS DATOS FINANCIEROS DE LA EMPRESA " + empresa.toUpperCase() + " EN EL ULTIMO AÑO FACTURADO ===");
            System.out.println("============================================================================================================================================================");

            activoCorriente = leerNumero(sc, "Activo Corriente: ");
            inventario = leerNumero(sc, "Inventario: ");
            efectivo = leerNumero(sc, "Efectivo: ");
            pasivoCorriente = leerNumero(sc, "Pasivo Corriente: ");
            activoTotal = leerNumero(sc, "Activo Total: ");
            pasivoTotal = leerNumero(sc, "Pasivo Total: ");
            patrimonio = leerNumero(sc, "Patrimonio: ");
            ingresos = leerNumero(sc, "Ingresos (Ventas): ");
            costos = leerNumero(sc, "Costos: ");
            gastos = leerNumero(sc, "Gastos: ");
            intereses = leerNumero(sc, "Intereses: ");
            cxc = leerNumero(sc, "Cuentas por cobrar: ");
            cxp = leerNumero(sc, "Cuentas por pagar: ");
            System.out.println("=========================================================");

            guardarDatos(usuario, empresa, activoCorriente, inventario, efectivo,
                    pasivoCorriente, activoTotal, pasivoTotal, patrimonio,
                    ingresos, costos, gastos, intereses, cxc, cxp);


            double utilidad = ingresos - costos - gastos;
                
            do { 
            System.out.println("======================================================");
            System.out.println("| == YA INGRESADOS LOS DATOS, QUE DESEAS ENCONTRAR ==|");
            System.out.println("======================================================");
            System.out.println("======================================================");
            System.out.println("| 1 == INDICADORES DE LIQUIDEZ                       |");
            System.out.println("======================================================");
            System.out.println("| 2 == INDICADORES DE ENDEUDAMIENTO                  |");
            System.out.println("======================================================");
            System.out.println("| 3 == INDICADORES DE RENTABILIDAD                   |");
            System.out.println("======================================================");
            System.out.println("| 4 == INDICADORES DE INVENTARIO                     |");
            System.out.println("======================================================");
            System.out.println("| 5 == INDICADORES DE CREDITO                        |");
            System.out.println("======================================================");
            System.out.println("| 6 == LA IDENTIDAD DUPONT                           |");
            System.out.println("======================================================");
            System.out.println("| 7 == MEDIDAS DEL VALOR DEL MERCADO                 |");
            System.out.println("======================================================");
            System.out.println("| 8 == VARIACION RELATIVA Y ABSOLUTA                 |");
            System.out.println("======================================================");
            System.out.println("| 0 == SALIR                                         |");
            System.out.println("======================================================");

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
                    System.out.println("==================================================================================================================================");
                    System.out.println("=PARA ESTA OPCION SE LE SOLICITA INGRESAR LOS DATOS FINANCIEROS DE LA EMPRESA " + empresa + " EN EL PENULTIMO AÑO FACTURADO =");
                    System.out.println("==================================================================================================================================");

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

        public static double[] cargarDatos(String usuario) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("datos_financieros.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");

                if (d[0].equals(usuario)) {
                    double[] datos = new double[13];
                    for (int i = 0; i < 13; i++) {
                        datos[i] = Double.parseDouble(d[i + 2]);
                    }
                    br.close();
                    return datos;
                }
            }
            br.close();
        } catch (Exception e) {}
        return null;
    }

    public static String obtenerEmpresa(String usuario) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("datos_financieros.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d[0].equals(usuario)) {
                    br.close();
                    return d[1];
                }
            }
            br.close();
        } catch (Exception e) {}
        return "";
    }

    // ===== GUARDAR =====
    public static void guardarDatos(String usuario, String empresa, double... datos) {
        try {
            FileWriter fw = new FileWriter("datos_financieros.txt", true);
            fw.write(usuario + "," + empresa);
            for (double d : datos) fw.write("," + d);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error guardando");
        }
    }

    // ===== LOGIN =====
    public static String iniciarSesion(Scanner sc) {
        System.out.println("=========");
        System.out.println("USUARIO: ");
        System.out.println("=========");
        String u = sc.nextLine();
        System.out.println("=========");
        System.out.println("CLAVE:   ");
        System.out.println("=========");
        
        String c = sc.nextLine();

        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String l;

            while ((l = br.readLine()) != null) {
                String[] d = l.split(",");
                if (u.equals(d[0]) && c.equals(d[1])) { // SIN ENCRIPTAR
                    br.close();
                    return u;
                }
            }
            br.close();
        } catch (Exception e) {}
        return null;
    }

    // ===== REGISTRO =====
    public static void registrarUsuario(Scanner sc) {
        System.out.println("===============");
        System.out.println("NUEVO USUARIO: ");
        System.out.println("===============");
        String u = sc.nextLine();

        if (usuarioExiste(u)) {
            System.out.println("Ya existe");
            return;
        }

        System.out.println("=========");
        System.out.println("CLAVE: ");
        System.out.println("=========");
        String c = sc.nextLine();

        try {
            FileWriter fw = new FileWriter("usuarios.txt", true);
            fw.write(u + "," + c + "\n"); // SIN ENCRIPTAR
            fw.close();
        } catch (Exception e) {}
    }

    public static boolean usuarioExiste(String u) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String l;
            while ((l = br.readLine()) != null) {
                if (l.split(",")[0].equals(u)) {
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (Exception e) {}
        return false;
    }

    public static void actualizarDatos(String usuario, String empresa, double... nuevosDatos) {

    try {
        File inputFile = new File("datos_financieros.txt");
        File tempFile = new File("temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        FileWriter fw = new FileWriter(tempFile);

        String linea;

        while ((linea = br.readLine()) != null) {
            String[] d = linea.split(",");

            if (d[0].equals(usuario)) {
                //  REEMPLAZA LOS DATOS
                fw.write(usuario + "," + empresa);
                for (double val : nuevosDatos) {
                    fw.write("," + val);
                }
                fw.write("\n");
            } else {
                // deja las otras líneas igual
                fw.write(linea + "\n");
            }
        }

        br.close();
        fw.close();

        // reemplazar archivo original
        inputFile.delete();
        tempFile.renameTo(inputFile);

        System.out.println(" Datos actualizados correctamente");

    } catch (IOException e) {
        System.out.println(" Error al actualizar datos");
    }
}
    
}