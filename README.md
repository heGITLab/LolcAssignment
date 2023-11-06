# LOLC Assignment

### Note
* Please note that this project is made using MacOS, Because of that
reason you have to change the web driver path in order to work with windows.
You can find the web driver in **"resources > webdrivers"**. Please provide the 
absolute path in **"resources > configurations > config.properties"**

### How to run the project
* Run **"mvn clean install"**
* Then **"mvn test"**

### Running project manually
For web automation
* If above commands didn't start the project, please run as below.
go to **"src > test > java > com.lolc > testcases > web"** and right click 
on the **"SLLoginTestCase"** and run the project.

For api automation
* If above commands didn't start the project, please run as below.
go to **"src > test > java > com.lolc > testcases > api"** and right click 
on the **"Requests"** and run the project.

### Report
* You can find the html report at **"test-output > LOLCReport.html"**.