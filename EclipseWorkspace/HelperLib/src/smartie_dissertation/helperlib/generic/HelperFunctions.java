package smartie_dissertation.helperlib.generic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import smartie_dissertation.helperlib.log.MyLog;

public class HelperFunctions {
	
	/*
	 * online source
	 */
	public static String StringToSH1(String inputString)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(inputString.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	    	MyLog.log(e.getMessage(), MyLog.logMessageType.ERROR, HelperFunctions.class.toString());
	    }
	    catch(UnsupportedEncodingException e)
	    {
	    	MyLog.log(e.getMessage(), MyLog.logMessageType.ERROR, HelperFunctions.class.toString());
	    }
	    return sha1;
	}

	/*
	 * online source
	 */
	public static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	
	/*
	 * online source
	 */
	public static boolean isJSONValid(String test) {
	    try {
	        new JSONObject(test);
	    } catch (JSONException ex) {
	        // edited, to include @Arthur's comment
	        // e.g. in case JSONArray is valid as well...
	        try {
	            new JSONArray(test);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static boolean ReturnTrue() {
	    return true;
	}
}
