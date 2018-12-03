import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Triangle {
    double maxMutateRate;
    double midMutateRate;
    double minMutateRate;

    Coordinate one;
    Coordinate two;
    Coordinate three;

    Color color;
    Graphics2D gr2d;

    public Triangle(int width, int height) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        this.one = new Coordinate(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.two = new Coordinate(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.three = new Coordinate(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));

        this.color = new Color();
    }


    private void mutate(Triangle parent) {
        //TODO
    }

    private Graphics2D draw() {
        //TODO
        return this.gr2d;
    }

    class Coordinate {
        int vertical;
        int horizontal;

        public Coordinate(int ver, int hor) {
            this.vertical = ver;
            this.horizontal = hor;
        }
    }
}
