import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Canvas {
    double mutateRate = 0.01;
    int width = 256;
    int height = 256;
    int width_one = 255;
    int height_one = 255;

    private double fractionForNextGeneration = 0.5;

    double matchRate;

    List<Triangle> triangles;
    BufferedImage bi;

    public Canvas() {
        this.triangles = new ArrayList<>();
        this.matchRate = 0;

        for (int i = 0; i < 100; i++) {
            triangles.add(new Triangle(new Genotype(width_one, height_one)));
        }
    }

    public Canvas genNextGeneration(Canvas parent) {
        //TODO
        return this;
    }

    private double calMatchRate() {
        //TODO
        return 0;
    }

    //to do?
    public ArrayList<Triangle> selection(List<Triangle> triangles) {
        return new ArrayList<>();

    }
}
