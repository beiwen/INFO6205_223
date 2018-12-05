import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Canvas {
    private final int width_one = 255;
    private final int height_one = 255;
    private BufferedImage target;
    private final double fractionForNextGeneration = 0.1;
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
        List<List<Triangle>> selectedCandidates = selection(this.triangles);
        List<Triangle> nextCandidates = new ArrayList<>();
        for(Triangle triangle :selectedCandidates.get(0)) {
            List<Triangle> mutationChildren = triangle.reproduction(null);
            nextCandidates.addAll(mutationChildren);
        }
        List<Triangle> trianglesForCrossover = selectedCandidates.get(1);
        while(!trianglesForCrossover.isEmpty()) {
            ThreadLocalRandom tlr = ThreadLocalRandom.current();
            int pairOneIndex = 0;
            int pairTwoIndex = tlr.nextInt(1, trianglesForCrossover.size());
            Triangle pairTwo = trianglesForCrossover.remove(pairTwoIndex);
            Triangle pairOne = trianglesForCrossover.remove(pairOneIndex);
            List<Triangle> crossoverChildren = pairOne.reproduction(pairTwo);
            nextCandidates.addAll(crossoverChildren);
        }
        Canvas newCanvas = new Canvas(target, nextCandidates);
        return newCanvas;
    }

    private double calMatchRate() {
        //TODO
        return 0;
    }

    private List<List<Triangle>> selection(List<Triangle> triangles) {
        List<List<Triangle>> selectionResult = new ArrayList<>(2);
        int numberOfCrossover = (int)(triangles.size() * fractionForNextGeneration);
        PriorityQueue<Triangle> partForMutationPQ = new PriorityQueue<>(triangles.size() - numberOfCrossover, new MyComparator());
        List<Triangle> partForCrossover = new LinkedList<>();
        for(Triangle triangle : triangles) {
            if(partForMutationPQ.size() < triangles.size() - numberOfCrossover) {
                partForMutationPQ.offer(triangle);
            } else {
                if(triangle.getFitness() > partForMutationPQ.peek().getFitness()) {
                    partForCrossover.add(partForMutationPQ.poll());
                    partForMutationPQ.offer(triangle);
                } else {
                    partForCrossover.add(triangle);
                }
            }
        }
        List<Triangle> partForMuatation = new  LinkedList<>(Arrays.asList((partForMutationPQ.toArray(new Triangle[partForMutationPQ.size()]))));
        selectionResult.add(partForMuatation);
        selectionResult.add(partForCrossover);
        return selectionResult;
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
