package cn.letsky.movie.util;

import java.util.Arrays;
import java.util.UUID;

public class ImageUtils {

    private static final String[] SUFFIX = {".png", ".jpg", ".jpeg",
            ".gif", ".webp", ".svg", ".bmp"};

    /**
     * 生成随机文件名
     *
     * @return
     */
    public static String generateFilename() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 根据后缀判断是否为图片
     *
     * @param suffix 后缀
     * @return
     */
    public static boolean isImage(String suffix) {
        return Arrays.asList(SUFFIX).contains(suffix);
    }
}
