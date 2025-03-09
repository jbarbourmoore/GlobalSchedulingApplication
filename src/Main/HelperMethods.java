package Main;

import DataStructures.Appointment;
import DataStructures.Person;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Created class HelperMethods.java
 * <p>
 * This class contains static methods the application uses throughout.
 *
 * @author Jamie Barbour-Moore
 */
public class HelperMethods {

    /**
     * Template for dateTime formatting
     */
    private static final String dateTimeTemplate = "MM/dd/yy HH:mm";

    /**
     * The locale that the application is running in
     */
    private static Locale locale;
    /**
     * The string holding the text for the exit button so the application can run in English or French.
     */
    private static String exitButtonText = "";
    /**
     * The string holding the text for the back button so the application can run in English or French.
     */
    private static String backButtonText = "";
    /**
     * The string holding the text for the application title so the application can run in English or French.
     */
    private static String headerText = "";
    /**
     * The string holding the text for the localization label so the application can run in English or French.
     */
    private static String localizationText;
    /**
     * The string holding the text for the exit pop up title so the application can run in English or French.
     */
    private static String exitTitle = "";
    /**
     * The string holding the text for the exit popup header so the application can run in English or French.
     */
    private static String exitHeader = "";
    /**
     * The string holding the text for the exit pop up message so the application can run in English or French.
     */
    private static String exitMessage = "";
    /**
     * The string holding the text for the back pop up title so the application can run in English or French.
     */
    private static String backTitle = "";
    /**
     * The string holding the text for the back pop up header so the application can run in English or French.
     */
    private static String backHeader = "";
    /**
     * The string holding the text for the back pop up message so the application can run in English or French.
     */
    private static String backMessage = "";
    /**
     * The string holding the text for the main screen title so the application can run in English or French.
     */
    private static String mainScreenText = "";
    /**
     * The string holding the text for the appointment screen title so the application can run in English or French.
     */
    private static String appointmentScreenText = "";
    /**
     * The string holding the text for the author screen title so the application can run in English or French.
     */
    private static String authorScreenText = "";
    /**
     * The string holding the text for the editor screen title so the application can run in English or French.
     */
    private static String editorScreenText = "";
    /**
     * The string holding the text for the report screen title so the application can run in English or French.
     */
    private static String reportScreenText = "";

    /**
     * This method gets the text for the exit button.
     *
     * @return the text for the exit button
     */
    public static String getExitButtonText() {
        return exitButtonText;
    }

    /**
     * This method sets the text for the exit button.
     *
     * @param exitButtonText the text for the exit button
     */
    public static void setExitButtonText(String exitButtonText) {
        HelperMethods.exitButtonText = exitButtonText;
    }

    /**
     * This method gets the text for the back button.
     *
     * @return the text for the back button
     */
    public static String getBackButtonText() {
        return backButtonText;
    }

    /**
     * This method sets the text for the back button.
     *
     * @param backButtonText the text for the back button
     */
    public static void setBackButtonText(String backButtonText) {
        HelperMethods.backButtonText = backButtonText;
    }

    /**
     * This method gets the text for the application title.
     *
     * @return the text for the application title
     */
    public static String getHeaderText() {
        return headerText;
    }

    /**
     * This method sets the text for the application title.
     *
     * @param headerText the text for the application title
     */
    public static void setHeaderText(String headerText) {
        HelperMethods.headerText = headerText;
    }

    /**
     * This method gets the locale.
     *
     * @return the locale
     */
    public static Locale getLocale() {
        return locale;
    }

    /**
     * This method sets the locale.
     *
     * @param locale the locale
     */
    public static void setLocale(Locale locale) {
        HelperMethods.locale = locale;
    }

    /**
     * This method gets the text for the application localization.
     *
     * @return the text for the application localization
     */
    public static String getLocalizationText() {
        return localizationText;
    }

    /**
     * This method sets the text for the application localization.
     *
     * @param localizationText the text for the application localization
     */
    public static void setLocalizationText(String localizationText) {
        HelperMethods.localizationText = localizationText;
    }

    /**
     * This method gets the text for the main screen title.
     *
     * @return the text for the main screen title
     */
    public static String getMainScreenText() {
        return mainScreenText;
    }

    /**
     * This method sets the text for the main screen title.
     *
     * @param mainScreenText the text for the main screen title
     */
    public static void setMainScreenText(String mainScreenText) {
        HelperMethods.mainScreenText = mainScreenText;
    }

    /**
     * This method gets the text for the appointment screen title.
     *
     * @return the text for the appointment screen title
     */
    public static String getAppointmentScreenText() {
        return appointmentScreenText;
    }

