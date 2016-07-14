package settings;

import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.Segment;
import javax.swing.text.Utilities;

public class SetJLabelHeight
{

	private static int labelHeight;

	public static void setJLabelHeight(int fixedWidth, String text, JLabel label)
	{
		
		FontMetrics fm = label.getFontMetrics(label.getFont());
	    PlainDocument doc = new PlainDocument();
	    Segment segment = new Segment();
	    try {
	        doc.insertString(0, text, null);
	    } catch (BadLocationException e) {

	    }

	    StringBuffer sb = new StringBuffer();
	    int noOfLine = 0;
	    for (int i = 0; i < text.length();) {
	        try {
	            doc.getText(i, text.length() - i, segment);
	        } catch (BadLocationException e) {
	            throw new Error("Can't get line text");
	        }
	        int breakpoint = Utilities.getBreakLocation(segment, fm, 0, fixedWidth, null, 0);
	        sb.append(text.substring(i, i + breakpoint));
	        sb.append("<br/>");
	        i += breakpoint;

	        noOfLine++;
	    }

	    labelHeight = noOfLine * fm.getHeight();
	    label.setPreferredSize(new Dimension(fixedWidth, (int)(labelHeight * 1.5)));
		
	}
	
}
