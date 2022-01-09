package kr.revelope.study.refactoring;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Objects;

// https://javacan.tistory.com/tag/CharsetDecoder
public class CharsetDetector {
	private final String[] charsets = {"UTF-8", "MS949"};

	public Charset getCharset(File file) {
		for (String charsetName : charsets) {
			Charset charset = detectCharset(file, Charset.forName(charsetName));
			if (Objects.nonNull(charset)) {
				return charset;
			}
		}

		return null;
	}

	private Charset detectCharset(File file, Charset charset) {
		try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
			CharsetDecoder decoder = charset.newDecoder();
			decoder.reset();

			byte[] fileBytes = new byte[bufferedInputStream.available()];

			if ((bufferedInputStream.read(fileBytes) > -1)) {
				decoder.decode(ByteBuffer.wrap(fileBytes));
			}
		} catch (Exception e) {
			return null;
		}

		return charset;
	}
}
