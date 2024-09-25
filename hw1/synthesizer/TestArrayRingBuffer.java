package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test(timeout = 1000)
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Integer>(10);
        assert (arb.isEmpty());
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        assert (arb.isFull());
        for (int i = 0; i < 5; i++) {
            assertEquals(arb.dequeue(), i);
        }
        for (int i = 10; i < 15; i++) {
            arb.enqueue(i);
        }
        assertFalse(arb.isEmpty());
        for (int i = 0; i < 114; i++) {
            assertEquals(arb.peek(), 5);
        }
        for (Object ne : arb) {
            assertEquals(ne, arb.dequeue());
        }
        assertEquals(arb.fillCount(), 10);
        for (int i = 0; i < 10; i++) {
            assertEquals(arb.dequeue(), i + 5);
        }
        assert (arb.isEmpty());
    }

    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
