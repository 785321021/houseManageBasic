package com.coins.entity.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class Oper implements Serializable {
	private static Map<Integer, List<String>> creatorMaps = new HashMap<>(3);
	private static Map<String, Integer> revertCreatorMaps = new HashMap<>(); // creatorMaps 的反转.
	private static Map<Integer, List<String>> modifierMaps = new HashMap<>(3);
	private static Map<String, Integer> revertModifierMaps = new HashMap<>(); // modifierMaps 的反转.

	static {
		initCreatorMaps();
		initModifierMaps();
	}

	private int id;
	private String name;
	private Date time;

	private Oper() {
	}

	/**
	 * 以构建时间为操作时间构建实例.如果是系统操作,请使用{@link Oper#system()}.
	 */
	public Oper(int id, String name) {
		this.id = id;
		this.name = name;
		this.time = new Date();
	}

	public Oper(int id, String name, Date time) {
		this(id, name);
		this.time = time;
	}

	/**
	 * 员工作为操作者,实例构建时间为操作时间.
	 */
//    public Oper(Employee employee) {
//        this(employee.getId(), employee.getName());
//    }

	/**
	 * 系统操作者.每次返回新的实例，因为操作时间为每次调用的时间. 请调用这个方法后，将返回值存起来.
	 */
	public static Oper system() {
		return new Oper(1, "sys", new Date());
	}

	private static void initCreatorMaps() {

		creatorMaps.put(1, Lists.newArrayList("creatorId"));
		creatorMaps.put(2, Lists.newArrayList("creatorName"));
		creatorMaps.put(3, Lists.newArrayList("createdTime"));

		for (Map.Entry<Integer, List<String>> entrySet : creatorMaps.entrySet()) {
			for (String s : entrySet.getValue()) {
				revertCreatorMaps.put(s, entrySet.getKey());
			}
		}
	}

	private static void initModifierMaps() {

		modifierMaps.put(1, Lists.newArrayList("modifierId"));
		modifierMaps.put(2, Lists.newArrayList("modifierName"));
		modifierMaps.put(3, Lists.newArrayList("modifiedTime"));

		for (Map.Entry<Integer, List<String>> entrySet : modifierMaps.entrySet()) {
			for (String s : entrySet.getValue()) {
				revertModifierMaps.put(s, entrySet.getKey());
			}
		}
	}

	/**
	 * 讲system设置为创建者.
	 *
	 * @param model
	 * @param       <T>
	 */
	public static <T> void setSystemAsCreator(T model) {
		setCreator(model, Oper.system());
	}

	/**
	 * 设置创建者.
	 *
	 * @param model
	 * @param oper
	 * @param       <T>
	 */
	public static <T> void setCreator(T model, Oper oper) {
		setOper(model, oper, revertCreatorMaps);
	}

	/**
	 * 设置创建者和修改者.
	 *
	 * @param model
	 * @param oper
	 * @param       <T>
	 */
	public static <T> void setCreatorAndModifier(T model, Oper oper) {
		setCreator(model, oper);
		setModifier(model, oper);
	}

	/**
	 * 设置系统为创建者和修改者.
	 *
	 * @param model
	 * @param       <T>
	 */
	public static <T> void setSystemAsCreatorAndModifier(T model) {

	}

	/**
	 * 设置系统为修改者.
	 *
	 * @param model
	 * @param       <T>
	 */
	public static <T> void setSystemAsModifier(T model) {
		setModifier(model, Oper.system());
	}

	/**
	 * 设置修改者.
	 *
	 * @param model
	 * @param oper
	 * @param       <T>
	 */
	public static <T> void setModifier(T model, Oper oper) {
		setOper(model, oper, revertModifierMaps);
	}

	/**
	 * 设置创建者.
	 *
	 * @param model
	 * @param oper
	 * @param revertMaps
	 * @param            <T>
	 */
	private static <T> void setOper(T model, Oper oper, Map<String, Integer> revertMaps) {

		List<Field> fields = getAllDeclaredFields(model.getClass());

		for (Field field : fields) {
			field.setAccessible(true);
			try {

				final String fieldName = field.getName();

				final Integer fieldIndex = revertMaps.get(fieldName);

				if (fieldIndex == null || fieldIndex > 3) {
					continue;
				}

				Object value = null;
				switch (fieldIndex) {
				case 1:
					value = oper.getId();
					break;
				case 2:
					value = oper.getName();
					break;
				case 3:
					value = oper.getTime();
					break;
				default:
					break;
				}

				field.set(model, value);

			} catch (Exception e) {
				throw new RuntimeException("设置操作人字段失败", e);
			}

		}
	}

	/**
	 * 获取继承链上的定义的所有字段(所有访问级别).
	 *
	 * @param clazz
	 * @return
	 */
	private static List<Field> getAllDeclaredFields(final Class<?> clazz) {

		Class<?> newClazz = clazz;
		List<Field> fields = new ArrayList<>();

		while (newClazz != null) {
			fields.addAll(Arrays.asList(newClazz.getDeclaredFields()));
			newClazz = newClazz.getSuperclass();
		}
		return fields;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getTime() {
		return time;
	}
}
