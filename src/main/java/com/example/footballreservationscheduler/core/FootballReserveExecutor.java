package com.example.footballreservationscheduler.core;

import com.example.footballreservationscheduler.util.BeanUtil;
import com.example.footballreservationscheduler.util.WebDriverUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by jyh1004 on 2023-04-19
 */

@Slf4j
public class FootballReserveExecutor {

    private final WebDriver webDriver;
    private final LocalDate now;
    private final LocalDate targetDate;
    private final String formattedTargetDate;

    public FootballReserveExecutor(WebDriver webDriver, LocalDate now, LocalDate targetDate) {
        this.webDriver = webDriver;
        this.now = now;
        this.targetDate = targetDate;
        this.formattedTargetDate = this.targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void execute(FootballReserveInfo reserveInfo) {
        log.info("[FootballReserveExecutor] Start foot ball reservation!!! reserveInfo => {}", reserveInfo);

        try {
            login(reserveInfo);
            reserve(reserveInfo);

            log.info("[FootballReserveExecutor] Football reservation completed successfully!!!");
            sendSuccessMail();
        } catch (Exception e) {

            log.error("[FootballReserveExecutor] Could not process foot ball reservation!!! Exception -> {}", e);
            sendFailureMail(e);
        } finally {
            WebDriverUtil.quit(webDriver);
        }
    }

    private void sendSuccessMail() {
        EmailSendService emailSendService = BeanUtil.getBean(EmailSendService.class);
        emailSendService.sendMail("Football reservation completed successfully!!!",
                                  String.format("Reserve Date -> %s", formattedTargetDate));
    }

    private void sendFailureMail(Exception e) {
        EmailSendService emailSendService = BeanUtil.getBean(EmailSendService.class);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        emailSendService.sendMail("Exception has occurred while foot ball reservation processing!!!", sw.toString());
    }

    private void login(FootballReserveInfo reserveInfo) {
        webDriver.get("https://www.seongnam.go.kr/member/login.do?menuIdx=1000437&returnURL=%2Fmain.do");
        webDriver.findElement(By.id("member_id")).sendKeys(reserveInfo.getAccountId());
        webDriver.findElement(By.id("pwd")).sendKeys(reserveInfo.getPassword());
        webDriver.findElement(By.id("loginBtn")).click();
    }

    private void reserve(FootballReserveInfo reserveInfo) throws InterruptedException {
        // 01 시설 및 날짜 선택
        webDriver.get("https://www.seongnam.go.kr/rent/rentParkDataCal.do?menuIdx=1001981&returnURL=%2Fmain.do");

        DayOfWeek dayOfWeekForOneMonthAfter = targetDate.getDayOfWeek();
        if (dayOfWeekForOneMonthAfter == DayOfWeek.SATURDAY || dayOfWeekForOneMonthAfter == DayOfWeek.SUNDAY) {
            return;
        }

        int monthDiff = targetDate.getMonth().getValue() - now.getMonth().getValue();
        for (int i = 0; i < monthDiff; i++) {
            webDriver.findElement(By.id("monthNext")).click();
        }

        // 20시~22시로 하드 코딩
        String reserveDateHyperLinkXpath = String.format("//a[contains(@onclick,\"selectWrite('%s', '20'\")]",
                                                         formattedTargetDate);
        webDriver.findElement(By.xpath(reserveDateHyperLinkXpath)).click();

        // 02 이용안내 및 동의
        webDriver.findElement(By.id("agree_yn")).click();

        String applyButtonXpath = "//a[contains(@href,'#go')]";
        webDriver.findElement(By.xpath(applyButtonXpath)).click();

        // 03 예약신청
        webDriver.findElement(By.id("rent_club_nm")).sendKeys(reserveInfo.getClubName());
        webDriver.findElement(By.id("rent_mobile2")).sendKeys(reserveInfo.getReservationSecondPhoneNum());
        webDriver.findElement(By.id("rent_mobile3")).sendKeys(reserveInfo.getReservationThirdPhoneNum());
        webDriver.findElement(By.id("rent_amount")).sendKeys(reserveInfo.getParticipantCount());
        webDriver.findElement(By.id("rent_ceo_nm")).sendKeys(reserveInfo.getClubCeoName());
        webDriver.findElement(By.id("rent_ceo_mobile2")).sendKeys(reserveInfo.getClubCeoSecondPhoneNum());
        webDriver.findElement(By.id("rent_ceo_mobile3")).sendKeys(reserveInfo.getClubCeoThirdPhoneNum());

        webDriver.findElement(By.id("uploadFile1")).sendKeys(reserveInfo.getEventPlanDocLocation());
        webDriver.findElement(By.id("uploadFile2")).sendKeys(reserveInfo.getParticipantListDocLocation());

        webDriver.findElement(By.id("agree_yn")).click();

        String saveButtonXPath = "//a[contains(@onclick,\"javascript:validCheck(); return false;\")]";
        webDriver.findElement(By.xpath(saveButtonXPath)).click();

        Thread.sleep(1000);
        webDriver.switchTo().alert().accept();

        Thread.sleep(1000);
        webDriver.switchTo().alert().accept();

        Thread.sleep(1000);
    }
}
