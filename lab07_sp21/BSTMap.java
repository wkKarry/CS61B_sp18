import bstmap.*;

import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.*;

import org.junit.Test;

public class BSTMap<K, V> implements Map61B<K, V> {

    public void clear() {
    }


    public boolean containsKey(K key) {
        return false;
    }


    public V get(K key) {
        return null;
    }


    public int size() {
        return 0;
    }


    public void put(K key, V value) {
        return;
    }

    public V remove(K key) {
        return null;
    }

    public void printInOrder() {
        return;
    }

    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    private class Iterator implements java.util.Iterator {
        public boolean hasNext() {
            return false;
        }

        ;

        public K next() {
            return null;
        }

        ;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
