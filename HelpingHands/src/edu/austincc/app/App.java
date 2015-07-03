package edu.austincc.app;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import sun.misc.BASE64Encoder;

public class App {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		 SecureRandom random = new SecureRandom();
         // Salt generation 64 bits long
         byte[] bSalt = new byte[8];
         random.nextBytes(bSalt);
         // Digest computation
         //byte[] bDigest = getHash(ITERATION_NUMBER,password,bSalt);
         //String sDigest = byteToBase64(bDigest);
         String sSalt = byteToBase64(bSalt);
         System.out.println(sSalt);
		
	}

	public static String byteToBase64(byte[] data){
	       BASE64Encoder endecoder = new BASE64Encoder();
	       return endecoder.encode(data);
	   }
}
