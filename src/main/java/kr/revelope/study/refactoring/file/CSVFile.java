package kr.revelope.study.refactoring.file;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.math.NumberUtils;

import kr.revelope.study.refactoring.file.argument.Column;

public class CSVFile {
	private final String[] columns;
	private final List<String> contents;

	public CSVFile(String[] columns, List<String> contents) {
		this.columns = columns;
		this.contents = contents;
	}

	public Map<String, List<String>> groupingByTargetColumns(List<Column> targetColumns) {
		List<Integer> targetColumnIndexes = targetColumns.stream()
			.map(Column::getName)
			.map(this::getTargetColumnIndex)
			.collect(Collectors.toList());

		List<String> targetColumnValues = targetColumns.stream()
			.map(Column::getValue)
			.collect(Collectors.toList());

		int length = columns.length;
		return contents.stream()
			.map(content -> content.split(","))
			.filter(content -> {
				if (content.length != length) {
					return false;
				}

				return !IntStream.range(0, targetColumnValues.size())
					.filter(index -> targetColumnValues.get(index) != null && NumberUtils.isParsable(targetColumnValues.get(index)))
					.filter(index -> NumberUtils.isParsable(content[targetColumnIndexes.get(index)]))
					.filter(index -> Integer.parseInt(content[targetColumnIndexes.get(index)]) < Integer.parseInt(targetColumnValues.get(index)))
					.findAny().isPresent();
			})
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
