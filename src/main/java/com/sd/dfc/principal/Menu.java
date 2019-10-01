package com.sd.dfc.principal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

	public String presentMenu() {
		return "-----Command Menu-----\n" + "1 - create\n" + "2 - readAll\n" + "3 - update\n" + "4 - delete\n"
				+ "Type 'exit' for quit.";
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.presentMenu();
	}

}
