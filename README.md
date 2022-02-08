# UI tests for Miro registration page

# Info
The project contains UI tests for registration on https://miro.com/signup/.

# Setup
1. Open the project in IDE
2. Install Gradle https://gradle.org/install/
3. Open build.gradle and reload all Gradle project
4. Download Allure framework https://github.com/allure-framework/allure2/releases and add its path to Path variable

# Run tests
1. Be sure that folders /allure-results is empty for each testrun
2. Run tests by running console command 'gradle clean test'.
   After tests will be complete, folder /allure-results is generated with testrun results.
   If a test fails a page screenshot is done and saved in /build/reports/tests folder
3. Generate Allure report by running console command 'allure generate allure-results --clean -o allure-report'.
   A generated report is saved in /allure-report folder.
4. To open Allure report run console command 'allure serve allure-results'

You can also run tests from IDE by clicking on run button near each test method or test class.

# Structure description
Test are based on Java + Gradle + Selenide + Junit 5 + Lombok + Faker + Allure reports. 
Tests can be run in Google Chrome browser.
Page object pattern is used for the project.
All tests can be found in /src/test/java/ru.miro.
All pages classes can be found in /src/main/java/ru.miro.pages.

# How I generated tests
If this was a work task I would ask for requirements and talk to analyst/business expert/developer/etc to understand better user story and data inputs. 
Exploratory testing method was used to create this tests.

# Some test results
If a user types invalid email address "bad@email" in email field and submit the form - there won't be an error on email field. 
It seems like a minor bug for me.