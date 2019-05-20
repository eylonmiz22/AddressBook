import java.util.Stack;

public class CareTaker
{
	private Stack<Memento> s1 = new Stack<Memento>(); //Stack for Undo
	private Stack<Memento> s2 = new Stack<Memento>(); //Stack for redo
	
	public void addMemento(Memento m)
	{//For AddButton: adds a memento into s1
		this.s1.push(m);
	}
	
	public Memento getMemento2()
	{//For UndoButton: takes the top memento of s1 and put it in s2
		this.s2.push(this.s1.pop());
		return s2.peek();
	}
	
	public Memento getMemento1()
	{//For RedoButton: takes the top memento of s2 and put it in s1
		this.s1.push(this.s2.pop());
		return s1.peek();
	}
	
	public Stack<Memento> getS1()
	{
		return this.s1;
	}
	
	public Stack<Memento> getS2()
	{
		return this.s2;
	}
}
