import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGA {

    public static void main(String[] args) {
        BufferedImage target = null;
        try {
            BufferedImage tmp = ImageIO.read(new File("target.png"));
            target = resize(tmp, 256, 256);
        } catch (IOException e) {
            System.out.println("Cannot read target image.");
            e.printStackTrace();
        }

        Canvas canvas = new Canvas();

        int i = 0;


        while (true) {
            if (i % 1024 == 0) {
                BufferedImage output = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
                File pngOutPut = new File("res_generation_" + i + 1);
                for (Triangle trg: canvas.triangles) {

                }
                try {
                    ImageIO.write(output, "png", pngOutPut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            canvas = canvas.genNextGeneration(canvas);
            i++;
        }
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
