package controllers;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import models.Article;
import models.Preference;
import models.PreferenceSubscription;
import models.Source;
import models.SourceBias;
import models.User;
import play.mvc.Controller;

public class PDFGenerator extends Controller {
	public static void index(String user) {
		
		
		System.out.println("User:" + user);
		
		User recipient = (User)User.find("byEmail", user).first();
		models.Subscription subscription = (models.Subscription)models.Subscription.find("byUser", recipient).first();
		List<PreferenceSubscription> preferences = subscription.preferences;
		
		//search for positive rated sources
		Vector<Source> positiveSources = new Vector<Source>();
		for(SourceBias sb : subscription.sourceBias) if(sb.bias>0) positiveSources.addElement(sb.source);
		
		//create empty vector to add articles matching the source and preference
		Vector<Article> articles = new Vector<Article>();
		
		for(PreferenceSubscription ps : preferences) {
			for(Source s : positiveSources) {
				List<Article> arts = Article.find("byPreferenceAndSource", ps.preference, s).fetch();
				for(Article a : arts) articles.addElement(a);
			}
			
		}
		
		
		
		Article titleArticle = articles.firstElement();
		
		
		Date newsPaperDate = new Date();
		
        render(newsPaperDate, titleArticle, articles, preferences, recipient);
    }
}
