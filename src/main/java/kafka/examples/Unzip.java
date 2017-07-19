package kafka.examples;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip
{
    List<String> fileList;
    //File currentPath = new File("c:/documents and settings/Zachary/desktop");
    File INPUT_ZIP_FILE ;
    private static final String OUTPUT_FOLDER = "C:\\KafkaFiles";

    public static void main( String[] args )
    {
    	File currentPath = new File("C:\\RawZip");
    	File INPUT_ZIP_FILE ;
    	
        File [] files = currentPath.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isFile()){ //this line weeds out other directories/folders
                System.out.println(files[i]);
                INPUT_ZIP_FILE=files[i];
                Unzip unZip = new Unzip();
            	unZip.unZipIt(INPUT_ZIP_FILE,OUTPUT_FOLDER);
            	String index = INPUT_ZIP_FILE.getName();
                File  f1 = new File("C:/Archive_Kafka/"+index);
                INPUT_ZIP_FILE.renameTo(f1);
                
            }
        }
    	
    }

    /**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
     */



    
    void unZipIt(File zipFile, String outputFolder){

     byte[] buffer = new byte[1024];

     try{

    	//create output directory is not exists
    	File folder = new File(OUTPUT_FOLDER);
    	if(!folder.exists()){
    		folder.mkdir();
    	}

    	//get the zip file content
    	ZipInputStream zis =
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();

    	while(ze!=null){

    	   String fileName = ze.getName();
           File newFile = new File(outputFolder + File.separator + fileName);

           System.out.println("file unzip : "+ newFile.getAbsoluteFile());

            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }

            fos.close();
            ze = zis.getNextEntry();
    	}

        zis.closeEntry();
    	zis.close();

    	System.out.println("Done");

    }catch(IOException ex){
       ex.printStackTrace();
    }
   }
}