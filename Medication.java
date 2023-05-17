import java.util.Date;


/**
 * This class is responsible to provide all medication related functionalities.
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class Medication implements java.io.Serializable {


    private String details;
    private int duration;
    private Date startDate;
    private double Dosage;
    private String notes;

    /**
     * Default constructor for Medication.
     */
    public Medication(){

        details = null;
        duration = 0;
        startDate = null;
        Dosage = 0;
        notes = null;

    }

    /**
     * Parametrized constructor for medication.
     *
     * @param details   is the details of medication.
     * @param duration  is the duration of the medication.
     * @param startDate is the start date of the medication.
     * @param dosage    is the dosage of the medication.
     */
    public Medication(String details, int duration, Date startDate, double dosage) {
        this.details = details;
        this.duration = duration;
        this.startDate = startDate;
        Dosage = dosage;
        this.notes = "No notes.";
    }

    /**
     * Parametrized constructor for medication.
     *
     * @param details   is the details of medication.
     * @param duration  is the duration of the medication.
     * @param startDate is the start date of the medication.
     * @param dosage    is the dosage of the medication.
     * @param notes     is the notes for the medication.
     */
    public Medication(String details, int duration, Date startDate, double dosage, String notes) {
        this.details = details;
        this.duration = duration;
        this.startDate = startDate;
        Dosage = dosage;
        this.notes = notes;
    }

    /**
     * Gives details of medication.
     *
     * @return the details of medication.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Gets duration of medication.
     *
     * @return the duration.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gives start date of medication.
     *
     * @return the start date of medication.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Gives dosage of medication.
     *
     * @return the dosage of medication.
     */
    public double getDosage() {
        return Dosage;
    }

    /**
     * Gives notes of medication.
     *
     * @return the notes of medication.
     */
    public String getNotes() {
        return notes;
    }


    /**
     * Sets details of medication.
     *
     * @param details the details of medication.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Sets duration of medication.
     *
     * @param duration the duration of medication.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Sets start date of medication.
     *
     * @param startDate the start date of medication.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets dosage of medication.
     *
     * @param dosage the dosage of medication.
     */
    public void setDosage(double dosage) {
        Dosage = dosage;
    }

    /**
     * Sets notes of medication.
     *
     * @param notes the notes of medication.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }





}
