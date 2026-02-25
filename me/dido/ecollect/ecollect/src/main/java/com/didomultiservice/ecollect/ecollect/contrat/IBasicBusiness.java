
/*
 * Created on 2019-10-16 ( Time 13:32:02 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.didomultiservice.ecollect.ecollect.contrat;

import java.text.ParseException;
import java.util.Locale;

/**
 * IBasic Business
 * 
 * @author Geo
 *
 */
public interface IBasicBusiness<T, K> {

	/**
	 * create Object by using T as object.
	 * 
	 * @param T
	 * @return K
	 * @throws ParseException 
	 * 
	 */
	public abstract K create(T request, Locale locale) throws ParseException;

	/**
	 * update Object by using T as object.
	 * 
	 * @param T
	 * @return K
	 * @throws ParseException 
	 * 
	 */
	public abstract K update(T request, Locale locale) throws ParseException;

	/**
	 * delete Object by using T as object.
	 * 
	 * @param T
	 * @return K
	 * 
	 */
	public abstract K delete(T request, Locale locale);
	
	/**
	 * delete Object by using T as object.
	 * 
	 * @param T
	 * @return K
	 * @throws ParseException 
	 * 
	 */
	public abstract K forceDelete(T request, Locale locale) throws ParseException;


	/**
	 * get a List of Object by using T as criteria object.
	 * 
	 * @param T
	 * @return K
	 * @throws Exception 
	 * 
	 */
	public abstract K getByCriteria(T request, Locale locale) throws Exception;
}
