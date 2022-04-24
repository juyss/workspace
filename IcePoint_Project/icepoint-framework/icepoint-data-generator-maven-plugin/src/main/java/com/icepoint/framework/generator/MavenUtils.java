package com.icepoint.framework.generator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
public class MavenUtils {

    private MavenUtils () {}

    public static ClassLoader getClassLoader(AbstractMojo mojo, MavenProject project) {
        try {
            // 所有的类路径环境，也可以直接用 compilePath
            List<String> classpathElements = project.getCompileClasspathElements();

            classpathElements.add(project.getBuild().getOutputDirectory());
            classpathElements.add(project.getBuild().getTestOutputDirectory());

            // 转为 URL 数组
            URL[] urls = new URL[classpathElements.size()];
            for (int i = 0; i < classpathElements.size(); ++i) {
                urls[i] = new File(classpathElements.get(i)).toURI().toURL();
            }
            // 自定义类加载器
            return new URLClassLoader(urls, MavenUtils.class.getClassLoader());
        } catch (Exception e) {
            mojo.getLog().warn("Couldn't get the classloader.");
            return MavenUtils.class.getClassLoader();
        }
    }

    public static String getProjectRoot(MavenProject project) {
        String baseDir = project.getBasedir().getAbsolutePath();
        return baseDir.endsWith("/") ? baseDir.substring(0, baseDir.length() - 1) : baseDir;
    }
}
