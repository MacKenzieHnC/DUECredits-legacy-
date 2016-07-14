package main;


public class TypeMinimizer
{

	private String type;
	private boolean isMinimized;
	
	TypeMinimizer(String type)
	{
		this.type = type;
		isMinimized = true;
	}
	
	public void setMinimized()
	{
		isMinimized = !isMinimized;
	}
	
	public String getType()
	{
		return type;
	}
	
	public boolean getMinimized()
	{
		return isMinimized;
	}
	
	public void setMaximized()
	{
		
		isMinimized = false;
		
	}
	
}
