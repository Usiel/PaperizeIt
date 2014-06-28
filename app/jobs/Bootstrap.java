package jobs;

import models.Preference;
import models.Source;
import models.SubscriptionModel;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
    	Preference.GeneratePreferences();
    	Source.GenerateSources();
    	SubscriptionModel.GenerateSubscriptionModels();
    	User.GenerateUsers();
    }    
}