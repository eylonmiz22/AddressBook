import javafx.scene.control.Button;

public class ABPDecorator
{
	private AddressBookPane tempABP;
	
	public ABPDecorator(AddressBookPane abp)
	{
		this.tempABP = abp;
	}
	
	public void addButtons(Button addBt, Button redoBt, Button undoBt)
	{
		addBt.setOnAction(this.tempABP.ae);
		redoBt.setOnAction(this.tempABP.ae);
		undoBt.setOnAction(this.tempABP.ae);
		this.tempABP.getJpButton().getChildren().addAll(addBt, redoBt, undoBt);
	}
	
	public AddressBookPane getABP()
	{
		return this.tempABP;
	}
}
