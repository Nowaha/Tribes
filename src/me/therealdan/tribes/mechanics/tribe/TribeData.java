package me.therealdan.tribes.mechanics.tribe;


import me.therealdan.tribes.Tribes;

/**
 * Created by Daniel on 1/09/2016.
 */
public class TribeData {

    public void setDescription(String tribe, String description) {
        Tribes.getInstance().TribeData.set("Tribes." + tribe + ".Description", description);
        Tribes.getInstance().saveTribeData();
    }

    public boolean hasGracePeriod(String tribe) {
        return (System.currentTimeMillis() / 1000 - Tribes.getInstance().TribeData.getLong("Tribes." + tribe + ".Created")
                > Tribes.getInstance().configuration.gracePeriod);
    }

    public long getGracePeriodTime(String tribe) {
        long difference = System.currentTimeMillis() / 1000 - Tribes.getInstance().TribeData.getLong("Tribes." + tribe + ".Created");
        return Tribes.getInstance().configuration.gracePeriod - difference;
    }

    public long getTimeRemaining(String tribe) {
        long timeStamp = Tribes.getInstance().TribeData.getLong("Tribes." + tribe + ".TimeStamp.Description");
        return (1000 * 60) - (System.currentTimeMillis() - timeStamp);
    }

    public String getDescription(String tribe) {
        if (!Tribes.getInstance().TribeData.contains("Tribes." + tribe + ".Description")) {
            setDescription(tribe, "/Tribes Description");
        }
        return Tribes.getInstance().TribeData.getString("Tribes." + tribe + ".Description");
    }
}