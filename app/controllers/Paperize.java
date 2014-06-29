package controllers;

import play.mvc.*;
import play.mvc.Http.Cookie;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;

import com.ning.http.client.Response;

import models.*;
import models.Subscription;

public class Paperize extends Controller {

    public static void index() {
        render();
    }


	public static void contactus () {
		render();
	}

	public static void jobs () {
		render();
	}
	
	public static void blog () {
		render();
	}
	
	public static void pricing () {
		render();
	}
	
	public static void features () {
		render();
	}
	
	public static void support () {
		render();
	}
	
}