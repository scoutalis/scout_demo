package scout.testdata;

/**
 * Created by amid.k on 7/16/2017.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.Iterator;


import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	//test
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
  //  private static XSSFRow Row;

    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
    public static void setExcelFile(String Path,String SheetName) throws Exception {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        }
        catch (Exception e){
            throw (e);
        }
    }

    public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow)    throws Exception
    {
        String[][] tabArray = null;
        try{
            FileInputStream ExcelFile = new FileInputStream(FilePath);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            //Get Number of Column
            //int noOfColumns = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
            //int noOfColumns1 = ExcelWSheet.getRow(0).getLastCellNum();

            int startCol = 1;
            int ci=0,cj=0;
            int totalRows = 1;
            //int totalRows = ExcelWSheet.getLastRowNum();
            //int totalCols = 5;
            int totalCols = ExcelWSheet.getRow(0).getLastCellNum();


            tabArray=new String[totalRows][totalCols];
/**
            for (int irow=1;irow<=totalRows;irow++)
                for (int icol= 1;icol<=totalCols;icol++) {
                    String str = getCellData(irow,icol);
                    tabArray[irow - 1][icol-1] = str;
                }
*/
            for (int j=startCol;j<=totalCols;j++, cj++)
            {
                tabArray[ci][cj]=getCellData(iTestCaseRow,j);
                //System.out.println(tabArray[ci][cj]);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }

        catch (IOException e)
        {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }

        return(tabArray);
    }


    public static String[] getInputDataHeaders(String FilePath, String SheetName) throws Exception{

        String[]headerArray = null;

        try{
            FileInputStream ExcelFile = new FileInputStream(FilePath);
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            //Get Number of Column
            //int noOfColumns = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
            int noOfColumns = ExcelWSheet.getRow(0).getLastCellNum();
            headerArray = new String[noOfColumns];
            for (int icol=0;icol<noOfColumns;icol++)
                headerArray[icol] =getCellData(0,icol);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }

        catch (IOException e)
        {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return (headerArray);
    }

    
    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public static String getCellData(int RowNum, int ColNum) throws Exception{
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;

        }catch (Exception e){
            return"";
        }

    }

    public static String getCellData(int RowNum, String ColumnName) throws Exception{
        try{
            int ColNum = getRowByHeader(ColumnName);
            String CellData ="";
            if(ColNum>-1) {
                Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                CellData = Cell.getStringCellValue();
            }
            return CellData;

        }catch (Exception e){
            return"";
        }

    }

    public static String getTestCaseName(String sTestCase)throws Exception{
        String value = sTestCase;
        try{
            int posi = value.indexOf("@");
            value = value.substring(0, posi);
            posi = value.lastIndexOf(".");
            value = value.substring(posi + 1);
            return value;
        }catch (Exception e){
            throw (e);
        }

    }

    public static int getRowContains(String sTestCaseName, int colNum) throws Exception{
        int i;
        try {
            int rowCount = ExcelUtils.getRowUsed();
            for ( i=0 ; i<rowCount; i++){
                if  (ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
                    break;
                }
            }
            return i;
        }catch (Exception e){
            throw(e);
        }
    }

    public static int getRowByHeader(String sHeader) throws Exception{

        int ifound = -1;
        try {
            int colCount = ExcelWSheet.getRow(0).getLastCellNum();
            for ( int i=0 ; i<colCount; i++){
              //  String str = ExcelUtils.getCellData(0,i);
                if  (ExcelUtils.getCellData(0,i).equalsIgnoreCase(sHeader)){
                    ifound = i;
                    break;
                }
            }
            return ifound;
        }catch (Exception e){
            throw(e);
        }
    }


    public static int getRowUsed() throws Exception {

        try{
            int RowCount = ExcelWSheet.getLastRowNum();
            return RowCount;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw (e);
        }
    }
}
