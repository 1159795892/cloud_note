package com.pj.cloud_note.util;

/**
 * 自定义异常
 * @author 小锦
 */
public class NoteException extends RuntimeException{
	public NoteException(String msg,Throwable t){
		super(msg,t);
	}
}
