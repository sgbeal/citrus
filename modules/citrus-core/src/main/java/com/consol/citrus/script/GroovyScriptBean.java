package com.consol.citrus.script;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.IOException;
import java.text.ParseException;

import org.codehaus.groovy.control.CompilationFailedException;
import org.springframework.core.io.Resource;

import com.consol.citrus.actions.AbstractTestAction;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.exceptions.TestSuiteException;

/**
 * Bean executing groovy scripts either specified inline or from external file resource.
 * @author deppisch Christoph Deppisch Consol* Software GmbH 2006
 */
public class GroovyScriptBean extends AbstractTestAction {

    /** Inline groovy script */
    private String script;

    /** External script file resource */
    private Resource fileResource;
    
    /** Executes a script using the TestContext */
    public interface ScriptExecutor {
        public void execute(TestContext context);
    }
    
    /**
     * (non-Javadoc)
     * @see com.consol.citrus.TestAction#execute(TestContext)
     */
    @Override
    public void execute(TestContext context) throws TestSuiteException {
        try {
            ClassLoader parent = getClass().getClassLoader();
            GroovyClassLoader loader = new GroovyClassLoader(parent);
            
            Class groovyClass;
            
            if(script != null) {
                groovyClass = loader.parseClass(context.replaceDynamicContentInString(script));
            } else if(fileResource != null) {
                groovyClass = loader.parseClass(fileResource.getFile());
            } else {
                throw new TestSuiteException("Neither inline script nor " +
                		"external file resource is defined for bean. " +
                		"Can not execute groovy script.");
            }
    
            if(groovyClass == null) {
                throw new TestSuiteException("Could not load groovy script!");    
            }
            
            GroovyObject groovyObject;
            groovyObject = (GroovyObject) groovyClass.newInstance();
            
            if(groovyObject instanceof ScriptExecutor) {
                ((ScriptExecutor)groovyObject).execute(context);
            } else {
                Object[] args = {};
                groovyObject.invokeMethod("run", args);
            }
        } catch (InstantiationException e) {
            throw new TestSuiteException(e);
        } catch (IllegalAccessException e) {
            throw new TestSuiteException(e);
        } catch (CompilationFailedException e) {
            throw new TestSuiteException(e);
        } catch (IOException e) {
            throw new TestSuiteException(e);
        } catch (ParseException e) {
            throw new TestSuiteException(e);
        }
    }

    /**
     * @param script the script to set
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * @return the script
     */
    public String getScript() {
        return script;
    }
    
    /**
     * @return the fileResource
     */
    public Resource getFileResource() {
        return fileResource;
    }

    /**
     * @param fileResource the fileResource to set
     */
    public void setFileResource(Resource fileResource) {
        this.fileResource = fileResource;
    }
}