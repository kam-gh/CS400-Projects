import java.util.NoSuchElementException;

/**
 * This class represents a HashtableMap object.
 * 
 * @param <KeyType>   the type of the key
 * @param <ValueType> the type of the value
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  // instance variables
  protected HashHelper<KeyType, ValueType>[] arr;
  protected int numElements;
  @SuppressWarnings({"unchecked", "rawtypes"})
  protected HashHelper<KeyType, ValueType> sentinelVal = new HashHelper(-1, -1);

  // constructors
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    this.arr = new HashHelper[capacity];
    this.numElements = 0;
  }

  @SuppressWarnings("unchecked")
  public HashtableMap() {
    // with default capacity = 8
    this.arr = new HashHelper[8];
    this.numElements = 0;
  }

  /**
   * Adds a new key-value pair/mapping to this collection, and rehashes the array if the current
   * array capacity is too small.
   * 
   * @throws IllegalArgumentException when key is null or duplicate of one already stored
   */
  @Override
  public void put(KeyType key, ValueType value) throws IllegalArgumentException {
    // check if key is null
    if (key == null) {
      throw new IllegalArgumentException("Key is null.");
    }

    // check if there is a duplicate key
    for (int i = 0; i < arr.length; i++) {
      if (this.arr[i] == null) {
        continue;
      } else if (this.arr[i].getKey().equals(key)) {
        throw new IllegalArgumentException("Duplicate key.");
      }
    }

    // create index from custom hash function
    int hashIndex = hashFunction(key, arr.length);

    // check if index calculated is taken, if so, use linear probing
    while (this.arr[hashIndex] != null) {
      hashIndex++;
      hashIndex = hashIndex % arr.length;
    }

    this.arr[hashIndex] = new HashHelper<>(key, value);

    this.numElements++;

    // check if we need to resize our array
    double loadFactor = this.getSize() / (double) this.getCapacity();

    if (loadFactor >= 0.70) {
      // rehash
      HashHelper<KeyType, ValueType>[] resized = new HashHelper[arr.length * 2];

      for (HashHelper<KeyType, ValueType> item : arr) {
        if (item != null) {
          int rehashIndex = hashFunction(item.getKey(), resized.length);

          while (resized[rehashIndex] != null) {
            rehashIndex++;
            rehashIndex = rehashIndex % arr.length;
          }

          resized[rehashIndex] = item;
        }
      }
      this.arr = resized;
    }
  }

  /**
   * This is a helper method for calculating the hash function.
   * 
   * @param key      the key of the HashHelper object
   * @param capacity the size of the array
   * @return the hash function of the given key
   */
  private int hashFunction(KeyType key, int capacity) {
    return Math.abs(key.hashCode()) % capacity;
  }

  /**
   * Checks whether a key maps to a value within this collection
   * 
   * @return true if it does, false if it doesn't
   */
  @Override
  public boolean containsKey(KeyType key) {

    for (int i = 0; i < arr.length; i++) {
      if (this.arr[i] == null) {
        continue;
      } else if (this.arr[i].getKey().equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieves the specific value that a key maps to
   * 
   * @throws NoSuchElementException when key is not stored in this collection
   * @returns the value of that the key maps to
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {

    for (int i = 0; i < arr.length; i++) {
      if (this.arr[i] == null) {
        continue;
      } else if (this.arr[i].getKey().equals(key)) {
        return this.arr[i].getValue();
      }
    }
    throw new NoSuchElementException("Key is not stored in this collection.");

  }

  /**
   * Removes the mapping for a given key from this collection
   * 
   * @throws NoSuchElementException when key is not stored in this collection
   * @return the removed key
   */
  @Override
  public ValueType remove(KeyType key) throws NoSuchElementException {

    for (int i = 0; i < arr.length; i++) {
      if (this.arr[i] == null) {
        continue;
      } else if (this.arr[i].getKey().equals(key)) {
        ValueType toRemove = this.arr[i].getValue();

        this.arr[i] = sentinelVal;
        this.numElements--;
        return toRemove;
      }
    }
    throw new NoSuchElementException("Key is not stored in this collection.");

  }

  /**
   * Removes all key-value pairs from this collection
   */
  @Override
  public void clear() {

    for (int i = 0; i < arr.length; i++) {
      this.arr[i] = null;
    }
    this.numElements = 0;
  }

  /**
   * Retrieves the number of keys stored within this collection
   * 
   * @return size the number of keys stored
   */
  @Override
  public int getSize() {
    return numElements;
  }

  /**
   * Retrieves this collection's capacity (size of its underlying array)
   * 
   * @return the capacity
   */
  @Override
  public int getCapacity() {
    return arr.length;
  }
}
