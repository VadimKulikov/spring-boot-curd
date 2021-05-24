package com.kvs.universityapplication.exporter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kvs.universityapplication.entity.Teacher;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class TeacherPdfExporter {
	private List<Teacher> teachers;

	public TeacherPdfExporter(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.WHITE);
		cell.setPadding(5);
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("ID"));

		table.addCell(cell);

		cell.setPhrase(new Phrase("Имя"));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Фамилия"));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Отчетсво"));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Кафедра"));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Ученое звание"));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Преподаваемые предметы"));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for (Teacher teacher : teachers) {
			table.addCell(String.valueOf(teacher.getId()));
			table.addCell(teacher.getName());
			table.addCell(teacher.getSurname());
			table.addCell(teacher.getPatronymic());
			table.addCell(teacher.getDepartment().getAbbr());
			table.addCell(teacher.getAcademicTitle());
			table.addCell(teacher.getDisciplines().toString().replace("[", "").replace("]", ""));
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A3);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Учителя");
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.0f, 2.0f, 3.0f, 3.0f, 1.5f, 1.5f, 4.0f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}
