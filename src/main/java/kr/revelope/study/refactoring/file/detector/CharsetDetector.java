package kr.revelope.study.refactoring.file.detector;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

// https://javacan.tistory.com/tag/CharsetDecoder
public class CharsetDetector {
	private final String[] charsets = {"UTF-8", "MS949"};

	private CharsetDetector() {}

	private static class Holder {
		static final CharsetDetector INSTANCE = new CharsetDetector();
	}

	public static CharsetDetector getInstance() {
		return Holder.INSTANCE;
	}

	public Charset getCharset(File file) throws IOException {
		for (String charsetName : charsets) {
			Charset charset = detectCharset(file, Charset.forName(charsetName));
			if (Objects.nonNull(charset)) {
				return charset;
			}
		}

		return StandardCharsets.UTF_8;
	}

	private Charset detectCharset(File file, Charset charset) throws IOException {
		try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
			CharsetDecoder decoder = charset.newDecoder();
			decoder.reset();

			byte[] fileBytes = new byte[bufferedInputStream.available()];
			if ((bufferedInputStream.read(fileBytes) > -1)) {
				decoder.decode(ByteBuffer.wrap(fileBytes));
			}

		} catch (CharacterCodingException e) {
			return null;
		}

		return charset;
	}
}
