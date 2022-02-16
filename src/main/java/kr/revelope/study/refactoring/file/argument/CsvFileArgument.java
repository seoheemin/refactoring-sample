package kr.revelope.study.refactoring.file.argument;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.cli.Option;

import kr.revelope.study.refactoring.argument.Argument;
import kr.revelope.study.refactoring.argument.option.OptionType;

public class CsvFileArgument {
	private final Argument argument;

	public CsvFileArgument(Argument argument) {
		this.argument = argument;
	}

	public String getFileName() {
		return Optional.ofNullable(argument.getOptionValue(OptionType.FILE_PATH.getOpt()))
			.orElseThrow(() -> new IllegalArgumentException("File name is required."));
	}

	public List<Column> getColumns() {
		List<Column> columns = new ArrayList<>();

		Iterator<Option> iterator = argument.getOptionIterator();

		while (iterator.hasNext()) {
			Option option = iterator.next();

			if (OptionType.COLUMN_NAME.equals(option.getOpt(), option.getLongOpt())) {
				if (option.getValues().length > 1) {
					String[] values = option.getValues();
					for (int index = 0; index < values.length; index++) {
						Column column = new Column();
						column.setName(values[index]);

						if (index + 1 == values.length) {
							if (iterator.hasNext()) {
								option = iterator.next();
								if (OptionType.VALUE.equals(option.getOpt(), option.getLongOpt())) {
									column.setValue(option.getValue());
								}
							}
						}

						columns.add(column);
					}
				} else if (option.getValues().length == 1) {
					Column column = new Column();
					column.setName(option.getValue());

					if (iterator.hasNext()) {
						option = iterator.next();
						if (OptionType.VALUE.equals(option.getOpt(), option.getLongOpt())) {
							column.setValue(option.getValue());
						}
					}

					columns.add(column);
				}
			}
		}

		columns.forEach(column -> Optional.ofNullable(column.getName())
			.orElseThrow(() -> new IllegalArgumentException("Target column is required.")));

		return columns;
	}
}
