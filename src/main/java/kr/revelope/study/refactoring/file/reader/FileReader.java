package kr.revelope.study.refactoring.file.reader;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileReader implements Closeable {
	private final BufferedReader reader;

	public FileReader(BufferedReader reader) {
		this.reader = reader;
	}

	public String readLine() throws IOException {
		return Optional.ofNullable(reader.readLine())
			.orElseThrow(() -> new IllegalArgumentException("First line must be columns. Column can not found."));
	}

	public List<String> readLines() {
		return reader.lines().collect(Collectors.toList());
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}
}
