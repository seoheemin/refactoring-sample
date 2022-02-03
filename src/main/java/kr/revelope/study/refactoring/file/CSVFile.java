package kr.revelope.study.refactoring.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import kr.revelope.study.refactoring.file.reader.FileReader;

public class CSVFile {
	private final BufferedReader reader;
	private final String[] columns;
	private final List<String> contents;

	public CSVFile(String filName) throws Exception {
		try(FileReader fileReader = new FileReader(filName)) {
			this.reader = fileReader.getBufferedReader();
			this.columns = readHeader().split(",");
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

	public Map<String, List<String>> groupingByTargetColumn(String targetColumnName) {
		int targetColumnIndex = getTargetColumnIndex(targetColumnName);

		int length = columns.length;
		return contents.stream()
			.map(content -> content.split(","))
			.filter(content -> content.length == length)
			.map(content -> content[targetColumnIndex])
			.collect(Collectors.groupingBy(targetColumn -> targetColumn));
	}

	public Map<String, List<String>> groupingByTargetColumns(String[] targetColumnNames) {
		List<Integer> targetColumnIndexes = Arrays.stream(targetColumnNames)
			.map(this::getTargetColumnIndex)
			.collect(Collectors.toList());

		int length = columns.length;
		return contents.stream()
			.map(content -> content.split(","))
			.filter(content -> content.length == length)
			.map(content -> targetColumnIndexes.stream()
				.map(targetColumnIndex -> content[targetColumnIndex])
				.collect(Collectors.joining(",")))
			.collect(Collectors.groupingBy(targetColumn -> targetColumn));
	}

	public int getTargetColumnIndex(String targetColumnName) {
		int targetColumnIndex = Arrays.asList(columns).indexOf(targetColumnName);

		if (targetColumnIndex == -1) {
			throw new IllegalStateException(String.format("Can not found target column %s", targetColumnName));
		}

		return targetColumnIndex;
	}
}
