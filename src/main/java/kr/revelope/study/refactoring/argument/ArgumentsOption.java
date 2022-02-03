package kr.revelope.study.refactoring.argument;

import org.apache.commons.cli.Option;

public enum ArgumentsOption {
	FILE_PATH("f", "file-path", true, "input file path"),
	COLUMN_NAME("c", "column-name", true, "input column name");

	private String opt;
	private String longOpt;
	private boolean hasArg;
	private String description;

	ArgumentsOption(String opt, String longOpt, boolean hasArg, String description) {
		this.opt = opt;
		this.longOpt = longOpt;
		this.hasArg = hasArg;
		this.description = description;
	}

	public String getOpt() {
		return opt;
	}

	public String getLongOpt() {
		return longOpt;
	}

	public boolean isHasArg() {
		return hasArg;
	}

	public String getDescription() {
		return description;
	}

	public Option makeOption() {
		return new Option(this.getOpt(), this.getLongOpt(), this.isHasArg(), this.getDescription());
	}
}
