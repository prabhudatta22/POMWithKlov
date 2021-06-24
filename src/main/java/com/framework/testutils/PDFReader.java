/**
 * 
 */
package com.framework.testutils;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * @author prabhudatta
 *
 */
public class PDFReader {
	
	
	public String readPDFFile(String path)
	{
		String text = "";
		
		try {
			
		//File file = 	getLastModified(path);
		File file = 	new File(path);
		PDDocument doc = PDDocument.load(file);
		text = new PDFTextStripper().getText(doc);
		System.out.println("PDF files contains>>>  "+ text);
		doc.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	public File getLastModified(String directoryFilePath)
	{
	    File directory = new File(directoryFilePath);
	    File[] files = directory.listFiles(File::isFile);
	    long lastModifiedTime = Long.MIN_VALUE;
	    File chosenFile = null;

	    if (files != null)
	    {
	        for (File file : files)
	        {
	            if (file.lastModified() > lastModifiedTime && file.getAbsolutePath().contains("pdf"))
	            {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }
	    System.out.println("File choosen for read operation >>> "+chosenFile.getAbsolutePath());
	    return chosenFile;
	}

	
	public static void main(String[] args) {
		
		new PDFReader().readPDFFile("");
	}

}
