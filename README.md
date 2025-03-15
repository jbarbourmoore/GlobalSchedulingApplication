# Global Scheduling Application

![The basic layout of the Global Scheduling Application](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/DesktopApplicationFlow.jpg "Application Layout")

## Description

The Global Scheduling Application was a project from my Bachelors of Science in Software Development. It was built using Java, JavaFX, mySQL, CSS and Junit. 

The application is a scheduling utility for a publishing company. It uses a database in order to store information about authors and editors as well as their appointments.

It provides a GUI interface built in JavaFX for the user to interact with the database.

The employee usernames for the system are stored in the mySQL database, however the passwords are stored as salted hashes using SHA-256.

## Getting Started

To use the Global Scheduling Application, you must first have a few things set up on your computer. The 
steps below should guide you through the basic process so that the Global Scheduling Application can run 
successfully. 

### Requirements

* Java JDK 17 installed (tested on version 17.0.2). 
* IntelliJ IDE (tested on version IDEA 2021.3.2 (Community Edition)). 
* JavaFX installed (tested on version 17.0.1). 
* Junit installed (tested on version 5.8.1). 
* MySQL Installer (tested on version 8.0) or existing MySQL database. 
* MySQL Workbench Installed (tested on version 8.0 CE).

### Database Setup

1. Use MySQL Installer to create a MySQL database on your machine. 
2. Ensure the database to note the port, username and password in order to allow the code to access the database by editing the values stored in src/Main/DatabaseConnectManager.java
3. Connect to the database using MySQL Workbench. 
4. Create a schema called 'globalschedulingapplication'. 
5. In the MySQL Workbench menus, go to Server>Data Import 
6. On the 'Import from Disk' tab, select 'Import from self-contained file' and choose your path to the DatabaseExport.sql file included in this project. 
7. Set default target schema to 'globalschedulingapplication'. 
8. Make sure 'Dump structure and Data' is selected in the dropdown. 
9. Click 'Start Import.'

### Environment Variables

The application is setup to pull the database credentials from the system's environment variables.
* The key for the database username is "GSA_DB_User"
* The key for the database password is "GSA_DB_Password"

### Running the Application

1. Download the project folders from GitHub
2. Open the IntelliJ IDE. 
3. In the menus, go to File>Open. 
4. Navigate to the Project Folder you just downloaded and hit ok.
5. IntelliJ may take a few minutes to open the new project. 
6. This should allow you to view the project and see the Run Configuration GSAstart. 
    The run configuration needs to include several things
    * JDK: Java 17
    * VM Options. CLI arguments to the Java command: --add-modules javafx.fxml, javafx.controls, javafx.graphics
    * Class that contains the main method: Main.Start
7. With the run configuration selected, click the green arrow to run the program. This should cause the Global Scheduling Application Login screen to appear.

### Running the application

#### Login

1. Using the textboxes for Username and Password, enter valid credentials.   
2. Valid credentials are employees for the globalschedulingapplication database. This includes 
username 'admin' and password 'admin'.   
3. Click on the 'Login' button.  
4. Assuming the credentials are correct, this should cause a pop-up informing you if there is an 
appointment within 15 minutes.   
5. Hit 'Ok' on the dialog to go to the application's main screen.  

(The login credentials are compared with those in the database in order to login successfully. The passwords are stored as salted hashes using SHA256. The application also generates a log file containing all log in attempts, including whether they are successful or not.)

![The login form for the Global Scheduling Application](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/GSA_LoginScreenshot.png "Login Form")

#### Main Screen

The Global Scheduling Application's Main is a simple layout that allows the user to navigate the application. The user can select from various buttons to access different features. 
* 'View Appointments' allows the user to view all the appointments stored in the database. 
* 'View Authors' allows the user to view the authors that Alanath Publishing has as contacts. 
* 'View Editors' allows the user to view the editors that Alanath Publishing has as contacts. 
* 'View Reports' allows the users to view various information sections and export the appointments information for a specified author or editor. 
* 'Exit' also allows the user to close the application.

![The main screen for the Global Scheduling Application](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/GSA_HomeScreenshot.png "Main Screen")

#### Appointments Screen

