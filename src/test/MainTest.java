
import main.MainGA;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    public void resizeTest() {
        BufferedImage origin =  new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
        BufferedImage after = MainGA.resize(origin, 100, 100);
        assertEquals(100, after.getWidth());
        assertEquals(100, after.getHeight());

        after = MainGA.resize(origin, 258, 300);
        assertEquals(258, after.getHeight());
        assertEquals(300, after.getWidth());
    }

}
