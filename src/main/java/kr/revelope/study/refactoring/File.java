package kr.revelope.study.refactoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class File {
	private String fileName;
	private BufferedReader reader;

	public File(String fileName) {
		this.fileName = fileName;
		setBufferedReader();
	}

	private void setBufferedReader() {
		this.reader = Optional.ofNullable(DirtyCodeMain.class.getClassLoader().getResourceAsStream(fileName))
			.map(is -> new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)))
			.orElseThrow(() -> new IllegalArgumentException("'" + fileName + "' file can not found."));
	}

	public String getFileHeader() throws IOException {
		return Optional.ofNullable(reader.readLine())
			.orElseThrow(() -> new IllegalArgumentException("First line must be columns. Column can not found."));
	}

	public List<String> getFileContents() throws IOException {
		List<String> fileContents = new ArrayList<>();

		String content;
		while((content = reader.readLine()) != null) {
			fileContents.add(content);
		}

		return fileContents;
	}
}
