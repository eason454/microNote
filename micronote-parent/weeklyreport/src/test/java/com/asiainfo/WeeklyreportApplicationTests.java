package com.asiainfo;

import com.asiainfo.service.weeklyreport.interfaces.WeeklyReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeeklyreportApplicationTests {

	@Autowired
	WeeklyReportService weeklyReportService;
	@Test
	public void contextLoads() {
	}

}
