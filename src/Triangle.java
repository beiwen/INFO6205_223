import java.awt.*;

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

    private void mutate(Triangle parent) {
        //TODO
    }

    private Graphics2D draw() {
        //TODO
        return this.gr2d;
    }

}
