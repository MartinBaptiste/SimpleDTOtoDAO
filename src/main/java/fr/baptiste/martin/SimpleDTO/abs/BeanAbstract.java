package fr.baptiste.martin.SimpleDTO.abs;

import fr.baptiste.martin.SimpleDTO.annotation.DAOTarget;
import fr.baptiste.martin.SimpleDTO.annotation.DTOTarget;
import fr.baptiste.martin.SimpleDTO.annotation.NoTransform;
import fr.baptiste.martin.SimpleDTO.annotation.TransformWith;
import fr.baptiste.martin.SimpleDTO.exception.TransformationException;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for DAO and DTO bean (Not directly extends)
 */
public class BeanAbstract {
    private static final Logger LOGGER = Logger.getLogger(BeanAbstract.class);
    protected Map<String, Method> getters;

    /**
     * Initialize bean transformer
     */
    protected void init() {
        getters = new HashMap<String, Method>();
        Method[] methods = getClass().getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                getters.put(methodName, method);
            }
        }
    }

    /**
     * Method to transform DTO to DAT or vice versa
     * @param methods Array of bean method target
     * @param result Instance of target
     * @throws TransformationException If error to reflections
     */
    protected void transformationAbstract(Method[] methods, Object result) throws TransformationException {
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("set")) {
                Method getter = null;
                try {
                    getter = findGetter(method);
                } catch (NoSuchMethodException e) {
                    throw new TransformationException("Getter error", e);
                }
                if (getter == null) {
                    LOGGER.debug("Getter is null for " + methodName);
                    continue;
                }
                if (getter.isAnnotationPresent(NoTransform.class)) {
                    LOGGER.info("Getter is annotated for " + methodName);
                    continue;
                }

                try {
                    method.invoke(result, getter.invoke(this));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new TransformationException("Getter or Setter error", e);
                }
            }
        }
    }

    /**
     * Find getter to current bean
     * @param method Setter method
     * @return Getter method
     * @throws NoSuchMethodException If method declared with annotation not found
     */
    protected Method findGetter(Method method) throws NoSuchMethodException {
        TransformWith annotation = method.getAnnotation(TransformWith.class);
        if (annotation != null) {
            return getClass().getMethod(annotation.getter());
        }
        return getters.get(method.getName().replace("set", "get"));
    }

    /**
     * Get DTO class name
     * @return DTO class name
     * @throws ClassNotFoundException If simple transformation name is equals to current name
     */
    protected String getDTOName() throws ClassNotFoundException {
        DTOTarget annotation = getClass().getAnnotation(DTOTarget.class);
        String name = getClassName("DAO", "DTO");
        if (annotation != null) {
            return annotation.className();
        }
        if (getClass().getName().equals(name)) {
            throw new ClassNotFoundException("Not get DTO class of " + getClass().getName());
        }
        return name;
    }

    /**
     * Get DAO class name
     * @return DAO class name
     * @throws ClassNotFoundException If simple transformation name is equals to current name
     */
    protected String getDAOName() throws ClassNotFoundException {
        DAOTarget annotation = getClass().getAnnotation(DAOTarget.class);
        String name = getClassName("DTO", "DAO");
        if (annotation != null) {
            return annotation.className();
        }
        if (getClass().getName().equals(name)) {
            throw new ClassNotFoundException("Not get DAO class of " + getClass().getName());
        }
        return name;
    }

    /**
     * Simple transformation to class name
     * @param with Current value to transform
     * @param to Target value to transform
     * @return Class name to target
     */
    private String getClassName(String with, String to) {
        String completeName = getClass().getName();
        int dotPosition = completeName.lastIndexOf('.');

        String className = completeName.substring(dotPosition + 1);
        String packageName = completeName.substring(0, dotPosition + 1);

        return packageName + className.replace(with, to);
    }
}
