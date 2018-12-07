import main.Canvas;
import main.Genotype;
import main.Triangle;
import org.junit.Test;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class CanvasTest {
    @Test
    public void testGetNextGeneration() {
        BufferedImage dummyTar = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
        List<Triangle> tris = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tris.add(new Triangle(new Genotype(255,  255), dummyTar));
        }
        Canvas origin = new Canvas(dummyTar, tris);
        Canvas next = origin.genNextGeneration(origin.getTriangles());
        assertNotSame(origin, next);
    }

    @Test
    public void selectionTest() {
        BufferedImage dummyTar = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
        List<Triangle> tris = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tris.add(new Triangle(new Genotype(255,  255), dummyTar));
        }
        Canvas origin = new Canvas(dummyTar, tris);
        List<List<Triangle>> res = origin.selection(origin.getTriangles());
        assertEquals(2, res.size());
    }
}
