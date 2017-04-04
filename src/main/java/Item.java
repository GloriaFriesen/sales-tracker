import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Item {
  private String name;
  private int price;
  private int quantity;
  private int id;

  public Item(String name, int price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  @Override
  public boolean equals(Object otherItem){
    if (!(otherItem instanceof Item)) {
      return false;
    } else {
      Item newItem = (Item) otherItem;
      return this.getName().equals(newItem.getName()) &&
             this.getPrice() == newItem.getPrice() &&
             this.getQuantity() == newItem.getQuantity();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO items (name, price, quantity) VALUES (:name, :price, :quantity)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("price", this.price)
        .addParameter("quantity", this.quantity)
        .executeUpdate()
        .getKey();
    }
  }

  public int getId() {
    return id;
  }

  public static List<Item> all() {
    String sql = "SELECT * FROM items";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Item.class);
    }
  }
}
