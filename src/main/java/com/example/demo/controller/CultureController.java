package com.example.demo.controller;

import com.example.demo.dao.Culture;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
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
    private List<Culture> cultureObj = new ArrayList<Culture>();
    private List<String> culture = new ArrayList<String>();
    private final String[] tag_s = new String[]{"Let's walk Together", "Spectacular Night View", "Newtro", "In the Drama/Movie ", "For foreigners", "MUKBANG"};
    private final String[] theme = new String[]{"History", "Market", "Temple", "Bukchon_traditional_activity", "All"}; // 테마들

    public CultureController() throws InvalidFormatException {
        try {
            //FileInputStream fis = new FileInputStream(getClass().getResource("/data.xlsx").getFile());
            //XSSFWorkbook workbook = new XSSFWorkbook(fis);
            InputStream in = getClass().getResourceAsStream("/dataTag.xlsx");
            OPCPackage opc = OPCPackage.open(in);
            XSSFWorkbook workbook = new XSSFWorkbook(opc);
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
                            rowData.add(value);
                        } else {
                            value = col.toString();
                            if (colIndex == 3 || colIndex == 4) { // 위도 경도 컬럼
                                rowData.add(value.replaceAll(",", "")); // 실수 데이터 콤마 제거
                            } else {
                                rowData.add(value);
                            }
                        }
                    }

                    //System.out.println(rowData.get(0)+" "+ rowData.get(1)+" "+ rowData.get(2)+" "+ Double.parseDouble(rowData.get(3))+" "+ Double.parseDouble(rowData.get(4))+" "+ rowData.get(5)+" "+ rowData.get(6)+" "+ rowData.get(7)+" "+ rowData.get(8)+" "+ rowData.get(9)+" "+ rowData.get(10)+" "+ rowData.get(11));
                    cultureObj.add(new Culture(rowData.get(0), rowData.get(1), rowData.get(2), Double.parseDouble(rowData.get(3)), Double.parseDouble(rowData.get(4)), rowData.get(5), rowData.get(6), rowData.get(7), rowData.get(8), rowData.get(9), rowData.get(10), rowData.get(11)));

                }
            }
            //for (int i = 0; i < cultureObj.size(); i++) {
            //    System.out.println(i + " " + cultureObj.get(i).getCategory() + " " + cultureObj.get(i).getK_name() + " " + cultureObj.get(i).getE_name() + " " + cultureObj.get(i).getLng() + " " + cultureObj.get(i).getLat() + " " + cultureObj.get(i).getGu() + " " + cultureObj.get(i).getDong() + " " + cultureObj.get(i).getK_describe() + " " + cultureObj.get(i).getE_describe() + " " + cultureObj.get(i).getTel() + " " + cultureObj.get(i).getImg_url() + " " + cultureObj.get(i).getTag());
            //}
            ObjectMapper objectMapper = new ObjectMapper();
            //object serialize error
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            for (int i = 0; i < cultureObj.size(); i++) {
                culture.add(objectMapper.writeValueAsString(cultureObj.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/range_search")
    public String range_search(Model model, String category) {
        model.addAttribute("culture", culture);
        model.addAttribute("theme", theme);
        model.addAttribute("category", category);
        return "range_search";
    }

    @RequestMapping("/tag1")
    public String tag1(Model model) {
        model.addAttribute("culture", culture);
        model.addAttribute("tag_s", tag_s);
        return "tag1";
    }

    @RequestMapping("/tag2")
    public String tag2(Model model) {
        model.addAttribute("culture", culture);
        model.addAttribute("tag_s", tag_s);
        return "tag2";
    }

    @RequestMapping("/tag3")
    public String tag3(Model model) {
        model.addAttribute("culture", culture);
        model.addAttribute("tag_s", tag_s);
        return "tag3";
    }

    @RequestMapping("/tag4")
    public String tag4(Model model) {
        model.addAttribute("culture", culture);
        model.addAttribute("tag_s", tag_s);
        return "tag4";
    }

    @RequestMapping("/tag5")
    public String tag5(Model model) {
        model.addAttribute("culture", culture);
        model.addAttribute("tag_s", tag_s);
        return "tag5";
    }

    @RequestMapping("/tag6")
    public String tag6(Model model) {
        model.addAttribute("culture", culture);
        model.addAttribute("tag_s", tag_s);
        return "tag6";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("culture", culture);
        model.addAttribute("theme", tag_s);
        return "index";
    }

}
