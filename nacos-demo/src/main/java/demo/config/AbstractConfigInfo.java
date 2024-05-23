package demo.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * The type Abstract config info.
 *
 * @author LiHaoHan Created on 2024-02-22
 */
@Slf4j
public abstract class AbstractConfigInfo implements InitializingBean, Listener {

    /**
     * The Clazz simple name.
     */
    protected final String clazzSimpleName = getClass().getSimpleName();

    /**
     * The Config service.
     */
    @Resource
    NacosConfigManager nacosConfigManager;

    /**
     * 返回null，使用默认的executor
     *
     * @return
     */
    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
        parseConfigInfo(configInfo);
    }

    @Override
    public void afterPropertiesSet() throws RuntimeException {
        try {
            String configInfo = nacosConfigManager.getConfigService()
                    .getConfig(getDataId(), getGroupId(), getTimeout());
            log.info("{} afterPropertiesSet init configInfo. configInfo: {}",
                    clazzSimpleName,
                    JSON.parse(configInfo));
            parseConfigInfo(configInfo);
            nacosConfigManager.getConfigService().addListener(getDataId(), getGroupId(), this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取nacos dataId
     *
     * @return nacos dataId
     */
    protected abstract String getDataId();

    /**
     * 获取nacos groupId
     * <p>默认NacosConstant.GROUP_ID</p>
     *
     * @return nacos groupId
     */
    protected abstract String getGroupId();

    /**
     * 获取nacos 超时时间
     * <p>默认NacosConstant.TIMEOUT</p>
     *
     * @return nacos 超时时间
     */
    protected long getTimeout() {
        return 3000L;
    }

    /**
     * 将输入转为数据
     *
     * @param dataStr the data str
     */
    protected abstract void parseConfigInfo(String dataStr);
}