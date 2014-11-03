package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * The Class Template.
 */
public class Template {

	/**
	 * Write file.
	 *
	 * @param filePath the file path
	 * @param content the content
	 */
	public static void writeFile(String filePath, String content) {
		File file = new File(filePath);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Read and print template.
	 *
	 * @param templateFileName the template file name
	 * @param variables the variables
	 * @param outputFileName the output file name
	 */
	public static void readAndPrintTemplate(String templateFileName, HashMap<String, String> variables, String outputFileName) {
		String s = readTemplate(templateFileName, variables);
		writeFile(outputFileName, s);
	}
	
	/**
	 * Read template.
	 *
	 * @param templateFileName the template file name
	 * @param variables the variables
	 * @return the string
	 */
	public static String readTemplate(String templateFileName, HashMap<String, String> variables) {
		String s = getFileContent(templateFileName);
		s = replaceVariablesInString(s, variables);
		return s;
	}
	
	/**
	 * Gets the file content.
	 *
	 * @param fileName the file name
	 * @return the file content
	 */
	private static String getFileContent(String fileName) {
		String s = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				s += line + "\n";
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * Replace variables in string.
	 *
	 * @param string the string
	 * @param variables the variables
	 * @return the string
	 */
	private static String replaceVariablesInString(String string, HashMap<String, String> variables) {
		String s = string;
		for (String k : variables.keySet()) {
			s = s.replaceAll(k, variables.get(k));
		}
		return s;
	}

}