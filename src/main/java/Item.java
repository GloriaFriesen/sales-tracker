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
}
