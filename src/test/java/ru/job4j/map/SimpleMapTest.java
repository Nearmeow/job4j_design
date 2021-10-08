package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void putToTHeMap() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(12, "December"));
        assertTrue(map.put(8, "August"));
        assertFalse(map.put(12, "What"));
    }

    @Test
    public void putToTHeMapWithExpand() {
        Map<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "January"));
        assertTrue(map.put(2, "February"));
        assertTrue(map.put(3, "March"));
        assertTrue(map.put(4, "April"));
        assertTrue(map.put(5, "May"));
        assertTrue(map.put(6, "June"));
        assertTrue(map.put(7, "July"));
        assertTrue(map.put(8, "August"));
        assertTrue(map.put(9, "September"));
        assertTrue(map.put(10, "October"));

    }

    @Test
    public void getFromMapSuccess() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(12, "December");
        assertThat(map.get(12), is("December"));
    }

    @Test
    public void getFromMapNull() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(12, "December");
        assertNull(map.get(11));
    }

    @Test
    public void removeFromMapTrue() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(12, "December");
        assertTrue(map.remove(12));
        assertNull(map.get(12));
    }

    @Test
    public void removeFromMapFalse() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(12, "December");
        assertFalse(map.remove(11));
    }

    @Test
    public void iteratorTest() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "January");
        map.put(2, "February");
        map.put(3, "March");
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorTestWithException() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "January");
        map.put(2, "February");
        map.put(3, "March");
        Iterator<Integer> iterator = map.iterator();
        map.put(4, "April");
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorTestWithException2() {
        Map<Integer, String> map = new SimpleMap<>();
        map.put(1, "January");
        map.put(2, "February");
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }

}