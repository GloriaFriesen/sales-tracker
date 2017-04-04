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

public class ItemTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Item_instantiatesCorrectly_true() {
    Item testItem = new Item("collar", 10, 5);
    assertEquals(true, testItem instanceof Item);
  }

  @Test
  public void Item_instantiatesWithName_String() {
    Item testItem = new Item("collar", 10, 5);
    assertEquals("collar", testItem.getName());
  }

  @Test
  public void Item_instantiatesWithPrice_int() {
    Item testItem = new Item("collar", 10, 5);
    assertEquals(10, testItem.getPrice());
  }

  @Test
  public void Item_instantiatesWithQuantity_int() {
    Item testItem = new Item("collar", 10, 5);
    assertEquals(5, testItem.getQuantity());
  }
}
