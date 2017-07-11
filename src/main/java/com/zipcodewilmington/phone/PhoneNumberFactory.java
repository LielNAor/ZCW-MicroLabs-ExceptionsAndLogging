package com.zipcodewilmington.phone;

import java.util.logging.Logger;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.RandomNumberFactory;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     * @throws InvalidPhoneNumberFormatException 
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) throws InvalidPhoneNumberFormatException {
    	PhoneNumber[] result = new PhoneNumber[phoneNumberCount];
    	for (int i=0; i<phoneNumberCount; i++){
    		result[i] = createRandomPhoneNumber();
    	}
        return result;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     * @throws InvalidPhoneNumberFormatException 
     */ //TODO - Implement logic
    private static PhoneNumber createRandomPhoneNumber() throws InvalidPhoneNumberFormatException {
        Integer areaCode = RandomNumberFactory.createInteger(100, 999);
        Integer centralOfficeCode = RandomNumberFactory.createInteger(100, 999);
        Integer phoneLineCode = RandomNumberFactory.createInteger(1000, 9999);
        PhoneNumber result = createPhoneNumberSafely((int)areaCode, (int)centralOfficeCode, (int)phoneLineCode);
    	return result;
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException 
     */ 
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) throws InvalidPhoneNumberFormatException {
    	String phoneNumberString = "("+areaCode+")-"+centralOfficeCode+"-"+phoneLineCode;
    	if(phoneNumberString.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
    		return createPhoneNumber(phoneNumberString);
    	}
        return null;
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ 
    public static PhoneNumber createPhoneNumber (String phoneNumberString) throws InvalidPhoneNumberFormatException{
        logger.info("Attempting to create a new PhoneNumber object with a value of"+phoneNumberString);
    	return new PhoneNumber(phoneNumberString);
    }
}