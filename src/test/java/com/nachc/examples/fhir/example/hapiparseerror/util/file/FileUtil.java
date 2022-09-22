package com.nachc.examples.fhir.example.hapiparseerror.util.file;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	
	public static String getAsString(String filePath) {
		InputStream is = null;
		File file = getForLocalFile(filePath);
		if(file != null && file.exists()) {
			is = getInputStream(file);
		} else {
			is = FileUtil.class.getResourceAsStream(filePath);
		}
		return getAsString(is);
	}

	public static String getAsString(InputStream is) {
		return head(is, null);
	}

	public static File getForLocalFile(String filePath) {
		File file = new File(filePath);
		if (file != null && file.exists()) {
			return file;
		} else {
			return null;
		}
	}

	public static InputStream getInputStream(File file) {
		try {
			InputStream rtn = new FileInputStream(file);
			return rtn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	public static String head(InputStream is, Integer n) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			int cnt = 0;
			while (line != null) {
				cnt++;
				if (n != null && cnt > n) {
					break;
				}
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String rtn = sb.toString();
			return rtn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception exp) {
					throw new RuntimeException(exp);
				}
			}
		}
	}

}
