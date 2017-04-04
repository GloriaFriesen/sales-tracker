import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jbdc:postgresql://localhost:5432/sales_tracker_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteSaleQuery = "DELETE FROM sales *;";
      con.createQuery(deleteSaleQuery).executeUpdate();
      String deleteItemQuery = "DELETE FROM items *;";
      con.createQuery(deleteItemQuery).executeUpdate();
    }
  }
}
