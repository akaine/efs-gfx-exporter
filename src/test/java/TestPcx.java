import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.imaging.formats.bmp.BmpImageParser;
import org.apache.commons.imaging.formats.pcx.PcxImageParser;
import org.apache.commons.imaging.formats.pcx.PcxWriter;

public class TestPcx {

    public static void main(final String[] args) throws Exception {

        final PcxImageParser pcxImageParser = new PcxImageParser();
        final BufferedImage pcxImage = pcxImageParser.getBufferedImage(new File("e:\\_CODE\\_PROJECTS\\efs_phoenix\\EFS\\PCX\\AIPLAT.PCX"), null);

        ImageIO.write(pcxImage, "bmp", new File("e:\\_CODE\\_PROJECTS\\efs_phoenix\\EFS\\PCX\\AIPLAT.bmp"));

        final BmpImageParser bmpImageParser = new BmpImageParser();
        final BufferedImage bmpImage = bmpImageParser.getBufferedImage(new File("e:\\_CODE\\_PROJECTS\\efs_phoenix\\EFS\\PCX\\AIPLAT.bmp"), null);

        final PcxWriter pcxWriter = new PcxWriter(null);
        pcxWriter.writeImage(bmpImage, new FileOutputStream("e:\\_CODE\\_PROJECTS\\efs_phoenix\\EFS\\PCX\\AIPLAT1.PCX"));

    }
}
