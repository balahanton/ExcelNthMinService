package com.example.excelnthminservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class ExcelNthMinService {

    public int findNthMin(String filePath, int n) {
        List<Integer> numbers = readNumbersFromExcel(filePath);
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException(("Файл не содержит чисел"));
        }
        if (n < 1 || n > numbers.size()) {
            throw new IllegalArgumentException(
                    String.format("N должно быть от 1 до %d, Вы ввели: %d", numbers.size(), n)
            );
        }
        int[] arr = numbers.stream().mapToInt(Integer::intValue).toArray();
        return quickSelect(arr, 0, arr.length - 1, n - 1);
    }

    private int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);
        pivotIndex = partition(arr, left, right, pivotIndex);
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private List<Integer> readNumbersFromExcel(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    try {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            numbers.add((int) cell.getNumericCellValue());
                        } else if (cell.getCellType() == CellType.STRING) {
                            String value = cell.getStringCellValue().trim();
                            if (!value.isEmpty()) {
                                numbers.add(Integer.parseInt(value));
                            }
                        }
                    } catch (NumberFormatException e) {
                        log.warn("Не удалось прочитать число из строки {}: {}",
                                row.getRowNum(), e.getMessage());
                    }
                }
            }
            log.info("Прочитано {} чисел из файла {}", numbers.size(), filePath);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + filePath, e);
        }
        return numbers;
    }
}
