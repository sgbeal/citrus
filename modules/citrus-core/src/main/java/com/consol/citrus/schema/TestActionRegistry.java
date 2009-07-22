package com.consol.citrus.schema;

import java.util.HashMap;
import java.util.Map;

/**
 * Registers BeanDefinitionParser for action in testcase.
 * @author deppisch Christoph Deppisch Consol* Software GmbH 2007
 */
public class TestActionRegistry {
    private static Map parser = new HashMap();

    /**
     * Default constructor.
     */
    static {
        registerActionParser("send", new SendActionParser());
        registerActionParser("receive", new ReceiveActionParser());
        registerActionParser("updateDatabase", new ExecuteSqlActionParser());
        registerActionParser("queryDatabase", new SqlQueryActionParser());
        registerActionParser("java", new ClassRunnerActionParser());
        registerActionParser("sleep", new DelayActionParser());
        registerActionParser("traceVariables", new InfoActionParser());
        registerActionParser("createVariables", new SetVariablesActionParser());
        registerActionParser("traceTime", new TimeWatcherActionParser());
        registerActionParser("echo", new EchoActionParser());
        registerActionParser("expectTimeout", new ValidateJMSTimeoutActionParser());
        registerActionParser("purgeJmsQueues", new PurgeJmsQueuesActionParser());
        registerActionParser("action", new ActionParser());
        registerActionParser("template", new TemplateParser());
        registerActionParser("call-template", new CallTemplateParser());
        registerActionParser("sequential", new SequenceParser());
        registerActionParser("iterate", new IterateParser());
        registerActionParser("repeat-until-true", new RepeatUntilTrueParser());
        registerActionParser("repeat-onerror-until-true", new RepeatOnErrorUntilTrueParser());
        registerActionParser("fail", new FailActionParser());
        registerActionParser("input", new InputActionParser());
        registerActionParser("load", new LoadActionParser());
        registerActionParser("parallel", new ParallelParser());
        registerActionParser("catch", new CatchParser());
        registerActionParser("assert", new AssertParser());
        registerActionParser("alterDatabase", new ExecutePLSQLActionParser());
        registerActionParser("groovy", new GroovyActionParser());
    }

    /**
     * Register method to add new action parser.
     * @param actionName
     * @param parserObject
     */
    public static void registerActionParser(String actionName, Object parserObject) {
        parser.put(actionName, parserObject);
    }

    /**
     * Getter for parser.
     * @return
     */
    public static Map getRegisteredActionParser() {
        return parser;
    }
}