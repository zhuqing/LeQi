/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqigame.pinyin;

import java.util.ArrayList;
import java.util.List;
import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 处理拼音的工具
 *
 * @author zhuqing
 */
public class PhoneticizeUtil {

    /**
     * 返回中文的首字母
     *
     * @param str
     * @return
     */
    public static List<String> getPinYinHeadChars(String str) {

        List<List<String>> headCharList = new ArrayList<>();
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            List<String> headChars = new ArrayList<>();
            if (!(word >= 0x4e00 && word <= 0x9fbb)) {
                headChars.add(word + "");
                headCharList.add(headChars);
                continue;
            }
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);

            for (String item : pinyinArray) {
                if (!headChars.contains(item.charAt(0) + "")) {
                    headChars.add(item.charAt(0) + "");
                }
            }
            headCharList.add(headChars);

        }
        List<String> res = new ArrayList<>();
        linkAllString("", 0, headCharList, res);
        return res;
    }

    /**
     * 联接所有的字符串
     *
     * @param res
     * @param index
     * @param strs
     * @param resList
     */
    private static void linkAllString(String res, int index, List<List<String>> strs, List<String> resList) {
        if (index >= strs.size()) {
            return;
        }
        List<String> items = strs.get(index);
        for (String item : items) {
            if (index == strs.size() - 1) {
                resList.add((res + item).toUpperCase());
            } else {
                linkAllString(res + item, index + 1, strs, resList);
            }
        }
    }
}
