package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.HotelModel;

public class Storage {
	private HotelModel hotelM;
	// private static final String FILE_LOCATION ="C:\\Temp\\Hotel.ser";
	private static final String FILE_LOCATION = "Hotel.ser";

	public Storage() {

	}

	public static void storeObject(Object o, String filename) {
		try {
			File f = new File(FILE_LOCATION);
			FileOutputStream fileOut = new FileOutputStream(f);
			if (!f.exists()) {
				f.createNewFile();
			}
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + f);
		} catch (IOException i) {
			storeObject(i, "IOException.ser");
		}
	}

	public static Object readObject(String filename) {
		Object obj = null;
		try {
			FileInputStream fileIn = new FileInputStream(FILE_LOCATION);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			obj = in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException a) {
			storeObject(a, "FileNotFound.ser");
		} catch (IOException i) {
			storeObject(i, "IOException.ser");
		} catch (ClassNotFoundException c) {
			storeObject(c, "ClassNotFoundException.ser");
		}
		return obj;
	}

}
