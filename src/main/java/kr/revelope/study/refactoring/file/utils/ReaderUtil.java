package kr.revelope.study.refactoring.file.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;

import kr.revelope.study.refactoring.file.detector.CharsetDetector;
import kr.revelope.study.refactoring.file.reader.FileReader;

public class ReaderUtil {
	public static BufferedReader makeBufferReader(String fileName) throws Exception {
		File file = new File(
			Optional.ofNullable(FileReader.class.getClassLoader().getResource(fileName))
				.orElseThrow(() -> new FileNotFoundException(String.format("%s file can not found.", fileName)))
				.toURI()
		);

		Charset charset = Optional.ofNullable(CharsetDetector.getInstance().getCharset(file))
			.orElseThrow(() -> new IOException("UTF-8 또는 MS949 형식의 파일이 아닙니다."));

		return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
	}
}
