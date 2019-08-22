package cn.letsky.movie.util;

import cn.letsky.movie.exception.GlobalException;

public class CommonUtils {

    public static void check(int result, String errorMessage) {
        if (result < 1) {
            throw new GlobalException(errorMessage);
        }
    }

    public static void checkInsert(int result) {
        check(result, "添加失败");
    }

    public static void checkUpdate(int result) {
        check(result, "更新失败");
    }

    public static void checkDelete(int result) {
        check(result, "删除失败");
    }
}
