package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportClass implements ITestListener {

	public ExtentReports r;
	public ExtentTest t;
	public void onStart(ITestContext context) {

		String time=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String path=".\\reports\\"+time+"extent.html";
		ExtentSparkReporter e=new ExtentSparkReporter(path);
		e.config().setDocumentTitle("ExtentsReport");
		e.config().setReportName("Extents Report");
		r=new ExtentReports();
		r.attachReporter(e);
		r.setSystemInfo("Tester", "Shivaraj");

	}
	public void onTestStart(ITestResult result) {

		t=r.createTest(result.getMethod().getMethodName());
		t.assignCategory(result.getMethod().getGroups());
		t.createNode(result.getName());
		
	}


	public void onTestSuccess(ITestResult result) {

		t.log(Status.PASS, "Test is Pass");

	}

	public void onTestFailure(ITestResult result) {

		t.log(Status.FAIL, "Test is Failed");

	}
	public void onTestSkipped(ITestResult result) {
		
		t.log(Status.SKIP, "Test is Skipped");

	}


	


	public void onFinish(ITestContext context) {

		r.flush();

	}

}
