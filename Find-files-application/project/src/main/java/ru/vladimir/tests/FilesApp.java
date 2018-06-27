package ru.vladimir.tests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FilesApp {
	
	//Хранение расширений файлов и их количества
	public static Map<String, Integer> amount = new HashMap<String, Integer>();
	//Хранение общего количества файлов и папок
	public static short[] countDF = new short[] {0, 0};
	
	/**
	 * Определение расширения файла
	 * @param mystr
	 * @return Расширение файла
	 */
	private static String getFileExtension(String mystr) {
		int index = mystr.indexOf('.');
		return index == -1? null : mystr.substring(index);
	}
	
	/**
	 * Увеличение значения по ключу
	 * @param key
	 */
	public static void incValue(String key) {
		Integer i = amount.get(key);
		if(i != null) {
			amount.put(key, ++i);
		} else {
			amount.put(key, 1);
		}
	}
	
	/**
	 * Анализ каталогов и файлов
	 * @param str
	 * @return
	 */
	public static void getDirsFiles(String str) {
		File dir = new File(str);		
		for(File item : dir.listFiles()) {
			if(item.isDirectory() && !(item.isHidden())) {
				countDF[0]++;
				System.out.println(item.getName() + "\t-----\tкаталог");
				getDirsFiles(item.getAbsolutePath());
			}
			else if (item.isFile()) {
				countDF[1]++;
				incValue(getFileExtension(item.getName()));
				System.out.println(item.getName() + "\t-----\tфайл");
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Введите путь до папки (например: C://имя_папки): ");
		getDirsFiles(sc.nextLine());
		System.out.println("\n=============\nTotal:\n=============");
		System.out.println("Количество папок: " + countDF[0] + "\nКоличество файлов: " + countDF[1] +
				"\n\nКоличество типов файлов:");
		for(Map.Entry<String, Integer> i : amount.entrySet()) {
			System.out.printf("%s: %s \n", i.getKey(), i.getValue());
		}
	}

}
