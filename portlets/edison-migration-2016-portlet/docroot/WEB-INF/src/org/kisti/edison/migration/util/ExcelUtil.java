package org.kisti.edison.migration.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.kisti.edison.util.CustomUtil;

import com.liferay.portal.kernel.util.DateUtil;

public class ExcelUtil {
	// EXCEL TYPE 따른 VALUE 가지고 오기
	public static String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		} else {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					return String.valueOf(formatter.format(cell.getDateCellValue()));
				} else {
					return String.valueOf((long) cell.getNumericCellValue()).trim();
				}
			case Cell.CELL_TYPE_BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue()).trim();
			case Cell.CELL_TYPE_FORMULA:
				switch (cell.getCachedFormulaResultType()) {
				case Cell.CELL_TYPE_NUMERIC:
					if (cell.getCellStyle().getDataFormatString().contains("%")) {
						return roundOff(((double) cell.getNumericCellValue() * 100), 2) + "%";
					} else {
						return String.valueOf((long) cell.getNumericCellValue()).trim();
					}

				case Cell.CELL_TYPE_STRING:
					return cell.getStringCellValue().replaceAll("'", "").trim();
				}
			default:
				return cell.getStringCellValue().trim();
			}
		}
	}
	
	public static Date getDateCellCalue(Cell cell) throws ParseException{
		if ( CustomUtil.strNull(cell).equals("")) {
			return null;
		}else {
			DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					return sdFormat.parse(sdFormat.format(cell.getDateCellValue()));
				case Cell.CELL_TYPE_STRING:
					String date = getCellValue(cell);
					return sdFormat.parse(date);
				default:
					return cell.getDateCellValue();
			}	
			
		}		
	}

	public static String roundOff(double num, int point) {
		return String.valueOf(Math.floor(num * Math.pow(10, point) + 0.5) / Math.pow(10, point));
	}

	public static void removeAllFiles(String parentPath) {
		//경로 하위 파일및 폴더 삭제 메소드
		
		File file = new File(parentPath); 
		
		if(file.exists()){
			String[] fnameList = file.list();
			int fCnt = fnameList.length;
			String childPath = "";
	
			if(fCnt > 0 ){
				for (int i = 0; i < fCnt; i++) {
					childPath = parentPath + "/" + fnameList[i];
					File f = new File(childPath);
					if (!f.isDirectory()) {
						f.delete(); // 파일이면 바로 삭제
					} else {//재귀함수
						removeAllFiles(childPath);
					}
				}
			}
			file.delete(); // 폴더는 맨 나중에 삭제
		}
	}
}
