package kr.revelope.study.refactoring;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;

public class FileReader implements Closeable {
	private final BufferedReader bufferedReader;

	public FileReader(String fileName) throws Exception {
		this.bufferedReader = makeBufferReader(fileName);
	}

	public BufferedReader makeBufferReader(String fileName) throws Exception {
		File file = new File(
			Optional.ofNullable(DirtyCodeMain.class.getClassLoader().getResource(fileName))
				.orElseThrow(() -> new FileNotFoundException(String.format("%s file can not found.", fileName)))
				.toURI()
		);

		CharsetDetector charsetDetector = new CharsetDetector();
		Charset charset = Optional.ofNullable(charsetDetector.getCharset(file))
			.orElseThrow(() -> new IOException("UTF-8 또는 MS949 형식의 파일이 아닙니다."));

		return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
	}

	public BufferedReader getBufferedReader() {
		return this.bufferedReader;
	}

	@Override
	public void close() {

	}
}
