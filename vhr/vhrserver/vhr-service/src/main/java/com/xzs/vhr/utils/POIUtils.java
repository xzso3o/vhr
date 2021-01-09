package com.xzs.vhr.utils;

import com.xzs.vhr.model.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class POIUtils {
    public static ResponseEntity<byte[]> employeeToExcel(List<Employee> list) {
        //Excel生成

        //1.创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建文档摘要,主要配置文档的一些基本信息
        workbook.createInformationProperties();
        //3.获取并配置文档信息
        DocumentSummaryInformation information = workbook.getDocumentSummaryInformation();
        //文档类别
        information.setCategory("员工信息");
        //文档管理员
        information.setManager("xzs");
        //设置公司信息
        information.setCompany("www.xzs.com");
        //4.获取并配置摘要信息
        SummaryInformation summaryInfo = workbook.getSummaryInformation();
        //文档标题
        summaryInfo.setTitle("员工信息表");
        //文档作者
        summaryInfo.setAuthor("xzs");
        //文档备注
        summaryInfo.setComments("本文档由 xzs 友情提供！");
        //接下来就可以处理我们拿到的list数据了
        //5.创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //设置日期的格式
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("员工信息表");
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 12 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(12, 16 * 256);
        sheet.setColumnWidth(13, 14 * 256);
        sheet.setColumnWidth(14, 14 * 256);
        sheet.setColumnWidth(15, 12 * 256);
        sheet.setColumnWidth(16, 8 * 256);
        sheet.setColumnWidth(17, 20 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        sheet.setColumnWidth(20, 8 * 256);
        sheet.setColumnWidth(21, 25 * 256);
        sheet.setColumnWidth(22, 14 * 256);
        sheet.setColumnWidth(23, 15 * 256);
        sheet.setColumnWidth(24, 15 * 256);
        //6.创建标题行
        HSSFRow r0 = sheet.createRow(0);//第一行
        HSSFCell c0 = r0.createCell(0);//第一列
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);//第二列
        c1.setCellValue("姓名");
        c1.setCellStyle(headerStyle);
        HSSFCell c2 = r0.createCell(2);//第三列
        c2.setCellValue("工号");
        c2.setCellStyle(headerStyle);
        HSSFCell c3 = r0.createCell(3);//第四列
        c3.setCellValue("性别");
        c3.setCellStyle(headerStyle);
        HSSFCell c4 = r0.createCell(4);
        c4.setCellValue("出生日期");
        c4.setCellStyle(headerStyle);
        HSSFCell c5 = r0.createCell(5);
        c5.setCellValue("身份证号码");
        c5.setCellStyle(headerStyle);
        HSSFCell c6 = r0.createCell(6);
        c6.setCellValue("婚姻状况");
        c6.setCellStyle(headerStyle);
        HSSFCell c7 = r0.createCell(7);
        c7.setCellValue("民族");
        c7.setCellStyle(headerStyle);
        HSSFCell c8 = r0.createCell(8);
        c8.setCellValue("籍贯");
        c8.setCellStyle(headerStyle);
        HSSFCell c9 = r0.createCell(9);
        c9.setCellValue("政治面貌");
        c9.setCellStyle(headerStyle);
        HSSFCell c10 = r0.createCell(10);
        c10.setCellValue("电话号码");
        c10.setCellStyle(headerStyle);
        HSSFCell c11 = r0.createCell(11);
        c11.setCellValue("联系地址");
        c11.setCellStyle(headerStyle);
        HSSFCell c12 = r0.createCell(12);
        c12.setCellValue("所属部门");
        c12.setCellStyle(headerStyle);
        HSSFCell c13 = r0.createCell(13);
        c13.setCellValue("职称");
        c13.setCellStyle(headerStyle);
        HSSFCell c14 = r0.createCell(14);
        c14.setCellValue("职位");
        c14.setCellStyle(headerStyle);
        HSSFCell c15 = r0.createCell(15);
        c15.setCellValue("聘用形式");
        c15.setCellStyle(headerStyle);
        HSSFCell c16 = r0.createCell(16);
        c16.setCellValue("最高学历");
        c16.setCellStyle(headerStyle);
        HSSFCell c17 = r0.createCell(17);
        c17.setCellValue("专业");
        c17.setCellStyle(headerStyle);
        HSSFCell c18 = r0.createCell(18);
        c18.setCellValue("毕业院校");
        c18.setCellStyle(headerStyle);
        HSSFCell c19 = r0.createCell(19);
        c19.setCellValue("入职日期");
        c19.setCellStyle(headerStyle);
        HSSFCell c20 = r0.createCell(20);
        c20.setCellValue("在职状态");
        c20.setCellStyle(headerStyle);
        HSSFCell c21 = r0.createCell(21);
        c21.setCellValue("邮箱");
        c21.setCellStyle(headerStyle);
        HSSFCell c22 = r0.createCell(22);
        c22.setCellValue("合同期限（年）");
        c22.setCellStyle(headerStyle);
        HSSFCell c23 = r0.createCell(23);
        c23.setCellValue("合同起始日期");
        c23.setCellStyle(headerStyle);
        HSSFCell c24 = r0.createCell(24);
        c24.setCellValue("合同终止日期");
        c24.setCellStyle(headerStyle);
        //遍历list集合，往excel表里放数据即可
        for (int i = 0; i < list.size(); i++) {
            Employee emp = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1).setCellValue(emp.getName());
            row.createCell(2).setCellValue(emp.getWorkID());
            row.createCell(3).setCellValue(emp.getGender());
            HSSFCell ceil4 = row.createCell(4);
            ceil4.setCellStyle(dateCellStyle);
            ceil4.setCellValue(emp.getBirthday());
            row.createCell(5).setCellValue(emp.getIdCard());
            row.createCell(6).setCellValue(emp.getWedlock());
            row.createCell(7).setCellValue(emp.getNation().getName());
            row.createCell(8).setCellValue(emp.getNativePlace());
            row.createCell(9).setCellValue(emp.getPoliticsstatus().getName());
            row.createCell(10).setCellValue(emp.getPhone());
            row.createCell(11).setCellValue(emp.getAddress());
            row.createCell(12).setCellValue(emp.getDepartment().getName());
            row.createCell(13).setCellValue(emp.getJobLevel().getName());
            row.createCell(14).setCellValue(emp.getPosition().getName());
            row.createCell(15).setCellValue(emp.getEngageForm());
            row.createCell(16).setCellValue(emp.getTiptopDegree());
            row.createCell(17).setCellValue(emp.getSpecialty());
            row.createCell(18).setCellValue(emp.getSchool());
            HSSFCell ceil19 = row.createCell(19);
            ceil19.setCellStyle(dateCellStyle);
            ceil19.setCellValue(emp.getBeginDate());
            row.createCell(20).setCellValue(emp.getWorkState());
            row.createCell(21).setCellValue(emp.getEmail());
            row.createCell(22).setCellValue(emp.getContractTerm());
            HSSFCell ceil23 = row.createCell(23);
            ceil23.setCellStyle(dateCellStyle);
            ceil23.setCellValue(emp.getBeginContract());
            HSSFCell ceil24 = row.createCell(24);
            ceil24.setCellStyle(dateCellStyle);
            ceil24.setCellValue(emp.getEndContract());
            HSSFCell ceil25 = row.createCell(25);
            ceil25.setCellStyle(dateCellStyle);
            ceil25.setCellValue(emp.getConversionTime());
        }

        //文件下载
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("员工表.xls"
                    .getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    //Excel解析成员工集合
    public static List<Employee> excelToEmployee(MultipartFile file, List<Nation> allNations, List<Politicsstatus> allPoliticsstatus, List<Department> allDepartments, List<Position> allPositions, List<JobLevel> allJobLevels) {
        List<Employee> list = new ArrayList<>();
        Employee employee = null;
        try {
            //1. 创建一个workbook对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取workbook中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//是我们的标题行，就不解析了
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    employee = new Employee();
                    //8. 把Excel中数据拿出来做处理赋给employee，再加到list里传出去
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellType()) {
                            case STRING://如果是字符串
                                String cellValue = cell.getStringCellValue();
                                switch (k) {//根据列数来决定怎么处理
                                    case 1:
                                        employee.setName(cellValue);
                                        break;
                                    case 2:
                                        employee.setWorkID(cellValue);
                                        break;
                                    case 3:
                                        employee.setGender(cellValue);
                                        break;
                                    case 5:
                                        employee.setIdCard(cellValue);
                                        break;
                                    case 6:
                                        employee.setWedlock(cellValue);
                                        break;
                                    case 7:
                                        int nationIndex = allNations.indexOf(new Nation(cellValue));
                                        employee.setNationId(allNations.get(nationIndex).getId());
                                        break;
                                    case 8:
                                        employee.setNativePlace(cellValue);
                                        break;
                                    case 9:
                                        int politicsstatusIndex = allPoliticsstatus.indexOf(new Politicsstatus(cellValue));
                                        employee.setPoliticId(allPoliticsstatus.get(politicsstatusIndex).getId());
                                        break;
                                    case 10:
                                        employee.setPhone(cellValue);
                                        break;
                                    case 11:
                                        employee.setAddress(cellValue);
                                        break;
                                    case 12:
                                        int departmentIndex = allDepartments.indexOf(new Department(cellValue));
                                        employee.setDepartmentId(allDepartments.get(departmentIndex).getId());
                                        break;
                                    case 13:
                                        int jobLevelIndex = allJobLevels.indexOf(new JobLevel(cellValue));
                                        employee.setJobLevelId(allJobLevels.get(jobLevelIndex).getId());
                                        break;
                                    case 14:
                                        int positionIndex = allPositions.indexOf(new Position(cellValue));
                                        employee.setPosId(allPositions.get(positionIndex).getId());
                                        break;
                                    case 15:
                                        employee.setEngageForm(cellValue);
                                        break;
                                    case 16:
                                        employee.setTiptopDegree(cellValue);
                                        break;
                                    case 17:
                                        employee.setSpecialty(cellValue);
                                        break;
                                    case 18:
                                        employee.setSchool(cellValue);
                                        break;
                                    case 20:
                                        employee.setWorkState(cellValue);
                                        break;
                                    case 21:
                                        employee.setEmail(cellValue);
                                        break;
                                }
                                break;
                            default: {
                                switch (k) {
                                    case 4:
                                        employee.setBirthday(cell.getDateCellValue());
                                        break;
                                    case 19:
                                        employee.setBeginDate(cell.getDateCellValue());
                                        break;
                                    case 22:
                                        employee.setContractTerm(cell.getNumericCellValue());
                                        break;
                                    case 23:
                                        employee.setBeginContract(cell.getDateCellValue());
                                        break;
                                    case 24:
                                        employee.setEndContract(cell.getDateCellValue());
                                        break;
                                    case 25:
                                        employee.setConversionTime(cell.getDateCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    list.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}