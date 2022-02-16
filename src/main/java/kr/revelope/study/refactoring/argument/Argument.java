package kr.revelope.study.refactoring.argument;

import java.util.Iterator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

public class Argument {
	private final CommandLine commandLine;

	public Argument(CommandLine commandLine) {
		this.commandLine = commandLine;
	}

	public Iterator<Option> getOptionIterator() {
		return this.commandLine.iterator();
	}

	public String getOptionValue(String optionName) {
		return this.commandLine.getOptionValue(optionName);
	}
}
