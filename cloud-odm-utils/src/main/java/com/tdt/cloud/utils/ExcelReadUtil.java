package com.tdt.cloud.utils;

import jcifs.smb.SmbFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className ExcelReadUtil
 * @description 读取Excel文件
 * @date 2019-04-01 15:58
 **/

public class ExcelReadUtil {

    /**
     * excel读取
     *
     * @param file      文件
     * @param ignoreRow 忽略行数
     * @return
     */
    public static Map<String, List<List<String>>> readExcel(File file, Integer ignoreRow) {
        try {
            return readExcel(file, ignoreRow, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * excel读取
     *
     * @param file      文件
     * @param ignoreRow 忽略行数
     * @param limit     读取行数
     * @return
     */
    public static Map<String, List<List<String>>> readExcel(File file, int ignoreRow, Integer limit) {
        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
            return readExcel(workbook, ignoreRow, limit);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    /**
     * smbFile
     *
     * @param file
     * @param ignoreRow
     * @return
     */
    public static Map<String, List<List<String>>> readExcel(SmbFile file, Integer ignoreRow) {
        try {
            return readExcel(file, ignoreRow, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static Map<String, List<List<String>>> readExcel(SmbFile file, int ignoreRow, Integer limit) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            return readExcel(workbook, ignoreRow, limit);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * smbFile
     *
     * @param file
     * @param ignoreRow
     * @return
     */
    public static Map<String, List<List<String>>> readExcel(com.hierynomus.smbj.share.File file, Integer ignoreRow) {
        try {
            return readExcel(file, ignoreRow, null);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public static Map<String, List<List<String>>> readExcel(com.hierynomus.smbj.share.File file, int ignoreRow, Integer limit) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            return readExcel(workbook, ignoreRow, limit);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }


    /**
     * 读取excel 文件
     *
     * @param workbook
     * @param ignoreRow
     * @return
     */
    private static Map<String, List<List<String>>> readExcel(Workbook workbook, int ignoreRow, Integer limit) throws Exception {
        Map<String, List<List<String>>> map = new HashMap<>();
        int rowSize = 0;
        try {
            Cell cell = null;
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                List<List<String>> lists = new ArrayList<>();
                int rowNum;
                if (limit == null) {
                    rowNum = sheet.getLastRowNum();
                } else {
                    rowNum = 1;
                }
                for (int rowIndex = ignoreRow; rowIndex <= rowNum; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (null == row) {
                        continue;
                    }

                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }
                    List<String> list = new ArrayList<>();
                    int col = 0;
                    for (int colIndex = 0; colIndex <= row.getLastCellNum(); colIndex++) {
                        cell = row.getCell(colIndex);
                        String value = "";
                        if (cell != null) {
                            CellType cellType = cell.getCellType();

                            switch (cellType) {
                                case NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        value = String.valueOf(cell.getDateCellValue());
                                    } else {
                                        DecimalFormat df = new DecimalFormat("0.000000000000");
                                        value = subZeroAndDot(String.valueOf(df.format(cell.getNumericCellValue())));
                                    }
                                    break;
                                case STRING:
                                    value = String.valueOf(cell.getStringCellValue());
                                    break;
                                case FORMULA:
                                    value = String.valueOf(cell.getCellFormula());
                                    break;
                                case BLANK:
                                    value = "";
                                    break;
                                case BOOLEAN:
                                    value = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                case ERROR:
                                    value = String.valueOf(cell.getErrorCellValue());
                                    break;
                                default:
                                    value = "";
                            }
                            if (StringUtils.isNotBlank(value)) {
                                list.add(value);
                            } else {
                                col++;
                            }
                        }
                    }
                    if (col == row.getRowNum()) {
                        continue;
                    }
                    if (list.size() > 0) {
                        lists.add(list);
                    }
                }
                map.put("sheet" + sheetIndex, lists);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    private static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}
