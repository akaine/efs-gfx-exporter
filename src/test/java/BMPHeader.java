

import java.nio.ByteBuffer;

public class BMPHeader{

	private final static int FILEHEADER_SIZE = 14;
	private final static int INFOHEADER_SIZE = 40;
	private final static int PALETTE_SIZE = 256*4;

	private byte[] bfType = {'B','M'};
	private int bfSize = 0;
	private int bfReserved1 = 0;
	private int bfReserved2 = 0;
	private int bfOffBits = FILEHEADER_SIZE + INFOHEADER_SIZE + PALETTE_SIZE;

	private int biSize = INFOHEADER_SIZE;
	private int biWidth = 0;
	private int biHeight = 0;
	private int biPlanes = 1;
	private int biBitCount = 8;
	private int biCompression = 0;
	private int biSizeImage = 0;
	private int biXPelsPerMeter = 2834;
	private int biYPelsPerMeter = 2834;
	private int biClrUsed = 0;
	private int biClrImportant = 0;
	
	private byte[] paletteBytes;

	public BMPHeader(int biWidth, int biHeight, byte[][] rgbBytes){
		this.biWidth = biWidth;
		this.biHeight = biHeight;
		
		paletteBytes = new byte[256*4];
		for(int i=0; i<256; i++){
			paletteBytes[i*4] = (byte)rgbBytes[2][i];
			paletteBytes[i*4+1] = (byte)rgbBytes[1][i];
			paletteBytes[i*4+2] = (byte)rgbBytes[0][i];
			paletteBytes[i*4+3] = (byte)0x00;
		}
	}

	public byte[] getHeaderBytes(){
		int pad = (4 - (biWidth % 4)) % 4;
		System.out.println("pad="+pad);
		biSizeImage = (biWidth * biHeight)+2;
		bfSize = biSizeImage + bfOffBits;

		ByteBuffer buffer = ByteBuffer.allocate(bfOffBits);
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
	
	public int getOffBits(){
		return bfOffBits;
	}

	private byte[] getWord(int value){
		byte word[] = new byte[2];
		word[0] = (byte)(value & 0x00FF);
		word[1] = (byte)((value >> 8) & 0x00FF);
		return word;
	}

	private byte[] getDWord(int value){
		byte dword[] = new byte[4];
		dword[0] = (byte)(value & 0x00FF);
		dword[1] = (byte)((value >> 8) & 0x000000FF);
		dword[2] = (byte)((value >> 16) & 0x000000FF);
		dword[3] = (byte)((value >> 24) & 0x000000FF);
		return dword;
	}
}
