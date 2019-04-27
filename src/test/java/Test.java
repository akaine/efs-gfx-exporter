

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;

public class Test{

    public static final int HEIGHT = 40;
    public static final int WIDTH = 48;
    public static final String EFS_TILES1 = "e:/_code/_projects/efs_phoenix/EFS/BIN/EFSTILE2.BIN";
    public static final String EFS_TILES1_OUT = "e:/_code/_projects/efs_phoenix/EFS/BIN/EFSTILE2.bmp";
    public static final String EFS_PAL = "e:/_code/_projects/efs_phoenix/EFS/EFS.PAL";

    public static final byte ALPHA_BYTE = (byte)0x00;

    public static void main(final String[] args) throws IOException{
        final byte[][] tileSet = readTileSet(EFS_TILES1);
        final byte[][] rgbBytes = Util.getRgbBytes();

        final int[] imgSize = getImgLibSize(48, 40, 134);
        final BMPHeader bmpHeader = new BMPHeader(imgSize[0], imgSize[1], rgbBytes);
        System.out.println("w="+imgSize[0]+"  h="+imgSize[1]+"  rows="+imgSize[3]);
        final byte[] bmpHeaderBytes = bmpHeader.getHeaderBytes();

        //		OutputStream os = null;
        //		try{
        //			os = new FileOutputStream(new File(EFS_TILES1_OUT));
        //			IOUtils.write(bmpHeaderBytes, os);
        //
        //			byte[] blankBytes = new byte[imgSize[0]*imgSize[1]];
        //			for(int i=0; i<blankBytes.length; i++){
        //				blankBytes[i] = (byte)0x0;
        //			}
        //			IOUtils.write(blankBytes, os);
        //
        //			//IOUtils.write(bmpHeaderBytes, os);
        //			//IOUtils.write(invertBitmapBytes(tileSet[15]), os);
        //
        //			byte[] padbytes = {0x0, 0x0};
        //			IOUtils.write(padbytes, os);
        //		}catch(Exception e){
        //			e.printStackTrace();
        //		}finally{
        //			Util.closeResource(os);
        //		}

        RandomAccessFile raf = null;
        try{
            raf = new RandomAccessFile(EFS_TILES1_OUT, "rw");

            raf.write(bmpHeaderBytes);

            //			byte[] blankBytes = new byte[imgSize[0]*imgSize[1]];
            //			for(int i=0; i<blankBytes.length; i++){
            //				blankBytes[i] = (byte)0x0;
            //			}
            //			raf.write(blankBytes);

            //			byte[] padbytes = {0x0, 0x0};
            //			raf.write(padbytes);

            final ByteBuffer bb = ByteBuffer.allocate(imgSize[0]*imgSize[1]+imgSize[0]*40);

            final int startPos = bmpHeader.getOffBits();
            raf.seek(startPos);

            int row = 0;
            for(int i=0; i<134; i++){
                for(int j=0; j<40; j++){
                    bb.position(48*i + imgSize[0]*j + imgSize[0]*40*row);
                    bb.put(tileSet[i], 48*j, 48);
                }
                if((i+1) % 16 == 0){
                    row++;
                }
            }

            raf.seek(startPos);
            raf.write(invertBitmapBytes(bb.array(), imgSize[0], imgSize[1]));

        }catch(final Exception e){
            e.printStackTrace();
        }finally{
            Util.closeResource(raf);
        }
    }



    public static int[] getImgLibSize(final int tileWidth, final int tileHeight, final int tilesNum){
        final int[] sizeTiles = new int[4];
        final int xTiles = 16;
        final int yTiles = tilesNum / xTiles + (tilesNum % xTiles == 0 ? 0 : 1);
        sizeTiles[0] = tileWidth * xTiles;
        sizeTiles[1] = tileHeight * yTiles;
        sizeTiles[2] = xTiles;
        sizeTiles[3] = yTiles;
        //		if(tilesNum % xTiles == 0){
        //			sizeTiles[2] = 0;
        //		}else{
        //			sizeTiles[2] = tileWidth * (tilesNum - (xTiles*(yTiles-1)));
        //		}
        return sizeTiles;
    }

