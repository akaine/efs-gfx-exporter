package com.ontalsoft.efs.exporter.bin;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import com.ontalsoft.efs.exporter.Constants;
import com.ontalsoft.efs.exporter.EnumFile;

public class BinReader{

    private static final int HEXAGON_BYTES_LENGTH = 1520;

    private final String dirPath;
    private final EnumFile enumFile;
    private byte[][] imageBytes;

    public BinReader(final String dirPath, final EnumFile enumFile){
        this.dirPath = dirPath;
        this.enumFile = enumFile;
    }

    public byte[][] readTilesBytes() throws IOException{
        final byte[][] imageBytes = new byte[enumFile.getTilesNumber()][enumFile.getTileHeight() * enumFile.getTileWidth()];
        final Path path = FileSystems.getDefault().getPath(dirPath+enumFile.getBinName());

        try{
            final byte[] fileBytes = Files.readAllBytes(path);
            if(enumFile.isHexagonal()){
                for(int i = 0; i < enumFile.getTilesNumber(); i++){
                    imageBytes[i] = hexagonToSqaure(Arrays.copyOfRange(fileBytes, HEXAGON_BYTES_LENGTH*i, HEXAGON_BYTES_LENGTH*(i+1)));
                }
            }else{
                for(int i = 0; i < enumFile.getTilesNumber(); i++){
                    //imageBytes[i] = raw2sqaure(Arrays.copyOfRange(fileBytes, HEXAGON_BYTES_LENGTH*i, HEXAGON_BYTES_LENGTH*(i+1)));
                }
            }
        }catch(final IOException e){
            throw new IOException(e);
        }
        return imageBytes;
    }


    // LOCAL METHODS =============================================================================0

    private byte[] hexagonToSqaure(final byte[] hexagonBytes){
        final byte[] bitmap = new byte[enumFile.getTileHeight() * enumFile.getTileWidth()];

        int currentIdx = 0, offsetIdx = 0;
        for(int i = 10; i >= 0; i--){
            for(int k = 0; k < 2; k++){
                if(i != 10 || k != 0){
                    insertAplhaBytes(bitmap, offsetIdx, i);
                    offsetIdx += i;
                    for(int j = 0; j < enumFile.getTileWidth() - 2 * i; ++j){
                        bitmap[offsetIdx] = hexagonBytes[currentIdx];
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
                    for (int j = 0; j < enumFile.getTileWidth() - 2 * i; ++j) {
                        bitmap[offsetIdx] = hexagonBytes[currentIdx];
                        offsetIdx++;
                        currentIdx++;
                    }
                    insertAplhaBytes(bitmap, offsetIdx, i);
                    offsetIdx += i;
                }
            }
        }
        return bitmap;
    }

    private static void insertAplhaBytes(final byte[] squareMapBytes, int offset, final int length){
        for(int i=0; i<length; i++){
            squareMapBytes[offset] = Constants.ALPHA_BYTE;
            offset++;
        }
    }
}
