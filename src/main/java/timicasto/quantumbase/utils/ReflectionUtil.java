package timicasto.quantumbase.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import timicasto.quantumbase.block.IModBlock;
import timicasto.quantumbase.item.IModItem;
import timicasto.quantumbase.utils.annotation.ManualRegisterConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author lama
 */
public class ReflectionUtil {
    private static final String CLASS_SUFFIX = ".class";

    public static List<String> listPkgClasses(String pkg) {
        List<String> clazzNames = new ArrayList<>();
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(pkg.replaceAll("\\.", "/"));
            listClasses(pkg, clazzNames, urls);
        } catch (Exception ignored) {
        }

        return clazzNames;
    }

    public static List<String> listPkgClassesAt(String pkg, Class clazz) {
        List<String> clazzNames = new ArrayList<>();
        try {
            Enumeration<URL> urls = clazz.getClassLoader().getResources(pkg.replaceAll("\\.", "/"));
            listClasses(pkg, clazzNames, urls);
        } catch (Exception ignored) {
        }

        return clazzNames;
    }

    public static List<String> listPkgClassesAt(String pkg, Thread thread) {
        List<String> clazzNames = new ArrayList<>();
        try {
            Enumeration<URL> urls = thread.getContextClassLoader().getResources(pkg.replaceAll("\\.", "/"));
            listClasses(pkg, clazzNames, urls);
        } catch (Exception ignored) {
        }

        return clazzNames;
    }

    private static void listClasses(String pkg, List<String> clazzNames, Enumeration<URL> urls) {
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            try {
                JarFile jf = ((JarURLConnection) url.openConnection()).getJarFile();
                jf.stream()
                        .filter((v) -> !v.isDirectory() && v.getName().endsWith(CLASS_SUFFIX))
                        .forEach((v) -> {
                            String name = v.getName().replace(CLASS_SUFFIX, "").replace("/", ".");
                            if (name.startsWith(pkg)) {
                                clazzNames.add(name);
                            }
                        });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static IModBlock<? extends Block> initBlockClass(String clazzName) {
        try{
            Class<? extends IModBlock<? extends Block>> clazz = (Class<? extends IModBlock<? extends Block>>) Class.forName(clazzName);
            if(clazz.isInterface()) { return null; }
            Constructor<? extends IModBlock<? extends Block>> constructor =  clazz.getDeclaredConstructor();
            for (Annotation annotation : constructor.getAnnotations()) {
                if(annotation instanceof ManualRegisterConstructor) {
                    return null;
                }
            }
            return constructor.newInstance();
        } catch (Exception ignored) { }
        return null;
    }

    public static IModItem<? extends Item> initItemClass(String clazzName) {
        try{
            Class<? extends IModItem<? extends Item>> clazz = (Class<? extends IModItem<? extends Item>>) Class.forName(clazzName);
            if(clazz.isInterface()) { return null; }
            Constructor<? extends IModItem<? extends Item>> constructor =  clazz.getDeclaredConstructor();
            for (Annotation annotation : constructor.getAnnotations()) {
                if(annotation instanceof ManualRegisterConstructor) {
                    return null;
                }
            }
            return constructor.newInstance();
        } catch (Exception ignored) { }
        return null;
    }

    //DO NOT USE
    @Deprecated
    private static void handlePkg(List<String> clazzNames, String currentFolderAppendStr, String pkg, JarFile jf) {
        Enumeration<JarEntry> entries = jf.entries();
        pkg += "." + currentFolderAppendStr;
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();

            String name = jarEntry.getName();
            if(!jarEntry.isDirectory()) {
                handlePkg(clazzNames, jarEntry.getName(), pkg, jf);
            }
            if (name.endsWith(CLASS_SUFFIX)) {
                name = name.replace(CLASS_SUFFIX, "").replace("/", ".");
                if (pkg.equals(name.substring(0, name.lastIndexOf(".")))) {
                    clazzNames.add(name);
                }
            }
        }
    }
}
