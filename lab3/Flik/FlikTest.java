
import static org.junit.Assert.*;
import org.junit.Test;
import static Flik.Flik.*;

public class FlikTest {
    @Test
    public void test() {
        assertEquals(1,1);
        assert(isSameNumber(1,1));
        assertFalse(isSameNumber(1,2));
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        assertTrue(isSameNumber(a,b));
        assertTrue(isSameNumber(1,a));
        assertTrue(isSameNumber(a,1));
    }
}
