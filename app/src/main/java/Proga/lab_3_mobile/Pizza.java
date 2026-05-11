package Proga.lab_3_mobile;
import androidx.room.*;

@Entity(tableName = "pizza")
public class Pizza {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "diameter")
    private int diameter;
    @ColumnInfo(name = "weight")
    private int weight;
    @ColumnInfo(name = "isSpicy")
    private boolean isSpicy;
    @ColumnInfo(name = "price")
    private int price;
    public Pizza(String name, int diameter, int weight, boolean isSpicy, int price) {
        this.name = name;
        this.diameter = diameter;
        this.weight = weight;
        this.isSpicy = isSpicy;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDiameter() {
        return diameter;
    }
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public boolean isSpicy() {
        return isSpicy;
    }
    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}