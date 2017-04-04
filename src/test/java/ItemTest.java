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

  @Test
  public void equals_returnsTrueIfPropertiesAreSame_true() {
    Item testItem = new Item("collar", 10, 5);
    Item anotherItem = new Item("collar", 10, 5);
    assertTrue(testItem.equals(anotherItem));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAreTheSame() {
    Item testItem = new Item("collar", 10, 5);
    testItem.save();
    assertTrue(Item.all().get(0).equals(testItem));
  }

  @Test
  public void save_assignsIdToItem() {
    Item testItem = new Item("collar", 10, 5);
    testItem.save();
    Item savedItem = Item.all().get(0);
    assertEquals(savedItem.getId(), testItem.getId());
  }

  @Test
  public void all_returnsAllInstancesOfItem_true() {
    Item firstItem = new Item("collar", 10, 5);
    firstItem.save();
    Item secondItem = new Item("leash", 12, 2);
    secondItem.save();
    assertEquals(true, Item.all().get(0).equals(firstItem));
    assertEquals(true, Item.all().get(1).equals(secondItem));
  }

  @Test
  public void find_returnsItemWithSameId_secondItem() {
    Item firstItem = new Item("collar", 10, 5);
    firstItem.save();
    Item secondItem = new Item("leash", 12, 2);
    secondItem.save();
    assertEquals(Item.find(secondItem.getId()), secondItem);
  }
}
