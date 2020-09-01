package com.jtgtgames;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil{
	
	public static void CreateNewFile(String path) {
		int last = path.lastIndexOf(File.separator);
		if (last > 0) {
			String directoryPath = path.substring(0, last);
			MakeDirectory(directoryPath);
		}

		File _file = new File(path);

		try {
			if (!_file.exists())
				_file.createNewFile();
		} catch (IOException _e) {
			_e.printStackTrace();
		}
	}
	public static void MakeDirectory(String path) {
		if (!isExistFile(path)) {
			File Efile = new File(path);
			Efile.mkdirs();
		}
	}
	public static boolean isExistFile(String path) {
		File Efile = new File(path);
		return Efile.exists();
	}
	public static void WriteFile(String path, String string) {
		CreateNewFile(path);
		FileWriter _FileWriter = null;

		try {
			_FileWriter = new FileWriter(new File(path), false);
			_FileWriter.write(string);
			_FileWriter.flush();
		} catch (IOException _e) {
			_e.printStackTrace();
		} finally {
			try {
				if (_FileWriter != null)
					_FileWriter.close();
			} catch (IOException _e) {
				_e.printStackTrace();
			}
		}
	}
	public static String ReadFilePath(String path) {
		CreateNewFile(path);

		StringBuilder strbuild = new StringBuilder();
		FileReader reader = null;
		try {
			reader = new FileReader(new File(path));

			char[] buffChar = new char[1024];
			int _length = 0;

			while ((_length = reader.read(buffChar)) > 0) {
				strbuild.append(new String(buffChar, 0, _length));
			}
		} catch (IOException _e) {
			_e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception _e) {
					_e.printStackTrace();
				}
			}
		}
		return strbuild.toString();
	}
}
