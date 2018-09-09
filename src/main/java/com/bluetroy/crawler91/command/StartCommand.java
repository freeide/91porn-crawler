package com.bluetroy.crawler91.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: heyixin
 * Date: 2018-07-09
 * Time: 下午7:49
 */
/*@Order(2)
@Component*/
public class StartCommand implements CommandLineRunner {
    @Autowired
    ScanCommand scanCommand;

    @Override
    public void run(String... args) throws Exception {
        scanCommand.process();
    }
}