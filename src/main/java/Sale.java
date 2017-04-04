import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Sale {
  private int item_id;
  private Timestamp time_sold;
  private int id;

  public Sale(int item_id, Timestamp time_sold) {
    this.item_id = item_id;
    this.time_sold = time_sold;
    this.id = id;
  }

  public int getItemId() {
    return item_id;
  }

  public Timestamp getTimeSold() {
    return time_sold;
  }

  @Override
  public boolean equals(Object otherSale) {
    if (!(otherSale instanceof Sale)) {
      return false;
    } else {
      Sale newSale = (Sale) otherSale;
      return this.getItemId() == (newSale.getItemId());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sales (item_id, time_sold) VALUES (:item_id, :time_sold)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("item_id", this.item_id)
      .addParameter("time_sold", this.time_sold)
      .executeUpdate()
      .getKey();
    }
  }

  public int getId() {
    return id;
  }

  public static List<Sale> all() {
    String sql = "SELECT * FROM sales";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Sale.class);
    }
  }

  public static Sale find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sales WHERE id=:id";
      Sale sale = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sale.class);
      return sale;
    }
  }
}
