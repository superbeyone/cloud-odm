package com.tdt.cloud.commons.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.tdt.cloud.commons.configuration.OdmProperties;
import com.tdt.cloud.commons.result.TdtStaticConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;


/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className TdtExecutorBean
 * @description
 * @date 2019-04-12 16:24
 **/
@Component
public class TdtExecutor {

    @Autowired
    OdmProperties odmProperties;

    public ExecutorService getExecutor(String username, int threadCount) {
        return getThreadExecutor(TdtStaticConst.RUNNING_TASK_CORD_NAME, username, threadCount);
    }

    public ExecutorService getExecutor(String threadPrefix, String username, int threadCount) {
        return getThreadExecutor(threadPrefix, username, threadCount);
    }

    private ExecutorService getThreadExecutor(String threadPrefix, String username, int threadCount) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(threadPrefix + "_" + username + "_%d").build();
        //cpu核心数
        int processors = Runtime.getRuntime().availableProcessors();
        int availableProcessors = Math.min(threadCount, processors);
        int min = Math.abs(Math.min(odmProperties.getThread().getMin(), odmProperties.getThread().getMax()));
        int max = Math.abs(Math.max(odmProperties.getThread().getMin(), odmProperties.getThread().getMax()));
        return new ThreadPoolExecutor(min * availableProcessors + 1,
                max * threadCount + 10,
                10, TimeUnit.HOURS, new LinkedBlockingQueue<>(80),
                threadFactory, new TdtRejectPolicy());
    }
}

