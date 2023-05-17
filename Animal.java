import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * This abstract class is responsible to provide a skeleton for the rest of the animals.
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public abstract class Animal implements java.io.Serializable {

    private int tagNo;
    private String gender;
    private Date dateOfBirth;
    private boolean purchased;

    private HashMap Milking;
    private ArrayList<Treatment> treatments;


    /**
     * Instantiates a new Animal.
     *
     * @param tagNo       the tag no
     * @param gender      the gender
     * @param dateOfBirth the date of birth
     * @param purchased   the purchased
     * @param milking     the milking
     * @param treatments  the treatments
     */
    public Animal(int tagNo, String gender, Date dateOfBirth, boolean purchased, HashMap milking, ArrayList<Treatment> treatments) {
        this.tagNo = tagNo;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.purchased = purchased;
        Milking = milking;
        this.treatments = treatments;
    }

    /**
     * Instantiates a new Animal.
     *
     * @param tagNo       the tag no
     * @param gender      the gender
     * @param dateOfBirth the date of birth
     * @param purchased   the purchased
     * @param treatments  the treatments
     */
    public Animal(int tagNo, String gender, Date dateOfBirth, boolean purchased, ArrayList<Treatment> treatments) {
        this.tagNo = tagNo;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.purchased = purchased;
        Milking = new HashMap<LocalDate,Double>();
        this.treatments = treatments;
    }

    /**
     * Instantiates a new Animal.
     */
    public Animal() {
        dateOfBirth = new Date();
        Milking = new HashMap<LocalDate,Double>();
        treatments = new ArrayList<Treatment>();
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {

        Date D = getDateOfBirth();
        Instant I = D.toInstant();
        ZonedDateTime Z = I.atZone(ZoneId.systemDefault());
        LocalDate L = Z.toLocalDate();
        Period P =Period.between(L, LocalDate.now());
        return P.getYears();

    }


    /**
     * Gets tag no.
     *
     * @return the tag no
     */
    public int getTagNo() {
        return tagNo;
    }


    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }


    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }


    /**
     * Is purchased boolean.
     *
     * @return the boolean
     */
    public boolean isPurchased() {
        return purchased;
    }

    /**
     * Gets milking.
     *
     * @return the milking
     */
    public HashMap getMilking() {
        return Milking;
    }


    /**
     * Gets treatments.
     *
     * @return the treatments
     */
    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }


    /**
     * Sets tag no.
     *
     * @param tagNo the tag no
     */
    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }


    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Sets purchased.
     *
     * @param purchased the purchased
     */
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    /**
     * Sets treatments for cow.
     *
     * @param treatments the treatments of cow.
     */
    public void setTreatments(Treatment treatments) {
        this.treatments.add(treatments);
    }

    /**
     * Sets milking.
     *
     * @param milking the milking
     */
    public void setMilking(HashMap milking) {
        Milking = milking;
    }

    /**
     * Feeding.
     */
    public abstract String feeding();


}
