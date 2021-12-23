package kr.revelope.study.refactoring;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class File {
	private String header;
	private List<String> contents;

	public File(String header, List<String> contents) {
		this.header = header;
		this.contents = contents;
	}

	public List<String> findTargetColumn(String targetColumnName) {
		String[] columns = header.split(",");

		int length = columns.length;
		int targetColumnIndex = Arrays.asList(columns).indexOf(targetColumnName);

		if (targetColumnIndex == -1) {
			throw new IllegalStateException("Can not found target column '" + targetColumnName + "'");
		}

		return contents.stream()
			.map(content -> content.split(","))
			.filter(content -> content.length == length)
			.map(content -> content[targetColumnIndex])
			.collect(Collectors.toList());
	}
}
