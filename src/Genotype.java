import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Genotype {
    private Coordinate one;
    private Coordinate two;
    private Coordinate three;
    private Color color;

    public Genotype(int width, int height){
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        this.one = new Coordinate(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.two = new Coordinate(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.three = new Coordinate(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.color = new Color();
    }

    public Coordinate getOne() {
        return one;
    }

    public void setOne(Coordinate one) {
        this.one = one;
    }

    public Coordinate getTwo() {
        return two;
    }

    public void setTwo(Coordinate two) {
        this.two = two;
    }

    public Coordinate getThree() {
        return three;
    }

    public void setThree(Coordinate three) {
        this.three = three;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // Implement:
    public ArrayList<Genotype> crossover(Genotype pair){
        return new ArrayList<>();
    }

    // To do, get called by crossover
    private Genotype mutation(Genotype target) {
        return new Genotype(2,3);
    }

    class Coordinate {
        private int vertical;
        private int horizontal;

        public Coordinate(int ver, int hor) {
            this.vertical = ver;
            this.horizontal = hor;
        }
    }
}
