package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    static ExtentReports extent;

    public static ExtentReports getReporter() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            spark.config().setReportName("Playwright Automation Report");

            spark.config().setDocumentTitle("Execution Report");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Abhishek");

            extent.setSystemInfo("Framework", "Playwright Java");
        }

        return extent;
    }
}