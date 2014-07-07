package org.arcie.dong.reader;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

/**
 * @author 刘春东
 * @version 
 * @param <E>
 * @time 2014年1月30日下午4:30:37
 */
public class JavaCollectionFramework<E> {
	/**
	 * 容器类Collection
	 */
	Collection<?> cl;
	List<?> list = null;
	Vector<?> vector = null;
	HashMap<?, ?> hm;
	Hashtable<?, ?> ht;
	
	AbstractCollection<E> sc;
	EnumSet<?> es;
	
}
