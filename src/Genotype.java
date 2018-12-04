import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Genotype {
    private Coordinate one;
    private Coordinate two;
    private Coordinate three;
    private ColorChromo colorChromo;
    private double maxMutateRate = 0.08;
    private double midMutateRate = 0.3;
    private double minMutateRate = 0.8;
    private ThreadLocalRandom tlr = ThreadLocalRandom.current();

    public Genotype(int width, int height){
        this.one = new Coordinate(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.two = new Coordinate(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.three = new Coordinate(tlr.nextInt(0, width + 1), tlr.nextInt(0, height + 1));
        this.colorChromo = new ColorChromo();
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

    public ColorChromo getColorChromo() {
        return colorChromo;
    }

    public void setColorChromo(ColorChromo colorChromo) {
        this.colorChromo = colorChromo;
    }

    // Implement:
    public ArrayList<Genotype> crossover(Genotype pair){
        
        return new ArrayList<>();
    }

    // To do, get called by crossover
    private Genotype mutation(Genotype target) {

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
        if(randomforCorlorA <= maxMutateRate) {
            target.colorChromo.alpha = tlr.nextInt(0, 256);
        } else if(randomforCorlorA > maxMutateRate && randomforCorlorA <= midMutateRate) {
            target.colorChromo.alpha = Math.min(Math.max(0, target.colorChromo.alpha + tlr.nextInt(-30, 31)), 255);
        } else if(randomforCorlorA <= minMutateRate && randomforCorlorA > midMutateRate) {
            target.colorChromo.alpha = Math.min(Math.max(0, target.colorChromo.alpha + tlr.nextInt(-10, 11)), 255);
        }
        return target;
    }



    class Coordinate {
        private int vertical;
        private int horizontal;

        public Coordinate(int ver, int hor) {
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
            this.alpha = tlr.nextInt(0, 256);

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