The Appointments Screen allows the user to view all appointments in the appointments database table. It also allows the user to add, modify or delete appointments. The appointments can be filtered by all appointments, the current month's appointments, or those in the next seven days. At the bottom, there is a 'Back' button which will take the user back to the Main Screen, and an 'Exit' button which allows the user to exit the application. 

![The appointment screen for the Global Scheduling Application](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/GSA_AppointmentsScreenshot.png "Appointment Screen")

The radio buttons at the top of the appointments screen allow the user to select how they wish to filter the 
appointments. 
* 'All Appointments' allows the user to view all appointments in the database. 
* 'Appointments This Month' allows the user to view all appointments during the current month. 
For example, if the application is run during February, it shall show all February appointments. 
* 'Appointments Next 7 Days' filters the appointments based on the current time and shows only 
appointments in the next seven days. 

##### Add An Appointment 

1. Make sure you do not have an appointment currently selected. 
2. Fill in the information for the appointment. 
    * All non-disabled input fields must be filled in. 
    * Times should be entered in 24hr time formatted as hh:mm. 
    * Times should be entered based on your local time but must be within core business hours (08:00 to 22:00 EST).  
    * Authors, Editors, and Employees cannot be assigned to two different appointments simultaneously. 
3. Click the 'Add Appointment' button. 
4. Confirm that you wish to add the appointment. 

##### Modify An Appointment 

1. Select the appointment that you wish to modify. 
2. Fill in the information for the appointment. 
    * All non-disabled input fields must be filled in. 
    * Times should be entered in 24hr time formatted as hh:mm. 
    * Times should be entered based on your local time but must be within core business hours (08:00 to 22:00 EST).  
    * Authors, Editors, and Employees cannot be assigned to two different appointments simultaneously. 
3. Click the 'Modify Appointment' button 
4. Confirm that you wish to modify the appointment. 

##### Delete An Appointment 

1. Select the appointment that you wish to delete. 
2. Click 'Delete Appointment.'
3. Confirm that you wish to delete the appointment.

#### Authors Screen

The Authors Screen allows the user to view all the authors in the database table. It also allows the 
user to add, modify or delete authors. A search bar and button will enable the user to quickly find the 
author they are looking for. At the bottom, there is a 'Back' button which will take the user back to the 
Main Screen, and an 'Exit' button which allows the user to exit the application. 

![The Authors Screen for the Global Scheduling Application](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/GSA_AuthorsScreenshot.png "Authors Screen")

##### Search 

1. Use the Search input field above the table to enter the value you wish to search for. The search will include the authors' IDs and the authors' names. 
2. Click the 'Search' button. 
    * A successful search will show the authors found in the table. 
    * If nothing is found, a pop-up will inform you, and the table will show all of the authors again. 

##### Add An Author 

1. Make sure you do not have an author currently selected. 
2. Fill in the information for the author. 
    * All non-disabled input fields must be filled in. 
3. Click the 'Add Author' button. 
4. Confirm that you wish to add the author. 

##### Modify An Author 

1. Select the author that you wish to modify. 
2. Fill in the modified information for the author. 
    * All non-disabled input fields must be filled in. 
3. Click the 'Modify Author' button. 
4. Confirm that you wish to modify the author. 

##### Delete An Author 

1. Select the author that you wish to delete. 
2. Click the 'Delete Author' button. 
3. Confirm that you wish to delete the author. 
4. If the author has appointments, there will be a second confirmation window asking you to confirm that you wish to delete all of the author's appointments as well. If you do not confirm, the author will not be deleted.

#### Editors Screen

The Editors Screen allows the user to view all the editors in the editors database table. It also allows the user to add, modify or delete editors. A search bar and button enable the user to quickly find the editor they are looking for. At the bottom, there is a 'Back' button which will take the user back to the Main Screen, and an 'Exit' button which allows the user to exit the application. 

![The Editors Screen for the Global Scheduling Application](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/GSA_EditorsScreenshot.png "Editors Screen")

##### Search 

1. Use the Search input field above the table to enter the value you wish to search for. 
    * The search will include the editors' IDs and the editors' names. 
2. Click the 'Search' button. 
    * A successful search will show the found editors in the table. 
    * If nothing is found, a pop-up will inform you, and the table will show all the editors again. 

##### Add An Editor 

