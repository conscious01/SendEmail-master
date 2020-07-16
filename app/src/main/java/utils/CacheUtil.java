package utils;

import android.content.Context;

import java.io.File;



/**
 * App's Cache Manager
 * Created by lds on 2016/6/15.
 */
public final class CacheUtil {
    // 缓存目录
    public static final String CACHE_DIR_NAME = "yezhan";
    public static final String SP_FILE_VERSIONCODE = "SP_FILE_VERSIONCODE";
    public static final String SP_KEY_VERSIONCODE = "SP_KEY_VERSIONCODE";
    public static final String SP_KEY_REWARD_SET = "SP_KEY_REWARD_SET";
    /**
     * 获取全局的统一缓存目录
     *
     * @param context Context
     * @param directoryName 子目录(传null, '.', '/' 都视为不需要创建子目录
     * @return 缓存目录
     */
    public static File getCacheDir(Context context, String directoryName) {
        final String directory = isRootDirectory(directoryName)
                ? CACHE_DIR_NAME
                : CACHE_DIR_NAME + "/" + directoryName;
        return StorageUtils.getOwnCacheDirectory(context, directory);
    }

    /**
     * 获取缓存根目录, 建议不要在根目录放置文件
     *
     * @param context context
     * @return 缓存目录
     */
    public static File getCacheDir(Context context) {
        return getCacheDir(context, ".");
    }

    /**
     * 获取临时缓存目录, 建议放置一些临时的文件
     *
     * @param context context
     * @return 缓存目录
     */
    public static File getTempCacheDir(Context context) {
        return getCacheDir(context, "temp");
    }

    /**
     * 获取图片缓存目录, 放置图片缓存文件(这个目录的文件可能会比较多)
     *
     * @param context context
     * @return 缓存目录
     */
    public static File getImageCacheDir(Context context) {
        return getCacheDir(context, "images");
    }

    /**
     * 获取文件缓存目录, 建议放置一些文件, 如apk等
     *
     * @param context context
     * @return 缓存目录
     */
    public static File getFileCacheDir(Context context) {
        return getCacheDir(context, "files");
    }

    /**
     * 获取数据缓存目录, 建议放置一些数据缓存
     *
     * @param context context
     * @return 缓存目录
     */
    public static File getDataCacheDir(Context context) {
        return getCacheDir(context, "data");
    }

    /**
     * 获取日志缓存目录, 建议放置一些日志文件
     *
     * @param context context
     * @return 缓存目录
     */
    public static File getLogCacheDir(Context context) {
        return getCacheDir(context, "log");
    }

    private static boolean isRootDirectory(String directoryName) {
        return directoryName == null
                || ".".equals(directoryName)
                || "/".equals(directoryName);
    }

}
