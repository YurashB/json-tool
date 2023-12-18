package com.jsontool.utils.validators.json.jsonvalidator;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class JSONCharsPositionFinder {

    private final String json;
    private final Map<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> positionMap;

    private JSONCharsPositionFinder(String json,
            Map<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> positionMap) {
        this.json = json;
        this.positionMap = positionMap;
    }

    public static JSONCharsPositionFinder create(String json) {
        Map<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> positionMap = getCharsPositionMap(json);

        return new JSONCharsPositionFinder(json, positionMap);
    }

    /* Map<fist, Map<second, third>>
    first - symbol position with formatting
    second - symbol position without formatting
    third - symbols line position
     */
    private static Map<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> getCharsPositionMap(String json) {
        Map<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> positionMap = new HashMap<>();
        char[] chars = json.toCharArray();

        int linePosition = 0;
        int formattedPosition = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\n') {
                linePosition += 1;
            } else {
                AbstractMap.SimpleImmutableEntry<Integer, Integer> entry = new AbstractMap.SimpleImmutableEntry<>(i,
                        linePosition);
                positionMap.put(formattedPosition, entry);
                formattedPosition += 1;
            }
        }

        return positionMap;
    }

    public Map<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> getPositionMap() {
        return positionMap;
    }

    public AbstractMap.SimpleImmutableEntry<Integer, Integer> getCharPosition(int formattedPosition) {
        return positionMap.get(formattedPosition);
    }

    public String getJson() {
        return json;
    }
}
