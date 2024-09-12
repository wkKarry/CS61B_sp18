import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.
    @Test
    public void testChars(){
        assertTrue(offByN.equalChars('1','6'));
        assertTrue(offByN.equalChars('a','f'));
        assertFalse(offByN.equalChars('e','e'));
        assertFalse(offByN.equalChars('&', '%'));
    }

}
