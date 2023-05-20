package DAO;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelo.Empleado;

public class OperacionEmpleado {
    Scanner scOpc = new Scanner(System.in);
    Scanner sc = new Scanner(System.in);
    Empleado[] array;
    List<Empleado> lista;
    Function<Empleado, String> nombreEmpleado = Empleado::getNombre;
    Function<Empleado, String> apellidoEmpleado = Empleado::getApellido;
    Comparator<Empleado> nombreLuegoApellido = Comparator.comparing(nombreEmpleado)
            .thenComparing(apellidoEmpleado);
    Empleado objEmpleado;
    
    public Empleado[] cargaEmpleado() {
        int cont = 0;
        array = new Empleado[6];
        do {
            
            System.out.printf("%nNombre del Empleado N°%d: ", cont + 1);
            String nom = sc.nextLine();
            System.out.printf("%nApellido del Empleado N°%d: ", cont + 1);
            String ape = sc.nextLine();
            
            System.out.printf("%nAsignado al Departamento de: ");
            String depar = sc.nextLine();
            System.out.printf("%nSalario del Empleado N°%d: ", cont + 1);
            double salario = sc.nextDouble();
            objEmpleado = new Empleado(nom, ape, salario, depar);
            array[cont] = objEmpleado;
            System.out.printf("%nQuedan %d Empleados por rellenar.%n", (6-(cont+1)));
            sc.nextLine();
            cont++;
            
        } while (cont < 6);
        System.out.println("¡Listo! Has rellenado los 6 Empleados!");
        return array;
    }

    public void mostrarListaEmpleados() {
        lista = Arrays.asList(array);
        lista.stream().forEach(e -> System.out.printf("%s %n", e));
    }

    public void totalSalarios() {
        lista = Arrays.asList(array);
        System.out.printf("Suma Total de Salarios: %.2f%n",
                lista.stream().mapToDouble(Empleado::getSalario).sum());
    }

    public void promedioSalarios() {
        lista = Arrays.asList(array);
        System.out.printf("Promedio de los Salarios: %.2f%n",
                lista.stream().mapToDouble(Empleado::getSalario).average().getAsDouble());
    }

    public void mostrarRangoSalarios() {
        int a, b;

        System.out.printf("Ingrese el rango salarial de los empleados(ej. 20 a 30)");
        System.out.print("De: $");
        a = sc.nextInt();
        System.out.println("");
        System.out.print("A: $");
        b = sc.nextInt();
        System.out.println("");
        Predicate<Empleado> rangoSalarial = e -> (e.getSalario() >= a && e.getSalario() <= b);
        System.out.println("\tTRABAJADORES POR RANGO SALARIAL DE $" + a + " A $" + b + ".");
        lista.stream().filter(rangoSalarial).sorted(Comparator.comparing(Empleado::getSalario))
                .forEach(System.out::println);
    }

    public void cantidadTrabajadores() {
        long l = lista.stream().count();
        System.out.println("Hay " + l + " empleados contratado en la empresa.");
    }

    public void trabajadoresPorDepartamento() {
        Map<String, List<Empleado>> grupoPorDepartamento = lista.stream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento));
        System.out.println("\tEMPLEADOS POR DEPARTAMENTO:");
        grupoPorDepartamento.forEach((departamento, empleados) -> {
            System.out.println(departamento);
            empleados.forEach(em -> System.out.printf("  %s%n", em));
        });
    }

    public void nombreYApellidoEmpleados() {
        lista.stream().sorted(nombreLuegoApellido).map(Empleado::getNombreCompleto)
                .forEach(System.out::println);
    }

    public void empleadosPorDepartamento() {
        Map<String, Long> conteoEmpleadoPorDepartamento = lista.stream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento,
                        TreeMap::new, Collectors.counting()));
        conteoEmpleadoPorDepartamento.forEach((depart, cantidad) -> {
            System.out.printf("En el %s hay %d de empleados.%n", depart, cantidad);
        });
    }

    public void menu() {
        int opc = 0;
        while (opc != 9) {
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("\tBIENVENIDO AL SISTEMA DE LA EMPRESA EMMANUEL S.A");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("1.Crear lista de Empleados.");
            System.out.println("2.Ver lista de Empleados.");
            System.out.println("3.Ver cantidad de Empleados en la empresa.");
            System.out.println("4.Ver el total de los Salarios de los Empleados.");
            System.out.println("5.Ver el promedio Salarial del conjunto de Empleados.");
            System.out.println("6.Ver los Empledos por Rango Salarial.");
            System.out.println("7.Ver Empleados por Departamento.");
            System.out.println("8.Ver Empleados solo por nombre y apellido.");
            System.out.println("9.Salir");
            System.out.printf("Digite una opción: ");
            opc = scOpc.nextInt();
            System.out.println("");
            if (opc > 0 && opc < 9) {
                switch (opc) {
                    case 1:
                        System.out.println("------------------=------------------");
                        System.out.println("\tRELLENE LOS DATOS DE LOS 6 EMPLEADOS");
                        cargaEmpleado();
                        System.out.println("------------------=------------------");
                        break;
                    case 2:
                        if(array != null || lista != null){
                        System.out.println("------------------=------------------");
                        mostrarListaEmpleados();
                        System.out.println("------------------=------------------");
                        }else{
                            System.out.println(">>Debes cargar los empleados en una lista.<<");
                        }
                        break;
                    case 3:
                        if(array != null || lista != null ){
                        System.out.println("------------------=------------------");
                        cantidadTrabajadores();
                        System.out.println("------------------=------------------");
                        } else{
                            System.out.println(">>Debes crear los empleados primero.<<");
                        }
                        break;
                    case 4:
                        if(array != null || lista != null){
                        System.out.printf("%n------------------=------------------%n");
                        totalSalarios();
                        System.out.printf("%n------------------=------------------%n");
                        }else{
                            System.out.println(">>No hay empleados. Debes crear los empleados primero.<<");
                        }
                        break;
                    case 5:
                        if(array != null || lista != null){
                        System.out.printf("%n------------------=------------------%n");
                        promedioSalarios();
                        System.out.printf("%n------------------=------------------%n");
                        }else{
                            System.out.println(">>Debes crear los empleados primero.<<");
                        }
                        break;
                    case 6:
                        if(array != null || lista != null){
                        System.out.printf("%n------------------=------------------%n");
                        mostrarRangoSalarios();
                        System.out.printf("%n------------------=------------------%n");
                        }else{
                            System.out.println(">>Debes crear los empleados primero.<<");
                        }
                        break;
                    case 7:
                        if(array != null || lista != null){
                        System.out.printf("%n------------------=------------------%n");
                        trabajadoresPorDepartamento();
                        System.out.printf("%n------------------=------------------%n");
                        }else{
                            System.out.println(">>No tienes empleados guardados.<<");
                        }
                        break;
                    case 8:
                        if(array != null || lista != null){
                        System.out.printf("%n------------------=------------------%n");
                        nombreYApellidoEmpleados();
                        System.out.printf("%n------------------=------------------%n");
                        }else{
                            System.out.println(">>No hay empleados en la lista.<<");
                        }
                        break;
                    default:
                        System.out.printf("%n------------------=------------------%n");
                        System.out.println("FIN DEL PROGRAMA.");
                        System.out.printf("%n------------------=------------------%n");
                        break;

                }
            }else{
            System.out.printf("%n------------------=------------------%n");
            if(opc != 9){
            System.out.println("Opción invalida!");
            }
            System.out.println("FIN DEL PROGRAMA");
            System.out.printf("%n------------------=------------------%n");
            }

        }
    }

}
