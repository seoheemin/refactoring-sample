package kr.revelope.study.refactoring.argument;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class ArgumentsOptions {
	private Options options;

	private ArgumentsOptions() {
		makeOptions();
	}

	private static class Holder {
		static final ArgumentsOptions INSTANCE = new ArgumentsOptions();
	}

	public static ArgumentsOptions getInstance() {
		return Holder.INSTANCE;
	}

	public Options getOptions() {
		return this.options;
	}

	private void makeOptions() {
		options = new Options();

		Option filePath = ArgumentsOption.FILE_PATH.makeOption();
		filePath.setRequired(true);
		options.addOption(filePath);

		Option columnName = ArgumentsOption.COLUMN_NAME.makeOption();
		columnName.setRequired(true);
		columnName.setArgs(Option.UNLIMITED_VALUES);
		options.addOption(columnName);
	}
}
