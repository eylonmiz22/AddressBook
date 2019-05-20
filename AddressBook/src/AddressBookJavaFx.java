import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
public class AddressBookJavaFx extends Application
{	public static void main(String[] args)
    { launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		AddressBookPane secondPane1 = AddressBookPane.getInstance();
		AddressBookPane secondPane2 = AddressBookPane.getInstance();
		AddressBookPane firstPane = new FirstAddressBookPane(AddressBookPane.getInstance()).getABP();
		
		Scene firstScene = new Scene(firstPane);
		Scene secondScene1 = new Scene(secondPane1);
		Scene secondScene2 = new Scene(secondPane2);
		
		Stage secondStage1 = new Stage();
		Stage secondStage2 = new Stage();
		
		primaryStage.setTitle("AddressBook");
		secondStage1.setTitle("AddressBook");
		secondStage2.setTitle("AddressBook");
		
		primaryStage.setScene(firstScene);
		secondStage1.setScene(secondScene1);
		secondStage2.setScene(secondScene2);
		
		primaryStage.show();
		secondStage1.show();
		secondStage2.show();
		primaryStage.setAlwaysOnTop(true);
	}
}

class AddressBookPane extends GridPane
{
  private static int numOfPanes = 0;
  public static final int MAX_NUM_OF_PANES = 3;
  
  private RandomAccessFile raf;
  private FlowPane jpButton = new FlowPane();

