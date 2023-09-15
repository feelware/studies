package src.main.recibos;

import src.main.Sistema;

public class Factura extends Recibo {
    private String ruc;
    private float montoImpuesto;
    
    public Factura() {
        super();
        ruc = scanRuc();
        montoImpuesto = calcMontoImpuesto(getMontoBruto());
        montoNeto = getMontoBruto() - montoImpuesto;
    }

    public String getRuc() {
        return ruc;
    }

    public float getMontoImpuesto() {
        return montoImpuesto;
    }

    private static String scanRuc() {
        System.out.print("\nIngrese RUC\n> ");
        return Sistema.sc.nextLine();
    }

    private static float calcMontoImpuesto(float montoBruto) {
        return montoBruto * 0.19f;
    }
}
