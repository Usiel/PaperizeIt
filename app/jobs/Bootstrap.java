package jobs;

import models.Preference;
import models.Source;
import models.SubscriptionModel;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

    public void generateDbEntries() {
    	Preference.GeneratePreferences();
    	Source.GenerateSources();
    	SubscriptionModel.GenerateSubscriptionModels();
    }    
}