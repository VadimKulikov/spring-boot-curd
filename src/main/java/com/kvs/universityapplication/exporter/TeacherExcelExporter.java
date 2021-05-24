package com.kvs.universityapplication.exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kvs.universityapplication.entity.Teacher;

public class TeacherExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Teacher> teachers;

	public TeacherExcelExporter(List<Teacher> teachers) {
		this.teachers = teachers;
		workbook = new XSSFWorkbook();
	}

	private void writeHeader() {
		sheet = workbook.createSheet("Учителя");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "ID", style);
		createCell(row, 1, "Имя", style);
		createCell(row, 2, "Фамилия", style);
		createCell(row, 3, "Отчество", style);
		createCell(row, 4, "Кафедра", style);
		createCell(row, 5, "Ученое звание", style);
		createCell(row, 5, "Преподаваемые предметы", style);
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

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Teacher teacher : teachers) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, teacher.getId(), style);
			createCell(row, columnCount++, teacher.getName(), style);
			createCell(row, columnCount++, teacher.getSurname(), style);
			createCell(row, columnCount++, teacher.getPatronymic(), style);
			createCell(row, columnCount++, teacher.getDepartment().getAbbr(), style);
			createCell(row, columnCount++, teacher.getAcademicTitle(), style);
			createCell(row, columnCount++, teacher.getDisciplines().toString().replace("[", "").replace("]", ""),
					style);

		}

	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeader();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}

}
