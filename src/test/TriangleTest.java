import main.Genotype;
import main.Triangle;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {

    @Test
    public void getColorTest() {
        Genotype tmp = new Genotype(255, 255);
        tmp.getColorChromo().setRed(20);
        tmp.getColorChromo().setGreen(40);
        tmp.getColorChromo().setBlue(60);

        Triangle testTri = new Triangle(tmp, new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB));

        assertEquals(testTri.getColor().getRed(), 20);
        assertEquals(testTri.getColor().getGreen(), 40);
        assertEquals(testTri.getColor().getBlue(), 60);
    }

    @Test
    public void testFit() {
        Genotype tmp = new Genotype(255, 255);
        tmp.getColorChromo().setRed(255);
        tmp.getColorChromo().setGreen(255);
        tmp.getColorChromo().setBlue(255);

        int rgb = Color.white.getRGB();
        BufferedImage bi = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0;  x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                bi.setRGB(x, y, rgb);
            }
        }
        Color c = new Color(bi.getRGB(0, 0));

        assertEquals(255, c.getRed());
        assertEquals(255, c.getGreen());
        assertEquals(255, c.getBlue());
    }

    @Test
    public void rerproductionTest() {
        Genotype tmp = new Genotype(255, 255);
        tmp.getColorChromo().setRed(255);
        tmp.getColorChromo().setGreen(255);
        tmp.getColorChromo().setBlue(255);
        Triangle testTri = new Triangle(tmp, new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB));

        List<Triangle> lis2 = testTri.reproduction(new Triangle(tmp, new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB)));

        assertEquals(2, lis2.size());
    }
}
