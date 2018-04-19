package model.prank;

import config.ConfigurationManager;
import exception.NotEnoughVictimsException;
import model.mail.Group;
import model.mail.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that generates Pranks to Groups
 *
 * @author Joël Kaufmann (Jokau), Loïc Schürch (loic-schurch)
 */
public class PrankGenerator {
    private ConfigurationManager confManager;
    private ArrayList<Group> groups;


    public PrankGenerator() throws NotEnoughVictimsException {
        confManager = ConfigurationManager.getConfigManager();
        int nbGroups = 5; //TODO confManager.getNbOfGroups();
        this.groups = createGroups(nbGroups);
        //TODO assign a Prank to each group
        //TODO
    }

    public ArrayList<Prank> getPranks() {
        //TODO
        return null;
    }

    /**
     * Creates the Pranks' groups
     * @param nbGroups number of groups set
     * @return a list of all groups
     */
    private ArrayList<Group> createGroups(int nbGroups) throws NotEnoughVictimsException {
        ArrayList<Person> victims = confManager.getVictims();

        ArrayList<Person>[] groupVictims = new ArrayList[nbGroups];
        for (int i = 0; i < groupVictims.length; i++) {
            // init lists
            groupVictims[i] = new ArrayList<Person>();
        }

        for (int j = 0; j < victims.size(); j++) {
            // fill lists with victims
            groupVictims[j % nbGroups].add(victims.get(j));
        }

        ArrayList<Group> groups = new ArrayList<Group>();
        for (int i = 0; i < nbGroups; i++) {
            // elect sender and delete from list
            Person sender = groupVictims[i].get(0);
            groupVictims[i].remove(0);
            // remaining people are the receivers

            //create groupe
            groups.add(new Group(sender, groupVictims[i]));
        }
        return groups;
    }
}
