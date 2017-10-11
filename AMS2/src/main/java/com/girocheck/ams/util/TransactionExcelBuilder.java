package com.girocheck.ams.util;

/**
 * @author @Roberto Rodriguez :: <RobertoSoftwareEngineer@gmail.com>
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 *
 * @author www.codejava.net
 *
 */
public class TransactionExcelBuilder extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<HashMap> list = (List<HashMap>) model.get("list");

        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Transactions");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Certegy#");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Merchant");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Terminal");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Transaction Type");
        header.getCell(4).setCellStyle(style);

        header.createCell(5).setCellValue("Card Number");
        header.getCell(5).setCellStyle(style);

        header.createCell(6).setCellValue("Operation");
        header.getCell(6).setCellStyle(style);

        header.createCell(7).setCellValue("Payout");
        header.getCell(7).setCellStyle(style);

        header.createCell(8).setCellValue("Fee");
        header.getCell(8).setCellStyle(style);

        header.createCell(9).setCellValue("Amount");
        header.getCell(9).setCellStyle(style);

        header.createCell(10).setCellValue("Client Name");
        header.getCell(10).setCellStyle(style);

        header.createCell(11).setCellValue("Client Last Name");
        header.getCell(11).setCellStyle(style);

        header.createCell(12).setCellValue("Client Phone");
        header.getCell(12).setCellStyle(style);

        header.createCell(13).setCellValue("Maker Name");
        header.getCell(13).setCellStyle(style);

        header.createCell(14).setCellValue("Check Number");
        header.getCell(14).setCellStyle(style);

        header.createCell(15).setCellValue("Date");
        header.getCell(15).setCellStyle(style);

        header.createCell(16).setCellValue("Address");
        header.getCell(16).setCellStyle(style);

        header.createCell(17).setCellValue("Result Code");
        header.getCell(17).setCellStyle(style);

        header.createCell(18).setCellValue("Result Message");
        header.getCell(18).setCellStyle(style);

        // create data rows
        int rowCount = 1;

        for (HashMap element : list) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue((String) (element.get("id") + ""));
            aRow.createCell(1).setCellValue(element.get("certegyApprovalNumber") + "");
            aRow.createCell(2).setCellValue(element.get("merchant") + "");
            aRow.createCell(3).setCellValue(element.get("terminal") + "");
            aRow.createCell(4).setCellValue(element.get("transactionTypeStr") + "");
            aRow.createCell(5).setCellValue(element.get("card") + "");
            aRow.createCell(6).setCellValue(element.get("operationStr") + "");
            aRow.createCell(7).setCellValue(element.get("payoutAmmount") + "");
            aRow.createCell(8).setCellValue(element.get("feeAmmount") + "");
            aRow.createCell(9).setCellValue(element.get("amount") + "");
            aRow.createCell(10).setCellValue(element.get("clientName") + "");
            aRow.createCell(11).setCellValue(element.get("clientLastName") + "");
            aRow.createCell(12).setCellValue(element.get("clientPhone") + "");
            aRow.createCell(13).setCellValue(element.get("makerName") + "");
            aRow.createCell(14).setCellValue(element.get("checkNumber") + "");
            aRow.createCell(15).setCellValue(element.get("dateStr") + "");
            aRow.createCell(16).setCellValue(element.get("fullAddress") + "");
            aRow.createCell(17).setCellValue(element.get("resultCode") + "");
            aRow.createCell(18).setCellValue(element.get("resultMessage") + "");
        }
    }

}
