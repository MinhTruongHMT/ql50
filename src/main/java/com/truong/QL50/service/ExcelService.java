package com.truong.QL50.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    private static final String FILE_PATH = "uploads/users.xlsx";
    // Đọc dữ liệu
    public List<String[]> readAll() throws IOException {
        List<String[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    rowData.add(cell.toString());
                }
                data.add(rowData.toArray(new String[0]));
            }
        }
        return data;
    }

    // Thêm 1 dòng
    public void addRow(String[] values) throws IOException {
        File file = new File(FILE_PATH);

        Workbook workbook;
        Sheet sheet;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Users");
        }

        int lastRowNum = sheet.getLastRowNum();
        Row newRow = sheet.createRow(lastRowNum + 1);

        for (int i = 0; i < values.length; i++) {
            newRow.createCell(i).setCellValue(values[i]);
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    // Xóa dòng theo index
    public void deleteRow(int rowIndex) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        fis.close();

        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex <= lastRowNum) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                sheet.removeRow(row);
            }
            if (rowIndex < lastRowNum) {
                sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    // Sửa dòng (ghi đè lại dữ liệu rowIndex)
    public void updateRow(int rowIndex, String[] values) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        fis.close();

        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        for (int i = 0; i < values.length; i++) {
            Cell cell = row.getCell(i);
            if (cell == null) {
                cell = row.createCell(i);
            }
            cell.setCellValue(values[i]);
        }

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    public ResponseEntity<Resource> downloadFile() throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.xlsx")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }
}
