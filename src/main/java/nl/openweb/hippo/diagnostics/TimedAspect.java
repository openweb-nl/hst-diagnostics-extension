/*
 * Copyright 2018 Open Web IT B.V. (https://www.openweb.nl/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.openweb.hippo.diagnostics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hippoecm.hst.diagnosis.HDC;
import org.hippoecm.hst.diagnosis.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect
public class TimedAspect {

    private static final Logger LOG = LoggerFactory.getLogger(TimedAspect.class);

    @Around("@annotation(nl.openweb.hippo.diagnostics.Timed)")
    public Object timed(ProceedingJoinPoint joinPoint) throws Throwable {
        Task queryTask = null;
        try {
            queryTask = getTask(getTaskName(joinPoint));
            return joinPoint.proceed();
        } finally {
            if (queryTask != null) {
                queryTask.stop();
            }
        }
    }

    private Task getTask(String taskName) {
        Task queryTask = null;
        try {
            if (HDC.isStarted()) {
                queryTask = HDC.getCurrentTask().startSubtask(taskName);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return queryTask;
    }

    private String getTaskName(ProceedingJoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            return method.getDeclaringClass().getSimpleName() + "." + method.getName();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return "";
        }
    }


}
