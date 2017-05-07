package demo.sphinx.helloworld;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

import java.net.URL;
import java.io.File;
import java.io.IOException;

public class HelloWorld {
	public static void main(String[] args) {
    	try {
    	String resultText = null;
        URL url;
        if (args.length > 0) 
        	url = new File(args[0]).toURI().toURL();
        	   
        else
        	url = HelloWorld.class.getResource("helloworld.config.xml.xml");

       
    ConfigurationManager configure = new ConfigurationManager(url);

	Recognizer recognizer = (Recognizer) configure.lookup("recognizer");
	Microphone microphone = (Microphone) configure.lookup("microphone");

    recognizer.allocate();
	    
     if (microphone.startRecording())
         {
    	 	 System.out.println("Command:\n");
        	 System.out.println("Shut Down\n "
            			+ "Command Prompt\n "
            			+ "Control Panel\n "
            			+ "My Computer\n "
            			+ "Task Manager\n ");
            
        	 while (true) 
        	 	{
    
        	 		Result result = recognizer.recognize();
		    
        	 		if (result != null)
        	 		{
		    	
        	 			resultText = result.getBestFinalResultNoFiller();
        	 		}
        	 		
        	 		System.out.println("Your command: " + resultText + "\n");
			
        	 		if(resultText.equalsIgnoreCase("Shut Down"))
			        {
			        	Runtime.getRuntime().exec("shutdown -s -t 0");
			        	System.exit(0);
 		
			        }
        	 		if (resultText.equalsIgnoreCase("Command Prompt"))
        	 		{
        	 			Runtime.getRuntime().exec("cmd /c start cmd");
 
        	 		}
        	 		if (resultText.equalsIgnoreCase("Control Panel"))
        	 		{
        	 			Runtime.getRuntime().exec("cmd /c start control");
 		     
        	 		}
        	 		if (resultText.equalsIgnoreCase("My Computer"))
        	 		{
        	 			Runtime.getRuntime().exec("cmd /c start explorer");
 		     
        	 		}
        	 		if (resultText.equalsIgnoreCase("Task Manager"))
        	 		{
        	 			Runtime.getRuntime().exec("cmd /c start taskmgr.exe");
 
        	 		}
        	 	}
        	 }
        	 		else
        	 		{
        	 			System.out.println("Failed. Try Again");
        	 			recognizer.deallocate();
        	 			System.exit(1);
        	 		}
        	 	}
        	 	catch (IOException e) {
                    System.err.println("Problem when loading" + e);
                    e.printStackTrace();
                } 
        	 	catch (PropertyException e) {
                    System.err.println("Problem configuring" + e);
                    e.printStackTrace();
                } 
        	 	catch (InstantiationException e) {
                    System.err.println("Problem creating" + e);
                    e.printStackTrace();
                }
        	 }
}
