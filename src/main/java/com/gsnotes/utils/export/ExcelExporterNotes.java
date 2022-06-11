package com.gsnotes.utils.export;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.CFRuleBase.ComparisonOperator;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormattingRule;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFFontFormatting;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFPatternFormatting;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheetConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelExporterNotes {	
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	private String[] columnNames;
	private String[][] data;
	private String[][] headerData;
	private String sheetName = "";

	public ExcelExporterNotes(String[] columnNames, String[][] data, String sheetName, String[][] headerData) {
		this.columnNames = columnNames;
		this.headerData = headerData;
		this.data = data;
		this.sheetName = sheetName;
		workbook = new XSSFWorkbook();

	}
	
	private void writeHeaderCells() {
		sheet = workbook.createSheet(sheetName);

		Row row0 = sheet.createRow(0);
		Row row1 = sheet.createRow(1);
		Row row = sheet.createRow(3);
		
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
        style.setBorderBottom(BorderStyle.THIN);  
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);  
        style.setBorderLeft(BorderStyle.THIN); 
	    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		
		
		CellStyle styleNormal = workbook.createCellStyle();
		XSSFFont fontNormal = workbook.createFont();
        styleNormal.setBorderBottom(BorderStyle.THIN);
        styleNormal.setBorderTop(BorderStyle.THIN);  
        styleNormal.setBorderRight(BorderStyle.THIN);  
        styleNormal.setBorderLeft(BorderStyle.THIN);  
	    styleNormal.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	    styleNormal.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		fontNormal.setBold(true);
		fontNormal.setFontHeight(16);
		styleNormal.setFont(fontNormal);
		
		
		CellStyle styleNrml = workbook.createCellStyle();
		XSSFFont fontNrml = workbook.createFont();
		fontNrml.setFontHeight(16);
		styleNrml.setFont(fontNrml);
		
		createCell(row0, 0, headerData[0][0], styleNormal);
		createCell(row0, 1, headerData[0][1], styleNrml);
		createCell(row0, 2, headerData[0][2], styleNormal);
		createCell(row0, 3, headerData[0][3], styleNrml);
		createCell(row0, 4, headerData[0][4], styleNormal);
		createCell(row0, 5, headerData[0][5], styleNrml);
		
		
		createCell(row1, 0, headerData[1][0], styleNormal);
		createCell(row1, 1, headerData[1][1], styleNrml);
		createCell(row1, 2, headerData[1][2], styleNormal);
		createCell(row1, 3, headerData[1][3], styleNrml);
		createCell(row1, 4, headerData[1][4], styleNormal);
		createCell(row1, 5, headerData[1][5], styleNrml);

		int i = 0;
		for (String it : columnNames) {
			createCell(row, (i++), it, style);
		}
	}


	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	
	private void writeDataLinesNote() {

		int rowCount = 4;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (int i = 0; i < data.length; i++) {
			
			Row row = sheet.createRow(rowCount);
			int columnCount = 0;
			for (int j = 0; j < data[i].length; j++)
			{
				createCell(row, columnCount++, data[i][j], style);
			
				
				XSSFCell formulaCell = sheet.getRow(rowCount).createCell(6);
				formulaCell.setCellFormula("E"+(rowCount+1)+" * 0.6"+"+F"+(rowCount+1)+" * 0.4");
				XSSFFormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
				formulaEvaluator.evaluateFormulaCell(formulaCell);
					
				
								
                XSSFSheetConditionalFormatting my_cond_format_layer = sheet.getSheetConditionalFormatting();
                XSSFConditionalFormattingRule my_rule = my_cond_format_layer.createConditionalFormattingRule(ComparisonOperator.GE, "12");
                XSSFFontFormatting my_rule_pattern = my_rule.createFontFormatting();
                my_rule_pattern.setFontColorIndex(IndexedColors.GREEN.getIndex());                
                // Rang des adresses
                CellRangeAddress[] my_data_range = {CellRangeAddress.valueOf("G5:G"+data[i].length)};
                
                
                XSSFConditionalFormattingRule my_rule2 = my_cond_format_layer.createConditionalFormattingRule("$G"+(rowCount+1)+">=12");                
                XSSFPatternFormatting  my_rule_pattern2 = my_rule2.createPatternFormatting();
                my_rule_pattern2.setFillBackgroundColor(IndexedColors.GREEN.index);
                CellRangeAddress[] my_data_range2 = {CellRangeAddress.valueOf("H5:H"+data[i].length)};
                
                
                
                
                XSSFConditionalFormattingRule my_rule3 = my_cond_format_layer.createConditionalFormattingRule("AND($G"+(rowCount+1)+"<12, $G"+(rowCount+1)+">0)");                
                XSSFPatternFormatting  my_rule_pattern3 = my_rule3.createPatternFormatting();
                my_rule_pattern3.setFillBackgroundColor(IndexedColors.RED.index);
                CellRangeAddress[] my_data_range3 = {CellRangeAddress.valueOf("H5:H"+data[i].length)};
                
                
                
                // Attachement des règles avec les addresses
                my_cond_format_layer.addConditionalFormatting(my_data_range,my_rule);
                my_cond_format_layer.addConditionalFormatting(my_data_range2, my_rule2);
                my_cond_format_layer.addConditionalFormatting(my_data_range3, my_rule3);

			}
			rowCount++;
			
		}
	}

	
	public void exportNote(HttpServletResponse response) throws IOException {
		
		writeHeaderCells();
		writeDataLinesNote();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}
