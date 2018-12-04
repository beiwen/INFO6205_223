import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    public Canvas genNextGeneration() {
        //TODO, need to continue implementing!!!:
        List<Triangle> selectedCandidates = selection(this.triangles);
        for(int i = selectedCandidates.size() - 1; i >= 1; i-=2) {
            ThreadLocalRandom tlr = ThreadLocalRandom.current();
            int pairOneIndex = tlr.nextInt(0, i);
            int pairTwoIndex = tlr.nextInt(0, i - 1);
            Triangle pairOne =selectedCandidates.get(pairOneIndex);
            Triangle pairTwo = selectedCandidates.get(pairTwoIndex);
            List<Triangle> childrenTriangles = pairOne.reproduction(pairTwo);
        }
        return this;
    }

    private double calMatchRate() {
        //TODO
        return 0;
    }

    private List<Triangle> selection(List<Triangle> triangles) {
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

        return Arrays.asList((Triangle[]) minHeap.toArray());
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
