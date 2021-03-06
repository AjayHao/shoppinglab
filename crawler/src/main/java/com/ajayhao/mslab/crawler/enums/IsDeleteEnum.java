package com.ajayhao.mslab.crawler.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @EnumName IsDeleteEnum
 * @Description 是否失效字典
 * @Author Ajay Hao
 * @Date 2018/6/30 12:04
 * @Version 1.0
 **/

@Getter
@AllArgsConstructor
public enum IsDeleteEnum{
    YES("Y"),
    NO("N");

    private final String code;

    /**
     * Get yes no enum.
     *
     * @param code the code
     * @return the yes no enum
     */
    public static IsDeleteEnum get(String code) {
        if (code != null) {
            for (IsDeleteEnum e : IsDeleteEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
        }
        return null;
    }
}
