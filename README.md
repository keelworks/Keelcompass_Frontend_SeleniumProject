🚀 KeelCompass Frontend Automation Framework

Enterprise-grade UI Automation Framework built using Selenium WebDriver, Java, TestNG, and Maven for validating critical workflows of the KeelCompass Platform.

📖 Overview

The KeelCompass Frontend Automation Framework is a scalable and maintainable test automation solution developed to validate critical application workflows through automated UI testing.

The framework follows industry-standard automation engineering practices including:

✅ Page Object Model (POM)
✅ Modular Framework Architecture
✅ Reusable Utilities
✅ Parallel Test Execution
✅ Reporting & Logging Integration
✅ Retry Mechanism for Flaky Tests

This framework helps improve:

⚡ Regression execution speed
🛡️ Release confidence
🐞 Faster defect identification
📈 Test coverage
🔄 Automation scalability
🛠️ Technology Stack
Category	Technology
💻 Programming Language	Java
🤖 Automation Tool	Selenium WebDriver
🧪 Test Framework	TestNG
📦 Build Tool	Maven
🖥️ IDE	Eclipse IDE
📊 Reporting	Extent Reports
📝 Logging	Log4j
🌐 Version Control	Git / GitHub
🏗️ Framework Design	Page Object Model (POM)
🏗️ Framework Architecture
<img width="1199" height="1312" alt="image" src="https://github.com/user-attachments/assets/7038e7c6-a865-4fe5-84cd-f434bb02dc77" />



Automates end-to-end frontend workflows using Selenium WebDriver.

🧩 Page Object Model (POM)

Implements reusable page classes for clean code organization and maintainability.

⚡ Parallel Execution

Supports parallel execution through TestNG suite configuration.

🔁 Retry Mechanism

Automatically retries failed test cases to handle intermittent failures.

📊 Extent Reporting

Generates detailed execution reports including:

✅ Pass/Fail Status
📌 Execution Summary
🖼️ Failure Screenshots
🕒 Execution Timeline
📄 Logs & Debug Details
⚙️ Centralized Configuration Management

Application configurations handled using external property files.

📝 Logging Framework

Integrated logging support for:

Execution tracking
Runtime debugging
Error tracing
Failure analysis
📦 Maven Dependency Management

Simplified dependency and build management using Maven.

📋 Prerequisites

Ensure the following tools are installed before execution:

☕ Java JDK 17+
📦 Maven
🖥️ Eclipse IDE / IntelliJ IDEA
🌐 Google Chrome Browser
🔧 Git
🔍 Verify Installations
java -version
mvn -version
git --version
⚙️ Setup Instructions
1️⃣ Clone Repository
git clone <repository-url>
2️⃣ Import Project into IDE

Open Eclipse IDE:

File → Import → Existing Maven Project

Select the cloned repository folder.

3️⃣ Install Dependencies

Run the following command:

mvn clean install

Or inside Eclipse:

Right Click Project → Maven → Update Project
4️⃣ Configure Environment

Update configuration values inside the properties/configuration files.

Example:

baseUrl=https://application-url.com
username=testuser
password=testpassword
browser=chrome
▶️ Test Execution
🧪 Execute Using TestNG

Run the suite file:

testng.xml

Execution path:

Right Click → Run As → TestNG Suite
💻 Execute Using Maven
mvn test
📄 Sample TestNG Suite Configuration
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">

<suite name="RunTest" parallel="tests" thread-count="2">

    <listeners>
        <listener class-name="utils.ExtentTestListener"/>
        <listener class-name="utils.RetryListener"/>
    </listeners>

    <test name="Chrome_Login">

        <parameter name="browser" value="chrome"/>

        <classes>
            <class name="testcases.LoginTest"/>
        </classes>

    </test>

</suite>
📊 Reporting

After execution, Extent Reports are automatically generated.

The reports provide:

✅ Passed Test Cases
❌ Failed Test Cases
⚠️ Skipped Test Cases
📈 Execution Summary
🪲 Error Logs
🖼️ Failure Screenshots
🕒 Execution Timeline

📝 Logging

The framework includes centralized logging utilities to capture:

🔍 Test execution flow
⚠️ Errors and exceptions
🐞 Debug information
📄 Runtime activities

This simplifies troubleshooting and root cause analysis.

✅ Framework Best Practices

✔️ Follow Page Object Model principles
✔️ Keep test data externalized
✔️ Avoid hardcoded waits
✔️ Use reusable utility methods
✔️ Maintain independent test cases
✔️ Keep assertions meaningful
✔️ Update suite files for new modules

🚧 Future Enhancements

Potential improvements planned for the framework:

🔄 Jenkins CI/CD Integration
🐳 Docker Execution Support
🌐 Selenium Grid Integration
🔌 API Automation Integration
☁️ Cloud Execution Support
📸 Enhanced Screenshot Capturing
🎯 Purpose of the Project

This framework was developed to support automated validation of critical workflows within the KeelCompass Platform while establishing a scalable foundation for future automation initiatives at KeelWorks.

Primary goals include:

⚡ Faster regression cycles
📈 Improved automation scalability
🛡️ Increased release reliability
🔄 Faster quality feedback loops
👥 Contributors
KeelWorks QA & Automation Team
📌 License

This project is intended for internal automation and testing purposes only.