1. Make sure you do not have an editor currently selected. 
2. Fill in the information for the editor. 
    * All non-disabled input fields must be filled in. 
3. Click the 'Add Editor' button. 
4. Confirm that you wish to add the editor. 

##### Modify An Editor 

1. Select the editor that you wish to modify. 
2. Fill in the modified information for the editor. 
    * All non-disabled input fields must be filled in. 
3. Click the 'Modify Editor' button. 
4. Confirm that you wish to modify the editor. 

##### Delete An Editor 

1. Select the editor that you wish to delete. 
2. Click the 'Delete Editor' button. 
3. Confirm that you wish to delete the editor. 
4. If the editor has appointments, there will be a second confirmation window asking you to confirm that you wish to delete all the editor's appointments as well. If you do not confirm, the editor will not be deleted.

#### Reports Screen 

The Reports Screen allows the user to view several tabs with different information from the application. These tabs do not allow the user to change any information in the database. At the bottom, there is a 'Back' button which will take the user back to the Main Screen, and an 'Exit' button which allows the user to exit the application.

##### Appointments By Editor Tab 

![The reports screen for the Global Scheduling Application showing the Appointments by Editor Tab](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/GSA_ReportScreenshot_Editor.png "Reports Screen on the Appointemnts By Editor Tab")

1. The 'Appointments By Editor' tab allows the user to filter the appointments based on the assigned editor. 
    * Use the dropdown after 'Which Editor?' to select the editor whose appointments you wish to view. 
    * Click the 'Submit' button to update the table to match the dropdown's selected editor. 
2. It also allows the user to export all the appointment information for the selected editor to a .CSV file. 
    * Use the dropdown after 'Which Editor?' to select the editor whose appointments you wish to export. 
    * Click the 'Submit' button to update the table to match the dropdown's selected editor. 
    * The button to the right of the 'Submit' should now display 'Export [your selected editor's name]'s Appointments.'
    * Click that button. 
    * A popup should appear letting you know the file name that the appointments data has been exported to in the root fold of the application. Click ok. 

##### Appointments By Author Tab 

![The reports screen for the Global Scheduling Application showing the Appointments by Author Tab](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/GSA_ReportScreenshot_Author.png "Reports Screen on the Appointemnts By Author Tab")

1. The 'Appointments By Author' tab allows the user to filter the appointments based on the author assigned to them. 
   * Use the dropdown after 'Which Author?' to select the editor whose appointments you wish to view. 
   * Click the 'Submit' button to update the table to match the dropdown's selected author. 
2. It also allows the user to export all the appointment information for the selected author to a .CSV file. 
   * Use the dropdown after 'Which Author?' to select the author whose appointments you wish to export. 
   * Click the 'Submit' button to update the table to match the dropdown's selected author. 
   * The button to the right of the 'Submit' should now display 'Export [your selected author's name]'s Appointments.'
   * Click that button. 
   * A popup should appear letting you know the file name that the appointments data has been exported to in the root fold of the application. Click ok. 

##### Appointment Count Tab 

![The reports screen for the Global Scheduling Application showing the Appointment Count Tab](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/GSA_ReportScreenshot_Count.png "Reports Screen on the Appointment Count Tab")

The Appointment Counts tab displays a simple table with the count of appointments in the database, which fall into specific categories. The categories are chosen by the user using the radio buttons at the top of the screen. 
* By Author' displays all of the authors that have appointments in the system and how many appointments they have. 
* 'By Editor' displays all of the editors that have appointments in the system and how many appointments they have. 
*  'By Type' displays all the appointment types in the system and how many appointments have each type.  
* 'By Month' displays all of the months that appointments are during in the system and how many appointments are in each month.

##### Authors / Editors by Country Tab

![The reports screen for the Global Scheduling Application showing the Authors / Editors by Country Tab](https://github.com/jbarbourmoore/GlobalSchedulingApplication/blob/e2ada7f146031a0b8487d08eb80b51009c860a7a/Screenshots/GSA_ReportScreenshot_Country.png "Reports Screen on the Authors / Editors By Country Tab")

The Authors/Editors By Country tab displays a simple table with the number of authors or editors that have addresses in each country.  The user chooses whether to count the authors or the editors by using the radio buttons at the top of the tab.
