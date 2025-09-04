package com.truong.QL50.controller;

import com.truong.QL50.service.ExcelService;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping
    public List<String[]> getAll() throws IOException {
        return excelService.readAll();
    }

    @PostMapping("/add")
    public String addRow(@RequestBody String[] values) throws IOException {
        excelService.addRow(values);
        return "✅ Added row successfully";
    }

    @PutMapping("/update/{rowIndex}")
    public String updateRow(@PathVariable int rowIndex, @RequestBody String[] values) throws IOException {
        excelService.updateRow(rowIndex, values);
        return "✅ Updated row successfully";
    }

    @DeleteMapping("/delete/{rowIndex}")
    public String deleteRow(@PathVariable int rowIndex) throws IOException {
        excelService.deleteRow(rowIndex);
        return "✅ Deleted row successfully";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadExcel() throws IOException {
        return excelService.downloadFile();
    }
}

