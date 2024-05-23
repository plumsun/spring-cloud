package demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author LiHaoHan Created on 2024/2/28
 */
@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "file.export")
public class ExcelPortConfig {

    private String dataId;

    private String group;

    private Short headRowHeight;

    private Short contentRowHeight;

    private short headFontSize;

    private Integer columnWidth;

    @NonNull
    private List<String> heads;
}
