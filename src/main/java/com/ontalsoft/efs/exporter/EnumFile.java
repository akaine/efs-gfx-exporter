package com.ontalsoft.efs.exporter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum EnumFile{
	EFSTILE0 ("EFSTILE0.BIN", "EFSTILE0.bmp", 48, 40, 134, true, "Temperate-type terrain tiles"),
	EFSTILE1 ("EFSTILE1.BIN", "EFSTILE1.bmp", 48, 40, 134, true, "Megacity-type terrain tiles"),
	EFSTILE2 ("EFSTILE2.BIN", "EFSTILE2.bmp", 48, 40, 134, true, "Arctic-type terrain tiles"),
	EFSTILE3 ("EFSTILE3.BIN", "EFSTILE3.bmp", 48, 40, 134, true, "Jungle-type terrain tiles"),
	EFSTILE4 ("EFSTILE4.BIN", "EFSTILE4.bmp", 48, 40, 134, true, "Volcanic-type terrain tiles"),
	STRUCT0 ("STRUCT0.BIN", "STRUCT0.bmp", 48, 40, 32, true, "Temperate-type buildings icons"),
	STRUCT1 ("STRUCT1.BIN", "STRUCT1.bmp", 48, 40, 32, true, "Megacity-type buildings icons"),
	STRUCT2 ("STRUCT2.BIN", "STRUCT2.bmp", 48, 40, 32, true, "Arctic-type buildings icons"),
	STRUCT3 ("STRUCT3.BIN", "STRUCT3.bmp", 48, 40, 32, true, "Jungle-type buildings icons"),
	STRUCT4 ("STRUCT4.BIN", "STRUCT4.bmp", 48, 40, 32, true, "Volcanic-type buildings icons");
	
	private static final Map<String,EnumFile> lookup = new HashMap<>();

	private String binName;
	private String bmpName;
	private int tileWidth;
	private int tileHeight;
	private int tilesNumber;
	private boolean isHexagonal;
	private String description;
	
	private EnumFile(String binName, String bmpName, int tileWidth, int tileHeight, int tilesNumber, boolean isHexagonal, String description){
		this.binName = binName;
		this.bmpName = bmpName;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tilesNumber = tilesNumber;
		this.isHexagonal = isHexagonal;
		this.description = description;
	}
	
	static{
        for (EnumFile t : EnumSet.allOf(EnumFile.class)) {
            lookup.put(t.name(), t);
        }
    }
	
	public static EnumFile getEnumFileByName(final String name){
		return lookup.get(name);
	}
	
	public static Set<String> listAllBinNames(){
		Set<String> set = new HashSet<String>();
		for(EnumFile o : EnumFile.values()){
			set.add(o.getBinName());
		}
		return set;
	}

	public String getBinName(){
		return binName;
	}

	public String getBmpName(){
		return bmpName;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getTilesNumber() {
		return tilesNumber;
	}

	public boolean isHexagonal() {
		return isHexagonal;
	}

	public String getDescription() {
		return description;
	}
}
