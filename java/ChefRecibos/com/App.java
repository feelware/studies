package com;

import java.util.Scanner;

import com.recibos.Boleta;
import com.recibos.Factura;
import com.recibos.Honorario;

import java.util.List;
import java.util.ArrayList;

public class App {
    private static List<Object> recibos = new ArrayList<Object>();
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("Elegir una opcion:\n");
            System.out.println("1. Ingresar Boleta");
            System.out.println("2. Ingresar Factura");
            System.out.println("3. Ingresar Honorario");
            System.out.println("4. Mostrar montos totales\n");
            System.out.print("0. Salir\n> ");

            switch (Integer.parseInt(System.console().readLine())) {
                case 0: System.exit(0);
                case 1: recibos.add(new Boleta()); break;
                case 2: recibos.add(new Factura()); break;
                case 3: recibos.add(new Honorario()); break;
                case 4: mostrarMontosTotales(); break;
            }
        }
    }

    private static void mostrarMontosTotales() {
        float montoBrutoTotal = 0;
        float montoNetoTotal = 0;
        float montoImpuestoTotal = 0;

        for (Object recibo : recibos) {
            if (recibo instanceof Boleta) {
                montoBrutoTotal += ((Boleta) recibo).getMontoBruto();
                montoNetoTotal += ((Boleta) recibo).getMontoNeto();
            } else if (recibo instanceof Factura) {
                montoBrutoTotal += ((Factura) recibo).getMontoBruto();
                montoNetoTotal += ((Factura) recibo).getMontoNeto();
                montoImpuestoTotal += ((Factura) recibo).getMontoImpuesto();
            } else if (recibo instanceof Honorario) {
                montoBrutoTotal += ((Honorario) recibo).getMontoBruto();
                montoNetoTotal += ((Honorario) recibo).getMontoNeto();
                montoImpuestoTotal += ((Honorario) recibo).getMontoImpuesto();
            }
        }

        System.out.println("\nMonto bruto total: " + montoBrutoTotal);
        System.out.println("Monto impuesto total: " + montoImpuestoTotal);
        System.out.println("Monto neto total: " + montoNetoTotal + "\n");
    }
}
