package src.main.recibos;

import src.main.App;

public class Honorario extends Recibo {
    private String codigoColegio;
    private float montoImpuesto;

    public Honorario() {
        super();
        codigoColegio = scanCodigoColegio();
        montoImpuesto = calcMontoImpuesto(getMontoBruto());
        montoNeto = getMontoBruto() - montoImpuesto;
    }

    public String getRuc() {
        return codigoColegio;
    }

    public float getMontoImpuesto() {
        return montoImpuesto;
    }

    private static String scanCodigoColegio() {
        System.out.print("\nIngrese código de colegiatura\n> ");
        return App.sc.nextLine();
    }

    private static float calcMontoImpuesto(float montoBruto) {
        return montoBruto * 0.19f;
    }
}
