package demo.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author LiHaoHan Created on 2024/2/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Configuration
@Slf4j
public class ExcelInitConfig extends AbstractConfigInfo {

    @Resource
    private ExcelPortConfig excelPortConfig;

    // TODO: 2024/5/23 读取本地json文件，不从配置中心读取
    private Map<String, List<List<ExportMapping>>> exportHeadMapping = new HashMap<>();

    @Override
    protected String getDataId() {
        return excelPortConfig.getDataId();
    }

    @Override
    protected String getGroupId() {
        return excelPortConfig.getGroup();
    }

    @Override
    protected void parseConfigInfo(String dataStr) {
        JSONObject jsonObject = JSON.parseObject(dataStr);
        List<String> heads = excelPortConfig.getHeads();
        for (String head : heads) {
            JSONArray jsonArray = jsonObject.getJSONArray(head);
            Optional.ofNullable(jsonArray)
                    .ifPresent(array -> exportHeadMapping.put(head,
                            array.to(new TypeReference<List<List<ExportMapping>>>() {
                            }.getType()))
                    );
        }
        log.info("excel export config. mapping info :{}", JSON.toJSONString(exportHeadMapping));
    }
}
