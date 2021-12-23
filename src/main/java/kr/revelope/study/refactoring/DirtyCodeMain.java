package kr.revelope.study.refactoring;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * csv 파일을 읽어서 특정 컬럼명을 group by 하여 출력하는 프로그램이다.
 * args[0] : resources에 보관된 csv 파일명
 * args[1] : 카운트 할 컬럼명
 *
 * 아래 코드를 리팩토링 해보시오
 */
public class DirtyCodeMain {
	public static void main(String[] args) {
		if (args == null || args.length < 2) {
			throw new IllegalArgumentException("File name and target column name is required.");
		}

		String fileName = args[0];
		String targetColumn = args[1];

		try {
			File file = new File(fileName);

			String[] header = file.getFileHeader().split(",");
			int length = header.length;
			int targetIndex = Arrays.asList(header).indexOf(targetColumn);

			if (targetIndex == -1) {
				throw new IllegalStateException("Can not found target column '" + targetColumn + "'");
			}

			file.getFileContents().stream()
				.map(content -> content.split(","))
				.filter(content -> content.length == length)
				.map(content -> content[targetIndex])
				.collect(Collectors.groupingBy(target -> target))
				.forEach((key, value) -> System.out.println(key + ":" + value.size()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
