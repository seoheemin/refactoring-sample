package kr.revelope.study.refactoring.file.argument;

public class Column {
	private String name; // -c
	private String value; // -v

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
