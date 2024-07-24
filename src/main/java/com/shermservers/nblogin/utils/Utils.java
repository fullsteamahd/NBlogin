package com.shermservers.nblogin.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    @NotNull
    public static <T> T pop(T[] array) {
        return array[array.length - 1];
    }

    @Nullable
    private static String removeSuffixNullable(String str, String suffix) {
        if (str.substring(suffix.length(), str.length()).equals(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }
        return null;
    }

    @NotNull
    public static String removeSuffix(String str, String suffix) {
        String data = removeSuffixNullable(str, suffix);
        if (data == null) return str;
        else return data;
    }

    @NotNull
    public static String removeSuffix(String str, String[] suffixes) {
        for (String suffix : suffixes) {
            String data = removeSuffixNullable(str, suffix);
            if (data != null) return data;
        }
        return str;
    }

    @NotNull
    public static String removeSuffix(String str, List<String> suffixes) {
        return removeSuffix(str, suffixes.toArray(String[]::new));
    }

    @SafeVarargs
    public static <T> List<T> combine(List<T> ...lists) {
        List<T> ret = new ArrayList<>();
        for (List<T> list : lists) {
            ret.addAll(list);
        }
        return ret;
    }
}
