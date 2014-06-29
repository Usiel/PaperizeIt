package jobs;

import models.Preference;
import models.PreferenceSubscription;
import models.Source;
import models.SourceBias;
import models.Subscription;
import models.SubscriptionModel;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {

	// For DEMO purposes only
    public void doJob() {
    	Preference.GeneratePreferences();
    	Source.GenerateSources();
    	SubscriptionModel.GenerateSubscriptionModels();
    	User.GenerateUsers();
    	Subscription.GenerateSubscriptions();
    	SourceBias.GenerateSourceBias();
    	PreferenceSubscription.GeneratePreferenceSubscription();
    }    
}