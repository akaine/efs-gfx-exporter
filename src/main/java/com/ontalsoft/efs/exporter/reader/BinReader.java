package com.ontalsoft.efs.exporter.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.ontalsoft.efs.exporter.GfxFileSpecs;

public class BinReader extends GfxReader {

    public static final byte ALPHA_BYTE = (byte)0x00;

    public BinReader(final String basePath, final GfxFileSpecs gfxFile) {
        super(basePath, gfxFile);
    }

    @Override
    public byte[][] read() throws IOException {
        final byte[][] framesBytes = new byte[gfxFileSpecs.getFrameCount()][gfxFileSpecs.getFrameHeight() * gfxFileSpecs.getFrameWidth()];

        try {
            final byte[] fileBytes = Files.readAllBytes(Paths.get(getFilePath()));

            if(gfxFileSpecs.isHexagonal()) {
                for(int i = 0; i < gfxFileSpecs.getFrameCount(); i++) {
                    framesBytes[i] = hexagonToSqaure(
                            Arrays.copyOfRange(fileBytes,
                                    gfxFileSpecs.getHexagonPixelsLength() * i,
                                    gfxFileSpecs.getHexagonPixelsLength() * (i + 1))); //get single frame bytes
                }
            }
            else {
                for(int i = 0; i < gfxFileSpecs.getFrameCount(); i++) {
                    framesBytes[i] = raw2sqaure(Arrays.copyOfRange(fileBytes,
                            gfxFileSpecs.getFrameWidth() * gfxFileSpecs.getFrameHeight() * i,
                            gfxFileSpecs.getFrameWidth() * gfxFileSpecs.getFrameHeight() * (i + 1)));
                }
            }
        }
        catch(final IOException e) {
            throw new IOException(e);
        }
        return framesBytes;
    }

    private byte[] raw2sqaure(final byte[] bitmapBytes) {
        final byte[] squareBitmap = new byte[gfxFileSpecs.getFrameHeight() * gfxFileSpecs.getFrameWidth()];

        for(int i = 0; i < bitmapBytes.length; i++) {
            squareBitmap[i] = bitmapBytes[i];
        }

        return squareBitmap;
    }

    /**
     * Creates a square bitmap from hexagon bytes, adding alpha bytes (pixels)
     * between the hexagon chunks.
     * <br/>
     * In EFS, the hexagon tiles are stored in the following format with
     * the effective bitmap bytes going consecuentially, without any spaces
     * (in one big "line"):
     * <ul>
     * <li>28 bytes (hexagon line 0)</li>
     * <li>30 bytes (hexagon line 1)</li>
     * <li>30 bytes (hexagon line 2)</li>
     * <li>32 bytes (hexagon line 3)</li>
     * <li>32 bytes (hexagon line 4)</li>
     * <li>...</li>
     * <li>48 bytes (hexagon line 19)</li>
     * <li>48 bytes (hexagon line 20)</li>
     * <li>46 bytes (hexagon line 21)</li>
     * <li>46 bytes (hexagon line 22)</li>
     * <li>...</li>
     * <li>30 bytes (hexagon line 37)</li>
     * <li>30 bytes (hexagon line 38)</li>
     * <li>28 bytes (hexagon line 39)</li>
     * </ul>
     * First and last lines appear only once so they would form a pair when
     * connected with other tiles.
     * <br/><br/>
     *
     * @param hexagonBytes
     * @return
     */
    private byte[] hexagonToSqaure(final byte[] hexagonBytes) {
        final byte[] squareBitmap = new byte[gfxFileSpecs.getFrameHeight() * gfxFileSpecs.getFrameWidth()];

        int hexagonBytesIdx = 0, squarebBitmapIdx = 0;

        // read 1st half of hexagon up till line 20
        for(int i = 10; i >= 0; i--) {  // start with 10 bytes for alpha
            for(int k = 0; k < 2; k++) {  // pair repeater
                if(i != 10 || k != 0) {  // skip first line once to avoid pair
                    insertAplhaBytes(squareBitmap, squarebBitmapIdx, i); // write alpha before hexagon line
                    squarebBitmapIdx += i; // move square cursor to position after alpha

                    // write hexagon line
                    for(int j = 0; j < gfxFileSpecs.getFrameWidth() - 2 * i; ++j) {
                        squareBitmap[squarebBitmapIdx] = hexagonBytes[hexagonBytesIdx];
                        squarebBitmapIdx++; // adjust square cursor
                        hexagonBytesIdx++; // go to next hexagon byte
                    }
                    insertAplhaBytes(squareBitmap, squarebBitmapIdx, i); // write alpha after hexagon line
                    squarebBitmapIdx += i; // move square cursor to position after alpha
                }
            }
        }

        // read 2nd half of hexagon starting from line 21
        for(int i = 1; i <= 10; i++) {  // start with 1 byte for alpha
            for(int k = 0; k < 2; k++) {
                if(i != 10 || k != 0) {  // skip last line once to avoid pair
                    insertAplhaBytes(squareBitmap, squarebBitmapIdx, i);
                    squarebBitmapIdx += i;
                    for(int j = 0; j < gfxFileSpecs.getFrameWidth() - 2 * i; ++j) {
                        squareBitmap[squarebBitmapIdx] = hexagonBytes[hexagonBytesIdx];
                        squarebBitmapIdx++;
                        hexagonBytesIdx++;
                    }
                    insertAplhaBytes(squareBitmap, squarebBitmapIdx, i);
                    squarebBitmapIdx += i;
                }
            }
        }
        return squareBitmap;
    }

    /**
     * Sets bitmap bytes starting from the offset up to specified length with
     * alpha byte value.
     *
     * @param squareBitmap bitmap
     * @param offset offset
     * @param length length
     */
    private static void insertAplhaBytes(final byte[] squareBitmap, int offset, final int length) {
        for(int i = 0; i < length; i++) {
            squareBitmap[offset] = ALPHA_BYTE;
            offset++;
        }
    }
}
