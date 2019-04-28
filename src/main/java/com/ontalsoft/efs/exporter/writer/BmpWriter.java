package com.ontalsoft.efs.exporter.writer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

import com.ontalsoft.efs.exporter.GfxFileSpecs;
import com.ontalsoft.efs.exporter.bmp.BMPHeader;

public class BmpWriter extends GfxWriter {

    public BmpWriter(final String basePath, final GfxFileSpecs gfxFileSpecs, final byte[][] rgbPalette) {
        super(basePath, gfxFileSpecs, rgbPalette);
    }

    /**
     * https://engineering.purdue.edu/ece264/17au/hw/HW15
     */
    @Override
    public byte[][] write(final byte[][] gfxData) throws IOException {
        final int[] bmpDimmsConfig = getBmpDimmsConfig(gfxFileSpecs);
        final BMPHeader bmpHeader = new BMPHeader(bmpDimmsConfig[0], bmpDimmsConfig[1], rgbPalette);
        final byte[] bmpHeaderBytes = bmpHeader.getHeaderBytes();
        final int pad = bmpHeader.getPad();

        try(final RandomAccessFile raf = new RandomAccessFile(getFilePath(), "rw");) {

            raf.write(bmpHeaderBytes);

            final ByteBuffer bb = ByteBuffer.allocate(bmpDimmsConfig[0] * bmpDimmsConfig[1] + bmpDimmsConfig[1] * pad);

            final int startPos = bmpHeader.getOffBits();
            raf.seek(startPos);

            int row = 0;
            int inlineFrameIdx = 0;
            for(int i = 0; i < gfxFileSpecs.getFrameCount(); i++) {  // for each frame
                for(int j = 0; j < gfxFileSpecs.getFrameHeight(); j++) {  // for each line

                    // set cursor position to:
                    bb.position((bmpDimmsConfig[0] + pad) * gfxFileSpecs.getFrameHeight() * row  // after previous frame rows
                            + gfxFileSpecs.getFrameWidth() * inlineFrameIdx  // after previous inline frames
                            + (bmpDimmsConfig[0] + pad) * j);  // change scan line (don't forget the padding)

                    // put current frame line
                    bb.put(gfxData[i], gfxFileSpecs.getFrameWidth() * j, gfxFileSpecs.getFrameWidth());
                }

                // change inline frame index, after finishing copying the frame
                inlineFrameIdx++;

                // check if a new frames row should be created
                if((i + 1) % gfxFileSpecs.getOutputWidthInFrames() == 0) {
                    row++;
                    inlineFrameIdx = 0;  // reset inline frames index
                }
            }

            raf.write(invertBitmapBytes(bb.array(), bmpDimmsConfig[0] + pad, bmpDimmsConfig[1]));

        }
        catch(final Exception e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * Calculates output bitmap dimensions configuration.
     *
     * @param gfxFile graphics file specs
     * @return output bitmap dimensions configuration
     */
    private static int[] getBmpDimmsConfig(final GfxFileSpecs gfxFile) {
        final int xTiles = gfxFile.getOutputWidthInFrames();  // horizontal size in frames
        final int yTiles = gfxFile.getFrameCount() / xTiles +
                (gfxFile.getFrameCount() % xTiles == 0 ? 0 : 1);  // vertical size in frames

        final int[] sizeTiles = new int[4];
        sizeTiles[0] = gfxFile.getFrameWidth() * xTiles;  // total width in pixels
        sizeTiles[1] = gfxFile.getFrameHeight() * yTiles;  // total height in pixels
        sizeTiles[2] = xTiles;
        sizeTiles[3] = yTiles;

        return sizeTiles;
    }

    public static byte[] invertBitmapBytes(final byte[] bitmapBytes, final int width, final int height) {
        final byte[] inverted = new byte[bitmapBytes.length];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                inverted[(height - 1 - i) * width + j] = bitmapBytes[i * width + j];
            }
        }
        return inverted;
    }
}
