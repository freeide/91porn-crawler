package com.bluetroy.crawler91.utils;

import com.bluetroy.crawler91.dao.entity.KeyContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: heyixin
 * Date: 2018-07-11
 * Time: 下午3:49
 */
@Component
public class ScannerUtils {
    @Autowired
    XpathUtils xpathUtils;

    public void scanDownloadUrls(LinkedBlockingDeque<KeyContent> keyContentQueue) {
        //todo 增加延时获取的功能，因为contentQueue中的内容会动态的增加
        KeyContent keyContent;
        while ((keyContent = keyContentQueue.poll()) != null) {
            if (keyContent.getContent().isDone()) {
                xpathUtils.scanDownloadUrl(keyContent);
            } else {
                keyContentQueue.offer(keyContent);
            }
        }
    }

    public void scanMovies(LinkedBlockingDeque<Future<String>> movieContents) {
        //todo 增加延时获取的功能，因为contentQueue中的内容会动态的增加
        Future<String> future;
        while ((future = movieContents.poll()) != null) {
            if (future.isDone()) {
                xpathUtils.setMovie(future);
            } else {
                movieContents.offer(future);
            }
        }
    }

    public String scanLoginState(String loginResult) {
        return xpathUtils.getLoginState(loginResult);
    }
}