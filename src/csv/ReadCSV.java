package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
	static String m_auto = null;
	static boolean m_moveToNext = false;
	static boolean m_defaultTomahawkPosition = false;
	static int lineToRefference;
	static String value = null;//Holds the the specific object that we want's values
	static int i = 0;

	public ReadCSV(String auto, boolean moveToNext, boolean defaultTomahawkPosition) {
		m_auto = auto;
		m_moveToNext = moveToNext;
		m_defaultTomahawkPosition = defaultTomahawkPosition;
	}

	public static void main(String m_auto, boolean m_moveToNext) {
		BufferedReader br = null;//Create a Buffered Reader object and set it to null
		try {//Try executing and handle exceptions
			String csvFile = "/Users/Alexander the geek/Desktop/Autonomous.csv";//String to identify location of CSV file

			br = new BufferedReader(new FileReader(csvFile));//Set the Buffered Reader object to a new Buffered Reader

			List<String> lines = new ArrayList<>();//Create an List of String which will hold each independent line of the CSV file
			String line = null;// Create a Line object which is identified as a string this will later be appended to the list
			while ((line = br.readLine()) != null) {//Loop continuously until you reach a line while does not hold any values
				lines.add(line);//For each iteration of the loop append a line String to the List contain the value of total row of the CSV
			}
			for (int i = 0; i < lines.size(); i++) {//For each line appended to the List lines
				String column = lines.get(i);//Create a new string which will help us to find the values of the first column through each iteration
				if (column == null) {//Double check that it is not null even though he loop should stop before it reaches one which is
					break;//Stop the loop if it is
				} else if (column != null) {//If it is okay
					String[] first = column.split(",");//Create an 1D array of strings
					if (first[0] == m_auto) {//Check to see if the first object in every row is equivalent to the desired name
						lineToRefference = i;//If it is set the line reference to that number so that we know the line to reference later

					}
				}
			}

			String chosenLine = lines.get(lineToRefference);//Create a new string equivalent to the line which was chosen earlier
			String[] command = chosenLine.split(",");//Create a 1D array of strings so we can get each object
			int loop = 0;//Integer to control loop timing

			List<String> objects = new ArrayList<>();//Create a new List of strings which will hold each object retrieved from the line
			while (!command[loop].equals("\"last\"")) {//Loop until we reach the word "last"
				objects.add(command[loop]);//Append the current object in string to the list
				loop++;//Add toe the control integer so that the loop will move to the next object
			}
			while (i < objects.size()) {//while the static control integer at the beginning of the class remains less than the number of objects in the List
				String element = objects.get(i);//Create a new string which will hold the current object
				if (element == null || element ==  "\"last\"") {//Double check if the object is either null or equivalent to "last"
					value = null;//if either if true the value  is set to null
				} else if (element != null && element != "\"last\"") {//Else if the object is neither null nor equivalent to "last"
					value = element;//Set the final value to that object
				}
				if (m_moveToNext) {//If the command is given add to the integer and move to the next object
					i++;
				}
			}

		} catch (FileNotFoundException e) {//Here down handle exceptions and create method so that we can retrieve values from the current object
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getValue() {
		return value;
	}

	public boolean getTomahawkState(boolean m_defaultTomahawkPosition) {
		String v = getValue();
		if (v.equals("\"ba\"")) {
			return !m_defaultTomahawkPosition;
		} else
			return m_defaultTomahawkPosition;
	}

	public boolean getVisionInitialize() {
		String v = getValue();
		if (v.equals("\"sh\"")) {
			return true;
		} else
			return false;
	}

	public boolean turnToAngle() {
		String v = getValue();
		if (v.charAt(0) == 't') {
			return true;
		} else
			return false;
	}

	public boolean autoDrive() {
		String v = getValue();
		if (v.charAt(0) == 'd') {
			return true;
		} else
			return false;
	}

	public int turnToAnglePos() {
		String v = getValue();
		final int value = Integer.parseInt(v.substring(2, v.length()));
		if (v.charAt(0) == 't' && v.charAt(1) == '+') {

			return (value);

		} else
			return -1;

	}

	public boolean anglePosReverse() {
		String v = getValue();
		if (v.charAt(1) == '-') {
			return true;

		} else
			return false;
	}

	public int autoDriveDistance() {
		String v = getValue();
		final int value = Integer.parseInt(v.substring(2, v.length()));
		if (v.charAt(0) == 'd' && v.charAt(1) == '+') {

			return (value);

		} else
			return -1;
	}

	public boolean reverseDrive() {
		String v = getValue();
		if (v.charAt(1) == '-') {
			return true;

		} else
			return false;
	}

	public String getSelectedAuto() {
		return m_auto;

	}

	public boolean moveToNext() {
		return m_moveToNext;
	}
}
