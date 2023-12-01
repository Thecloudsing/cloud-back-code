package org.dreams.weyun.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/11/29
 */
@AllArgsConstructor
@Getter
public enum DeleteFlagEnum {
    NORMAL(false, "正常"),
    DELETE(true, "删除"),
            ;

    private final static Map<Boolean, DeleteFlagEnum> cache;

    static {
        cache = Arrays.stream(DeleteFlagEnum.values()).collect(Collectors.toMap(DeleteFlagEnum::getStatus, Function.identity()));
    }

    private final Boolean status;
    private final String desc;

    public static DeleteFlagEnum of(Boolean type) {
        return cache.get(type);
    }
}
