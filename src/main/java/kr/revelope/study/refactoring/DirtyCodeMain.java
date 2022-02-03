package kr.revelope.study.refactoring;

import kr.revelope.study.refactoring.argument.Arguments;
import kr.revelope.study.refactoring.file.CSVFile;

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
 * // todo 3. -c를 여러개 줄 수 있도록 해주세요. (순서중요)
 *
 * // todo 4-1. -f file.csv -c column1 -v value1 -c column2 -v value2 column1 이 숫자 컬럼인 경우 value1 값보다 크고 column2 가 숫자 컬럼인 경우 value2 값보다 큰 데이터에 대하여 그룹 카운트
 * // todo column1-value1 / column2-value2 셋트, 숫자가 아닌경우에는 무시
 *
 * // todo 4-2. -f file.csv -c column1 -c column2 -v value2 column2 가 숫자 컬럼인 경우 value2 값보다 큰 데이터에 대하여 그룹 카운트
 * // todo column1은 상관안하고, column2-value2만 신경쓰면됨.
 *
 * 아래 코드를 리팩토링 해보시오
 */
public class DirtyCodeMain {
	public static void main(String[] args) throws Exception {
		Arguments arguments = new Arguments(args);

		String fileName = arguments.getOptionValue("file-path"); // f or file-path
		String[] targetColumnName = arguments.getOptionValues("column-name"); // c or column-name

		CSVFile csvFile = new CSVFile(fileName);

		csvFile.groupingByTargetColumns(targetColumnName)
			.forEach((key, value) -> System.out.println(key + " : " + value.size()));
	}
}
