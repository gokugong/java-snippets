package weakreference;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * SoftRefenceCache
 * @param <K> The type of the key's.
 * @param <V> The type of the value's.
 */
public class SoftReferenceCache<K, V> {
    private final HashMap<K, SoftReference<V>> mCache;

    public SoftReferenceCache() {
        mCache = new HashMap<K, SoftReference<V>>();
    }

    /**
     * Put a new item in the cache. This item can be gone after a GC run.
     *
     * @param key
     *            The key of the value.
     * @param value
     *            The value to store.
     */
    public void put(K key, V value) {
        mCache.put(key, new SoftReference<V>(value));
    }

    /**
     * Retrieve a value from the cache (if available).
     *
     * @param key
     *            The key to look for.
     * @return The value if it's found. Return null if the key-value pair is not
     *         stored yet or the GC has removed the value from memory.
     */
    public V get(K key) {
        V value = null;

        SoftReference<V> reference = mCache.get(key);

        if (reference != null) {
            value = reference.get();
        }

        return value;
    }
}