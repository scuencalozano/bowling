package bowling;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FrameTest {

    Frame frame;

    @Before
    public void setUp(){
        frame = new Frame("10");
    }

    @Test
    public void shouldVerifyFrameIsStrike() {
        assertTrue(frame.isStrike());
    }

    @Test
    public void shouldVerifyFrameIsSpare() {
        frame.setPinfallA("7");
        frame.setPinfallB("3");
        assertTrue(frame.isSpare());
        assertFalse(frame.isStrike());
    }
}
