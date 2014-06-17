import java.util.HashMap;
import java.util.Map;

import net.sf.oval.constraint.AssertURLCheck;

import org.junit.Test;

import play.test.*;
import play.mvc.Http.*;

/*
 * We use more or less integration tests, since there is no relevant code from us to unit test
 */
public class PaperizeTest extends FunctionalTest {

	/*
	 * Subscribe renders a form allowing the user to take the first step of subscribing 
	 * Sends a valid request and expects a 200 status code, correct content type and charset
	 */
    @Test
    public void subscribe_validRequest_okResponseContentTypeAndCharset() {
        Response response = GET("/paperize/subscribe");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
    
    /*
     * SelectPreferences takes the values provided by the user from the form rendered by Subscribe
     * Sends an invalid request (no params), status should be 400
     */
    //@Test
    public void selectPreferences_invalidRequest_statusIsBadRequest() {
    	Response response = POST("/paperize/selectpreferences");
    	assertStatus(400, response);
    }
    
    /*
     * Makes valid request and expects OK response
     */
    //@Test
    public void selectPreferences_validRequest_okResponse() {
    	HashMap<String, String> parameters = new HashMap<String, String>();
    	
    	// Set 2 preferences
    	parameters.put("preference", "1");
    	parameters.put("preference", "2");
    	
    	// Set 1 source bias
    	parameters.put("source_0_option", "1");
    	parameters.put("source_0_bias", "3");
    	
    	Response response = POST("paperize/selectpreferences", parameters);
    	assertStatus(200, response);
    }
    
    /*
     * Makes valid request (no source bias selected!)
     */
    //@Test
    public void selectPreferences_validRequestNoBias_okResponse() {
    	HashMap<String, String> parameters = new HashMap<String, String>();
    	
    	// Set 2 preferences
    	parameters.put("preference", "1");
    	parameters.put("preference", "2");
    	
    	// Simulate invalid source bias (no option selected) which is OK and should not lead to an error
    	parameters.put("source_0_option", "0");
    	parameters.put("source_0_bias", "3");
    	
    	Response response = POST("paperize/selectpreferences", parameters);
    	assertStatus(200, response);
    }
    
    /*
     * Make sure GET requests to selectPreferences submit action are ignored and lead to 404
     */
    @Test
    public void selectPreferences_getRequest_returnPageNotFound() {
    	// Do GET request instead of POST
    	Response response = GET("paperize/selectpreferences");
    	assertStatus(404, response);
    }
}