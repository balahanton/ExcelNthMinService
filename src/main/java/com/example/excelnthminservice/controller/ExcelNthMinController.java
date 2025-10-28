package com.example.excelnthminservice.controller;

import com.example.excelnthminservice.service.ExcelNthMinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
@Tag(name = "Excel Nth Min Controller", description = "Операции с Excel файлами по вычислению N-го минимального числа")
public class ExcelNthMinController {

    private final ExcelNthMinService excelNthMinService;

    @GetMapping("/nth-minimum")
    @Operation(
            summary = "Найти N-ное минимальное число из Excel файла",
            description = "Читает числа из первого столбца xlsx файла и возвращает N-ное минимальное значение")
    @ApiResponse(responseCode = "200", description = "Успешно найдено N-ное минимальное число")
    @ApiResponse(responseCode = "400", description = "Некорректные входные данные")
    @ApiResponse(responseCode = "404", description = "Файл не найден")
    public ResponseEntity<Integer> getNthMinimum(@Parameter(
                                                         description = "Полный путь к xlsx файлу",
                                                         example = "C:/Users/Имя пользователя/Desktop/test.xlsx")
                                                 @RequestParam String filePath,
                                                 @Parameter(
                                                         description = "Порядковый номер минимального элемента",
                                                         example = "3")
                                                 @RequestParam int n) {
        int result = excelNthMinService.findNthMin(filePath, n);
        return ResponseEntity.ok(result);
    }
}
