package com.ontalsoft.efs.exporter.bin;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import com.ontalsoft.efs.binutils.Constants;
import com.ontalsoft.efs.binutils.EnumFile;

public class BinReader{
	
	private static final int HEXAGON_BYTES_LENGTH = 1520;

	private String dirPath;
	private EnumFile enumFile;
	private byte[][] imageBytes;

	public BinReader(String dirPath, EnumFile enumFile){
		this.dirPath = dirPath;
		this.enumFile = enumFile;
	}
	
	public byte[][] readTilesBytes() throws IOException{
		byte[][] imageBytes = new byte[enumFile.getTilesNumber()][enumFile.getTileHeight() * enumFile.getTileWidth()];
		Path path = FileSystems.getDefault().getPath(dirPath+enumFile.getBinName());
		
		try{
			byte[] fileBytes = Files.readAllBytes(path);
			if(enumFile.isHexagonal()){
				for(int i = 0; i < enumFile.getTilesNumber(); i++){
					imageBytes[i] = hexagonToSqaure(Arrays.copyOfRange(fileBytes, HEXAGON_BYTES_LENGTH*i, HEXAGON_BYTES_LENGTH*(i+1)));
				}
			}else{
				for(int i = 0; i < enumFile.getTilesNumber(); i++){
					//imageBytes[i] = raw2sqaure(Arrays.copyOfRange(fileBytes, HEXAGON_BYTES_LENGTH*i, HEXAGON_BYTES_LENGTH*(i+1)));
				}
			}
		}catch(IOException e){
			throw new IOException(e);
		}
		return imageBytes;
	}
	
	
	// LOCAL METHODS =============================================================================0
	
	private byte[] hexagonToSqaure(byte[] hexagonBytes){
		byte[] bitmap = new byte[enumFile.getTileHeight() * enumFile.getTileWidth()];
		
		int currentIdx = 0, offsetIdx = 0;
		for(int i = 10; i >= 0; i--){
            for(int k = 0; k < 2; k++){
                if(i != 10 || k != 0){
                	insertAplhaBytes(bitmap, offsetIdx, i);
                	offsetIdx += i;
                	for(int j = 0; j < (enumFile.getTileWidth() - (2 * i)); ++j){
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
                    for (int j = 0; j < (enumFile.getTileWidth() - (2 * i)); ++j) {
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
	
	private static void insertAplhaBytes(byte[] squareMapBytes, int offset, int length){
		for(int i=0; i<length; i++){
			squareMapBytes[offset] = Constants.ALPHA_BYTE;
			offset++;
		}
	}
}
