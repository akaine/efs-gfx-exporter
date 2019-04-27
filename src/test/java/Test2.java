

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;

public class Test2{
	
	public static final int HEIGHT = 40;
	public static final int WIDTH = 48;
	public static final String EFS_TILES1 = "e:/_code/_projects/efs_phoenix/EFS/BIN/EFSTILE1.BIN";
	public static final String EFS_PAL = "e:/_code/_projects/efs_phoenix/EFS/EFS.PAL";
	
	public static final byte ALPHA_BYTE = (byte)0x00;

	public static void main(String[] args) throws IOException{
		byte[][] tileSet = readTileSet(EFS_TILES1);
		byte[][] rgbBytes = Util.getRgbBytes();

		
		OutputStream os = null;
		try{
			os = new FileOutputStream(new File("e:/_code/_projects/efs_phoenix/EFS/BIN/zzz.bmp"));
			BMPHeader bmpHeader = new BMPHeader(48, 40, rgbBytes);
			byte[] bmpHeaderBytes = bmpHeader.getHeaderBytes();
			
			byte[] padbytes = {0x0, 0x0};
			
			IOUtils.write(bmpHeaderBytes, os);
			IOUtils.write(invertBitmapBytes(tileSet[15]), os);
			IOUtils.write(padbytes, os);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			Util.closeResource(os);
		}
	}
	
	public static byte[] invertBitmapBytes(byte[] bitmapBytes){
		byte[] inverted = new byte[bitmapBytes.length];
		for(int i=0; i<HEIGHT; i++){
			for(int j=0; j<WIDTH; j++){
				inverted[((HEIGHT-1)-i)*WIDTH+j] = bitmapBytes[i*WIDTH+j];
			}
		}
		return inverted;
	}
	
	public static void printBytes(byte[] tile){
		for(int i=0; i<HEIGHT; i++){
			for(int j=0; j<WIDTH; j++){
				System.out.print(String.format("%02X ", tile[i*WIDTH+j]));
			}
			System.out.println();
		}
	}
	
	
	public static byte[][] readTileSet(String tilesFilePath){
		byte[][] tileSet = new byte[134][HEIGHT*WIDTH];
		Path path = FileSystems.getDefault().getPath(tilesFilePath);
		
		byte[] rawBytes;
		try(FileChannel fc = (FileChannel.open(path))){
			for(int i = 0; i < 134; i++){
				rawBytes = readBytes(fc, 1520*i, 1520, ByteOrder.BIG_ENDIAN);
				tileSet[i] = raw2sqaure(rawBytes);
			}
		}catch(IOException e){
            e.printStackTrace();
        }
		
		return tileSet;
	}
	
	public static void insertAplhaBytes(byte[] squareMapBytes, int offset, int length){
		for(int i=0; i<length; i++){
			squareMapBytes[offset] = ALPHA_BYTE;
			offset++;
		}
	}
	
	public static byte[] raw2sqaure(byte[] rawBytes){
		byte[] bitmap = new byte[HEIGHT*WIDTH];
		
		int s_idx = 0, b_idx = 0;
		for(int i = 10; i >= 0; i--){
            for(int k = 0; k < 2; k++){
                if(i != 10 || k != 0){
                	insertAplhaBytes(bitmap, b_idx, i);
                	b_idx += i;
                	for(int j = 0; j < (WIDTH - (2 * i)); ++j){
                		bitmap[b_idx] = rawBytes[s_idx];
                		b_idx++;
                        s_idx++;
                    }
                	insertAplhaBytes(bitmap, b_idx, i);
                	b_idx += i;
                }
            }
		}
		for(int i = 1; i <= 10; i++){
            for(int k = 0; k < 2; k++){
                if(i != 10 || k != 0){
                	insertAplhaBytes(bitmap, b_idx, i);
                	b_idx += i;
                    for (int j = 0; j < (WIDTH - (2 * i)); ++j) {
                    	bitmap[b_idx] = rawBytes[s_idx];
                		b_idx++;
                        s_idx++;
                    }
                    insertAplhaBytes(bitmap, b_idx, i);
                    b_idx += i;
                }
            }
        }
		return bitmap;
	}
	
	public static byte[] readBytes(FileChannel fc, long index, int length, ByteOrder bo) throws IOException {
        byte[] ret_val = new byte[length];
        int nread;
        ByteBuffer bf = ByteBuffer.allocate(length);
        bf.order(bo);
        fc.position(index);
        do {
            nread = fc.read(bf);
        } while (nread != -1 && bf.hasRemaining());
        bf.rewind();
        byte[] tmp = bf.array();
        System.arraycopy(tmp, 0, ret_val, 0, length);
        return ret_val;
    }
	
	public static byte[] getBmpHeader(){
		byte[] headerBytes = new byte[0x436];
		
		InputStream is = null;
		try{
			is = new FileInputStream(new File("e:/efs_tile.bmp"));
			IOUtils.readFully(is, headerBytes, 0, 0x436);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			Util.closeResource(is);
		}
		return headerBytes;
	}
}
