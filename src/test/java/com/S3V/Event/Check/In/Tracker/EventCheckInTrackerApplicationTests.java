package com.S3V.Event.Check.In.Tracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.*;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

@SpringBootTest
class EventCheckInTrackerApplicationTests {

	@Test
	void contextLoads() {
	}
	@Before
	public void prepare(){
		setBaseUrl("https://event-check-in-tracker.herokuapp.com/");
	}
	@Test
	public void testLoginPage(){
		assertTitleEquals("Poolesville Event Check-In");
		assertLinkPresent("login");
		clickLink("login");
	}
	@Test
	public void testStudentListPage(){
		beginAt("/students");
		assertTitleEquals("Student List");
		assertLinkPresent("logout");
		assertLinkPresent("help");
	}
	@Test
	public void testHelpPage(){
		beginAt("/students");
		clickLink("help");
		assertTitleEquals("Help");
	}
	@Test
	public void testUploadInfo(){
		beginAt("/help");
		assertLinkPresent("upload");
		clickLink("upload");
	}
	@Test
	public void testSettingsInfo(){
		beginAt("/help");
		assertLinkPresent("settings");
		clickLink("settings");
	}
	@Test
	public void testSearchInfo(){
		beginAt("/help");
		assertLinkPresent("search");
		clickLink("search");
	}
	@Test
	public void testUpdateInfo(){
		beginAt("/help");
		assertLinkPresent("update");
		clickLink("update");
	}
	@Test
	public void testExportInfo(){
		beginAt("/help");
		assertLinkPresent("export");
		clickLink("export");
	}

}
