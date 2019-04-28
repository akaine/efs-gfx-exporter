package com.ontalsoft.efs.exporter;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.ontalsoft.efs.exporter.io.BinReader;
import com.ontalsoft.efs.exporter.swing.SwingUtil;

public class Events{
	
	public void scanBinDirectoryOnFocusLost(FocusEvent e, Main main){
		JTextField textBinPath = SwingUtil.getComponentByName(main.getComponentMap(), Main.TEXT_BINPATH);
		Operations.scanBinDirectory(textBinPath.getText().trim(), main);
	}
	
	public void browseBinPath(ActionEvent e, Main main){
		JFrame mainFrame = SwingUtil.getComponentByName(main.getComponentMap(), Main.MAIN_FRAME);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Select EFS BIN directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if(fileChooser.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			JTextField textBinPath = SwingUtil.getComponentByName(main.getComponentMap(), Main.TEXT_BINPATH);
			textBinPath.setText(file.getAbsolutePath());
			
			boolean scanStatus = Operations.scanBinDirectory(file.getAbsolutePath(), main);
			if(scanStatus){
				JTextField textBmpPath = SwingUtil.getComponentByName(main.getComponentMap(), Main.TEXT_BMPPATH);
				if(textBmpPath.getText() == null || textBmpPath.getText().trim().isEmpty()){
					textBmpPath.setText(file.getAbsolutePath());
				}
			}
		}
	}
	
	public void convertBinsToBmps(ActionEvent e, Main main){
		GfxFileSpecs[] enumFiles = GfxFileSpecs.values();
		Set<String> binFileNames = GfxFileSpecs.listAllBinNames();
		
		JTextField textBinPath = SwingUtil.getComponentByName(main.getComponentMap(), Main.TEXT_BINPATH);
		JTextField textBmpPath = SwingUtil.getComponentByName(main.getComponentMap(), Main.TEXT_BMPPATH);
		
		try{
			BinReader binReader;
			byte[][] binData;
			for(GfxFileSpecs enumFile : enumFiles){
				binReader = new BinReader(textBinPath.getText(), enumFile);
				binData = binReader.readTilesBytes();
			}
		}catch(Exception ex){
			main.appendToLog("lalala");
		}
	}
}
