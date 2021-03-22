/**
 * Created by Nitin D on 2020
 */
package main.java.utils;

import io.appium.java_client.MobileElement;

import static main.java.core.BaseTestClass.excelDataFile;

public class ExcelFunctions {

    /**
     * function will get value in excel
     *
     * @param sheetName
     * @param colName
     * @param romNumber
     * @return
     */
    public static String getValueFromExcel(String sheetName, String colName, int romNumber) {

        String value = excelDataFile.getCellData(sheetName, colName, romNumber);
        return value;
    }

    /**
     * function will set value in excel
     *
     * @param sheetName
     * @param colName
     * @param romNumber
     * @param data
     */
    public static void setValueInExcel(String sheetName, String colName, int romNumber, String data) {

        excelDataFile.setCellData(sheetName, colName, romNumber, data);
    }

    /**
     * function will set random data in excel data file which can be only number or alphabets
     *
     * @param sheetName
     * @param sColumnName
     * @param romNumber
     * @param sOnlyNumber
     * @param iNumberOfCharacter
     */
    public static void setRandomValue(String sheetName, String sColumnName, int romNumber, String sOnlyNumber, int iNumberOfCharacter) {

        String value = null;

        if (sOnlyNumber.equalsIgnoreCase("Yes")) {

            value = UtilLib.generateRandomNumber(iNumberOfCharacter);

        } else {

            value = UtilLib.generateRandomString(iNumberOfCharacter);
        }

        setValueInExcel(sheetName, sColumnName, romNumber, value);
    }

    /**
     * function will type excel data or type random data if excel data is blank
     *
     * @param element
     * @param excelMappedVariable
     * @param sheetName
     * @param sColumnName
     * @param romNumber
     * @param sOnlyNumber
     * @param iNumberOfCharacter
     */
    public static void setRandomValueOrDefaultValueInExcel(MobileElement element, String excelMappedVariable, String sheetName, String sColumnName, int romNumber, String sOnlyNumber, int iNumberOfCharacter) {

        element.clear();

        if (excelMappedVariable.equals("") || excelMappedVariable.equals(null)) {

            setRandomValue(sheetName, sColumnName, romNumber, sOnlyNumber, iNumberOfCharacter);

            element.setValue(excelMappedVariable);
        } else {

            element.setValue(excelMappedVariable);
        }
    }
}
