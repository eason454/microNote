package com.asiainfo;

import com.asiainfo.service.weeklyreport.interfaces.IWeeklyReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeeklyreportApplicationTests {

	@Autowired
	IWeeklyReportService weeklyReportService;
	@Test
	public void contextLoads() {
	}

}
