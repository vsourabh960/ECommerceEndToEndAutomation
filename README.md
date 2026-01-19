eCommerceWebsiteEndToEndAutomation
=================================

Project overview
----------------
This repository contains an automated end-to-end test suite for the sample eCommerce website used in the course by Rahul Shetty Academy. Tests are written with Selenium WebDriver, TestNG and Maven. WebDriver binaries are managed via WebDriverManager.

What's in this project
- src/test/java: Test code and test components (pages, tests, helpers).
- src/main/java: Project code/resources used by tests (e.g. configuration resources).
- TestSuites/: TestNG suite XML files you can run as suites.
- reports/, test-output/: Generated test reports and screenshots.

Prerequisites
-------------
- Java 11+ (JDK installed and JAVA_HOME set)
- Maven 3.6+
- A supported browser installed: Google Chrome, Mozilla Firefox, or Microsoft Edge
  (WebDriverManager in the project will download drivers automatically)
- Internet access (tests navigate to a remote demo site)

Quick setup
-----------
1. Open a terminal (Windows Command Prompt) and change to the project folder:

```cmd
cd /d D:\WORK\FrameWorkDesign\eCommerceWebsiteEndToEndAutomation
```

2. Verify Java and Maven are available:

```cmd
java -version
mvn -v
```

How tests are configured
------------------------
- Browser selection: The project reads a system property named "browser" and falls back to the value in
  `src/main/java/bluebeast/resources/GlobalData.properties` if the property is not provided.
  - Examples of values supported by the code:
    - chrome (regular Chrome)
    - chromeheadless (Chrome + headless mode)
    - firefox
    - edge
  The Chrome headless detection uses a simple "contains(\"headless\")" check, so variants like
  `chrome-headless` or `chromeheadless` work as long as they contain the word "headless".

- Base URL: The test base URL is currently set in `BaseTest.java` (driver.get(...)). Update that file if you need to change the target site.

Running tests (Windows cmd.exe)
-------------------------------
- Run the full test suite (default configuration from project):

```cmd
mvn clean test
```

- Run tests with a specific browser (example: Chrome):

```cmd
mvn clean test -Dbrowser=chrome
```

- Run tests with Chrome in headless mode (example):

```cmd
mvn clean test -Dbrowser=chromeheadless
```

- Run tests in Firefox:

```cmd
mvn clean test -Dbrowser=firefox
```

- Run tests in Edge:

```cmd
mvn clean test -Dbrowser=edge
```

- Run a single TestNG test class or method using Maven Surefire (example):

```cmd
mvn -Dtest=package.ClassName test
mvn -Dtest=package.ClassName#methodName test
```

- Run one of the TestNG suite XML files (recommended in IDE):
  - Open the suite XML (TestSuites/*.xml) in your IDE and "Run as TestNG Suite".
  - If you want to run a suite from Maven you can either configure Surefire plugin's suiteXmlFiles
    in pom.xml, or run the tests from your IDE.

Where results and screenshots are written
----------------------------------------
- Screenshots created by the tests are saved to the `reports/` directory at the project root (filename: <testCaseName>.png).
- TestNG/Maven reports: look in `test-output/` and `target/surefire-reports` (if present).
- There are sample generated report files and images already in `reports/` and `test-output/`.

Useful notes and troubleshooting
--------------------------------
- No manual driver management required — WebDriverManager handles drivers automatically.
- If a browser fails to start, ensure the installed browser version is compatible with the driver WebDriverManager downloads.
- If tests fail due to elements not found or timing issues, try increasing explicit wait timeouts in the page objects or use more robust waits.
- If you need to change the test base URL, update the call to `driver.get(...)` inside `BaseTest.java`.

Contributing and extending
--------------------------
- Add new page objects to `src/test/java/bluebeast/pageobjects` and tests under `src/test/java/bluebeast`.
- Add new TestNG suite XMLs to `TestSuites/` for grouped runs.

Contact / Author
----------------
- This repository originates from an automation sample project. For questions about test logic or enhancements, open an issue or contact the repository owner.

License
-------
- No explicit license in the repository. Treat the code as sample/demo code and adapt as needed for your projects.
