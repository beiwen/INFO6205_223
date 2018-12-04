import java.awt.image.BufferedImage;
import java.util.*;

public class Canvas {
    double mutateRate = 0.01;
    int width = 256;
    int height = 256;
    int width_one = 255;
    int height_one = 255;
    BufferedImage target;

    private final double fractionForNextGeneration = 0.5;

    double matchRate;

    List<Triangle> triangles;
    BufferedImage bi;

    public Canvas(BufferedImage target) {
        this.triangles = new ArrayList<>();
        this.matchRate = 0;
        this.target = target;
        for (int i = 0; i < 100; i++) {
            triangles.add(new Triangle(new Genotype(width_one, height_one), target));
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


    public ArrayList<Triangle> selection(List<Triangle> triangles) {
        int numberOfSelected = (int)(triangles.size() * fractionForNextGeneration);
        PriorityQueue<Triangle> minHeap = new PriorityQueue<>(numberOfSelected, new MyComparator<Triangle>());
        for(Triangle triangle : triangles) {
            if(minHeap.size() != numberOfSelected) {
                minHeap.offer(triangle);
            } else {

            }
        }

        return new ArrayList<>();

    }

    private static class MyComparator<Triangle> implements Comparator<Triangle> {
        @Override
        public int compare(Triangle o1, Triangle o2) {
            return 0;
        }
    }

}
