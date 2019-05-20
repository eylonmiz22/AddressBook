
public class Originator 
{
	private String record;
	
	public void Set(String newrecord)
	{
		this.record = newrecord;
	}
	
	public Memento storeInMemento()
	{
		return new Memento(this.record);
	}
	
	public String restoreFromMemento(Memento memento)
	{
		this.record = memento.getSavedRecord();
		return this.record;
	}
}
