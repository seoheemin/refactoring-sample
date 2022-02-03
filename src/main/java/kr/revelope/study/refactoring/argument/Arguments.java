package kr.revelope.study.refactoring.argument;

import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Arguments {
	private final CommandLine commandLine;

	public Arguments(String[] args) {
		this.commandLine = makeCommandLine(args);
	}

	private CommandLine makeCommandLine(String[] args) {
		Options options = ArgumentsOptions.getInstance().getOptions();
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

	public String[] getOptionValues(String optionName) {
		return this.commandLine.getOptionValues(optionName);
	}
}
