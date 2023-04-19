package com.example.footballreservationscheduler;

import com.example.footballreservationscheduler.util.WebDriverUtil;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FootballReservationSchedulerApplicationTests {

	@Test
	void contextLoads() {
		String chromeDriver = "webdriver.chrome.driver"; // 크롬드라이버 명시
		String chromePath = "D:\\chromedriver.exe"; // 셀레니움 크롬 .exe 경로

		System.setProperty(chromeDriver, chromePath); // 위의 드라이버 셋팅

		//Driver SetUp ( 셀레니움을 막는 것을 방지 )
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectedModeSettings", true);

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30); // Thread.sleep 대신 사용하는 것으로, 대기를 위해 사용

		driver.get("https://www.naver.com/"); // get을 통해 해당 url을 open

		// 셀레니움을 통한 자동화 일 경우, 에러가 날 수 있으니 try구문에 넣는 것이 좋음
		try {

			// 명시적 대기 ( 해당 요소가 나타날 때까지 대기 )
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#account > a")));
			driver.findElement(By.cssSelector("#account > a")).click(); // #account > a 요소를 찾아서 클릭

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));
			driver.findElement(By.id("id")).sendKeys("아이디"); // 해당 요소를 찾아서, 입력

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pw")));
			driver.findElement(By.id("pw")).sendKeys("비밀번호");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("log.login")));
			driver.findElement(By.id("log.login")).click();

		} catch (Exception e) {

			e.printStackTrace();
		}

		System.out.println("== end ==");
	}

}
