package com.example.demo.controller;

import com.example.demo.dao.Culture;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class CultureController {
    private final String dataFile =  "E:\\Git\\workspace\\Contest\\CultureData\\src\\main\\resources\\data.xlsx"; // 전체 데이터 파일
    private List<Culture> cultureObj = new ArrayList<Culture>();
    private List<String> culture = new ArrayList<String>();
    private final String[] theme = new String[]{"유적지", "사찰", "전통시장", "역사", "박물관/미술관", "기념관", "관광지", "공원", "문화", "체험", "스토리"}; // 테마들

    public CultureController() {
        try {
            FileInputStream fis = new FileInputStream(dataFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet=workbook.getSheetAt(0); // 시트 인덱스
            int rowLength = sheet.getPhysicalNumberOfRows(); // 행 수
            for (int rowIndex = 1; rowIndex < rowLength; rowIndex++) { // 첫 번째 행은 컬럼이므로 제외
                XSSFRow row = sheet.getRow(rowIndex); // 행 읽음
                ArrayList<String> rowData = new ArrayList();
                if (row != null) {
                    // int colLength = row.getPhysicalNumberOfCells();
                    int colLength = 12; // 열 수 (뒤에 콤마가 이상하게 들어가 있으면 컬럼 수가 크게 나올 수 있으므로 컬럼 수 지정)
                    for (int colIndex = 0; colIndex <= colLength; colIndex++) {
                        XSSFCell col = row.getCell(colIndex); // 열 읽음
                        String value = "";
                        if (col == null) { // 비어 있을 경우
                            value = "";
                        } else {
                            value = col.toString();
                            if (colIndex == 3 || colIndex == 4) { // 위도 경도 컬럼
                                rowData.add(value.replaceAll(",", "")); // 실수 데이터 콤마 제거
                            } else {
                                rowData.add(value);
                            }
                        }
                    }
                    cultureObj.add(new Culture(rowData.get(0), rowData.get(1), rowData.get(2), Double.parseDouble(rowData.get(3)), Double.parseDouble(rowData.get(4)), rowData.get(5), rowData.get(6), rowData.get(7), rowData.get(8), rowData.get(9), rowData.get(10), rowData.get(11)));
                }
            }
            // for (int i = 0; i < cultureObj.size(); i++) { System.out.println(i + " " + cultureObj.get(i).getCategory() + " " + cultureObj.get(i).getK_name() + " " + cultureObj.get(i).getE_name() + " " + cultureObj.get(i).getLng() + " " + cultureObj.get(i).getLat() + " " + cultureObj.get(i).getGu() + " " + cultureObj.get(i).getDong() + " " + cultureObj.get(i).getK_describe() + " " + cultureObj.get(i).getE_describe() + " " + cultureObj.get(i).getTel() + " " + cultureObj.get(i).getImg_url() + " " + cultureObj.get(i).getTag()); }
            ObjectMapper objectMapper = new ObjectMapper();
            for (int i = 0; i < cultureObj.size(); i++) {
                culture.add(objectMapper.writeValueAsString(cultureObj.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/range_search")
    public String range_search(Model model) {
        model.addAttribute("culture", culture);
        model.addAttribute("theme", theme);
        return "range_search";
    }
}
