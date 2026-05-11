package Proga.lab_3_mobile;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private PizzaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pizza-db")
                .allowMainThreadQueries()
                .build();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PizzaAdapter(new PizzaAdapter.OnPizzaClickListener() {
            @Override
            public void onEdit(Pizza pizza) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                intent.putExtra("PIZZA_ID", pizza.getId());
                startActivity(intent);
            }
            @Override
            public void onDelete(Pizza pizza) {
                db.pizzaDao().delete(pizza);
                loadPizzas();
            }
        });
        recyclerView.setAdapter(adapter);
        Button btnAdd = findViewById(R.id.button);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadPizzas();
    }
    private void loadPizzas() {
        adapter.setPizzas(db.pizzaDao().getAll());
    }
}
