package genericlibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains reusable methods to read the data from excel
 * 
 * @author kuthadi.Tarunteja
 *
 */
public class ExcelUtility {
	private Workbook wb;

	/**
	 * This method is used to initialize excel
	 * 
	 * @param excelpath
	 */

	public void excelInitilization(String excelpath) {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(excelpath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {

			e.printStackTrace();
		}

	}
	
	/**
	 * This method is used to read the data from execl sheet
	 * @param SheetName
	 * @param ExpectedTest
	 * @return
	 */

	public Map<String, String> readFromExcel(String SheetName, Object ExpectedTest) {
		Map<String, String> map = new HashedMap<String, String>();

		DataFormatter df = new DataFormatter();

		Sheet sh = wb.getSheet(SheetName);

		for (int i = 0; i < sh.getLastRowNum(); i++) {

			if (df.formatCellValue(sh.getRow(i).getCell(1)).equals(ExpectedTest)) {
				for (int j = 0; j < sh.getLastRowNum(); j++) {
					map.put(df.formatCellValue(sh.getRow(j).getCell(2)), df.formatCellValue(sh.getRow(j).getCell(3)));

					if (df.formatCellValue(sh.getRow(j).getCell(2)).equals("#####"))
						break;

				}

				break;
			}

		}
		return map;

	}
	
	/**
	 * This method is used to update test status in excel
	 * 
	 * @param sheetName
	 * @param expectedTest
	 * @param status
	 * @param execlPath
	 */

	public void writeExcel(String sheetName, String expectedTest, String  status, String execlPath) {
		Sheet sh = wb.getSheet(sheetName);
		for (int i = 0; i <= sh.getLastRowNum(); i++) {
			if (sh.getRow(i).getCell(1).getRichStringCellValue().equals(expectedTest)) {
				sh.getRow(i).createCell(4).setCellValue(status);
				break;

			}
		}

		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(execlPath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
/**
 * This method is used to close excel 
 */
	
	// comment
	public void CloseExcel() {
		try {
			wb.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
