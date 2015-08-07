package com.github.liuxboy.jdk.source.code.effective.java;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 刘春东
 * @version 
 * @time 2014年1月30日下午11:44:32
 */
public class StaticFactoryMethod {
	public static void main(String[] args) {
		//传递是Apple
		Friut apple = FriutFactory.getFriut("Apple");//Not-New
		if(apple != null){
			apple.say();
		}else{
			System.out.println("暂时不生产该水果");
		}
	}
}

class Gender {
	private String description;
	private Gender(String description){
		this.description = description;
	}
	private static final Gender female = new Gender("女");
	private static final Gender male = new Gender("男");
	
	public static Gender getFemale(){
		return female;
	}
	
	public static Gender getMale(){
		return male;
	}
	
	public String getDescription(){
		return description;
	}
}



///////////////////////////////////////////////////////////////////////
interface Friut{
	public void say();
}

class Apple implements Friut{
	public void say(){
		System.out.println("I am an Apple");
	}
}

class Grape implements Friut{
	public void say(){
		System.out.println("I am a Grape");
	}
}

class FriutFactory{
	public static Friut getFriut(String type){
		Friut friut = null;
		try {
			friut = (Friut)Class.forName("org.arcie.dong.effectivejava." + type).newInstance();
		} catch (InstantiationException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch(IllegalAccessException e){
			// TODO: handle exception
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			// TODO: handle exception
			e.printStackTrace();
		}
		return friut;
	}
}
////////////////////////////////////////////////////////////////////////////////
//Service provider framework sketch
//Service interface
interface Service{
	//Service-specific methods go here
}

interface Provider{
	Service newService();
}

class Services{
	private Services(){	}	//Prevents instantiation (Item 4)
	
	//Maps service names to services
	private static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();
	public static final String DEFAULT_PROVIDER_NAME = "<def>";
	//Provider registration API
	public static void registerDefaultProvider(Provider p){
		registerProvider(DEFAULT_PROVIDER_NAME, p);
	}
	public static void registerProvider(String name, Provider p){
		providers.put(name, p);
	}
	
	//Service access API
	public static Service newInstance(){
		return newInstance(DEFAULT_PROVIDER_NAME);
	}
	public static Service newInstance(String name){
		Provider p = providers.get(name);
		if(p == null){
			throw new IllegalArgumentException("No Provider registered with name:" + name);
		}
		return p.newService();
	}
}
























