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
    private ArrayList<Prank> pranks;


    public PrankGenerator() throws NotEnoughVictimsException {
        confManager = ConfigurationManager.getConfigManager();
        int nbGroups = confManager.getNumberOfGroups();
        groups = createGroups(nbGroups);
        pranks = assignMessageToGroups();
    }

    /**
     * Creates pranks by assigning a message to each groups
     * @return The list of pranks
     */
    private ArrayList<Prank> assignMessageToGroups() {
        ArrayList<Prank> pranks = new ArrayList<Prank>();
        ArrayList<String> messages = confManager.getMessages();
        for(int i = 0; i < groups.size(); ++i) {
            pranks.add(new Prank(groups.get(i).getSender(),
                       groups.get(i).getReceivers(), messages.get(i % messages.size())));
        }
        return pranks;
    }

    public ArrayList<Prank> getPranks() {
        return pranks;
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
