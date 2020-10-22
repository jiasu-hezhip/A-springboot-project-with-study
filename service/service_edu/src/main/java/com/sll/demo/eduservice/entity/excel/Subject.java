package com.sll.demo.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Subject {
    @ExcelProperty(index = 0)
    private String oneSubject;
    @ExcelProperty(index = 1)
    private String twoSubject;

}
