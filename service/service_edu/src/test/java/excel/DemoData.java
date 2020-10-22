package excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    @ExcelProperty(value = "id",index = 0)
    private Integer id;
    @ExcelProperty(value = "name",index = 1)
    private String name;

}