    /**
     * This method sets the text for the appointment screen title.
     *
     * @param appointmentScreenText the text for the appointment screen title
     */
    public static void setAppointmentScreenText(String appointmentScreenText) {
        HelperMethods.appointmentScreenText = appointmentScreenText;
    }

    /**
     * This method gets the text for the author screen title.
     *
     * @return the text for the author screen title
     */
    public static String getAuthorScreenText() {
        return authorScreenText;
    }

    /**
     * This method sets the text for the author screen title.
     *
     * @param authorScreenText the text for the author screen title
     */
    public static void setAuthorScreenText(String authorScreenText) {
        HelperMethods.authorScreenText = authorScreenText;
    }

    /**
     * This method gets the text for the editor screen title.
     *
     * @return the text for the editor screen title
     */
    public static String getEditorScreenText() {
        return editorScreenText;
    }

    /**
     * This method gets the text for the editor screen title.
     *
     * @param editorScreenText the text for the editor screen title
     */
    public static void setEditorScreenText(String editorScreenText) {
        HelperMethods.editorScreenText = editorScreenText;
    }

    /**
     * This method gets the text for the report screen title.
     *
     * @return the text for the report screen title
     */
    public static String getReportScreenText() {
        return reportScreenText;
    }

    /**
     * This method sets the text for the report screen title.
     *
     * @param reportScreenText the text for the report screen title
     */
    public static void setReportScreenText(String reportScreenText) {
        HelperMethods.reportScreenText = reportScreenText;
    }

    /**
     * This method sets the text for the application's exit dialog.
     *
     * @param title   the dialog's title
     * @param header  the dialog's header
     * @param message the dialog's message
     */
    public static void setExitDialog(String title, String header, String message) {
        exitTitle = title;
        exitHeader = header;
        exitMessage = message;
    }

    /**
     * This method runs the application's exit dialog.
     *
     * @return true if the user confirms to exit
     */
    public static boolean exitDialog() {
        return confirmDialog(exitTitle, exitHeader, exitMessage);
    }

    /**
     * This method sets the text for the application's back dialog.
     *
     * @param title   the dialog's title
     * @param header  the dialog's header
     * @param message the dialog's message
     */
    public static void setBackDialog(String title, String header, String message) {
        backTitle = title;
        backHeader = header;
        backMessage = message;
    }

    /**
     * This method runsr the application's back dialog.
     *
     * @return true if the user confirms to go back
     */
    public static boolean backDialog() {
        return confirmDialog(backTitle, backHeader, backMessage);
    }

    /**
     * This method allows the simple creation of confirm dialogs
     *
     * @param title   title to be displayed in confirm dialog
     * @param header  header to be displayed in confirm dialog
     * @param message message to be displayed in confirm dialog
     * @return boolean of the user's choice
     */
    public static boolean confirmDialog(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    /**
     * This method allows the simple creation of an information alert for the user.
     *
     * @param title   title to be displayed in information alert
     * @param header  header to be displayed in information alert
     * @param message message to be displayed in information alert
     */
    public static void infoDialog(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * This method format's a LocalDateTime to display for the user.
     * It assumes the LocalDateTime is in UTC and the output is in System Default.
     *
     * @param localDateTime The localDateTime to be displayed (UTC)
     * @return The String with the date and time information in System Default
     */
    public static String formatDateTimeDisplay(LocalDateTime localDateTime) {
        String dateTimeString = "";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeTemplate);
        localDateTime = getDateTimeForLocation(localDateTime);
        dateTimeString = localDateTime.format(dateTimeFormatter);
        return dateTimeString;
    }

    /**
     * This method converts a localDateTime from utc to the system default.
     *
     * @param localDateTimeUTC the localdatetime in zone UTC
     * @return the localdatetime in zone system default
     */
    public static LocalDateTime getDateTimeForLocation(LocalDateTime localDateTimeUTC) {
        ZonedDateTime zonedDateTime = localDateTimeUTC.atZone(ZoneId.of("Etc/UTC"));
        ZonedDateTime zonedDateTimeLocal = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime localDateTimeHere = zonedDateTimeLocal.toLocalDateTime();
        return localDateTimeHere;
    }

    /**
     * This method converts a localDateTime from the system default to utc.
     *
     * @param localDateTimeHere the localdatetime in zone system default
     * @return the localdatetime in zone utc
     */
    public static LocalDateTime getDateTimeForUTC(LocalDateTime localDateTimeHere) {
        ZonedDateTime zonedDateTimeHere = localDateTimeHere.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTimeUTC = zonedDateTimeHere.withZoneSameInstant(ZoneId.of("Etc/UTC"));
        LocalDateTime localDateTimeUTC = zonedDateTimeUTC.toLocalDateTime();
        return localDateTimeUTC;
    }

    /**
     * This method formats a timestamp to display for the user.
     * It assumes the timestamp is in UTC and the output is in System Default.
     *
     * @param timestamp The timestamp to be displayed (UTC)
     * @return The String with the date and time information in System Default
     */
    public static String formatDateTimeDisplay(Timestamp timestamp) {
        String dateTimeString = "";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeTemplate);
        dateTimeString = getDateTimeForLocation(timestamp.toLocalDateTime()).format(dateTimeFormatter);
        return dateTimeString;
    }