  // Text fields
  private TextField jtfName = new TextField();
  private TextField jtfStreet = new TextField();
  private TextField jtfCity = new TextField();
  private TextField jtfState = new TextField();
  private TextField jtfZip = new TextField();
  // Buttons
  private FirstButton jbtFirst;
  private NextButton jbtNext;
  private PreviousButton jbtPrevious;
  private LastButton jbtLast;
  public EventHandler<ActionEvent> ae = 
	new EventHandler<ActionEvent>()
  {	public void handle(ActionEvent arg0)
    { ((Command) arg0.getSource()).Execute();
	}
  };
  private AddressBookPane()
  { // Open or create a random access file
 	try
 	{ setRaf(new RandomAccessFile("address.dat", "rw"));
	} 
 	catch (IOException ex)
 	{ System.out.print("Error: " + ex);
	  System.exit(0);
	}
	jtfState.setAlignment(Pos.CENTER_LEFT);
	jtfState.setPrefWidth(100);
	jtfZip.setPrefWidth(60);
	jbtFirst = new FirstButton(this, getRaf());
	jbtNext = new NextButton(this, getRaf());
	jbtPrevious = new PreviousButton(this, getRaf());
	jbtLast = new LastButton(this, getRaf());
	Label state = new Label("State");
	Label zp = new Label("Zip");
	Label name = new Label("Name");
	Label street = new Label("Street");
	Label city = new Label("City");		
	// Panel p1 for holding labels Name, Street, and City
	GridPane p1 = new GridPane();
	p1.add(name, 0, 0);
	p1.add(street, 0, 1);
	p1.add(city, 0, 2);
	p1.setAlignment(Pos.CENTER_LEFT);
	p1.setVgap(8);
	p1.setPadding(new Insets(0, 2, 0, 2));
	GridPane.setVgrow(name, Priority.ALWAYS);
	GridPane.setVgrow(street, Priority.ALWAYS);
	GridPane.setVgrow(city, Priority.ALWAYS);
	// City Row
	GridPane adP = new GridPane();
	adP.add(jtfCity, 0, 0);
	adP.add(state, 1, 0);
	adP.add(jtfState, 2, 0);
	adP.add(zp, 3, 0);
	adP.add(jtfZip, 4, 0);
	adP.setAlignment(Pos.CENTER_LEFT);
	GridPane.setHgrow(jtfCity, Priority.ALWAYS);
	GridPane.setVgrow(jtfCity, Priority.ALWAYS);
	GridPane.setVgrow(jtfState, Priority.ALWAYS);
	GridPane.setVgrow(jtfZip, Priority.ALWAYS);
	GridPane.setVgrow(state, Priority.ALWAYS);
	GridPane.setVgrow(zp, Priority.ALWAYS);
	// Panel p4 for holding jtfName, jtfStreet, and p3
	GridPane p4 = new GridPane();
	p4.add(jtfName, 0, 0);
	p4.add(jtfStreet, 0, 1);
	p4.add(adP, 0, 2);
	p4.setVgap(1);
	GridPane.setHgrow(jtfName, Priority.ALWAYS);
	GridPane.setHgrow(jtfStreet, Priority.ALWAYS);
	GridPane.setHgrow(adP, Priority.ALWAYS);
	GridPane.setVgrow(jtfName, Priority.ALWAYS);
	GridPane.setVgrow(jtfStreet, Priority.ALWAYS);
	GridPane.setVgrow(adP, Priority.ALWAYS);
	// Place p1 and p4 into jpAddress
	GridPane jpAddress = new GridPane();
	jpAddress.add(p1, 0, 0);
	jpAddress.add(p4, 1, 0);
	GridPane.setHgrow(p1, Priority.NEVER);
	GridPane.setHgrow(p4, Priority.ALWAYS);
	GridPane.setVgrow(p1, Priority.ALWAYS);
	GridPane.setVgrow(p4, Priority.ALWAYS);
	// Set the panel with line border
	jpAddress.setStyle("-fx-border-color: grey;"
			+ " -fx-border-width: 1;"
			+ " -fx-border-style: solid outside ;");
	// Add buttons to a panel
	jpButton.setHgap(5);
	jpButton.getChildren().addAll(jbtFirst, 
		jbtNext, jbtPrevious, jbtLast);
	jpButton.setAlignment(Pos.CENTER);
	GridPane.setVgrow(jpButton, Priority.NEVER);
	GridPane.setVgrow(jpAddress, Priority.ALWAYS);
	GridPane.setHgrow(jpButton, Priority.ALWAYS);
	GridPane.setHgrow(jpAddress, Priority.ALWAYS);
	// Add jpAddress and jpButton to the stage
	this.setVgap(5);
	this.add(jpAddress, 0, 0);
	this.add(jpButton, 0, 1);
	jbtFirst.setOnAction(ae);
	jbtNext.setOnAction(ae);
	jbtPrevious.setOnAction(ae);
	jbtLast.setOnAction(ae);
	jbtFirst.Execute();
  }
  public static AddressBookPane getInstance() 
  {

	  if(numOfPanes < MAX_NUM_OF_PANES)
	  {
		  numOfPanes++;
		  return new AddressBookPane();
	  }
	  else
	  {
		  System.err.println("Singelton violation. Only " + MAX_NUM_OF_PANES + " panes were created");
		  return null;
	  }        
  }
	public void actionHandled(ActionEvent e)
	{ ((Command) e.getSource()).Execute();
	}
	public void SetName(String text)
	{ jtfName.setText(text);
	}
	public void SetStreet(String text)
	{ jtfStreet.setText(text);
	}
	public void SetCity(String text)
	{ jtfCity.setText(text);
	}
	public void SetState(String text)
	{ jtfState.setText(text);
	}
	public void SetZip(String text)
	{ jtfZip.setText(text);
	}
	public String GetName()
	{ return jtfName.getText();
	}
	public String GetStreet()
	{ return jtfStreet.getText();
	}
	public String GetCity()
	{ return jtfCity.getText();
	}
	public String GetState()
	{ return jtfState.getText();
	}
	public String GetZip()
	{ return jtfZip.getText();
	}
	public RandomAccessFile getRaf() 
	{
		return raf;
	}
	public void setRaf(RandomAccessFile raf)
	{
		this.raf = raf;
	}
	public FlowPane getJpButton()
	{
		return this.jpButton;
	}
	public void setJpButton(FlowPane jpButton)
	{
		this.jpButton = jpButton;
	}
}
interface Command
{ public void Execute();
}
class CommandButton extends Button implements Command
{ public final static int NAME_SIZE = 32;
  public final static int STREET_SIZE = 32;
  public final static int CITY_SIZE = 20;
  public final static int STATE_SIZE = 10;
  public final static int ZIP_SIZE = 5;
  public final static int RECORD_SIZE = 
	(NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);
  protected static Originator originator = new Originator();
  protected static CareTaker careTaker = new CareTaker();
  protected static boolean undoEnabled = false; 
  protected static boolean redoEnabled = false; 
  protected AddressBookPane p;
  protected RandomAccessFile raf;
  public CommandButton(AddressBookPane pane, RandomAccessFile r)
  {	super();
	p = pane;
	raf = r;
  }
  public void Execute()
  {
  }
  /** Write a record at the end of the file */
  public void writeAddress()
  {	try
    { raf.seek(raf.length());
	  FixedLengthStringIO.writeFixedLengthString(p.GetName(), 
		 NAME_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(p.GetStreet(),
		 STREET_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(p.GetCity(), 
		 CITY_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(p.GetState(),
		STATE_SIZE, raf);
	  FixedLengthStringIO.writeFixedLengthString(p.GetZip(), 
		ZIP_SIZE, raf);
	 } 
     catch (IOException ex)
     { ex.printStackTrace();
	 }
   }
   /** Reads a record at the specified position */
   public void readAddress(long position) throws IOException
   { raf.seek(position);
	 String name = 
	   FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
	 String street = 
	   FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
  	 String city = 
  	   FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
	 String state = 
	   FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
  	 String zip = 
  	   FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
	 p.SetName(name);
	 p.SetStreet(street);
	 p.SetCity(city);
	 p.SetState(state);
	 p.SetZip(zip);
	}
   /** Reads a record from given strings */
   public void readAddressFromStrings(String name, String street, String city,
		   						  	  String state, String zip)
   { 
	 p.SetName(name);
	 p.SetStreet(street);
	 p.SetCity(city);
	 p.SetState(state);
	 p.SetZip(zip);
   }
}
class AddButton extends CommandButton
{ public AddButton(AddressBookPane pane, RandomAccessFile r)
  {	super(pane, r);
	this.setText("Add");
  }
  @Override
  public void Execute()
  {	
   	  writeAddress();
   	  String record = p.GetName() + "," + p.GetStreet() + "," + p.GetCity() + ","
   	  				+ p.GetState() + "," + p.GetZip();
   	  originator.Set(record);
   	  careTaker.addMemento(originator.storeInMemento());
   	  undoEnabled = true;
  }
}
class UndoButton extends CommandButton
{ 
	public UndoButton(AddressBookPane pane, RandomAccessFile r)
	{	
		super(pane, r);
		this.setText("Undo");
	}
	@Override
	public void Execute()
	{	
		try 
		{
			if(!careTaker.getS1().isEmpty() && undoEnabled)
			{
				if(raf.length() > 0)
				{
					originator.restoreFromMemento(careTaker.getMemento2());
					raf.setLength(raf.length() - 2 * RECORD_SIZE);
					raf.seek(0);
					readAddress(2 * RECORD_SIZE);
				}
				else
				{
					readAddressFromStrings("", "", "", "", "");
				}
				redoEnabled = true;
			}
			else undoEnabled = false;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

class RedoButton extends CommandButton
{ 
	public RedoButton(AddressBookPane pane, RandomAccessFile r)
	{	
		super(pane, r);
		this.setText("Redo");
	}
	@Override
	public void Execute()
	{	
		if(!careTaker.getS2().isEmpty() && redoEnabled)
		{
			String[] recordArr = originator.restoreFromMemento(careTaker.getMemento1()).split(",");
			readAddressFromStrings(recordArr[0], recordArr[1], recordArr[2],
								   recordArr[3], recordArr[4]);
			writeAddress();
		   	originator.Set(careTaker.getS1().peek().getSavedRecord());
			undoEnabled = true;
		}
		else redoEnabled = false;
	}
}

class NextButton extends CommandButton
{ public NextButton(AddressBookPane pane, RandomAccessFile r)
  {	super(pane, r);
	this.setText("Next");
  }
  @Override
  public void Execute()
  {	try
    { long currentPosition = raf.getFilePointer();
	  if (currentPosition < raf.length())
		readAddress(currentPosition);
	} 
    catch (IOException ex)
    { ex.printStackTrace();
	}
  }
}
class PreviousButton extends CommandButton
{ public PreviousButton(AddressBookPane pane, RandomAccessFile r)
  {	super(pane, r);
	this.setText("Previous");
  }
  @Override
  public void Execute()
  {	try
    { long currentPosition = raf.getFilePointer();
	  if (currentPosition - 2 * 2 * RECORD_SIZE >= 0)
	 	    readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
	  else;
	  } 
      catch (IOException ex)
      {	ex.printStackTrace();
	  }
	}
  }
class LastButton extends CommandButton
{ public LastButton(AddressBookPane pane, RandomAccessFile r)
  {	super(pane, r);
	this.setText("Last");
  }
  @Override
  public void Execute()
  {	try
    { long lastPosition = raf.length();
	  if (lastPosition > 0)
		readAddress(lastPosition - 2 * RECORD_SIZE);
	} 
    catch (IOException ex)
    { ex.printStackTrace();
	}
   }
}
class FirstButton extends CommandButton
{ public FirstButton(AddressBookPane pane, RandomAccessFile r)
  {	super(pane, r);
	this.setText("First");
  }
  @Override
  public void Execute()
  {	try
    { if (raf.length() > 0) readAddress(0);
	} 
    catch (IOException ex)
    { ex.printStackTrace();
	}
  }
}

