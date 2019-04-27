package com.ontalsoft.efs.exporter;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class Operations{

	public static boolean scanBinDirectory(String sPath, Main main){
		boolean scanStatus = false;
		Path path = Paths.get(sPath);
		main.appendToLog("- Checking EFS directories... ");
		if(Files.exists(path)){
			Set<String> binFileNames = EnumFile.listAllBinNames();
			int binFileNamesSize = binFileNames.size();
			
			try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)){
				Path filePath;
				for(Path p : directoryStream){
					filePath = Paths.get(p.toString());
					//System.out.println(filePath.getFileName());
					if(binFileNames.contains(filePath.getFileName().toString())){
						binFileNames.remove(filePath.getFileName().toString());
					}
				}
				if(binFileNames.size() > 0){
					if(binFileNamesSize == binFileNames.size()){
						main.appendToLog("ERROR: \""+path.toAbsolutePath()+"\" is not an EFS graphics BINs directory. Please select a correct directory (ex. \"C:\\EFS\\BIN\") using the form above.");
					}else{
						StringBuilder sb = new StringBuilder("ERROR: The following required BIN files appear to be missing: ");
						for(String missingFile : binFileNames){
							sb.append(missingFile+", ");
						}
						sb.delete(sb.length()-2, sb.length());
						sb.append(". Please reinstall EFS.");
						main.appendToLog(sb.toString());
					}
				}else{
					main.appendToLog("OK. All graphics BIN files seem to be in place.");
					scanStatus = true;
				}
			}catch(IOException ex){
				main.appendToLog("ERROR: Can't read \""+path.toAbsolutePath()+"\" or it's not a directory.");
			}
		}else{
			main.appendToLog("ERROR: Directory \""+path.toAbsolutePath()+"\" does not exist.");
		}
		main.appendToLog("\n");
		return scanStatus;
	}
}
