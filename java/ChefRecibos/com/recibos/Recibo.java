package com.recibos;

import com.App;

public abstract class Recibo {
    private String emisorNombre;
    private String fecha;
    private float montoBruto;
    protected float montoNeto;

    public Recibo() {
        emisorNombre = scanEmisorNombre();
        fecha = scanFecha();
        montoBruto = scanMontoBruto();
    }

    public String getEmisor() {
        return emisorNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public float getMontoBruto() {
        return montoBruto;
    }

    public float getMontoNeto() {
        return montoNeto;
    }

    private static String scanEmisorNombre() {
        System.out.print("\nIngrese el nombre del emisor\n> ");
        return App.sc.nextLine();
    }

    private static String scanFecha() {
        System.out.print("\nIngrese la fecha\n> ");
        return App.sc.nextLine();
    }

    private static float scanMontoBruto() {
        System.out.print("\nIngrese el monto a pagar\n> ");
        return Float.parseFloat(App.sc.nextLine());
    }
}