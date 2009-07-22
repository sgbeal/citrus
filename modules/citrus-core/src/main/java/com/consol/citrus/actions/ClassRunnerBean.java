package com.consol.citrus.actions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consol.citrus.context.TestContext;
import com.consol.citrus.exceptions.TestSuiteException;

/**
 * Bean to enable class invokation through java reflection
 * @author deppisch Christoph Deppisch Consol* Software GmbH 2006
 */
public class ClassRunnerBean extends AbstractTestAction {
    /** Instance to be invoked, injected through java reflection */
    private Object instance;

    /** Name of class */
    private String className;

    /** Name of method to invoke */
    private String methodName;

    /** Method args */
    private List methodArgs = new ArrayList();

    /** Constructor args */
    private List constructorArgs = new ArrayList();

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ClassRunnerBean.class);

    /**
     * (non-Javadoc)
     * @see com.consol.citrus.TestAction#execute(TestContext)
     */
    @Override
    public void execute(TestContext context) throws TestSuiteException {
            try {
                if (className != null) {
                    log.info("Loading class " + className);
                    
                    Class classToRun = Class.forName(className);

                    Class[] constructorTypes = new Class[constructorArgs.size()];
                    for (int i = 0; i < constructorTypes.length; i++) {
                        if (constructorArgs.get(i).getClass().equals(Long.class)) {
                            constructorTypes[i] = long.class;
                        } else if (constructorArgs.get(i).getClass().equals(Integer.class)) {
                            constructorTypes[i] = int.class;
                        } else if (constructorArgs.get(i).getClass().equals(Short.class)) {
                            constructorTypes[i] = short.class;
                        } else if (constructorArgs.get(i).getClass().equals(Double.class)) {
                            constructorTypes[i] = double.class;
                        } else if (constructorArgs.get(i).getClass().equals(Float.class)) {
                            constructorTypes[i] = float.class;
                        } else if (constructorArgs.get(i).getClass().equals(Boolean.class)) {
                            constructorTypes[i] = boolean.class;
                        } else {
                            constructorTypes[i] = constructorArgs.get(i).getClass();
                        }
                    }

                    Object[] constructorObjects = new Object[constructorArgs.size()];
                    for (int i = 0; i < constructorObjects.length; i++) {
                        constructorObjects[i] = constructorArgs.get(i);
                    }

                    Constructor constr = classToRun.getConstructor(constructorTypes);
                    instance = constr.newInstance(constructorObjects);
                }

                Class[] methodTypes = new Class[methodArgs.size()];
                for (int i = 0; i < methodTypes.length; i++) {
                    if (methodArgs.get(i).getClass().equals(Long.class)) {
                        methodTypes[i] = long.class;
                    } else if (methodArgs.get(i).getClass().equals(Integer.class)) {
                        methodTypes[i] = int.class;
                    } else if (methodArgs.get(i).getClass().equals(Short.class)) {
                        methodTypes[i] = short.class;
                    } else if (methodArgs.get(i).getClass().equals(Double.class)) {
                        methodTypes[i] = double.class;
                    } else if (methodArgs.get(i).getClass().equals(Float.class)) {
                        methodTypes[i] = float.class;
                    } else if (methodArgs.get(i).getClass().equals(Boolean.class)) {
                        methodTypes[i] = boolean.class;
                    } else if (methodArgs.get(i).getClass().equals(ArrayList.class)) {
                        methodTypes[i] = String[].class;
                    } else {
                        methodTypes[i] = methodArgs.get(i).getClass();
                    }
                }

                Method methodToRun = instance.getClass().getMethod(methodName, methodTypes);

                Object[] methodObjects = new Object[methodArgs.size()];
                for (int i = 0; i < methodObjects.length; i++) {
                    if (methodArgs.get(i).getClass().equals(ArrayList.class)) {
                        ArrayList list = (ArrayList)methodArgs.get(i);
                        String[] converted = new String[list.size()];
                        for (int j = 0; j < converted.length; j++) {
                            converted[j] = (String)list.get(j);
                        }
                        methodObjects[i] = converted;
                    } else {
                        methodObjects[i] = methodArgs.get(i);
                    }
                }

                log.info("Invoking method " + methodToRun.toString());

                methodToRun.invoke(instance, methodObjects);
            } catch (SecurityException e) {
                log.error("Invocation failed due to errors", e);
                throw new TestSuiteException(e);
            } catch (IllegalArgumentException e) {
                log.error("Invocation failed due to errors", e);
                throw new TestSuiteException(e);
            } catch (ClassNotFoundException e) {
                log.error("Invocation failed due to errors", e);
                throw new TestSuiteException(e);
            } catch (NoSuchMethodException e) {
                log.error("Invocation failed due to errors", e);
                throw new TestSuiteException(e);
            } catch (InstantiationException e) {
                log.error("Invocation failed due to errors", e);
                throw new TestSuiteException(e);
            } catch (IllegalAccessException e) {
                log.error("Invocation failed due to errors", e);
                throw new TestSuiteException(e);
            } catch (InvocationTargetException e) {
                log.error("Invocation failed due to errors", e);
                throw new TestSuiteException(e);
            }
    }

    /**
     * Setter for class name
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Setter for constructor args
     * @param constructorArgs
     */
    public void setConstructorArgs(List constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    /**
     * Setter for method args
     * @param methodArgs
     */
    public void setMethodArgs(List methodArgs) {
        this.methodArgs = methodArgs;
    }

    /**
     * Setter for method name
     * @param methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Setter for object instance
     * @param instance
     */
    public void setInstance(Object instance) {
        this.instance = instance;
    }
}