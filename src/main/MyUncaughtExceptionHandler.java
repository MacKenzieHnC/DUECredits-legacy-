
package main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JOptionPane;

public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler
{

	@Override
	public void uncaughtException(Thread myThread, Throwable e)
	{

		System.out.println ( myThread.getName () + " throws exception: " + e );
		JOptionPane.showMessageDialog ( new javax.swing.JFrame () , "<html>" + myThread
				.getName () + " throws exception: " + e + ".\""
				+ "<br>This is an error I am not aware of. Please PM me or email me at MacKenzieHnC@gmail.com with the following:"
				+ "<br>&nbsp&nbsp&nbsp&nbsp "
				+ ""
				+ "1) What window you were using when the error occurred"
				+ ""
				+ "<br>&nbsp&nbsp&nbsp&nbsp "
				+ ""
				+ "2) What button you pressed that caused the error"
				+ ""
				+ "<br>&nbsp&nbsp&nbsp&nbsp "
				+ ""
				+ "3) The contents of the window that pops up after you close this one."
				+ ""
				+ "<br><br>Thank you, and I will get this error fixed as soon as possible."
				+ ""
				+ "<br>&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp "
				+ "&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp "
				+ "&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp "
				+ "&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp "
				+ "Best,"
				+ "<br>&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp "
				+ "&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp "
				+ "&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp "
				+ "&nbsp&nbsp&nbsp&nbsp &nbsp&nbsp&nbsp&nbsp "
				+ "MacKenzie" );
		e.printStackTrace ();
		try
		{
			Desktop.getDesktop ()
					.open ( new File ( "resources/DUECredits Errors.txt" ) );
		}
		catch(IOException e1)
		{
			{
				JOptionPane.showMessageDialog ( new javax.swing.JFrame () ,
						"<html>" + "Error opening \"DUECredits Errors.txt.\""
								+ "<br>Check to make sure it exists."
								+ "If this error persists, please email me at MacKenzieHnC@gmail.com with the error text." );
			}
		}

	}
}
