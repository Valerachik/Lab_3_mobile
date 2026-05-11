package Proga.lab_3_mobile;
import androidx.room.*;
import java.util.List;
@Dao
public interface PizzaDAO {
    @Query("SELECT * FROM pizza")
    List<Pizza> getAll();
    @Query("SELECT * FROM pizza WHERE id = :id LIMIT 1")
    Pizza findById(int id);
    @Query("SELECT * FROM pizza WHERE name LIKE :name LIMIT 1")
    Pizza findByName(String name);
    @Insert
    void insert(Pizza pizza);
    @Update
    void update(Pizza pizza);
    @Delete
    void delete(Pizza pizza);
}
