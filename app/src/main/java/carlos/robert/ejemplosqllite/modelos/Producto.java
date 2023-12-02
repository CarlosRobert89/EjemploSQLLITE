package carlos.robert.ejemplosqllite.modelos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "productos")
public class Producto {
    @DatabaseField(columnName = "id_producto", generatedId = true)
    private int id;
    @DatabaseField(columnName = "nombre", canBeNull = false)
    private String nombre;
    @DatabaseField(canBeNull = false)
    private int cantidad;
    @DatabaseField(canBeNull = false)
    private float precio;
    @DatabaseField(columnName = "total_producto", canBeNull = false)
    private float total;

    public Producto() {
    }

    public Producto(String nombre, int cantidad, float precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        calcularTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularTotal();
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
        calcularTotal();
    }

    public float getTotal() {
        return total;
    }

    private void calcularTotal() {
        total = precio * cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", total=" + total +
                '}';
    }
}
