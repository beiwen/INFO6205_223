import java.awt.*;
import java.awt.image.BufferedImage;

public class Triangle extends Polygon {
    double maxMutateRate;
    double midMutateRate;
    double minMutateRate;

    Genotype genotype;
    Color color;
    Graphics2D gr2d;


    public Triangle(Genotype gene) {
        this.npoints = 3;
        this.xpoints = new int[3];
        this.ypoints = new int[3];

        this.xpoints[0] = gene.getOne().getVertical();
        this.ypoints[0] = gene.getOne().getHorizontal();

        this.xpoints[1] = gene.getTwo().getVertical();
        this.ypoints[1] = gene.getTwo().getHorizontal();

        this.xpoints[2] = gene.getThree().getVertical();
        this.ypoints[2] = gene.getThree().getHorizontal();

        getColorFromGene(gene);
        this.genotype = gene;

    }

    private void getColorFromGene(Genotype gene) {
        float r = gene.getColorChromo().getRed();
        float g = gene.getColorChromo().getGreen();
        float b = gene.getColorChromo().getBlue();
        float a = gene.getColorChromo().getAlpha();

        this.color = new Color(r, g, b, a);
    }

    private double calFitness(BufferedImage target) {
        double res = 0;
        for (int x = 0; i < 256; i++) {
            for (int y = 0; y < 256; y++) {
                if (this.contains(x, y)) {
                    res += calFitHelper(target, x, y);
                }
            }
        }
        return res;
    }

    private double calFitHelper(BufferedImage target, int x, int y) {
        int tarColor = target.getRGB(x, y);
        int tarA = (tarColor >> 24) & 0xFF;
        int tarR = (tarColor >> 16) & 0xFF;
        int tarG = (tarColor >> 8) & 0xFF;
        int tarB = tarColor & 0xFF;

        int a = genotype.getColorChromo().getAlpha();
        int r = genotype.getColorChromo().getRed();
        int g = genotype.getColorChromo().getGreen();
        int b = genotype.getColorChromo().getBlue();

        return Math.pow((a - tarA), 2) + Math.pow((r - tarR), 2) + Math.pow((g - tarG), 2) + Math.pow((b - tarB), 2);
    }

    private void mutate(Triangle parent) {
        //TODO
    }

    private Graphics2D draw() {
        //TODO
        return this.gr2d;
    }

}
