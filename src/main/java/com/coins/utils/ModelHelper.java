package com.coins.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.coins.entity.common.ModifyModel;
import com.google.common.collect.Lists;

public class ModelHelper {
	public static <T> void emptyNullValue(final T model, String... ignoreNames) {
		Class<?> tClass = model.getClass();
		List<Field> fields = Lists.newArrayList();
		getAllFields(fields, tClass);
		for (Field field : fields) {
			Type t = field.getType();
			field.setAccessible(true);
			boolean isIgnore = false;
			for (int i = 0; i < ignoreNames.length; i++) {
				if (field.getName().equals(ignoreNames[i]))
					isIgnore = true;
				break;
			}
			if (isIgnore)
				continue;
			try {
				if (t == String.class && field.get(model) == null) {
					field.set(model, "");
				} else if (t == BigDecimal.class && field.get(model) == null) {
					field.set(model, new BigDecimal(0));
				} else if (t == Long.class && field.get(model) == null) {
					field.set(model, 0L);
				} else if (t == Integer.class && field.get(model) == null) {
					field.set(model, 0);
//                } else if (t == Date.class && field.get(model) == null) {
//                    field.set(model, TimeHelper.LocalDateTimeToDate(java.time.LocalDateTime.of(1990, 1, 1, 0, 0, 0, 0)));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

//    public static <T> void setDefaultAndSystemFieldsValue(final T model, final Employee user, boolean isSetDefaultFieldsValue) {
//        Class<?> tClass = model.getClass();
//        List<Field> fields = Lists.newArrayList();
//        getAllFields(fields, tClass);
//        boolean isCreate = true;
//        for (Field field : fields) {
//            Type t = field.getType();
//            field.setAccessible(true);
//            try {
//                if (t == Integer.class && field.getName().equals("id") && field.get(model) != null) {
//                    isCreate = Integer.parseInt(field.get(model).toString()) <= 0;
//                    break;
//                }
//            } catch (IllegalArgumentException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }
//        setDefaultAndSystemFieldsValue(model, fields, user, isCreate, isSetDefaultFieldsValue);
//
//    }
//
//    private static <T> void setDefaultAndSystemFieldsValue(final T model, List<Field> fields, final Employee user, boolean isCreate, boolean isSetDefaultFieldsValue) {
//
//        for (Field field : fields) {
//            Type t = field.getType();
//            field.setAccessible(true);
//            try {
//                //default value:null
//                if (isSetDefaultFieldsValue) {
//                    if (t == String.class && field.get(model) == null) {
//                        field.set(model, "");
//                    } else if (t == BigDecimal.class && field.get(model) == null) {
//                        field.set(model, new BigDecimal(0));
//                    } else if (t == Long.class && field.get(model) == null) {
//                        field.set(model, new Long(0));
//                    } else if (t == Integer.class && field.get(model) == null) {
//                        field.set(model, new Integer(0));
//                    } else if (t == Date.class && field.get(model) == null) {
//                        field.set(model, TimeHelper.LocalDateTimeToDate(LocalDateTime.of(1990, 1, 1, 0, 0, 0, 0)));
//                    }
//                }
//                //system field
//                if (isCreate) {
//                    if (field.getName().equals("cid") || field.getName().equals("creatorId")) {
//                        field.set(model, user.getId());
//                    }
//                    if (field.getName().equals("cname") || field.getName().equals("creator")) {
//                        field.set(model, user.getName());
//                    }
//                    if (field.getName().equals("ctime") || field.getName().equals("createdAt")) {
//                        field.set(model, new Date());
//                    }
//                    if (field.getName().equals("deleted")) {
//                        field.set(model, 0);
//                    }
//                }
//                if (field.getName().equals("mid") || field.getName().equals("modifierId")) {
//                    field.set(model, user.getId());
//                }
//                if (field.getName().equals("mname") || field.getName().equals("modifier")) {
//                    field.set(model, user.getName());
//                }
//                if (field.getName().equals("mtime") || field.getName().equals("modifiedAt")) {
//                    field.set(model, new Date());
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }

	private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	private static Field getField(String fieldName, Class<?> clazz) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			if (clazz.getSuperclass() != null) {
				return getField(fieldName, clazz.getSuperclass());
			}
		}
		return null;
	}

	public static <T> void buildCreateAndModify(T model, ModifyModel modifyModel) {
		Class<?> tClass = model.getClass();

		boolean isCreate = true;

		Field ctimeField = null;
		ctimeField = getField(modifyModel.getCreatedTime(), tClass);
		if (ctimeField == null) {
			ctimeField = getField(modifyModel.getcTime(), tClass);
		}
		if (ctimeField == null) {
			return;
		}

		try {
			ctimeField.setAccessible(true);
			Date createdTime = (Date) ctimeField.get(model);
			if (createdTime != null && createdTime.getTime() > 0) {
				isCreate = false;
			}
		} catch (IllegalAccessException e) {
		}

		List<Field> fields = Lists.newArrayList();
		getAllFields(fields, tClass);
		// List<Field> fields = Arrays.asList(tClass.getDeclaredFields());
		for (Field field : fields) {
			Type t = field.getType();
			field.setAccessible(true);
			try {
				if (isCreate) {
					if (field.getName().equals(modifyModel.getcId())) {
						field.set(model, modifyModel.getEmployeeId());
					}
					if (field.getName().equals(modifyModel.getcName())) {
						field.set(model, modifyModel.getEmployeeName());
					}
					if (field.getName().equals(modifyModel.getcTime())) {
						field.set(model, new Date());
					}
					if (field.getName().equals(modifyModel.getCreatorId())) {
						field.set(model, modifyModel.getEmployeeId());
					}
					if (field.getName().equals(modifyModel.getCreatorName())) {
						field.set(model, modifyModel.getEmployeeName());
					}
					if (field.getName().equals(modifyModel.getCreatedTime())) {
						field.set(model, new Date());
					}
				}
				if (field.getName().equals(modifyModel.getmId())) {
					field.set(model, modifyModel.getEmployeeId());
				}
				if (field.getName().equals(modifyModel.getmName())) {
					field.set(model, modifyModel.getEmployeeName());
				}
				if (field.getName().equals(modifyModel.getmTime())) {
					field.set(model, new Date());
				}
				if (field.getName().equals(modifyModel.getModifierId())) {
					field.set(model, modifyModel.getEmployeeId());
				}
				if (field.getName().equals(modifyModel.getModifierName())) {
					field.set(model, modifyModel.getEmployeeName());
				}
				if (field.getName().equals(modifyModel.getModifiedTime())) {
					field.set(model, new Date());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public static <T> void buildSysCreateAndModify(T model) {

		final String now = LocalDateTime.now().toString();

		ModifyModel modifyModel = new ModifyModel();
		modifyModel.setcId("1");
		modifyModel.setcName("sys");
		modifyModel.setcTime(now);

		modifyModel.setCreatorId("1");
		modifyModel.setCreatorName("sys");
		modifyModel.setCreatedTime(now);

		modifyModel.setmId("1");
		modifyModel.setmName("sys");
		modifyModel.setmTime(now);

		modifyModel.setModifierId("1");
		modifyModel.setModifierName("sys");
		modifyModel.setModifiedTime(now);

		modifyModel.setEmployeeId("1");
		modifyModel.setEmployeeName("sys");

		buildCreateAndModify(model, modifyModel);
	}

	public static <T> void buildModify(T model, ModifyModel modifyModel) {
		Class<?> tClass = model.getClass();
		List<Field> fields = Lists.newArrayList();
		getAllFields(fields, tClass);
		for (Field field : fields) {
			Type t = field.getType();
			field.setAccessible(true);
			try {

				if (field.getName().equals(modifyModel.getmId())) {
					field.set(model, modifyModel.getEmployeeId());
				}
				if (field.getName().equals(modifyModel.getmName())) {
					field.set(model, modifyModel.getEmployeeName());
				}
				if (field.getName().equals(modifyModel.getmTime())) {
					field.set(model, new Date());
				}
				if (field.getName().equals(modifyModel.getModifierId())) {
					field.set(model, modifyModel.getEmployeeId());
				}
				if (field.getName().equals(modifyModel.getModifierName())) {
					field.set(model, modifyModel.getEmployeeName());
				}
				if (field.getName().equals(modifyModel.getModifiedTime())) {
					field.set(model, new Date());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
