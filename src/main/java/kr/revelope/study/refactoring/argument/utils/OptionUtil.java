package kr.revelope.study.refactoring.argument.utils;

import java.util.Arrays;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import kr.revelope.study.refactoring.argument.option.OptionType;

public class OptionUtil {
	public static Options makeOptions(OptionType[] optionTypes) {
		Options options = new Options();

		Arrays.asList(optionTypes).forEach(optionValue -> {
				Option option = optionValue.makeOption();
				option.setArgs(optionValue.getNumberOfArgs());
				option.setRequired(optionValue.isRequired());
				options.addOption(option);
			});

		return options;
	}
}
