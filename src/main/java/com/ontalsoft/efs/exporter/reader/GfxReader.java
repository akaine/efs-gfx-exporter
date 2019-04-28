package com.ontalsoft.efs.exporter.reader;

import java.io.File;
import java.io.IOException;

import com.ontalsoft.efs.exporter.GfxFileSpecs;

public abstract class GfxReader {

    protected String basePath;
    protected GfxFileSpecs gfxFileSpecs;

    public GfxReader(final String basePath, final GfxFileSpecs gfxFileSpecs) {
        this.basePath = basePath;
        this.gfxFileSpecs = gfxFileSpecs;
    }

    /**
     * Reads bytes of every frame contained in a graphics file.
     *
     * @return array of frames and their corresponding bytes
     * @throws IOException
     */
    public abstract byte[][] read() throws IOException;

    /**
     * Returns an absolute path of the file provided during initialization
     *
     * @return file absolute path
     */
    protected String getFilePath() {
        final StringBuilder sb = new StringBuilder(basePath);
        sb.append(File.separator);
        sb.append(gfxFileSpecs.getDir());
        sb.append(File.separator);
        sb.append(gfxFileSpecs.getName());
        sb.append('.');
        sb.append(gfxFileSpecs.getType().name());

        return sb.toString();
    }
}
