import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Canvas {
    private final int width_one = 255;
    private final int height_one = 255;
    private BufferedImage target;
    private final double fractionForNextGeneration = 0.5;
    double matchRate;
    List<Triangle> triangles;

    public Canvas(BufferedImage target, List<Triangle> candidates) {
        this.matchRate = 0;
        this.target = target;
        if(candidates == null) {
            this.triangles = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                triangles.add(new Triangle(new Genotype(width_one, height_one), target));
            }
        } else {
            this.triangles = candidates;
        }
        for (Triangle tri: triangles) {
            matchRate += tri.fitness;
        }
    }

    public Canvas genNextGeneration() {
        List<Triangle> selectedCandidates = selection(this.triangles);
        List<Triangle> nextCandidates = new ArrayList<>();
        while(!selectedCandidates.isEmpty()) {
            ThreadLocalRandom tlr = ThreadLocalRandom.current();
            int pairOneIndex = 0;
            int pairTwoIndex;
            if(selectedCandidates.size() == 2) {
                pairTwoIndex = 1;
            } else {
                pairTwoIndex = tlr.nextInt(1, selectedCandidates.size());
            }
            Triangle pairTwo = selectedCandidates.remove(pairTwoIndex);
            Triangle pairOne =selectedCandidates.remove(pairOneIndex);
            List<Triangle> childrenTriangles = pairOne.reproduction(pairTwo);
            nextCandidates.addAll(childrenTriangles);
        }
        Canvas newCanvas = new Canvas(target, nextCandidates);
        return newCanvas;
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

        return new LinkedList<>(Arrays.asList((minHeap.toArray(new Triangle[minHeap.size()]))));
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
