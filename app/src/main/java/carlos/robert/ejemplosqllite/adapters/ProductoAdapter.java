package carlos.robert.ejemplosqllite.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import carlos.robert.ejemplosqllite.R;
import carlos.robert.ejemplosqllite.modelos.Producto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoVH> {
    private Context context;
    private List<Producto> objects;
    private int resource;

    public ProductoAdapter(Context context, List<Producto> objects, int resource) {
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ProductoAdapter.ProductoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(context).inflate(resource, null);

        productView.setLayoutParams(
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        return new ProductoVH(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ProductoVH holder, int position) {
        Producto producto = objects.get(position);

        holder.lbNombre.setText(producto.getNombre());
        holder.lbTotal.setText(String.valueOf(producto.getTotal()));

        holder.imEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarEditar(holder.getAdapterPosition()).show();
            }
        });
        holder.imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarBorrar(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    private AlertDialog confirmarEditar(int posicion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("MODIFICAR PRODUCTO");
        builder.setCancelable(false);

        View productoView = LayoutInflater.from(context).inflate(R.layout.product_view_model, null);
        EditText txtNombre = productoView.findViewById(R.id.txtNombreProductViewModel);
        EditText txtCantidad = productoView.findViewById(R.id.txtCantidadProductViewModel);
        EditText txtPrecio = productoView.findViewById(R.id.txtPrecioProductViewModel);
        builder.setView(productoView);

        Producto producto = objects.get(posicion);
        txtNombre.setText(producto.getNombre());
        txtCantidad.setText(String.valueOf(producto.getCantidad()));
        txtPrecio.setText(String.valueOf(producto.getPrecio()));

        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!txtNombre.getText().toString().isEmpty() &&
                        !txtCantidad.getText().toString().isEmpty() &&
                        !txtPrecio.getText().toString().isEmpty()) {
                    objects.set(posicion, new Producto(
                            txtNombre.getText().toString(),
                            Integer.parseInt(txtCantidad.getText().toString()),
                            Float.parseFloat(txtPrecio.getText().toString())
                    ));
                    notifyItemChanged(posicion);
                }
            }
        });

        return builder.create();
    }

    private AlertDialog confirmarBorrar(int posicion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ESTAS SEGURO??");
        builder.setCancelable(false);

        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                objects.remove(posicion);
                //////////////////
                notifyItemInserted(posicion);
            }
        });


        return builder.create();
    }

    public class ProductoVH extends RecyclerView.ViewHolder {
        TextView lbNombre, lbTotal;
        ImageButton imEdit, imDelete;

        public ProductoVH(@NonNull View itemView) {
            super(itemView);
            lbNombre = itemView.findViewById(R.id.lbNombreProductViewHolder);
            lbTotal = itemView.findViewById(R.id.lbTotalPorductViewHolder);

            imEdit = itemView.findViewById(R.id.imEditProductViewHolder);
            imDelete = itemView.findViewById(R.id.imDeleteProductViewHolder);
        }
    }


}
