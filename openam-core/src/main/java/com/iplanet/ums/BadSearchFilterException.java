/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2005 Sun Microsystems Inc. All Rights Reserved
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at
 * https://opensso.dev.java.net/public/CDDLv1.0.html or
 * opensso/legal/CDDLv1.0.txt
 * See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at opensso/legal/CDDLv1.0.txt.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * $Id: BadSearchFilterException.java,v 1.3 2008/06/25 05:41:44 qcheng Exp $
 *
 */

package com.iplanet.ums;

/**
 * Exception for invalid search filter.
 *
 * @supported.all.api
 */
public class BadSearchFilterException extends UMSException {
    /**
     * Default constructor
     */
    public BadSearchFilterException() {
        super();
    }

    /**
     * Constructor with a message string
     * 
     * @param msg
     *            Message string for the exception
     */
    public BadSearchFilterException(String msg) {
        super(msg);
    }

    /**
     * Constructor with message string and an embedded exception
     * 
     * @param msg
     *            Message string
     * @param t
     *            The embedded exception
     */
    public BadSearchFilterException(String msg, Throwable t) {
        super(msg, t);
    }
}
