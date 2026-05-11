package Proga.lab_3_mobile;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
public class EditorActivity extends AppCompatActivity{
    private AppDatabase db;
    private int pizzaId = -1;
    private EditText etName, etDiameter, etWeight, etPrice;
    private com.google.android.material.switchmaterial.SwitchMaterial cbSpicy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pizza-db")
                .allowMainThreadQueries()
                .build();
        etName = findViewById(R.id.etName);
        etDiameter = findViewById(R.id.etDiameter);
        etWeight = findViewById(R.id.etWeight);
        etPrice = findViewById(R.id.etPrice);
        cbSpicy = findViewById(R.id.cbSpicy);
        Button btnSave = findViewById(R.id.btnSave);
        pizzaId = getIntent().getIntExtra("PIZZA_ID", -1);
        if (pizzaId != -1) {
            Pizza pizza = db.pizzaDao().findById(pizzaId);
            if (pizza != null) {
                etName.setText(pizza.getName());
                etDiameter.setText(String.valueOf(pizza.getDiameter()));
                etWeight.setText(String.valueOf(pizza.getWeight()));
                etPrice.setText(String.valueOf(pizza.getPrice()));
                cbSpicy.setChecked(pizza.isSpicy());
            }
        }
        btnSave.setOnClickListener(v -> savePizza());
    }
    private void savePizza() {
        try {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                throw new Exception("Введіть назву піци!");
            }
            int diameter = Integer.parseInt(etDiameter.getText().toString());
            if(diameter>200){
                throw new Exception("Завелика піцца! Введіть менший діаметр");
            }
            int weight = Integer.parseInt(etWeight.getText().toString());
            if(weight>2000){
                throw new Exception("Заважка піцца! Введіть меншу вагу");
            }
            int price = Integer.parseInt(etPrice.getText().toString());
            if(price>5000){
                throw new Exception("Задорога піцца! Введіть меншу ціну");
            }
            if (price <= 0 || diameter <= 0 || weight <= 0) {
                throw new Exception("Числа повинні бути більшими за нуль!");
            }
            boolean isSpicy = cbSpicy.isChecked();
            Pizza pizza = new Pizza(name, diameter, weight, isSpicy, price);
            if (pizzaId == -1) {
                db.pizzaDao().insert(pizza);
            } else {
                pizza.setId(pizzaId);
                db.pizzaDao().update(pizza);
            }
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Помилка: введіть числа у відповідні поля", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
