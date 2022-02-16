package kr.revelope.study.refactoring.argument.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import kr.revelope.study.refactoring.argument.option.OptionType;

public class CommandLineUtil {
	public static CommandLine makeCommandLine(String[] args, OptionType[] optionTypes) {
		Options options = OptionUtil.makeOptions(optionTypes);
		CommandLineParser commandLineParser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine commandLine = null;

		try {
			commandLine = commandLineParser.parse(options, args);
		} catch (ParseException e) {
			formatter.printHelp("argument-options", options);
		}

		return commandLine;
	}
}
