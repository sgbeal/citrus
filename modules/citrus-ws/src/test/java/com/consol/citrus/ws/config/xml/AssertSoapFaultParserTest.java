/*
 * Copyright 2006-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.consol.citrus.ws.config.xml;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.consol.citrus.testng.AbstractActionParserTest;
import com.consol.citrus.ws.actions.AssertSoapFault;

/**
 * @author Christoph Deppisch
 */
public class AssertSoapFaultParserTest extends AbstractActionParserTest<AssertSoapFault> {

    @Test
    public void testAssertSoapFaultParser() {
        assertActionCount(4);
        assertActionClassAndName(AssertSoapFault.class, "assert");
        
        // 1st action
        AssertSoapFault action = getNextTestActionFromTest();
        Assert.assertNotNull(action.getAction());
        Assert.assertEquals(action.getMessageFactory(), beanDefinitionContext.getBean("messageFactory"));
        Assert.assertEquals(action.getValidator(), beanDefinitionContext.getBean("soapFaultValidator"));
        Assert.assertEquals(action.getFaultCode(), "{http://www.citrusframework.org/faults}FAULT-1001");
        Assert.assertNull(action.getFaultString());
        Assert.assertNull(action.getFaultDetail());
        Assert.assertNull(action.getFaultDetailResource());
        
        // 2nd action
        action = getNextTestActionFromTest();
        Assert.assertNotNull(action.getAction());
        Assert.assertEquals(action.getMessageFactory(), beanDefinitionContext.getBean("messageFactory"));
        Assert.assertEquals(action.getValidator(), beanDefinitionContext.getBean("soapFaultValidator"));
        Assert.assertEquals(action.getFaultCode(), "{http://www.citrusframework.org/faults}FAULT-1002");
        Assert.assertEquals(action.getFaultString(), "FaultString");
        Assert.assertNull(action.getFaultDetail());
        Assert.assertNull(action.getFaultDetailResource());
        
        // 3rd action
        action = getNextTestActionFromTest();
        Assert.assertNotNull(action.getAction());
        Assert.assertEquals(action.getMessageFactory(), beanDefinitionContext.getBean("messageFactory"));
        Assert.assertEquals(action.getValidator(), beanDefinitionContext.getBean("soapFaultValidator"));
        Assert.assertEquals(action.getFaultCode(), "{http://www.citrusframework.org/faults}FAULT-1003");
        Assert.assertEquals(action.getFaultString(), "FaultString");
        Assert.assertEquals(action.getFaultDetail(), "FaultDetail");
        Assert.assertNull(action.getFaultDetailResource());
        
        // 4th action
        action = getNextTestActionFromTest();
        Assert.assertNotNull(action.getAction());
        Assert.assertEquals(action.getMessageFactory(), beanDefinitionContext.getBean("customMessageFactory"));
        Assert.assertEquals(action.getValidator(), beanDefinitionContext.getBean("customSoapFaultValidator"));
        Assert.assertEquals(action.getFaultCode(), "{http://www.citrusframework.org/faults}FAULT-1004");
        Assert.assertEquals(action.getFaultString(), "FaultString");
        Assert.assertNull(action.getFaultDetail());
        Assert.assertNotNull(action.getFaultDetailResource());
    }
}
