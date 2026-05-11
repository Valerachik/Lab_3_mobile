package Proga.lab_3_mobile;
import androidx.room.*;
import androidx.room.RoomDatabase;

@Database(entities = {Pizza.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PizzaDAO pizzaDao();
}
