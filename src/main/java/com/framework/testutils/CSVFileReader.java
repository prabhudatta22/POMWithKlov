/**
 * 
 */
package com.framework.testutils;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
/**
 * @author prabhudatta
 *
 */
public class CSVFileReader {
	
	
	public List<String[]> readCSVFile(String filepath)
	{
		List<String[]> r  = null;
		try (CSVReader reader = new CSVReader(new FileReader(filepath))){
			
		      r = reader.readAll();
		      
		  }
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return r;
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
	            if (file.lastModified() > lastModifiedTime && file.getAbsolutePath().contains("csv"))
	            {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }
	    System.out.println("File choosen for read operation >>> "+chosenFile.getAbsolutePath());
	    return chosenFile;
	}

}