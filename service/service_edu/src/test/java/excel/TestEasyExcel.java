package excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //写
//        String fileName = "/Users/Apple/IdeaProjects/guli_parent/service/service_edu/src/test/java/test.xlsx";
//
//        EasyExcel.write(fileName,DemoData.class).sheet("test").doWrite(getData());

        //读
        String fileName = "/Users/Apple/IdeaProjects/guli_parent/service/service_edu/src/test/java/test.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcslListener()).sheet().doRead();
    }

    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0 ; i < 10 ;i++){
            DemoData demoData = new DemoData();
            demoData.setId(i);
            demoData.setName("q"+i);
            list.add(demoData);
        }
        return list;
    }
}
