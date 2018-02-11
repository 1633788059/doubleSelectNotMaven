package com.doubleselect.util.excel;

import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.doubleselect.model.student.Tstudent;
import com.doubleselect.model.teacher.Tteacher;

public class ExcelUtil {

	/**
	 * 将结果集写入到工作簿的一个sheet中
	 * 
	 * @param rs
	 *            结果集
	 * @param wb
	 *            工作簿
	 * @param headers
	 *            表头
	 * @throws Exception
	 */
	public static void fillExcelData(List list, Workbook wb, String[] headers) throws Exception {
		int rowIndex = 0; // 定义一个行索引
		Sheet sheet = wb.createSheet("学生信息表"); // 创建一个sheet
		Row row = sheet.createRow(rowIndex++); // 创建第一行
		for (int i = 0; i < headers.length; i++) {
			/**
			 * 设置列的类型,为文本型
			 */
			HSSFCell cell = (HSSFCell) row.createCell(i); 
			
			/**
			 * 设置列的类型,为文本型
			 */
			HSSFCellStyle cellStyle2 = (HSSFCellStyle)wb.createCellStyle();
	        HSSFDataFormat format = (HSSFDataFormat)wb.createDataFormat();
	        cellStyle2.setDataFormat(format.getFormat("@"));
	        //row.createCell(i).setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setCellStyle(cellStyle2); 
	        
			cell.setCellValue(headers[i]); // 写入表头
			

			cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
		}
		for(int j=0;j<list.size();j++){
			row = sheet.createRow(rowIndex++);
			Tstudent tstudent=(Tstudent)list.get(j);
			//String headers[] = { "学号", "姓名", "性别", "专业","最后学历","毕业学校","专业特长","毕业时间"}; 
			List<String> list2=new ArrayList<String>();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String str=sdf.format(tstudent.getTime());
			list2.add(tstudent.getStudentsno());
			list2.add(tstudent.getStudentname());
			list2.add(tstudent.getStudentsex());
			list2.add(tstudent.getStudentmajor());
			list2.add(tstudent.getFinaldegree());
			list2.add(tstudent.getGraduation());
			list2.add(tstudent.getExpertise());
			list2.add(str);
			for (int i = 0; i < headers.length; i++) {
				
				 HSSFCell cell = (HSSFCell) row.createCell(i); 
				
				/**
				 * 设置列的类型,为文本型
				 */
				HSSFCellStyle cellStyle2 = (HSSFCellStyle)wb.createCellStyle();
		        HSSFDataFormat format = (HSSFDataFormat)wb.createDataFormat();
		        cellStyle2.setDataFormat(format.getFormat("@"));
		        //row.createCell(i).setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setCellStyle(cellStyle2); 
		        
		        cell.setCellValue(list2.get(i));
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
			}
		}
	}

	/**
	 * 将结果集写入到工作簿的一个sheet中
	 * 
	 * @param rs
	 *            结果集
	 * @param wb
	 *            工作簿
	 * @param headers
	 *            表头
	 * @throws Exception
	 */
	public static void fillExcelDataByteacher(List list, Workbook wb, String[] headers) throws Exception {
		int rowIndex = 0; // 定义一个行索引
		Sheet sheet = wb.createSheet("教师信息表"); // 创建一个sheet
		Row row = sheet.createRow(rowIndex++); // 创建第一行
		for (int i = 0; i < headers.length; i++) {
			/**
			 * 设置列的类型,为文本型
			 */
			HSSFCell cell = (HSSFCell) row.createCell(i); 
			
			/**
			 * 设置列的类型,为文本型
			 */
			HSSFCellStyle cellStyle2 = (HSSFCellStyle)wb.createCellStyle();
	        HSSFDataFormat format = (HSSFDataFormat)wb.createDataFormat();
	        cellStyle2.setDataFormat(format.getFormat("@"));
	        //row.createCell(i).setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell.setCellStyle(cellStyle2); 
	        
			cell.setCellValue(headers[i]); // 写入表头
			

			cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
		}
		for(int j=0;j<list.size();j++){
			row = sheet.createRow(rowIndex++);
			Tteacher tteacher=(Tteacher)list.get(j);
		
			List<String> list2=new ArrayList<String>();
			list2.add(tteacher.getTeachersno());
			list2.add(tteacher.getTeachername());
			list2.add(tteacher.getTeachersex());
			list2.add(tteacher.getTeachermajor());
			list2.add(tteacher.getDegree());
			list2.add(tteacher.getGuidemin()+"");
			list2.add(tteacher.getGuidemax()+"");
			for (int i = 0; i < headers.length; i++) {
				
				 HSSFCell cell = (HSSFCell) row.createCell(i); 
				
				/**
				 * 设置列的类型,为文本型
				 */
				HSSFCellStyle cellStyle2 = (HSSFCellStyle)wb.createCellStyle();
		        HSSFDataFormat format = (HSSFDataFormat)wb.createDataFormat();
		        cellStyle2.setDataFormat(format.getFormat("@"));
		        //row.createCell(i).setCellType(HSSFCell.CELL_TYPE_STRING);
		        cell.setCellStyle(cellStyle2); 
		        
		        cell.setCellValue(list2.get(i));
		        cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
			}
		}
	}
	
	
	/**
	 * 利用模版导出数据 1.读入模版 2.读取模版 3.写入数据
	 * 
	 * @param rs
	 * @param templateFileName
	 * @return wb
	 * @throws Exception
	 */
	public static Workbook fillExcelDataByTemplate(ResultSet rs, String templateFileName) throws Exception {
		InputStream inp=ExcelUtil.class.getResourceAsStream("/com/asiainfo/template/"+templateFileName);
		POIFSFileSystem fs=new POIFSFileSystem(inp);
		Workbook wb = new HSSFWorkbook(fs);
		Sheet sheet=wb.getSheetAt(0);
		// 获取列数
		int cellNums=sheet.getRow(0).getLastCellNum();
		int rowIndex=1;
		while(rs.next()){
			Row row=sheet.createRow(rowIndex++);
			for(int i=0;i<cellNums;i++){
				row.createCell(i).setCellValue(rs.getObject(i+1).toString());
			}
		}
		ExcelExtractor ee = new ExcelExtractor((HSSFWorkbook) wb);
		//System.out.println(ee.getText());
		return wb;
	}

	/**
	 * 设置格式
	 * @param hssfCell
	 * @return
	 */
	public static String formatCell(HSSFCell hssfCell) {
		if(hssfCell==null){
			return "";
		}else{
			if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
				//System.out.println("bollean");
				return String.valueOf(hssfCell.getBooleanCellValue());
			}else if(hssfCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
				//System.out.println("number");
				return String.valueOf(hssfCell.getNumericCellValue());
			}else{
				//System.out.println("String");
				return String.valueOf(hssfCell.getStringCellValue());
			}
		}
	}
}
