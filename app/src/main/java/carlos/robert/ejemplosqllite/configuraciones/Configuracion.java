package carlos.robert.ejemplosqllite.configuraciones;

import java.text.NumberFormat;

public class Configuracion {
    public static final String BD_NAME = "tienda.bd";
    public static final int BD_VERSION = 1;
    public static final NumberFormat NF = NumberFormat.getNumberInstance();
    public static final NumberFormat NFM = NumberFormat.getCurrencyInstance();

}
