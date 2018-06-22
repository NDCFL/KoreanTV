package com.dt.common;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean属性工具类<br />
 * 创建于2017-12-18日<br />
 *
 * @author 陈飞龙
 * @version 1.0
 */
public class PropertyUtils {

    /**
     * 获取属性的getter方法名称
     * @param property 属性名
     * @return getter方法名
     */
    public static String getter(String property) {
        return "get" + StringUtils.capitalize(property);
    }

    /**
     * 获取属性的setter方法名称
     * @param property 属性名
     * @return setter方法名
     */
    public static String setter(String property) {
        return "set" + StringUtils.capitalize(property);
    }

    public static String columnToProperty(String column) {
        StringBuilder property = new StringBuilder("");
        if (column.contains("_")) {
            String[] strArray = column.split("_");
            property.append(strArray[0]);
            for (int i = 1, len = strArray.length; i < len; i++) {
                property.append(StringUtils.capitalize(strArray[i]));
            }
        } else {
            property.append(column);
        }
        return property.toString();
    }

    public static String propertyToColumn(String property) {
        List<Character> characterList = stringToList(property);
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0, len =characterList.size(); i < len; i++) {
            if (characterList.get(i) >= 65 && characterList.get(i) <= 90) {
                indexes.add(i);
            }
        }
        int count = 0;
        for (Integer index : indexes) {
            characterList.add(index + count, '_');
            characterList.set(index + count + 1, (char) (characterList.get(index + count + 1) + 32));
            count++;
        }
        return listToString(characterList);
    }

    private static List<Character> stringToList(String str) {
        char[] chars = str.toCharArray();
        List<Character> characters = new ArrayList<>();
        for (char c : chars) {
            characters.add(c);
        }
        return characters;
    }

    private static String listToString(List<Character> characters) {
        StringBuilder sb = new StringBuilder("");
        for (Character c : characters) {
            sb.append(c);
        }
        return sb.toString();
    }

}
