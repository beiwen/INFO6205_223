package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Triangle extends Polygon {

    private BufferedImage target;
    private double fitness;
    private Genotype genotype;
    private Color color;
    private final double mutateRate = 0.01;

    public Triangle(Genotype gene, BufferedImage target) {
        this.npoints = 3;
        this.xpoints = new int[3];
        this.ypoints = new int[3];

        this.xpoints[0] = gene.getOne().getHorizontal();
        this.ypoints[0] = gene.getOne().getVertical();

        this.xpoints[1] = gene.getTwo().getHorizontal();
        this.ypoints[1] = gene.getTwo().getVertical();

        this.xpoints[2] = gene.getThree().getHorizontal();
        this.ypoints[2] = gene.getThree().getVertical();

        getColorFromGene(gene);
        this.genotype = gene;
        this.target = target;
        this.fitness = calFitness(target);
    }

    private void getColorFromGene(Genotype gene) {
        float r = (float) (gene.getColorChromo().getRed() / 255.0);
        float g = (float) (gene.getColorChromo().getGreen() / 255.0);
        float b = (float) (gene.getColorChromo().getBlue() / 255.0);
        float a = (float) (gene.getColorChromo().getAlpha() / 255.0);

        this.color = new Color(r, g, b, a);
    }

    public double calFitness(BufferedImage target) {
        double res = 0;
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                if (this.contains(x, y)) {
                    res += calFitHelper(target, x, y);
                }
            }
        }
        return -res;
    }

    private double calFitHelper(BufferedImage target, int x, int y) {
        int tarColor = target.getRGB(x, y);
//        int tarA = (tarColor >> 24) & 0xFF;
//        int tarR = (tarColor >> 16) & 0xFF;
//        int tarG = (tarColor >> 8) & 0xFF;
//        int tarB = tarColor & 0xFF;

//        int tarA = (tarColor & 0xFF) >> 24;
        int tarR = (tarColor & 0xFF0000) >> 16;
        int tarG = (tarColor & 0xFF00) >> 8;
        int tarB = tarColor & 0xFF;

//        int a = genotype.getColorChromo().getAlpha();
        int r = this.genotype.getColorChromo().getRed();
        int g = this.genotype.getColorChromo().getGreen();
        int b = this.genotype.getColorChromo().getBlue();

        double tmp = Math.pow((r - tarR), 2) + Math.pow((g - tarG), 2) + Math.pow((b - tarB), 2);
        return tmp;
    }

    public List<Triangle> reproduction(Triangle pair) {
        List<Triangle> res = new ArrayList<>();

        // mutation
        if (pair == null) {
            ThreadLocalRandom tlr = ThreadLocalRandom.current();
            double mutationOrNot = tlr.nextDouble();
            if(mutationOrNot < mutateRate) {
                Genotype mutationChild = this.genotype.mutation(this.genotype);
                res.add(new Triangle(mutationChild, target));
                return res;
            } else {
                return null;
            }
        }

        // crossover
        List<Genotype> genes = pair.genotype.crossover(this.genotype);
        for (Genotype gene: genes) {
            res.add(new Triangle(gene, target));
        }

        res.add(new Triangle(this.genotype, target));
        res.add(new Triangle(pair.genotype, target));
        Collections.sort(res, new Comparator<Triangle>() {
            @Override
            public int compare(Triangle tri1, Triangle tri2) {
                if (tri1.fitness == tri2.fitness) {
                    return 0;
                }
                return tri2.fitness < tri1.fitness ? -1 : 1;
            }
        });
        return res.subList(0, 2);
    }


    public Triangle mutation(Triangle parent) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        double mutationOrNot = tlr.nextDouble();
        if(mutationOrNot < mutateRate) {
            Genotype mutationChild = parent.getGenotype().mutation(parent.getGenotype());
            return new Triangle(mutationChild, target);
        } else {
            return parent;
        }
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }


    public Genotype getGenotype() {
        return genotype;
    }

    public void setGenotype(Genotype genotype) {
        this.genotype = genotype;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
