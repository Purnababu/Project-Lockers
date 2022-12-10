package com.Lockers.com;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class LockedMe {

	static String DIRECTORY;
	File folder_name;

	public LockedMe() {
		DIRECTORY = System.getProperty("user.dir");
		folder_name = new File(DIRECTORY + "/files");
		if (!folder_name.exists())
			folder_name.mkdirs();
		System.out.println("DIRECTORY : " + folder_name.getAbsolutePath());
	}
//welcome screen
	public void WelcomeScreen() {
		System.err.println("                             **********WELCOME LOKEDME.COM**********");
		System.out.println("Developer--> P.Purna Babu");
		System.out.println("Email-->p.purnababu@gmail.com");

	}
//menuoptions for user
	private static void Mainmenu() {
		System.out.println("Hello user you ar in  Main menu");
		System.out.println("Select any of the following options");
		System.out.println("1 -> List files in directory");
		System.out.println("2 -> Add, Delete or Search");
		System.out.println("3 -> Exit Program");
	}
//secondarymenuoptins for user
	private static void Secondarymainmenu() {
		System.out.println("Select any of the following");
		System.out.println(" a -> Do you want to Add a file");
		System.out.println(" b -> Do you want to  Delete a file");
		System.out.println(" c -> Do you want to Search a file");
		System.out.println(" d -> Want Return to Mainmenu ");
	}
//implementation of primarymenuoption
	void showPrimaryMenu() {
		 Mainmenu();
		try {
			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			switch (option) {
			case 1: {
				showFiles();
				showPrimaryMenu();
			}
			case 2: {
				Secondarymainmenu();
			showSecondaryMenu();}
			case 3: {
				System.out.println("Thank You");
				System.exit(0);
			}
			default:
				showPrimaryMenu();
			}
		} catch (Exception e) {
			System.out.println("Please enter 1, 2 or 3");
			showPrimaryMenu();
		}
	}
//implementation of secondarymenu 
	void showSecondaryMenu() {
		
		try {
			Scanner scanner = new Scanner(System.in);
			char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
			char option = input[0];

			switch (option) {
			case 'a': {
				System.out.print("↳ Adding a file...Please Enter a File Name : ");
				String filename = scanner.next().trim().toLowerCase();
				addFile(filename);
				break;
			}
			case 'b': {
				System.out.print("↳ Deleting a file...Please Enter a File Name : ");
				String filename = scanner.next().trim();
				deleteFile(filename);
				break;
			}
			case 'c': {
				System.out.print("↳ Searching a file...Please Enter a File Name : ");
				String filename = scanner.next().trim();
				searchFile(filename);
				break;
			}
			case 'd': {
				System.out.println("Going Back to MAIN menu");
				showPrimaryMenu();
				break;
			}
			default:
				System.out.println("Please enter a, b, c or d");
			}
			showSecondaryMenu();
		} catch (Exception e) {
			System.out.println("Please enter a, b, c or d");
			showSecondaryMenu();
		}
	}
//Method for show files
	void showFiles() {
		if (folder_name.list().length == 0)
			System.out.println("The folder is empty");
		else {
			String[] list = folder_name.list();
			System.out.println("The files in " + folder_name + " are :");
			Arrays.sort(list);
			for (String str : list) {
				System.out.println(str);
			}
		}
	}
//Method for add file
	void addFile(String filename) throws IOException {
		File filepath = new File(folder_name + "/" + filename);
		String[] list = folder_name.list();
		for (String file : list) {
			if (filename.equalsIgnoreCase(file)) {
				System.out.println("File " + filename + " already exists at " + folder_name);
				return;
			}
		}
		filepath.createNewFile();
		System.out.println("File " + filename + " added to " + folder_name);
	}
//Method for deletefile.
	void deleteFile(String filename) {
		File filepath = new File(folder_name + "/" + filename);
		String[] list = folder_name.list();
		for (String file : list) {
			if (filename.equals(file) && filepath.delete()) {
				System.out.println("File " + filename + " deleted from " + folder_name);
				return;
			}
		}
		System.out.println("Delete Operation failed. FILE NOT FOUND");
	}
//method for search file
	void searchFile(String filename) {
		String[] list = folder_name.list();
		for (String file : list) {
			if (filename.equals(file)) {
				System.out.println("FOUND : File " + filename + " exists at " + folder_name);
				return;
			}
		}
		System.out.println("File NOT found (FNF)");
	}
//main method to run the program
	public static void main(String[] args) {

		LockedMe menu = new LockedMe();
		
		menu.WelcomeScreen();
		System.out.println();
		menu.showPrimaryMenu();
	}

}
