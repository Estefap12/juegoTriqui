package gestion;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        jugar();
    }

    public static void jugar() {
        char J1 = 'x';
        char J2 = '0';
        char vacio = '-';

        boolean turno = true;

        char tablero[][] = new char[3][3];
        rellenarTablero(tablero, vacio);
        int fila, columna;
        boolean posValida, correcto;


        while (!finPartida(tablero,vacio)) {

            do {
                mostrarTurno(turno);
                mostrarTablero(tablero);


                correcto = false;
                fila = pedirNumero("Dame la fila");
                columna = pedirNumero("Dame la Columna");
                posValida = validarPosicion(tablero, fila, columna);

                if (posValida) {
                    if (!valorPosicion(tablero, fila, columna, vacio)) {
                        correcto = true;
                    } else {
                        System.out.println("Esta llena la casilla");
                    }
                } else {
                    System.out.println("La posicion no es valida");
                }
            } while (!correcto);

            if (turno) {
                insertarEn(tablero, fila, columna,J1);

            } else {
                insertarEn(tablero, fila, columna,J2);
            }

            turno = !turno;

        }

        mostrarTablero(tablero);
        mostrarGanador(tablero, J1, J2, vacio);
    }
    public static void mostrarGanador(char [][] tablero, char J1, char J2, char simDef) {
        char simbolo = coincidenciaLinea(tablero, simDef);
        if (simbolo != simDef) {
            if (simbolo == J1) {
                System.out.println("Ha ganado el jugador 1");
            } else {
                System.out.println("Ha ganado el jugador 2");

            }
            return;
        }
        simbolo = coincidenciaColumna(tablero, simDef);
        if (simbolo != simDef) {
            if (simbolo == J1) {
                System.out.println("Ha ganado el jugador 1");
            } else {
                System.out.println("Ha ganado el jugador 2");

            }
            return;
        }
        simbolo = coincidenciaDiagonal(tablero, simDef);
        if (simbolo != simDef) {
            if (simbolo == J1) {
                System.out.println("Ha ganado el jugador 1");
            } else {
                System.out.println("Ha ganado el jugador 2");

            }
            return;
        }
        System.out.println("Hay empate");
    }


    public static void insertarEn(char[][] tablero, int fila, int columna, char simbolo){
    tablero [fila] [columna] = simbolo;
}

    public static void mostrarTurno (boolean turno) {
        if (turno){
            System.out.println("Turno al juagador 1");
        }else {
            System.out.println("Le toca al juagador 2");
        }
    }
    public static int pedirNumero (String mensaje){
        System.out.println(mensaje);
        int numero = sc.nextInt();
        return numero;
    }
    public static void rellenarTablero (char [][] tablero, char simbolo) {
        for (int i = 0; i < tablero.length; i++) {
          for  (int j = 0; j < tablero.length; j++){
              tablero [i][j] = simbolo;
          }
        }
    }
    public static boolean validarPosicion (char [][]tablero,int fila, int columna){
        if (fila >= 0 && fila<tablero.length && columna>= 0 && columna<tablero.length) {
            return true;
        }
        return false;
    }
    public static boolean valorPosicion (char [][] tablero, int fila, int columna, char simboloDef) {
        if (tablero[fila] [columna] != simboloDef){
           return true;
        }
        return false;
    }

    public static void mostrarTablero (char [][] tablero) {

        for (int i = 0; i< tablero.length; i++ ) {
           for (int j = 0; j <tablero [0].length; j++) {
               System.out.print(tablero [i][j]+ "");
           }
           //Espacio
            System.out.println("");
        }
    }
    public static boolean tableroLleno (char [][] tablero,char simboloDef) {
        for (int i = 0; i< tablero.length; i++ ) {
            for (int j = 0; j <tablero [0].length; j++) {
                if (tablero [i][j] ==simboloDef) {
                    return false;
                }
            }

        }
        return true;
    }
    public static boolean finPartida (char [][] tablero,char simboloDef) {
       if (tableroLleno(tablero, simboloDef) ||
            coincidenciaLinea(tablero, simboloDef) !=simboloDef ||
                    coincidenciaColumna(tablero, simboloDef) !=simboloDef  ||
                    coincidenciaDiagonal(tablero, simboloDef)!=simboloDef )  {
                     return true;
                }
                return false;
            }
    public static char coincidenciaLinea (char [][] tablero, char simboloDef) {
        char simbolo;
        boolean coincidencia;
        for (int i = 0; i < tablero.length; i++) {
             coincidencia=true;
             simbolo = tablero [i] [0];
             if (simbolo != simboloDef) {
                 for (int j = 1; j < tablero [0].length; j++) {
                     if (simbolo != tablero [i][j]) {
                          coincidencia=false;
                     }
                 }
                 if (coincidencia) {
                     return simbolo;
                 }
             }
        }
        return simboloDef;
    }
    public static char coincidenciaColumna(char [][] tablero, char simboloDef) {

        char simbolo;
        boolean coincidencia;
        for (int j = 0; j < tablero.length; j++) {
            coincidencia=true;
            simbolo = tablero [0] [j];
            if (simbolo != simboloDef) {
                for (int i = 1; i< tablero [0].length; i++) {
                    if (simbolo != tablero [i][j]) {
                        coincidencia=false;
                    }
                }
                if (coincidencia) {
                    return simbolo;
                }
            }
        }
        return simboloDef;
    }

    public static char coincidenciaDiagonal(char [][] tablero, char simboloDef) {
        char simbolo;
        boolean coincidencia = true;

        //Diagonal principal
        simbolo = tablero [0][0];
        if (simbolo !=simboloDef){
            for (int i = 1; i<tablero.length; i++) {
             if (simbolo !=tablero[i][i]) {
                coincidencia= false;
             }

            }
            if (coincidencia){
               return simbolo;
            }

        }
        //Diagonal inversa
        simbolo = tablero [0][2];
        if (simbolo !=simboloDef){
            for (int i = 1, j=1; i<tablero.length; i++, j--) {
                if (simbolo !=tablero[i][j]) {
                    coincidencia= false;
                }

            }
            if (coincidencia) {
                return simbolo;
            }

        }
       return simboloDef;
    }

}
