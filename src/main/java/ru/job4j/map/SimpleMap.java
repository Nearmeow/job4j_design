package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            return false;
        }
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        table[index] = entry;
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode % capacity;
    }

    private int indexFor(int hash) {
        return hash;
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity = capacity * 2;
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int newIndex = indexFor(hash(entry.key.hashCode()));
                newTable[newIndex] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        Integer index = getIndexIfFreeOrNull(key);
        if (index == null) {
            return null;
        }
        return table[index].value;
    }

    @Override
    public boolean remove(K key) {
        Integer index = getIndexIfFreeOrNull(key);
        if ((index == null)) {
            return false;
        }
        table[index] = null;
        count--;
        modCount++;
        return true;
    }

    private Integer getIndexIfFreeOrNull(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            return null;
        }
        return index;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int currentCount = 0;
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentCount < count;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[currentIndex] == null) {
                    currentIndex++;
                }
                currentCount++;
                return table[currentIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