    public static byte[] invertBitmapBytes(final byte[] bitmapBytes, final int width, final int height){
        final byte[] inverted = new byte[bitmapBytes.length];
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                inverted[(height-1-i)*width+j] = bitmapBytes[i*width+j];
            }
        }
        return inverted;
    }

    public static void printBytes(final byte[] tile){
        for(int i=0; i<HEIGHT; i++){
            for(int j=0; j<WIDTH; j++){
                System.out.print(String.format("%02X ", tile[i*WIDTH+j]));
            }
            System.out.println();
        }
    }


    public static byte[][] readTileSet(final String tilesFilePath){
        final byte[][] tileSet = new byte[134][HEIGHT*WIDTH];

        final Path path = FileSystems.getDefault().getPath(tilesFilePath);

        try{
            final byte[] fileBytes = Files.readAllBytes(path);
            for(int i = 0; i < 134; i++){
                tileSet[i] = raw2sqaure(Arrays.copyOfRange(fileBytes, 1520*i, 1520*(i+1)));
            }
        }catch(final IOException e){}


        //		byte[] rawBytes;
        //		try(FileChannel fc = (FileChannel.open(path))){
        //			for(int i = 0; i < 134; i++){
        //				rawBytes = readBytes(fc, 1520*i, 1520, ByteOrder.BIG_ENDIAN);
        //				tileSet[i] = raw2sqaure(rawBytes);
        //			}
        //		}catch(IOException e){
        //            e.printStackTrace();
        //        }

        return tileSet;
    }

    public static void insertAplhaBytes(final byte[] squareMapBytes, int offset, final int length){
        for(int i=0; i<length; i++){
            squareMapBytes[offset] = ALPHA_BYTE;
            offset++;
        }
    }

    public static byte[] raw2sqaure(final byte[] rawBytes){
        final byte[] bitmap = new byte[HEIGHT*WIDTH];

        int currentIdx = 0, offsetIdx = 0;
        for(int i = 10; i >= 0; i--){
            for(int k = 0; k < 2; k++){
                if(i != 10 || k != 0){
                    insertAplhaBytes(bitmap, offsetIdx, i);
                    offsetIdx += i;
                    for(int j = 0; j < WIDTH - 2 * i; j++){
                        bitmap[offsetIdx] = rawBytes[currentIdx];
                        offsetIdx++;
                        currentIdx++;
                    }
                    insertAplhaBytes(bitmap, offsetIdx, i);
                    offsetIdx += i;
                }
            }
        }
        for(int i = 1; i <= 10; i++){
            for(int k = 0; k < 2; k++){
                if(i != 10 || k != 0){
                    insertAplhaBytes(bitmap, offsetIdx, i);
                    offsetIdx += i;
                    for (int j = 0; j < WIDTH - 2 * i; j++) {
                        bitmap[offsetIdx] = rawBytes[currentIdx];
                        offsetIdx++;
                        currentIdx++;
                    }
                    insertAplhaBytes(bitmap, offsetIdx, i);
                    offsetIdx += i;
                }
            }
        }

        //		byte[] bitmap = new byte[HEIGHT * WIDTH];
        //
        //		int currentIdx = 0, offsetIdx = 0;
        //		for(int i = 10; i >= 0; i--){
        //            for(int j = 0; j < 2; j++){
        //                if(i != 10 || j != 0){
        //                	insertAplhaBytes(bitmap, offsetIdx, i);
        //                	offsetIdx += i;
        //                	for(int k = 0; k < (WIDTH - (2 * i)); ++k){
        //                		bitmap[offsetIdx] = rawBytes[currentIdx];
        //                		offsetIdx++;
        //                		currentIdx++;
        //                    }
        //                	insertAplhaBytes(bitmap, offsetIdx, i);
        //                	currentIdx += i;
        //                }
        //            }
        //		}
        //		for(int i = 1; i <= 10; i++){
        //            for(int j = 0; j < 2; j++){
        //                if(i != 10 || j != 0){
        //                	insertAplhaBytes(bitmap, offsetIdx, i);
        //                	offsetIdx += i;
        //                    for (int k = 0; k < (WIDTH - (2 * i)); ++k) {
        //                    	bitmap[offsetIdx] = rawBytes[currentIdx];
        //                    	offsetIdx++;
        //                    	currentIdx++;
        //                    }
        //                    insertAplhaBytes(bitmap, offsetIdx, i);
        //                    offsetIdx += i;
        //                }
        //            }
        //        }
        return bitmap;
    }

    public static byte[] readBytes(final FileChannel fc, final long index, final int length, final ByteOrder bo) throws IOException {
        final byte[] ret_val = new byte[length];
        int nread;
        final ByteBuffer bf = ByteBuffer.allocate(length);
        bf.order(bo);
        fc.position(index);
        do {
            nread = fc.read(bf);
        } while (nread != -1 && bf.hasRemaining());
        bf.rewind();
        final byte[] tmp = bf.array();
        System.arraycopy(tmp, 0, ret_val, 0, length);
        return ret_val;
    }

    public static byte[] getBmpHeader(){
        final byte[] headerBytes = new byte[0x436];

        InputStream is = null;
        try{
            is = new FileInputStream(new File("e:/efs_tile.bmp"));
            IOUtils.readFully(is, headerBytes, 0, 0x436);
        }catch(final Exception e){
            e.printStackTrace();
        }finally{
            Util.closeResource(is);
        }
        return headerBytes;
    }
}
