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

package com.consol.citrus.validation.interceptor;

import org.springframework.integration.Message;

import com.consol.citrus.context.TestContext;


/**
 * Implementing classes may intercept the message paload constructing mechanism in order 
 * to modify the message content.
 * 
 * @author Christoph Deppisch
 */
public interface MessageConstructionInterceptor<T> {

    /**
     * Intercept the message construction.
     * @param message the message to be modified.
     * @param context the current test context
     */
    Message<T> interceptMessageConstruction(Message<T> message, TestContext context);
    
    /**
     * Intercept the message payload construction.
     * @param messagePayload the payload
     * @param context the current test context
     */
    T interceptMessageConstruction(T messagePayload, TestContext context);
}
