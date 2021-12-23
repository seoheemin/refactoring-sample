package kr.revelope.study.refactoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileReader {
	private BufferedReader reader;

	public FileReader(String fileName) {
		this.reader = Optional.ofNullable(DirtyCodeMain.class.getClassLoader().getResourceAsStream(fileName))
		.map(is -> new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)))
		.orElseThrow(() -> new IllegalArgumentException("'" + fileName + "' file can not found."));
	}

	public String readHeader() throws IOException {
		return Optional.ofNullable(reader.readLine())
			.orElseThrow(() -> new IllegalArgumentException("First line must be columns. Column can not found."));
	}

	public List<String> readContents() throws IOException {
		return reader.lines().collect(Collectors.toList());
	}
}
