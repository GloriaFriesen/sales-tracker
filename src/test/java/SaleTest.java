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

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Sale_instantitatesCorrectly_true() {
    Sale testSale = new Sale(1);
    assertEquals(true, testSale instanceof Sale);
  }

  @Test
  public void equals_returnsTrueIfPropertiesAreSame() {
    Sale firstSale = new Sale(1);
    Sale secondSale = new Sale(1);
    assertTrue(firstSale.equals(secondSale));
  }

  @Test
  public void save_returnsTrueIfAllTheSame() {
    Sale testSale = new Sale(1);
    testSale.save();
    assertTrue(Sale.all().get(0).equals(testSale));
  }

  @Test
  public void save_assignsIdToSale() {
    Sale testSale = new Sale(1);
    testSale.save();
    Sale savedSale = Sale.all().get(0);
    assertEquals(testSale.getId(), savedSale.getId());
  }

  @Test
  public void all_returnsAllInstancesOfSale_true() {
    Sale firstSale = new Sale(1);
    firstSale.save();
    Sale secondSale = new Sale(2);
    secondSale.save();
    assertEquals(true, Sale.all().get(0).equals(firstSale));
    assertEquals(true, Sale.all().get(1).equals(secondSale));
  }

  @Test
  public void find_returnsSaleWithSameId_secondSale() {
    Sale firstSale = new Sale(1);
    firstSale.save();
    Sale secondSale = new Sale(2);
    secondSale.save();
    assertEquals(Sale.find(secondSale.getId()), secondSale);
  }

  @Test
  public void save_savesItemIdIntoDB_true() {
    Item testItem = new Item("collar", 10, 5);
    testItem.save();
    Sale testSale = new Sale(testItem.getId());
    testSale.save();
    Sale savedSale = Sale.find(testSale.getId());
    assertEquals(savedSale.getItemId(), testItem.getId());
  }

  @Test
  public void save_recordsTimeSoldInDatabase() {
    Sale testSale = new Sale(1);
    testSale.save();
    Timestamp savedSaleTimeSold = Sale.find(testSale.getId()).getTimeSold();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(rightNow.getDay(), savedSaleTimeSold.getDay());
  }

}
