/*
 * Copyright 2006-2011 the original author or authors.
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

package com.consol.citrus.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.consol.citrus.TestAction;
import com.consol.citrus.context.TestContext;

/**
 * @author Christoph Deppisch
 */
public class SequenceBeforeTest extends AbstractActionContainer {

    /**
     * Logger
     */
    private static Logger log = LoggerFactory.getLogger(SequenceBeforeTest.class);
    
    @Override
    public void doExecute(TestContext context) {
        if (CollectionUtils.isEmpty(actions)) {
            return;
        }
        
        log.info("Executing " + actions.size() + " actions before test");
        log.info("");

        for(TestAction action: actions)  {
            action.execute(context);
        }
    }
}
