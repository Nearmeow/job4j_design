package ru.job4j.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iteratorTest() {
        Set<String> set = new SimpleSet<>();
        set.add("hello");
        set.add("world");
        Iterator<String> iterator = set.iterator();
        set.add("!");
        iterator.next();
    }

}