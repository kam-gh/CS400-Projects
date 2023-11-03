/**
 * This is a helper class to group together key-value pairs within a single object so that you can
 * store these pairs within your hashtable's array.
 *
 * @param <KeyType>   the type of the key
 * @param <ValueType> the type of the value
 */
public class HashHelper<KeyType, ValueType> {

  // instance variables
  private KeyType key;
  private ValueType value;

  /**
   * Constructor of a HashHelper object
   * 
   * @param key   the given key
   * @param value the given value
   */
  public HashHelper(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Gets the key
   * 
   * @return the key
   */
  public KeyType getKey() {
    return this.key;
  }

  /**
   * Gets the value
   * 
   * @return the value
   */
  public ValueType getValue() {
    return this.value;
  }
}
