

import java.awt.image.IndexColorModel;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;

public class Util{
	
	public static final int STRUCT_BIN_HEIGHT = 40;
	public static final int STRUCT_BIN_WIDTH = 48;

	public static void closeResource(Closeable res){
		try{
			if(res != null){
				res.close();
			}
		}catch(IOException e){}
	}

	public static int[][] loadHexTiles(String file_name, int length) {
        int[][] hex_tiles = null;

        Path path = FileSystems.getDefault().getPath(file_name);

        try (FileChannel fc = (FileChannel.open(path))) {
            Counter count = new Counter();
            int image_size = STRUCT_BIN_HEIGHT * STRUCT_BIN_WIDTH;
            int image_skip = 1520;
            byte[] image_data = null;

            hex_tiles = new int[length][];
            for (int i = 0; i < length; i++) {
                int offset = 0;//i + j;
                int[] i_data_array = new int[image_size + offset];
                image_data = readBytes(fc, count.getSet(image_skip), image_skip, ByteOrder.BIG_ENDIAN);
                readHexData(i_data_array, image_data, offset);
                hex_tiles[i] = i_data_array;

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read " + file_name);
            System.exit(1);
        }

        return hex_tiles;
    }
	
	public static void readHexData(int[] i_data_array, byte[] image_data, int offset) {
        //read hexagonal source image to square target
        int s_idx = 0;
        int t_idx = 0 + offset;
        // ... top
        for (int i = 10; i > 0; i--) {
            for (int k = 0; k < 2; k++) {
                if (i != 10 || k != 0) {
                    t_idx += i;
                    for (int j = 0; j < (STRUCT_BIN_WIDTH - (2 * i)); ++j) {
                        int tmp = (int) image_data[s_idx] & 0xff;
                        i_data_array[t_idx] = tmp;
                        s_idx++;
                        t_idx++;
                    }
                    t_idx += i;
                }
            }
        }
        // ... center
        for (int i = 0; i < STRUCT_BIN_WIDTH * 2; ++i) {
            int tmp = (int) image_data[s_idx] & 0xff;
            i_data_array[t_idx] = tmp;
            s_idx++;
            t_idx++;
        }
        // ... bottom
        for (int i = 1; i <= 10; i++) {
            for (int k = 0; k < 2; k++) {
                if (i != 10 || k != 0) {
                    t_idx += i;
                    for (int j = 0; j < (STRUCT_BIN_WIDTH - (2 * i)); ++j) {
                        int tmp = (int) image_data[s_idx] & 0xff;
                        i_data_array[t_idx] = tmp;
                        s_idx++;
                        t_idx++;
                    }
                    t_idx += i;
                }
            }
        }
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
	
	public static byte[][] getRgbBytes(){
		byte[][] rgbBytes = null;
		InputStream is = null;
		try{
			is = Util.class.getResourceAsStream("../com/ontalsoft/efs/binutils/resource/EFS.PAL");
			byte[] binPalleteBytes = IOUtils.toByteArray(is);
			rgbBytes = new byte[3][256];
			for(int i = 0; i <= 255; i++){
				rgbBytes[0][i] = (byte)(4 * binPalleteBytes[3 * i]); //red
				rgbBytes[1][i] = (byte)(4 * binPalleteBytes[3 * i + 1]); //green
				rgbBytes[2][i] = (byte)(4 * binPalleteBytes[3 * i + 2]); //blue
	        }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeResource(is);
		}
        return rgbBytes;
    }
	
	public static IndexColorModel getIndexColorModel(byte[][] rgbBytes){
		return new IndexColorModel(8, 256, rgbBytes[0], rgbBytes[1], rgbBytes[2], 256);
	}
	
	public static byte[] readFile(String file_name, int size, ByteOrder byte_order) {

        byte[] file_data = null;

        Path path = FileSystems.getDefault().getPath(file_name);

        try (FileChannel fc = (FileChannel.open(path))) {

            if (size < 1) {
                size = (int) fc.size();
            }
            file_data = readBytes(fc, 0, size, byte_order);

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Failed to read " + file_name);
            System.exit(1);
        }
        return file_data;
    }
	
	public static void extractPallette(byte[] red, byte[] green, byte[] blue, byte[] pallette) {
        for (int i = 0; i <= 255; i++) {
            red[i] = (byte) (4 * pallette[3 * i]);
            green[i] = (byte) (4 * pallette[3 * i + 1]);
            blue[i] = (byte) (4 * pallette[3 * i + 2]);
        }
    }
}
