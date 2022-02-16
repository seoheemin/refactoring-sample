package kr.revelope.study.refactoring.argument.option;

import org.apache.commons.cli.Option;

public enum OptionType {
	FILE_PATH("f", "file-path", false, "input file path", 1, true),
	COLUMN_NAME("c", "column-name", true, "input column name", -2, true),
	VALUE("v", "value", true, "input value", -2, false);

	public static OptionType[] CSV_FILE_OPTIONS = { FILE_PATH, COLUMN_NAME, VALUE };

	private final String opt;
	private final String longOpt;
	private final boolean hasArg;
	private final String description;
	private final int numberOfArgs;
	private final boolean required;

	OptionType(String opt, String longOpt, boolean hasArg, String description, int numberOfArgs, boolean required) {
		this.opt = opt;
		this.longOpt = longOpt;
		this.hasArg = hasArg;
		this.description = description;
		this.numberOfArgs = numberOfArgs;
		this.required = required;
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

	public int getNumberOfArgs() {
		return numberOfArgs;
	}

	public boolean isRequired() {
		return required;
	}

	public Option makeOption() {
		return new Option(this.getOpt(), this.getLongOpt(), this.isHasArg(), this.getDescription());
	}

	public boolean equals(String opt, String longOpt) {
		return this.getOpt().equals(opt) || this.getLongOpt().equals(longOpt);
	}
}