    /**
     * This method converts a local time from system default to EST.
     * EST is the time zone the company's business hours use.
     *
     * @param localTimeEst the localTime object in EST
     * @return a local time object in System Default
     */
    public static LocalTime convertLocalTimeFromEST(LocalTime localTimeEst) {
        LocalDateTime localDateTimeEST = LocalDateTime.of(LocalDate.now(), localTimeEst);
        ZonedDateTime zonedDateTimeEST = ZonedDateTime.of(localDateTimeEST, ZoneId.of("America/New_York"));
        ZonedDateTime zonedDateTimeSystemDefault = zonedDateTimeEST.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime localTimeSystemDefault = zonedDateTimeSystemDefault.toLocalTime();
        return localTimeSystemDefault;
    }

    /**
     * This method converts a local time from system default to EST.
     * EST is the time zone the company's business hours use.
     *
     * @param localTimeSystemDefault the localTime object in system default
     * @return a local time object in EST
     */
    public static LocalTime convertLocalTimeToEST(LocalTime localTimeSystemDefault) {
        LocalDateTime localDateTimeSystemDefault = LocalDateTime.of(LocalDate.now(), localTimeSystemDefault);
        ZonedDateTime zonedDateTimeSystemDefault = ZonedDateTime.of(localDateTimeSystemDefault, ZoneId.systemDefault());
        ZonedDateTime zonedDateTimeEST = zonedDateTimeSystemDefault.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalTime localTimeEST = zonedDateTimeEST.toLocalTime();
        return localTimeEST;
    }

    /**
     * This method allows the export of a list of people to a csv file.
     *
     * @param people   the list of people to be exported
     * @param location the location of the csv file
     * @return true if the export succeeds
     */
    public static boolean exportPersonListToCSV(List<Person> people, String location) {
        boolean successfulExport = false;
        try {
            List<String> exportableDataList = new ArrayList<String>();
            String headers = "ID,Name,Address,Region,Country,Phone,Email,Create Date,Created By,Last Updated,Last Updated By";
            exportableDataList.add(headers);
            for (Person person : people) {
                String personData = person.getID() + "," + person.getName() + "," + person.getAddress() + "," + person.getRegion_Name() + "," + person.getCountry_Name() + "," + person.getPhone() + "," + person.getEmail() + "," + person.getCreate_Date().toString() + "," + person.getCreated_By() + "," + person.getLast_Update().toString() + "," + person.getLast_Updated_By();
                exportableDataList.add(personData);
            }
            FileWriter fileWriter = new FileWriter(location, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String line : exportableDataList) {
                printWriter.print(line + "\n");
            }
            printWriter.close();
            successfulExport = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return successfulExport;
    }

    /**
     * This method allows the export of a list of appointments to a csv file.
     *
     * @param appointmentList the list of appointments to be exported
     * @param location        the location of the csv file
     * @return true if the export succeeds
     */
    public static boolean exportAppointmentListToCSV(List<Appointment> appointmentList, String location) {
        boolean successfulExport = false;
        try {
            List<String> exportableDataList = new ArrayList<String>();
            String headers = "ID,Title,Description,Location,Type,Start,End,Author,Editor,Employee,Create Date,Created By,Last Updated,Last Updated By";
            exportableDataList.add(headers);
            for (Appointment appointment : appointmentList) {
                String personData = appointment.getAppointment_ID() + "," + appointment.getTitle() + "," + appointment.getDescription() + "," + appointment.getLocation() + "," + appointment.getType() + "," + appointment.getStart().toString() + "," + appointment.getEnd().toString() + "," + appointment.getAuthor_Name() + "," + appointment.getEditor_Name() + "," + appointment.getEmployee_Name() + "," + appointment.getCreate_Date().toString() + "," + appointment.getCreated_By() + "," + appointment.getLast_Update().toString() + "," + appointment.getLast_Updated_By();
                exportableDataList.add(personData);
            }
            FileWriter fileWriter = new FileWriter(location, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (String line : exportableDataList) {
                printWriter.print(line + "\n");
            }
            printWriter.close();
            successfulExport = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return successfulExport;
    }

    public static String generateHash(String password, String salt) {

        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
}
