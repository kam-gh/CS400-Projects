import java.util.NoSuchElementException;

/**
 * This class holds all the testers for the HashtableMap class.
 */
public class HashtableMapsTests {

  /**
   * This method tests the constructors of the HashtableMap class, as well as the getCapacity() and
   * getSize() methods.
   * 
   * @return true if the tests passed, false otherwise
   */
  public static boolean test1() {
    HashtableMap map = new HashtableMap(8);

    map.put(1, 1);

    if (map.getCapacity() != 8) {
      return false;
    }
    if (map.getSize() != 1) {
      return false;
    }
    return true;
  }

  /**
   * This method checks that the put() method resizes the array when necessary
   * 
   * @return true if the tests passed, false otherwise
   */
  public static boolean test2() {

    HashtableMap<Integer, String> map = new HashtableMap(4);
    map.put(3, "three");
    map.put(1, "one");
    for (HashHelper item : map.arr) {
      if (item != null)
        System.out.println(item.getValue());
      else
        System.out.println("null");
    }

    map.put(2, "two");
    System.out.println();

    for (HashHelper item : map.arr) {
      if (item != null)
        System.out.println(item.getValue());
      else
        System.out.println("null");
    }

    if (map.getCapacity() != 8) {
      return false;
    }


    return true;
  }

  /**
   * This method tests that an error is thrown if the key is null or there is a duplicate key in the
   * put() method
   * 
   * @return true if the tests passed, false otherwise
   */
  public static boolean test3() {

    HashtableMap map = new HashtableMap(8);

    try {
      map.put(null, null);
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("IllegalArgumentException successfully caught with null args.");
    }

    map.put(1, 1);
    try {
      map.put(1, 1);
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("IllegalArgumentException successfully caught with duplicate keys.");
    }

    return true;
  }

  /**
   * This method checks that the remove() method works as described
   * 
   * @return true if the tests passed, false otherwise
   */
  public static boolean test4() {

    HashtableMap map = new HashtableMap(5);
    map.put(1, 1);
    map.remove(1);

    if (map.getSize() != 0) {
      return false;
    }

    try {
      map.remove(5);
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("NoSuchElementException caught.");
    }

    return true;
  }

  /**
   * This method tests the containsKey() , clear(), and get() methods
   * 
   * @return true if the tests passed, false otherwise
   */
  public static boolean test5() {

    HashtableMap map = new HashtableMap(5);
    map.put(1, 1);
    map.put(2, 2);

    if (!map.get(1).equals(1)) {
      return false;
    }
    try {
      map.get(5);
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("NoSuchElementException successfully caught.");
    }

    if (map.containsKey(1) == false) {
      return false;
    }
    if (map.containsKey(5) == true) {
      return false;
    }

    map.clear();
    if (map.getSize() != 0) {
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println("Test 1: " + test1());
    System.out.println("Test 2: " + test2());
    System.out.println("Test 3: " + test3());
    System.out.println("Test 4: " + test4());
    System.out.println("Test 5: " + test5());
  }
}
