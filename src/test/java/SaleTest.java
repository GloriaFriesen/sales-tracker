import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class SaleTest {

  Timestamp rightNow = new Timestamp(new Date().getTime());

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Sale_instantitatesCorrectly_true() {
    Sale testSale = new Sale(1, rightNow);
    assertEquals(true, testSale instanceof Sale);
  }

  @Test
  public void Sale_instantitatesWithTimeSold_Timestamp() {
    Sale testSale = new Sale(1, rightNow);
    assertEquals(rightNow, testSale.getTimeSold());
  }

  @Test
  public void equals_returnsTrueIfPropertiesAreSame() {
    Sale firstSale = new Sale(1, rightNow);
    Sale secondSale = new Sale(1, rightNow);
    assertTrue(firstSale.equals(secondSale));
  }

  @Test
  public void save_returnsTrueIfAllTheSame() {
    Sale testSale = new Sale(1, rightNow);
    testSale.save();
    assertTrue(Sale.all().get(0).equals(testSale));
  }

  @Test
  public void save_assignsIdToSale() {
    Sale testSale = new Sale(1, rightNow);
    testSale.save();
    Sale savedSale = Sale.all().get(0);
    assertEquals(testSale.getId(), savedSale.getId());
  }
}
