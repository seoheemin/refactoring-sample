package kr.revelope.study.refactoring;

import java.util.stream.Collectors;
/**
 * csv 파일을 읽어서 특정 컬럼명을 group by 하여 출력하는 프로그램이다.
 * args[0] : resources에 보관된 csv 파일명
 * args[1] : 카운트 할 컬럼명
 *
 *
 * // todo 1. arguments 받는 방식을 바꿔주세요.
 * // --file-path {filePath} 또는 -f {filePath}
 * // --column-name {columnName} 또는 -c {columnName}
 *
 * // todo 2. charset이 ms949인 것도 들어올 수 있게 하기
 *
 * 아래 코드를 리팩토링 해보시오
 */
public class DirtyCodeMain {
	public static void main(String[] args) throws Exception {
		Arguments arguments = new Arguments(args);

		String fileName = arguments.getOptionValue("file-path"); // f or file-path
		String targetColumnName = arguments.getOptionValue("column-name"); // c or column-name

		CSVFile csvFile = new CSVFile(fileName);

		csvFile.findTargetColumn(targetColumnName).stream()
			.collect(Collectors.groupingBy(target -> target))
			.forEach((key, value) -> System.out.println(key + ":" + value.size()));
	}
}
