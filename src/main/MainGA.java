package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainGA {

    public static void main(String[] args) throws FileNotFoundException {
        BufferedImage target = null;
        try {
            BufferedImage tmp = ImageIO.read(new File("target.png"));
            target = resize(tmp, 256, 256);
        } catch (IOException e) {
            System.out.println("Cannot read target image.");
            e.printStackTrace();
        }

        Canvas canvas = new Canvas(target, null);
        int i = 0;

        while (true) {
            if (i % 1023 == 0) {
                BufferedImage output = canvas.bi;
                FileOutputStream pngOutPut = new FileOutputStream("res_generation_" + (i + 1) + ".png");
//                for (main.Triangle trg: canvas.triangles) {
//                    Graphics2D g2d = (Graphics2D) output.getGraphics();
//                    g2d.setColor(trg.color);
//                    g2d.fillPolygon(trg);
//                }
                try {
                    ImageIO.write(output, "png", pngOutPut);
                    pngOutPut.flush();
                    pngOutPut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Canvas next = canvas.genNextGeneration(canvas.getTriangles());
            System.out.println("Generation " + (i + 1) + " " + canvas.getMatchRate() + " " + next.getMatchRate());

            if (next.getMatchRate() > canvas.getMatchRate()) {
                canvas = next;
            }
//            canvas = next;
            i++;
        }
    }

    public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
