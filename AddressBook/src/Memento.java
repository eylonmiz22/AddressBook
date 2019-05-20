
public class Memento
{
	private String record;
	
	public Memento(String record)
	{
		this.record = record;
	}
	
	public String getSavedRecord()
	{
		return this.record;
	}
}
