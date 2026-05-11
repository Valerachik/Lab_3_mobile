package Proga.lab_3_mobile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder>{
    private List<Pizza> pizzas = new ArrayList<>();
    private final OnPizzaClickListener listener;
    public interface OnPizzaClickListener {
        void onEdit(Pizza pizza);
        void onDelete(Pizza pizza);
    }
    public PizzaAdapter(OnPizzaClickListener listener) {
        this.listener = listener;
    }
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);
        holder.tvInfo.setText(pizza.getName() + " - " + pizza.getPrice() + " грн");
        holder.btnEdit.setOnClickListener(v -> listener.onEdit(pizza));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(pizza));
    }
    @Override
    public int getItemCount() {
        return pizzas.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInfo;
        Button btnEdit, btnDelete;
        ViewHolder(View view) {
            super(view);
            tvInfo = view.findViewById(R.id.tvInfo);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);
        }
    }
}
