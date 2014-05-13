package org.arcie.dong.reader;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**参考Michael Lee from web
 * Java Reflection Cookbook
 * @author Michael Lee
 * @since 2006-8-23
 * @version 0.1a
 */

public class ReflectExample {
	/**
	 * 得到某个对象的公共属性
	 * 
	 * @param owner, fieldName
	 * @return 该属性对象
	 * @throws Exception
	 * 
	 */
	public Object getProperty(Object owner, String fieldName) throws Exception {
		Class<? extends Object> ownerClass = owner.getClass();

		Field field = ownerClass.getField(fieldName);

		Object property = field.get(owner);

		return property;
	}

	/**
	 * 得到某类的静态公共属性
	 * 
	 * @param className 类名
	 * @param fieldName 属性名
	 * @return 该属性对象
	 * @throws Exception
	 */
	public Object getStaticProperty(String className, String fieldName)
			throws Exception {
		Class<?> ownerClass = Class.forName(className);

		Field field = ownerClass.getField(fieldName);

		Object property = field.get(ownerClass);

		return property;
	}

	/**
	 * 执行某对象方法
	 * 
	 * @param owner 对象
	 * @param methodName 方法名
	 * @param args 参数
	 * @return 方法返回值
	 * @throws Exception
	 */
	public Object invokeMethod(Object owner, String methodName, Object[] args)
			throws Exception {

		Class<? extends Object> ownerClass = owner.getClass();

		Class<?>[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(owner, args);
	}

	/**
	 * 执行某类的静态方法
	 * 
	 * @param className 类名
	 * @param methodName 方法名
	 * @param args 参数数组
	 * @return 执行方法返回的结果
	 * @throws Exception
	 */
	public Object invokeStaticMethod(String className, String methodName,
			Object[] args) throws Exception {
		Class<?> ownerClass = Class.forName(className);

		Class<?>[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(null, args);
	}

	/**
	 * 新建实例
	 * 
	 * @param className 类名
	 * @param args 构造函数的参数
	 * @return 新建的实例
	 * @throws Exception
	 */
	public Object newInstance(String className, Object[] args) throws Exception {
		Class<?> newoneClass = Class.forName(className);

		Class<?>[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Constructor<?> cons = newoneClass.getConstructor(argsClass);

		return cons.newInstance(args);

	}

	/**
	 * 是不是某个类的实例
	 * 
	 * @param obj 实例
	 * @param cls 类
	 * @return 如果 obj 是此类的实例，则返回 true
	 */
	public boolean isInstance(Object obj, Class<?> cls) {
		return cls.isInstance(obj);
	}

	/**
	 * 得到数组中的某个元素
	 * 
	 * @param array 数组
	 * @param index 索引
	 * @return 返回指定数组对象中索引组件的值
	 */
	public Object getByArray(Object array, int index) {
		return Array.get(array, index);
	}
	
	
	public static void main(String[] args) {
		ReflectExample reft = new ReflectExample();
		ReflectClass[] rc = {
								new ReflectClass(23L, "张三"), 
								new ReflectClass(12L, "xiaoming"),
								new ReflectClass(9L, "Anton")
							};
		String name;
		for(int i = 0; i < rc.length; i++){
			System.out.println(rc[i].id);
			try {
				Class<?> classType = rc[i].getClass();
				
				Field field = classType.getDeclaredField("name");
				field.setAccessible(true);	//设置私有域可见
				System.out.println(field.get(rc[i]));//可访问
				
				Method method = classType.getDeclaredMethod("getInfo",Long.class,String.class);
				method.setAccessible(true);	//设置私有域可见
				method.invoke(classType,new Long(25),"zhangshanfeng");//可访问
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(reft.getClass().getSimpleName());	//只是类名，不包含包名
		System.out.println(reft.getClass().getName());			//包含包名的类名					
		System.out.println(reft.getClass().getCanonicalName());	//包含包名的类名
	}
}

//普通类
class ReflectClass{	
	public Long id;
	private String name;		//私有域
	/**
	 * @param id
	 * @param name
	 */
	public ReflectClass(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	/**
	 * 私有方法
	 * @param id
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getInfo(Long id, String name){	//私有方法
		return id+"-"+name;
	}
}