package main;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Genotype {
    private CoordinateChromo one;
    private CoordinateChromo two;
    private CoordinateChromo three;
    private ColorChromo colorChromo;
    private final double maxMutateRate = 0.08;
    private final double midMutateRate = 0.3;
    private final double minMutateRate = 0.8;
    private final int fecundityOfmating = 2;
    private ThreadLocalRandom tlr = ThreadLocalRandom.current();

    public Genotype(int width, int height){
        this.one = new CoordinateChromo(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.two = new CoordinateChromo(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.three = new CoordinateChromo(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.colorChromo = new ColorChromo();
    }

    public Genotype() {

    }

    public CoordinateChromo getOne() {
        return one;
    }

    public void setOne(CoordinateChromo one) {
        this.one = one;
    }

    public CoordinateChromo getTwo() {
        return two;
    }

    public void setTwo(CoordinateChromo two) {
        this.two = two;
    }

    public CoordinateChromo getThree() {
        return three;
    }

    public void setThree(CoordinateChromo three) {
        this.three = three;
    }

    public ColorChromo getColorChromo() {
        return colorChromo;
    }

    public void setColorChromo(ColorChromo colorChromo) {
        this.colorChromo = colorChromo;
    }

    // Implement:
    public ArrayList<Genotype> crossover(Genotype pair){
        ArrayList<Genotype> children = new ArrayList<>();
        for(int i = 0; i < fecundityOfmating; i++) {
            Genotype child = new Genotype();
            int random = tlr.nextInt(0, 2);
            if(random == 0) {
                child.setOne(this.getOne());
            } else {
                child.setOne(pair.getOne());
            }
            random = tlr.nextInt(0, 2);
            if(random == 0) {
                child.setTwo(this.getTwo());
            } else {
                child.setTwo(pair.getTwo());
            }
            random = tlr.nextInt(0, 2);
            if(random == 0) {
                child.setThree(this.getThree());
            } else {
                child.setThree(pair.getThree());
            }
            random = tlr.nextInt(0, 2);
            if(random == 0) {
                child.setColorChromo(this.getColorChromo());
            } else {
                child.setColorChromo(pair.getColorChromo());
            }
            children.add(child);
        }
        return children;
    }

    // To do, get called by crossover
    private boolean mutateOrNot(double rate) {
        return rate > tlr.nextDouble();
    }

    public Genotype mutation(Genotype parent) {
        Genotype mutationResult = new Genotype(255,255);
//        mutationResult.setOne(new CoordinateChromo(parent.getOne().getHorizontal(), parent.getOne().getVertical()));
//        mutationResult.setTwo(new CoordinateChromo(parent.getTwo().getHorizontal(), parent.getTwo().getVertical()));
//        mutationResult.setThree(new CoordinateChromo(parent.getThree().getHorizontal(), parent.getThree().getVertical()));
//        mutationResult.setColorChromo(new ColorChromo(parent.getColorChromo().getRed(), parent.getColorChromo().getGreen(),
//                                                      parent.getColorChromo().getBlue(), parent.getColorChromo().getAlpha()));
        if(mutateOrNot(maxMutateRate)) {
           mutationResult.getOne().setHorizontal(tlr.nextInt(0, 256));
           mutationResult.getOne().setVertical(tlr.nextInt(0, 256));
        }
        if(mutateOrNot(midMutateRate)) {
           mutationResult.getOne().setHorizontal(Math.min(Math.max(0, parent.getOne().getHorizontal() + tlr.nextInt(-15, 16)), 255));
           mutationResult.getOne().setVertical(Math.min(Math.max(0, parent.getOne().getVertical() + tlr.nextInt(-15, 16)), 255));
        }
        if(mutateOrNot(minMutateRate)) {
           mutationResult.getOne().setHorizontal(Math.min(Math.max(0, parent.getOne().getHorizontal() + tlr.nextInt(-3, 4)), 255));
           mutationResult.getOne().setHorizontal(Math.min(Math.max(0, parent.getOne().getVertical() + tlr.nextInt(-3, 4)), 255));
        }
        if(mutateOrNot(maxMutateRate)) {
           mutationResult.getTwo().setHorizontal(tlr.nextInt(0, 256));
           mutationResult.getTwo().setVertical(tlr.nextInt(0, 256));
        }
        if(mutateOrNot(midMutateRate)) {
           mutationResult.getTwo().setHorizontal(Math.min(Math.max(0, parent.getTwo().getHorizontal() + tlr.nextInt(-15, 16)), 255));
           mutationResult.getTwo().setVertical(Math.min(Math.max(0, parent.getTwo().getVertical()+ tlr.nextInt(-15, 16)), 255));
        }
        if(mutateOrNot(minMutateRate)) {
           mutationResult.getTwo().setHorizontal(Math.min(Math.max(0, parent.getTwo().getHorizontal() + tlr.nextInt(-3, 4)), 255));
           mutationResult.getTwo().setVertical(Math.min(Math.max(0, parent.getTwo().getVertical() + tlr.nextInt(-3, 4)), 255));
        }

        if(mutateOrNot(maxMutateRate)) {
           mutationResult.getThree().setHorizontal(tlr.nextInt(0, 256));
           mutationResult.getThree().setVertical(tlr.nextInt(0, 256));
        }
        if(mutateOrNot(midMutateRate)) {
           mutationResult.getThree().setHorizontal(Math.min(Math.max(0, parent.getThree().getHorizontal() + tlr.nextInt(-15, 16)), 255));
           mutationResult.getThree().setVertical(Math.min(Math.max(0, parent.getThree().getVertical() + tlr.nextInt(-15, 16)), 255));
        }
        if(mutateOrNot(minMutateRate)) {
           mutationResult.getThree().setHorizontal(Math.min(Math.max(0, parent.getThree().getHorizontal() + tlr.nextInt(-3, 4)), 255));
           mutationResult.getThree().setVertical(Math.min(Math.max(0, parent.getThree().getVertical() + tlr.nextInt(-3, 4)), 255));
        }

        // corlor
        if(mutateOrNot(maxMutateRate)) {
            mutationResult.getColorChromo().setRed(tlr.nextInt(0, 256));
        }
        if(mutateOrNot(midMutateRate)) {
            mutationResult.getColorChromo().setRed(Math.min(Math.max(0, parent.getColorChromo().getRed() + tlr.nextInt(-30, 31)), 255));
        }
        if(mutateOrNot(minMutateRate)) {
            mutationResult.getColorChromo().setRed(Math.min(Math.max(0, parent.getColorChromo().getRed() + tlr.nextInt(-10, 11)), 255));
        }

        if(mutateOrNot(maxMutateRate)) {
            mutationResult.getColorChromo().setGreen(tlr.nextInt(0, 256));
        }
        if(mutateOrNot(midMutateRate)) {
            mutationResult.getColorChromo().setGreen(Math.min(Math.max(0, parent.getColorChromo().getGreen() + tlr.nextInt(-30, 31)), 255));
        }
        if(mutateOrNot(minMutateRate)) {
            mutationResult.getColorChromo().setGreen(Math.min(Math.max(0, parent.getColorChromo().getGreen() + tlr.nextInt(-10, 11)), 255));
        }

        if(mutateOrNot(maxMutateRate)) {
            mutationResult.getColorChromo().setBlue(tlr.nextInt(0, 256));
        }
        if(mutateOrNot(midMutateRate)) {
            mutationResult.getColorChromo().setBlue(Math.min(Math.max(0, parent.getColorChromo().getBlue() + tlr.nextInt(-30, 31)), 255));
        }
        if(mutateOrNot(minMutateRate)) {
            mutationResult.getColorChromo().setBlue(Math.min(Math.max(0, parent.getColorChromo().getBlue() + tlr.nextInt(-10, 11)), 255));
        }

        if(mutateOrNot(midMutateRate  )) {
            mutationResult.getColorChromo().setAlpha(tlr.nextInt(95, 116));
        }
        return mutationResult;
    }



    class CoordinateChromo {
        int vertical;
        int horizontal;

        public CoordinateChromo(int hor, int ver) {
            this.horizontal = hor;
            this.vertical = ver;
        }


        public int getVertical() {
            return vertical;
        }

        public void setVertical(int vertical) {
            this.vertical = vertical;
        }

        public int getHorizontal() {
            return horizontal;
        }

        public void setHorizontal(int horizontal) {
            this.horizontal = horizontal;
        }
    }

    class ColorChromo {
        private int red;
        private int green;
        private int blue;
        private int alpha;

        public ColorChromo() {
            this.red = tlr.nextInt(0, 256);
            this.green = tlr.nextInt(0, 256);
            this.blue = tlr.nextInt(0, 256);
            this.alpha = tlr.nextInt(95, 116);
        }

        public ColorChromo(int red, int green, int bule, int alpha) {
            this.red = red;
            this.green = green;
            this.blue = bule;
            this.alpha = alpha;
        }

        public int getRed() {
            return red;
        }

        public void setRed(int red) {
            this.red = red;
        }

        public int getGreen() {
            return green;
        }

        public void setGreen(int green) {
            this.green = green;
        }

        public int getBlue() {
            return blue;
        }

        public void setBlue(int blue) {
            this.blue = blue;
        }

        public int getAlpha() {
            return alpha;
        }

        public void setAlpha(int alpha) {
            this.alpha = alpha;
        }
    }

}
