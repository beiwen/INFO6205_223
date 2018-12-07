import main.Genotype;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GenotypeTest {
    Genotype tesGeno = new Genotype(255, 255);

    @Test
    public void crossOverTest() {
        ArrayList<Genotype> output = tesGeno.crossover(new Genotype(255, 255));
        assertEquals(2, output.size());
        assertNotSame(tesGeno, output.get(0));
        assertNotSame(tesGeno, output.get(1));
        assertNotSame(output.get(0), output.get(1));
    }

    @Test
    public void mutateTest() {
        Genotype mutated = tesGeno.mutation(new Genotype(255, 255));
        assertNotSame(tesGeno, mutated);
        assertTrue(this::mutaTestHelper);
        assertTrue(this::outBondIter);
    }

    private boolean mutaTestHelper() {
        for (int i = 0; i < 5; i++) {
            Genotype mutated = tesGeno.mutation(new Genotype(255, 255));
            if (!mutated.equals(tesGeno)) {
                return true;
            }
        }
        return false;
    }

    private boolean outBondHelper() {
        Genotype mutated = tesGeno.mutation(new Genotype(255, 255));
        return mutated.getOne().getVertical() > 255 || mutated.getOne().getHorizontal() > 255 || mutated.getTwo().getHorizontal() > 255 ||
        mutated.getTwo().getVertical() > 255 || mutated.getThree().getVertical() > 255 || mutated.getThree().getHorizontal() > 255 ||
        mutated.getColorChromo().getBlue() > 255 || mutated.getColorChromo().getGreen() > 255 || mutated.getColorChromo().getRed() > 255 ||
        mutated.getColorChromo().getAlpha() > 255;
    }

    private boolean outBondIter() {
        for (int i = 0; i < 100; i++) {
            if (outBondHelper()) {
                return false;
            }
        }
        return true;
    }
}
