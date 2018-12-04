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


    public Triangle[] selection(List<Triangle> triangles) {
        int numberOfSelected = (int)(triangles.size() * fractionForNextGeneration);
        PriorityQueue<Triangle> minHeap = new PriorityQueue<>(numberOfSelected, new MyComparator());
        for(Triangle triangle : triangles) {
            if(minHeap.size() != numberOfSelected) {
                minHeap.offer(triangle);
            } else {
                if(triangle.getFitness() > minHeap.peek().getFitness()) {
                    minHeap.poll();
                    minHeap.offer(triangle);
                }
            }
        }

        return (Triangle[]) minHeap.toArray();
    }

    static class MyComparator implements Comparator<Triangle> {
        @Override
        public int compare(Triangle o1, Triangle o2) {
            if(o1.getFitness() == o2.fitness) {
                return 0;
            }
            return o1.getFitness() < o2.getFitness()? -1 : 1;
        }
    }

}
