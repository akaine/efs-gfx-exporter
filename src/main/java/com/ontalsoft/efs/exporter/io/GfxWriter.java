package com.ontalsoft.efs.exporter.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.ontalsoft.efs.exporter.GfxFileSpecs;

public abstract class GfxWriter {

    protected final String basePath;
    protected final GfxFileSpecs gfxFileSpecs;
    protected final byte[][] rgbPalette;
    protected byte[][] gfxData;
    protected BufferedImage image;
    protected final String absolutePath;

    public GfxWriter(final String basePath, final GfxFileSpecs gfxFileSpecs, final byte[][] rgbPalette) {
        this.basePath = basePath;
        this.gfxFileSpecs = gfxFileSpecs;
        this.rgbPalette = rgbPalette;
        this.absolutePath = getFilePath();
    }

    /**
     * Reads bytes of every frame contained in a graphics file.
     *
     * @return array of frames and their corresponding bytes
     * @throws IOException
     */
    public abstract void write() throws Exception;

    /**
     * Returns an absolute path of the file provided during initialization
     *
     * @return file absolute path
     */
    private String getFilePath() {
        final StringBuilder sb = new StringBuilder(basePath);
        sb.append(File.separator);
        sb.append(gfxFileSpecs.getDir());
        sb.append(File.separator);
        sb.append(gfxFileSpecs.getName());
        sb.append('.');
        sb.append("bmp");

        return sb.toString();
    }
}
