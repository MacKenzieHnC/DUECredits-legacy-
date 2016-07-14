
package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class AppClass
{

	public static void main(String[] args)
	{

		GUI gui = new GUI ();
		gui.generateGUI ();
		

		PrintStream out = null;
		try
		{
			out = new PrintStream ( new FileOutputStream (
					"resources/DUECredits Errors.txt" ) );

			System.setErr ( out );
			Thread.setDefaultUncaughtExceptionHandler ( new MyUncaughtExceptionHandler() );
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace ();
		}

	}

}
