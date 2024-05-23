import demo.config.ExcelInitConfig;
import demo.config.ExportMapping;
import demo.constant.BusinessConstant;
import demo.utils.EasyExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @date: 2021/11/4 17:01
 * @author: LiHaoHan
 * @program: PACKAGE_NAME
 */
@Slf4j
public class ExcelTest {

    @Resource
    private ExcelInitConfig excelInitConfig;

    @Resource
    private EasyExcelUtil easyExcelUtil;

    @Test
    void exportTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String sheetName = "test";

        List<List<ExportMapping>> mapping = this.excelInitConfig
                .getExportHeadMapping()
                .get(BusinessConstant.FileExport.VISIT_PLAN_HEAD);
        List<List<Object>> data = this.easyExcelUtil.replace(mapping, Collections.emptyList());
        List<List<String>> head = this.easyExcelUtil.getHead(mapping);
        // 数据导出 excel
        try {
            this.easyExcelUtil.excelWrite(outputStream, head, sheetName, data);
        } catch (IOException e) {
            log.error("error", e);
        }
    }


}