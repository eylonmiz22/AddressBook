
public class FirstAddressBookPane extends ABPDecorator
{
	//Members to add
	private AddButton jbtAdd;
	private RedoButton jbtRedo;
	private UndoButton jbtUndo;

	public FirstAddressBookPane(AddressBookPane abp)
	{
		super(abp);
		if(abp != null)
		{
			this.jbtAdd = new AddButton(abp, abp.getRaf());
			this.jbtRedo = new RedoButton(abp, abp.getRaf());
			this.jbtUndo = new UndoButton(abp, abp.getRaf());
			addButtons(this.jbtAdd, this.jbtRedo, this.jbtUndo);
		}
	}

}
