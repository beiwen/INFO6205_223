import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Triangle extends Polygon {
    double maxMutateRate;
    double midMutateRate;
    double minMutateRate;

    Genotype genotype;
    Graphics2D gr2d;


    public Triangle() {
        this.npoints = 3;
        this.xpoints = new int[3];
        this.ypoints = new int[3];
    }


    private void mutate(Triangle parent) {
        //TODO
    }

    private Graphics2D draw() {
        //TODO
        return this.gr2d;
    }

}
