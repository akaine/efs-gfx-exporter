package com.ontalsoft.efs.exporter.bmp;

import java.nio.ByteBuffer;

import lombok.Getter;

public class BMPHeader {

    private final static int FILEHEADER_SIZE = 14;
    private final static int INFOHEADER_SIZE = 40;
    private final static int PALETTE_SIZE = 256 * 4;

    private final byte[] bfType = { 'B', 'M' };
    private int bfSize = 0;
    private final int bfReserved1 = 0;
    private final int bfReserved2 = 0;
    private final int bfOffBits = FILEHEADER_SIZE + INFOHEADER_SIZE + PALETTE_SIZE;

    private final int biSize = INFOHEADER_SIZE;
    private int biWidth = 0;
    private int biHeight = 0;
    private final int biPlanes = 1;
    private final int biBitCount = 8;
    private final int biCompression = 0;
    private int biSizeImage = 0;
    private final int biXPelsPerMeter = 2834;
    private final int biYPelsPerMeter = 2834;
    private final int biClrUsed = 0;
    private final int biClrImportant = 0;

    private final byte[] paletteBytes;

    @Getter
    private final int pad;

    public BMPHeader(final int biWidth, final int biHeight, final byte[][] rgbBytes) {
        this.pad = (4 - biWidth % 4) % 4;
        this.biWidth = biWidth;
        this.biHeight = biHeight;

        paletteBytes = new byte[256 * 4];
        for(int i = 0; i < 256; i++) {
            paletteBytes[i * 4] = rgbBytes[2][i];
            paletteBytes[i * 4 + 1] = rgbBytes[1][i];
            paletteBytes[i * 4 + 2] = rgbBytes[0][i];
            paletteBytes[i * 4 + 3] = (byte)0x00;
        }
    }

    public byte[] getHeaderBytes() {

        biSizeImage = biWidth * biHeight + 2;
        bfSize = biSizeImage + bfOffBits;

        final ByteBuffer buffer = ByteBuffer.allocate(bfOffBits);
        buffer.put(bfType);
        buffer.put(getDWord(bfSize));
        buffer.put(getWord(bfReserved1));
        buffer.put(getWord(bfReserved2));
        buffer.put(getDWord(bfOffBits));

        buffer.put(getDWord(biSize));
        buffer.put(getDWord(biWidth));
        buffer.put(getDWord(biHeight));
        buffer.put(getWord(biPlanes));
        buffer.put(getWord(biBitCount));
        buffer.put(getDWord(biCompression));
        buffer.put(getDWord(biSizeImage));
        buffer.put(getDWord(biXPelsPerMeter));
        buffer.put(getDWord(biYPelsPerMeter));
        buffer.put(getDWord(biClrUsed));
        buffer.put(getDWord(biClrImportant));

        buffer.put(paletteBytes);

        return buffer.array();
    }

    public int getOffBits() {
        return bfOffBits;
    }

    private byte[] getWord(final int value) {
        final byte word[] = new byte[2];
        word[0] = (byte)(value & 0x00FF);
        word[1] = (byte)(value >> 8 & 0x00FF);
        return word;
    }

    private byte[] getDWord(final int value) {
        final byte dword[] = new byte[4];
        dword[0] = (byte)(value & 0x00FF);
        dword[1] = (byte)(value >> 8 & 0x000000FF);
        dword[2] = (byte)(value >> 16 & 0x000000FF);
        dword[3] = (byte)(value >> 24 & 0x000000FF);
        return dword;
    }
}
