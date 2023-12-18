package com.jsontool.utils.validators.json.jsonvalidator;

import java.util.AbstractMap;
import java.util.Arrays;

import com.jsontool.utils.validators.json.jsonvalidator.exceptions.JSONException;
import com.jsontool.utils.validators.json.jsonvalidator.exceptions.JSONValidationException;
import com.jsontool.utils.validators.json.jsonvalidator.validators.keyValue.KeyValueValidationImpl;
import com.jsontool.utils.validators.json.jsonvalidator.validators.values.strategies.ObjectValidationStrategy;

public class JsonValidator {

    public void validate(String inputJson) {
        char[] json = inputJson.replaceAll("\\s", "").toCharArray();
        basicValidation(json);

        JSONCharsPositionFinder positionFinder = JSONCharsPositionFinder.create(inputJson);
        ObjectValidationStrategy objectValidation = new ObjectValidationStrategy(new KeyValueValidationImpl());
        try {
            for (int i = 1; i < json.length - 1; ) {
                i = objectValidation.validate(json, i);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JSONException("Unexpected validation behavior. Invalid json provided");
        } catch (JSONValidationException e) {
            int formattedPosition = e.getFormattedCharPosition();
            AbstractMap.SimpleImmutableEntry<Integer, Integer> charPosition = positionFinder.getCharPosition(
                    formattedPosition);

            throw new JSONException(String.format(e.getMessage() + " [character %s line %s]" , charPosition.getKey(), charPosition.getValue()));
        }
    }

    private void basicValidation(char[] json) {
        if (json[0] != '{') {
            throw new JSONException("A JSON text must begin with '{'");
        } else if (json[json.length - 1] != '}') {
            throw new JSONException("A JSON text must end with '}'");
        } else if (isOpenAndCloseArrayBracketHaveSameAmount(Arrays.toString(json))) {
            throw new JSONException("Open and close array brackets have different count");
        } else if (isOpenAndCloseObjectBracketHaveSameAmount(Arrays.toString(json))) {
            throw new JSONException("Open and close object brackets have different count");
        }

    }

    private boolean isOpenAndCloseObjectBracketHaveSameAmount(String json) {
        long openBracketsCount = json.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c == '{')
                .count();

        long closeBracketsCount = json.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c == '}')
                .count();
        return openBracketsCount != closeBracketsCount;

    }

    private boolean isOpenAndCloseArrayBracketHaveSameAmount(String json) {
        long openBracketsCount = json.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c == '[')
                .count();

        long closeBracketsCount = json.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c == ']')
                .count();
        return openBracketsCount != closeBracketsCount;
    }

//    private int validateKeyValues(char[] json, int i) {
//        if (json[i] == '\"') {
//            i++;
//            while (json[i] != '\"') {
//                i++;
//            }
//            i++;
//
//            if (json[i++] != ':') {
//                throw new JSONException(
//                        String.format("Expected a ':' after key at %s [character %s line %s]", i - 1, i, 0));
//            }
//
//            i = selectValueValidatingStrategy(json, i);
//
//            if (json[i] == ',') { // next key value pair is exist
//                return validateKeyValues(json, i + 1);
//            } else if (json[i] == '}') { // end of json object
//                return i;
//            } else {
//                throw new JSONException(
//                        String.format("Expected a ',' after key at %s [character %s line %s]", i - 1, i, 0));
//            }
//
//        } else {
//            throw new JSONException(
//                    String.format("Expected a '\"' after key at %s [character %s line %s]", i - 1, i, 0));
//        }
//    }
//
//    private int selectValueValidatingStrategy(char[] json, int i) {
//        if (json[i] == '\"') {
//            i = this.validateString(json, ++i);
//        } else if (Character.isDigit(json[i]) || json[i] == '-') {
//            i = validateNumber(json, i);
//        } else if (json[i] == 't') {
//            i = validateTrueValue(json, i);
//        } else if (json[i] == 'f') {
//            i = validateFalseValue(json, i);
//        } else if (json[i] == 'n') {
//            i = validateNullValue(json, i);
//        } else if (json[i] == '[') {
//            i = validateArray(json, i + 1);
//        } else if (json[i] == '{') {
//            i = validateObject(json, i + 1);
//        } else {
//            throw new JSONException(
//                    String.format("Expected a '\"' after key at %s or invalid symbol entered [character %s line %s]",
//                            i - 1, i, 0));
//        }
//
//        return i;
//    }
//
//    private int validateString(char[] json, int i) {
//        while (json[i] != '\"') {
//            i++;
//        }
//
//        return i + 1; // return next char after "
//    }
//
//    private int validateNumber(char[] json, int i) {
//        if (json[i] == '-') {
//            i++;
//        }
//
//        while ((Character.isDigit(json[i]))) {
//            i++;
//        }
//
//        return i;
//
//    }
//
//    private int validateTrueValue(char[] json, int i) {
//        char t = json[i];
//        char r = json[i + 1];
//        char u = json[i + 2];
//        char e = json[i + 3];
//
//        if (t != 't' || r != 'r' || u != 'u' || e != 'e') {
//            throw new JSONException(String.format("Unexpected symbol after key at %s [character %s line %s]", i - 1,
//                    i, 0));
//        }
//
//        return i + 4;
//    }
//
//    private int validateFalseValue(char[] json, int i) {
//        char f = json[i];
//        char a = json[i + 1];
//        char l = json[i + 2];
//        char s = json[i + 3];
//        char e = json[i + 4];
//
//        if (f != 'f' || a != 'a' || l != 'l' || s != 's' || e != 'e') {
//            throw new JSONException(String.format("Unexpected symbol after key at %s [character %s line %s]", i - 1,
//                    i, 0));
//        } else {
//            return i + 5; // return next symbol after false 'e'
//        }
//    }
//
//    private int validateNullValue(char[] json, int i) {
//        char n = json[i];
//        char u = json[i + 1];
//        char l = json[i + 2];
//        char l2 = json[i + 3];
//
//        if (n != 'n' || u != 'u' || l != 'l' || l2 != 'l') {
//            throw new JSONException(String.format("Unexpected symbol after key at %s [character %s line %s]", i - 1,
//                    i, 0));
//        }
//
//        return i + 4;
//    }
//
//    private int validateArray(char[] json, int i) {
//        while (json[i] != ']') {
//            i = selectValueValidatingStrategy(json, i);
//
//            if (json[i] != ',' && json[i] != ']') {
//                throw new JSONException(String.format(
//                        "Expected ']' after key at %s or invalid symbol entered [character %s line %s]",
//                        i - 1, i, 0));
//            } else if (json[i] == ',' && json[i + 1] == ']') {
//                throw new JSONException(String.format(
//                        "Expected value after ',' at but nothing provided [character %s line %s]",
//                        i - 1, i, 0));
//            } else if (json[i] == ',') {
//                i++;
//            }
//        }
//
//        return i + 1;
//    }
//
//    private int validateObject(char[] json, int i) {
//        while (json[i] != '}') {
//            i = validateKeyValues(json, i);
//        }
//
//        if(i == json.length - 1) {
//            return i;
//        } else {
//            return i + 1;
//        }
//    }

}
