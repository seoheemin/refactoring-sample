package kr.revelope.study.refactoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CSVFile {
	private BufferedReader reader;
	private String header;
	private List<String> contents;

	public CSVFile(String filName) throws Exception {
		try(FileReader fileReader = new FileReader(filName)) {
			this.reader = fileReader.getBufferedReader();
			this.header = readHeader();
			this.contents = readContents();
		}
	}

	public String readHeader() throws IOException {
		return Optional.ofNullable(reader.readLine())
			.orElseThrow(() -> new IllegalArgumentException("First line must be columns. Column can not found."));
	}

	public List<String> readContents() {
		return reader.lines().collect(Collectors.toList());
	}

	public List<String> findTargetColumn(String targetColumnName) {
		String[] columns = header.split(",");

		int targetColumnIndex = Arrays.asList(columns).indexOf(targetColumnName);
		if (targetColumnIndex == -1) {
			throw new IllegalStateException(String.format("Can not found target column %s", targetColumnName));
		}

		int length = columns.length;
		return contents.stream()
			.map(content -> content.split(","))
			.filter(content -> content.length == length)
			.map(content -> content[targetColumnIndex])
			.collect(Collectors.toList());
	}
}
