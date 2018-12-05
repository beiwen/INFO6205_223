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
    public Genotype mutation(Genotype target) {

        double randomforCoordinateOne = tlr.nextDouble();
        if(randomforCoordinateOne <= maxMutateRate) {
           target.one.horizontal = tlr.nextInt(0, 256);
           target.one.vertical = tlr.nextInt(0, 256);
        } else if(randomforCoordinateOne > maxMutateRate && randomforCoordinateOne <= midMutateRate) {
           target.one.horizontal = Math.min(Math.max(0, target.one.horizontal + tlr.nextInt(-15, 16)), 255);
           target.one.vertical = Math.min(Math.max(0, target.one.vertical + tlr.nextInt(-15, 16)), 255);
        } else if(randomforCoordinateOne <= minMutateRate && randomforCoordinateOne > midMutateRate) {
           target.one.horizontal = Math.min(Math.max(0, target.one.horizontal + tlr.nextInt(-3, 4)), 255);
           target.one.vertical = Math.min(Math.max(0, target.one.vertical + tlr.nextInt(-3, 4)), 255);
        }

        double randomforCoordinateTwo = tlr.nextDouble();
        if(randomforCoordinateTwo <= maxMutateRate) {
           target.two.horizontal = tlr.nextInt(0, 256);
           target.two.vertical = tlr.nextInt(0, 256);
        } else if(randomforCoordinateTwo > maxMutateRate && randomforCoordinateTwo <= midMutateRate) {
           target.two.horizontal = Math.min(Math.max(0, target.two.horizontal + tlr.nextInt(-15, 16)), 255);
           target.two.vertical = Math.min(Math.max(0, target.two.vertical + tlr.nextInt(-15, 16)), 255);
        } else if(randomforCoordinateTwo <= minMutateRate && randomforCoordinateTwo > midMutateRate) {
           target.two.horizontal = Math.min(Math.max(0, target.two.horizontal + tlr.nextInt(-3, 4)), 255);
           target.two.vertical = Math.min(Math.max(0, target.two.vertical + tlr.nextInt(-3, 4)), 255);
        }

        double randomforCoordinateThree = tlr.nextDouble();
        if(randomforCoordinateThree <= maxMutateRate) {
           target.three.horizontal = tlr.nextInt(0, 256);
           target.three.vertical = tlr.nextInt(0, 256);
        } else if(randomforCoordinateThree > maxMutateRate && randomforCoordinateThree <= midMutateRate) {
           target.three.horizontal = Math.min(Math.max(0, target.three.horizontal + tlr.nextInt(-15, 16)), 255);
           target.three.vertical = Math.min(Math.max(0, target.three.vertical + tlr.nextInt(-15, 16)), 255);
        } else if(randomforCoordinateThree <= minMutateRate && randomforCoordinateThree > midMutateRate) {
           target.three.horizontal = Math.min(Math.max(0, target.three.horizontal + tlr.nextInt(-3, 4)), 255);
           target.three.vertical = Math.min(Math.max(0, target.three.vertical + tlr.nextInt(-3, 4)), 255);
        }

        // corlor
        double randomforCorlorR = tlr.nextDouble();
        if(randomforCorlorR <= maxMutateRate) {
            target.colorChromo.red = tlr.nextInt(0, 256);
        } else if(randomforCorlorR > maxMutateRate && randomforCorlorR <= midMutateRate) {
            target.colorChromo.red = Math.min(Math.max(0, target.colorChromo.red + tlr.nextInt(-30, 31)), 255);
        } else if(randomforCorlorR <= minMutateRate && randomforCorlorR > midMutateRate) {
            target.colorChromo.red = Math.min(Math.max(0, target.colorChromo.red + tlr.nextInt(-10, 11)), 255);
        }

        double randomforCorlorG = tlr.nextDouble();
        if(randomforCorlorG <= maxMutateRate) {
            target.colorChromo.green = tlr.nextInt(0, 256);
        } else if(randomforCorlorG > maxMutateRate && randomforCorlorG <= midMutateRate) {
            target.colorChromo.green = Math.min(Math.max(0, target.colorChromo.green + tlr.nextInt(-30, 31)), 255);
        } else if(randomforCorlorG <= minMutateRate && randomforCorlorG > midMutateRate) {
            target.colorChromo.green = Math.min(Math.max(0, target.colorChromo.green + tlr.nextInt(-10, 11)), 255);
        }

        double randomforCorlorB = tlr.nextDouble();
        if(randomforCorlorB <= maxMutateRate) {
            target.colorChromo.blue = tlr.nextInt(0, 256);
        } else if(randomforCorlorB > maxMutateRate && randomforCorlorB <= midMutateRate) {
            target.colorChromo.blue = Math.min(Math.max(0, target.colorChromo.blue + tlr.nextInt(-30, 31)), 255);
        } else if(randomforCorlorB <= minMutateRate && randomforCorlorB > midMutateRate) {
            target.colorChromo.blue = Math.min(Math.max(0, target.colorChromo.blue + tlr.nextInt(-10, 11)), 255);
        }

        double randomforCorlorA = tlr.nextDouble();
        if(randomforCorlorA <= midMutateRate) {
            target.colorChromo.alpha = tlr.nextInt(95, 115);
        }
        return target;
    }



    class CoordinateChromo {
        private int vertical;
        private int horizontal;

        public CoordinateChromo(int ver, int hor) {
            this.vertical = ver;
            this.horizontal = hor;
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
