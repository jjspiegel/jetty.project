//
// ========================================================================
// Copyright (c) 1995-2020 Mort Bay Consulting Pty Ltd and others.
//
// This program and the accompanying materials are made available under
// the terms of the Eclipse Public License 2.0 which is available at
// https://www.eclipse.org/legal/epl-2.0
//
// This Source Code may also be made available under the following
// Secondary Licenses when the conditions for such availability set
// forth in the Eclipse Public License, v. 2.0 are satisfied:
// the Apache License v2.0 which is available at
// https://www.apache.org/licenses/LICENSE-2.0
//
// SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
// ========================================================================
//

package org.eclipse.jetty.annotations;

import org.eclipse.jetty.annotations.AnnotationParser.ClassInfo;
import org.eclipse.jetty.annotations.AnnotationParser.FieldInfo;
import org.eclipse.jetty.annotations.AnnotationParser.MethodInfo;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * WebFilterAnnotationHandler
 */
public class WebFilterAnnotationHandler extends AbstractDiscoverableAnnotationHandler
{
    private static final Logger LOG = Log.getLogger(WebFilterAnnotationHandler.class);

    public WebFilterAnnotationHandler(WebAppContext context)
    {
        super(context);
    }

    @Override
    public void handle(ClassInfo info, String annotationName)
    {
        if (annotationName == null || !"javax.servlet.annotation.WebFilter".equals(annotationName))
            return;

        WebFilterAnnotation wfAnnotation = new WebFilterAnnotation(_context, info.getClassName(), info.getContainingResource());
        addAnnotation(wfAnnotation);
    }

    @Override
    public void handle(FieldInfo info, String annotationName)
    {
        if (annotationName == null || !"javax.servlet.annotation.WebFilter".equals(annotationName))
            return;
        LOG.warn("@WebFilter not applicable for fields: " + info.getClassInfo().getClassName() + "." + info.getFieldName());
    }

    @Override
    public void handle(MethodInfo info, String annotationName)
    {
        if (annotationName == null || !"javax.servlet.annotation.WebFilter".equals(annotationName))
            return;
        LOG.warn("@WebFilter not applicable for methods: " + info.getClassInfo().getClassName() + "." + info.getMethodName() + " " + info.getSignature());
    }
}
