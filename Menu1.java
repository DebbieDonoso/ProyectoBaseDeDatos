/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import datingservice.DatingService;
import static datingservice.DatingService.usuarios;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import usuarios.Premium;
import usuarios.Usuario;

/**
 *
 * @author Cristhian
 */
public class Menu1 {
    public static Scanner sc = new Scanner(System.in);
    
    public static void mostrarMenu() {
        Usuario u = null;
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("Dating Service");
            System.out.println("1.\tCrear cuenta usuario\n"
                    + "2.\tIniciar sesión \n" + "3.\tSalir");
            System.out.println("");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println("");
            switch (opcion) {
                case 1:
                    u = crearCuenta();
                    if(u != null){
                        DatingService.usuarios.add(u);
                    } else{
                        System.out.println("El usuario no pudo ser creado");
                    }
                    break;
                case 2:
                    u = iniciarSesion();
                    if(u != null){
                        Menu2.mostrarMenu(u);
                    } else{
                        System.out.println("Usuario o contraseña incorrectas.");
                    }
                    break;
                case 3:
                    System.out.println("Gracias por usar el servicio.");
                    break;
            }
            System.out.println("");
        }
    }

    public static Usuario crearCuenta() {
        Usuario u = null;
        System.out.println("Ingrese su email:");
        String email = sc.nextLine();
        System.out.println("Ingrese su nombre de usuario (nickname):");
        String nickname = sc.nextLine();
        System.out.println("Ingrese su fecha de Nacimiento (Ejm: 21/08/1998):");
        String fecha = sc.nextLine();
        LocalDate fechaNacimiento = convertirAFecha(fecha);
        if (validarEdad(fechaNacimiento)) {
            System.out.println("Ingrese su contraseña:");
            String contra = sc.nextLine();
            System.out.println("");
            imprimirExplicacion();
            System.out.println("");
            System.out.println("Elija su tipo de usuario (S/P):");
            String tipo = sc.nextLine();
            if (tipo.equalsIgnoreCase("P")) {
                long numTarjeta = 0;
                LocalDate fechaCaducidad = LocalDate.now();
                while (!validarCaducidad(fechaCaducidad)) {
                    System.out.println("Ingrese su número de tarjeta:");
                    numTarjeta = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese la fecha de caducidad (Mes/Año)(Ejm: 08/2024):");
                    String caducidad = "01/" + sc.nextLine();
                    fechaCaducidad = convertirAFecha(caducidad).plusMonths(1);
                    if (!validarCaducidad(fechaCaducidad)) {
                        System.out.println("Tarjeta no válida. Ingrese otra.");
                        System.out.println("");
                    }
                }
                u = new Premium(email, nickname, fechaNacimiento, contra, numTarjeta, fechaCaducidad);
            } else {
                u = new Usuario(email, nickname, fechaNacimiento, contra);
            }
            if(DatingService.usuarios.contains(u)){
                System.out.println("");
                System.out.println("Error el usuario ya existe. Inicie sesión.");
            } else{
                System.out.println("");
                System.out.println("Usuario creado con éxito.");
            }
        } else {
            System.out.println("");
            System.out.println("Acceso denegado.");
            System.out.println("Solo mayores de edad pueden acceder.");
        }
        return u;
    }

    public static void imprimirExplicacion() {
        System.out.println("Existen dos tipos de usuario: Standard y Premium");
        System.out.println("Los usuarios Premium tienen la oportunidad de dar "
                + "un super me gusta que hará\nque sea visible su interés por "
                + "esa persona y más probabilidades de aparecer\ncomo candidato"
                + " para otras personas; para este beneficio es necesaria una "
                + "tarjeta.\nLos usuarios Standard sólo podrán dar me gusta y "
                + "tendrán menos probabilidades de aparecer como candidato.");
    }

    public static LocalDate convertirAFecha(String fecha) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate fechaNacimiento = LocalDate.parse(fecha, formateador);
        return fechaNacimiento;
    }

    public static boolean validarEdad(LocalDate fechaNacimiento) {
        LocalDate fechaMinima = LocalDate.now().minusYears(18);
        int diferencia = fechaMinima.compareTo(fechaNacimiento);
        return diferencia >= 0;
    }

    public static boolean validarCaducidad(LocalDate fechaCaducidad) {
        LocalDate fechaActual = LocalDate.now();
        int diferencia = fechaActual.compareTo(fechaCaducidad);
        return diferencia < 0;
    }
    
    public static Usuario iniciarSesion(){
        System.out.println("Ingrese su nombre de usuario (nickname):");
        String nickname = sc.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contra = sc.nextLine();
        Usuario u = new Usuario(nickname, contra);
        Premium p = new Premium(nickname, contra);
        Usuario v = null;
        for(Usuario w : DatingService.usuarios){
            if(w.equals(u)){
                v = w;
            }
            if(w.equals(p)){
                v = w;
            }
            
        }
        return v;
    }

}
