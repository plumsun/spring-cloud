package demo.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.alibaba.fastjson2.JSON;
import demo.config.CustomCellHeightStyleHandler;
import demo.config.CustomCellWriteHandler;
import demo.config.ExcelPortConfig;
import demo.config.ExportMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class EasyExcelUtil {

    private final ExcelPortConfig excelExportConfig;


    public void excelWrite(OutputStream outputStream,
                           List<List<String>> head,
                           String sheetName,
                           Collection<?> data) throws IOException {
        try {
            EasyExcelFactory.write(outputStream)
                    .sheet(sheetName)
                    .head(head)
                    .registerWriteHandler(new SimpleColumnWidthStyleStrategy(excelExportConfig.getColumnWidth()))
                    .registerWriteHandler(new CustomCellHeightStyleHandler(
                            excelExportConfig.getHeadRowHeight()
                            , excelExportConfig.getContentRowHeight()))
                    .registerWriteHandler(new CustomCellWriteHandler(excelExportConfig.getHeadFontSize()))
                    .doWrite(data);
        } catch (Exception e) {
            log.error("excelWrite error", e);
            throw e;
        }
    }

    public List<List<Object>> replace(List<List<ExportMapping>> head, List<?> data) {
        if (CollUtil.isEmpty(head)) {
            throw new RuntimeException("当前文档导出对象没有对应Excel格式");
        }
        // head 格式为 List<List<String>>，那对应的数据格式也要类似 List<List<Object>>
        ArrayList<List<Object>> lists = new ArrayList<>(data.size());
        for (Object datum : data) {
            ArrayList<Object> objects = new ArrayList<>();
            for (List<ExportMapping> exportMappings : head) {
                Object val = "";
                for (ExportMapping exportMapping : exportMappings) {
                    String property = exportMapping.getProperty();
                    Map<String, Object> innerMap = JSON.parseObject(JSON.toJSONString(datum));
                    Set<String> strings = innerMap.keySet();
                    if (strings.contains(property)) {
                        val = innerMap.get(property);
                    }
                    objects.add(val);
                }
            }
            lists.add(objects);
        }
        return lists;
    }

    public List<List<String>> getHead(List<List<ExportMapping>> source) {
        return source.stream()
                .map(lists -> lists.stream().map(ExportMapping::getColum).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }


    public void test(String[] args) {

    }

}