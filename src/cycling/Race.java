package cycling;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Race {
    private int[][] point_leaderboard;
    private int id;
    private static int[][] mountainCheckpointValues;
    private ArrayList<Rider> riders;
    private int[] generalClassification;
    private int[] sprinterClassification;
    private int[] mountainClassification;
    private String name;
    private String description;
    private double length;
    private static Map<Integer, Race> races;
    private Map<Integer, Stage> stages;
    private static ArrayList<Integer> usedStageIds;

    public Race() {
        
    }
    public Race(String name, String description) {
        int id = 0;
		boolean used = true;
		while(used) {
			id = (int)Math.floor(Math.random() *(1000 - 1000 + 1) + 1000);
			for(int i=0; i<races.size()-1; i++) {
				if(races.containsKey(id) == false) {
					used = false;
				}
			}
		}

        this.name = name;
        this.description = description;
        this.id = id;
        races.put(this.id, this);
    }
    public int getId() {
        return this.id;
    }
    public int stageNumber() {
        return this.stages.size();
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public double getLength() {
        double length = 0;
        for(int i = 0; i<stages.size() - 1; i++) {
            length = length + stages.get(i).getLength();
        }
        return length;
    }
    public static int[] getRaceIds() {
        return races.keySet().stream().mapToInt(Integer::intValue).toArray();
    }
    public static int[] getStageIds() {
        return usedStageIds.stream().mapToInt(Integer::intValue).toArray();
    }
    public static Map<Integer, Race> getRaces() {
        return races;
    }
    public int addStage(String name, String description, double Length, LocalDateTime starTime, StageType type) {
        int id = 0;
		boolean used = true;
		while(used) {
			id = (int)Math.floor(Math.random() *(1000 - 1000 + 1) + 1000);
			for(int i=0; i<stages.size()-1; i++) {
				if(stages.containsKey(id) == false) {
					used = false;
				}
			}
		}
        this.stages.put(id, new Stage(name, description, length, starTime, type, id));
        return id;
    }
    public ArrayList<Rider> getRiders() {
        return this.riders;
    }
    public Map<Integer, Stage> getStages() {
        return this.stages;
    }
    public static Race findStage(int stageId) {
        Race race = new Race();
        int[] raceIds = getRaceIds();
		for(int i=0; i<raceIds.length-1; i++) {
			if(races.get(raceIds[i]).getStages().containsKey(stageId)) {
                race = races.get(raceIds[i]);
            }
        }
        return race;
    }

    //Any formatted string containing the race ID, name, description, the number of stages, and the total length (i.e., the sum of all stages' length).
    @Override
    public String toString() {
        return "{" +
            ", id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", length=' " + getLength() + "'" +
            "}";
    }

}

