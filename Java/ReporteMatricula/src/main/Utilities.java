package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

public class Utilities {
    public static Scanner sc = new Scanner(System.in);

    public static String validarEntrada(String mensaje, String regexBusq) {
        System.out.print(mensaje + "\n> ");
        String resultado = sc.next();
        if (!Pattern.compile(regexBusq).matcher(resultado).find()) {
            System.out.print("\nEntrada inválida. ");
            resultado = validarEntrada(mensaje, regexBusq);
        }
        return resultado;
    }

    public static Vector<Vector<String>> buscarArchivo(InputStream inputStream, int[] indicesIn, String[] regexBusq, String regexSplit, int[] indicesOut) {
        String linea;
        Vector<Vector<String>> resultados = new Vector<Vector<String>>();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            while ((linea = rd.readLine()) != null) {
                boolean lineaEncontrada = false;
                Vector<String> resultado = new Vector<String>();
                for (int i = 0; i < indicesIn.length; i++) { // buscar en cada índice
                    lineaEncontrada = Pattern.compile(regexBusq[i]).matcher(linea.split(regexSplit)[indicesIn[i]]).find();
                    if (!lineaEncontrada) { break; }
                }
                if (!lineaEncontrada) { continue; }
                // linea encontrada
                for (int i = 0; i < indicesOut.length; i++) { // agregar campos al resultado
                    resultado.add(linea.split(regexSplit)[indicesOut[i]]);
                }
                resultados.add(resultado);
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultados;
    }

    public static void cls() { System.out.print("\033[H\033[2J"); }

    public static boolean continuar(String mensajeVerif) { return Utilities.validarEntrada(mensajeVerif, "^\\s*[SsNn]\\s*$").contains("s"); }
}
