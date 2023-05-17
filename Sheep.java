import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * This class is responsible to provide a sheep and has all related functionalities.
 *
 *  @author Muhammad Khizr Shahid 2413235
 * @version 1.0
 */
public class Sheep extends Animal{


    /**
     * Instantiates a new Sheep.
     */
    public Sheep() {
    }

    /**
     * Instantiates a new Sheep.
     *
     * @param tagNo       the tag no
     * @param gender      the gender
     * @param dateOfBirth the date of birth
     * @param purchased   the purchased
     * @param milking     the milking
     * @param treatments  the treatments
     */
    public Sheep(int tagNo, String gender, Date dateOfBirth, boolean purchased, HashMap milking, ArrayList<Treatment> treatments) {
        super(tagNo, gender, dateOfBirth, purchased, milking, treatments);
    }

    /**
     * Instantiates a new Sheep.
     *
     * @param tagNo       the tag no
     * @param gender      the gender
     * @param dateOfBirth the date of birth
     * @param purchased   the purchased
     * @param treatments  the treatments
     */
    public Sheep(int tagNo, String gender, Date dateOfBirth, boolean purchased, ArrayList<Treatment> treatments) {
        super(tagNo, gender, dateOfBirth, purchased, treatments);
    }



    public String feeding() {
        String result = " ";

        if ((getGender().equalsIgnoreCase("Male")) && getAge() <5) {
            result += "Only grass." + "\n";
        }
        else if ((getGender().equalsIgnoreCase("Female")) && getAge() < 8) {
            result += "Only grass." + "\n";
        }
        else if ((getGender().equalsIgnoreCase("Male")) && getAge() > 5) {
            result += "Total mixed ration (TMR) is needed." + "\n";
        }
        else if ((getGender().equalsIgnoreCase("Female")) && getAge() > 8) {
            result += "Total mixed ration (TMR) is needed." + "\n";
        }
        return result;
    }


}
