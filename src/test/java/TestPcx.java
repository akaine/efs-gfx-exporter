import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.pcx.PcxImageParser;

public class TestPcx {

    public static void main(final String[] args) throws IOException, ImageReadException {

        final PcxImageParser pcxImageParser = new PcxImageParser();
        final BufferedImage pcxImage = pcxImageParser.getBufferedImage(new File("e:\\_CODE\\_PROJECTS\\efs_phoenix\\EFS\\MANOWITZ\\BKIL01.PCX"), null);

        ImageIO.write(pcxImage, "bmp", new File("e:\\_CODE\\_PROJECTS\\efs_phoenix\\EFS\\MANOWITZ\\BKIL01.bmp"));
    }
}
