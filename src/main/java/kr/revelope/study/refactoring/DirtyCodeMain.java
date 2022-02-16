package kr.revelope.study.refactoring;

import java.util.List;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;

import kr.revelope.study.refactoring.argument.Argument;
import kr.revelope.study.refactoring.argument.option.OptionType;
import kr.revelope.study.refactoring.argument.utils.CommandLineUtil;
import kr.revelope.study.refactoring.file.CSVFile;
import kr.revelope.study.refactoring.file.argument.CsvFileArgument;
import kr.revelope.study.refactoring.file.reader.FileReader;
import kr.revelope.study.refactoring.file.argument.Column;
import kr.revelope.study.refactoring.file.utils.ReaderUtil;

public class DirtyCodeMain {
	public static void main(String[] args) throws Exception {
		CommandLine commandLine = Optional.ofNullable(CommandLineUtil.makeCommandLine(args, OptionType.CSV_FILE_OPTIONS))
			.orElseThrow(() -> new IllegalArgumentException("Filename and Target column is required."));

		Argument argument = new Argument(commandLine);

		CsvFileArgument csvFileArgument = new CsvFileArgument(argument);
		String fileName = csvFileArgument.getFileName();
		List<Column> targetColumns = csvFileArgument.getColumns();

		try(FileReader reader = new FileReader(ReaderUtil.makeBufferReader(fileName))) {
			String[] columns = reader.readLine().split(",");
			List<String> contents = reader.readLines();

			CSVFile csvFile = new CSVFile(columns, contents);

			csvFile.groupingByTargetColumns(targetColumns)
				.forEach((key, value) -> System.out.println(key + " : " + value.size()));
		}
	}
}
