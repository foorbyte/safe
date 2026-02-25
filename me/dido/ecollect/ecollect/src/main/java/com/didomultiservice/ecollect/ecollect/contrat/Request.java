
/*
 * Created on 2019-10-16 ( Time 13:32:02 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

package com.didomultiservice.ecollect.ecollect.contrat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
 * Request
 * 
 * @author Geo
 *
 */
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class Request<T> extends RequestBase {
	protected T			data;
	protected List<T>	datas;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	
	
	
}