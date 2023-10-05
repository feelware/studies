package com.recibos;

public class Boleta extends Recibo {
    public Boleta() {
        super();
        montoNeto = getMontoBruto();
    }
}
