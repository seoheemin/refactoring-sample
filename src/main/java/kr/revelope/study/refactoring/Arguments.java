package kr.revelope.study.refactoring;

import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Arguments {
	private final Options options;
	private final CommandLine commandLine;

	public Arguments(String[] args) {
		this.options = makeOptions();
		this.commandLine = makeCommandLine(args);
	}

	private Options makeOptions() {
		Options options = new Options();

		Option filePath = new Option("f", "file-path", true, "input file path");
		filePath.setRequired(true);
		options.addOption(filePath);

		Option columnName = new Option("c", "column-name", true, "input column name");
		columnName.setRequired(true);
		options.addOption(columnName);

		return options;
	}

	private CommandLine makeCommandLine(String[] args) {
		CommandLineParser commandLineParser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine commandLine = null;

		try {
			commandLine = commandLineParser.parse(options, args);
		} catch (ParseException e) {
			formatter.printHelp("argument-options", options);
		}

		return Optional.ofNullable(commandLine)
			.orElseThrow(() -> new IllegalArgumentException("File name and target column name is required."));
	}

	public String getOptionValue(String optionName) {
		return this.commandLine.getOptionValue(optionName);
	}
}
