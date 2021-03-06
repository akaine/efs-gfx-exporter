import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;

import org.apache.commons.io.IOUtils;

import com.ontalsoft.efs.exporter.GfxFileSpecs;
import com.ontalsoft.efs.exporter.io.BmpWriter;
import com.ontalsoft.efs.exporter.io.GfxReader;

public class TestAny {

    public static final String basePath = "e:/_code/_projects/efs_phoenix/EFS";

    final static byte[][] rgbPalette = getRgbPalette();

    public static void main(final String[] args) throws IOException {

        //exportFile(GfxFileSpecs.TERRAIN_SHIELD_EFFECT);

        EnumSet.allOf(GfxFileSpecs.class).forEach(gfxFileSpecs -> {
            exportFile(gfxFileSpecs);
        });
    }

    private static void exportFile(final GfxFileSpecs gfxFileSpecs) {
        try {
            final GfxReader reader = new GfxReader(gfxFileSpecs, basePath, rgbPalette);
            final BmpWriter writer = new BmpWriter(reader);
            writer.write();
        }
        catch(final Exception e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Reads RGB palette matrix from EFS.PAL.
     *
     * @return RGB palette bytes
     */
    private static byte[][] getRgbPalette() {
        byte[][] rgbBytes = null;
        try(final InputStream is = TestAny.class.getResourceAsStream("EFS.PAL")) {

            final byte[] binPalleteBytes = IOUtils.toByteArray(is);

            rgbBytes = new byte[3][256];
            for(int i = 0; i <= 255; i++) {
                rgbBytes[0][i] = (byte)(4 * binPalleteBytes[3 * i]); //red
                rgbBytes[1][i] = (byte)(4 * binPalleteBytes[3 * i + 1]); //green
                rgbBytes[2][i] = (byte)(4 * binPalleteBytes[3 * i + 2]); //blue
            }
        }
        catch(final Exception e) {
            e.printStackTrace();
        }
        return rgbBytes;
    }
}
