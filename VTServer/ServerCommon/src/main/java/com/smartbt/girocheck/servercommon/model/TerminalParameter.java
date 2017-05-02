/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package com.smartbt.girocheck.servercommon.model;

import java.io.Serializable;
public class TerminalParameter implements Serializable {
	public TerminalParameter() {
	}
	
	private int id;
	
	private String name;
	
	private Boolean readOnly;
	
	private String description;
	
	private Integer dataType;
	
	private java.util.Set<com.smartbt.girocheck.servercommon.model.TerminalParameterValue> terminal_Parameter_Value = new java.util.HashSet<com.smartbt.girocheck.servercommon.model.TerminalParameterValue>();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setReadOnly(boolean value) {
		setReadOnly(new Boolean(value));
	}
	
	public void setReadOnly(Boolean value) {
		this.readOnly = value;
	}
	
	public Boolean getReadOnly() {
		return readOnly;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDataType(int value) {
		setDataType(new Integer(value));
	}
	
	public void setDataType(Integer value) {
		this.dataType = value;
	}
	
	public Integer getDataType() {
		return dataType;
	}
	
	public void setTerminal_Parameter_Value(java.util.Set<com.smartbt.girocheck.servercommon.model.TerminalParameterValue> value) {
		this.terminal_Parameter_Value = value;
	}
	
	public java.util.Set<com.smartbt.girocheck.servercommon.model.TerminalParameterValue> getTerminal_Parameter_Value() {
		return terminal_Parameter_Value;
	}
	
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
