/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions Copyrighted [year] [name of copyright owner]".
 *
 * Copyright 2013-2014 ForgeRock AS. All rights reserved.
 */

package org.forgerock.openam.sts.token;

import javax.inject.Inject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.forgerock.openam.sts.TokenValidationException;
import org.restlet.representation.Representation;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Map;

/**
 * AMTokenParser implementation. Responsible for parsing out the OpenAM session id from all successful authentication
 * requests.
 */
public class AMTokenParserImpl implements AMTokenParser {
    private static final String TOKEN_ID = "tokenId";
    private final Logger logger;

    @Inject
    AMTokenParserImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String getSessionFromAuthNResponse(Representation representation) throws TokenValidationException {
        //want to test for header response 200? TODO, or is exception thrown whenever non-200 response returned?
        String responseBody = null;
        try {
            responseBody = representation.getText();
        } catch (IOException e) {
            throw new TokenValidationException("Exception caught pulling text from Representation in the context of " +
                    "parsing the AM Session from the authentication response: " + e.getMessage(), e);
        }
        Map<String,Object> responseAsMap = null;
        try {
            responseAsMap = new ObjectMapper().readValue(responseBody,
                    new TypeReference<Map<String,Object>>() {});
        } catch (IOException ioe) {
            String message = "Exception caught getting the text of the json authN response: " + ioe;
            throw new TokenValidationException(message, ioe);
        }
        String sessionId = (String)responseAsMap.get(TOKEN_ID);
        if (sessionId == null) {
            String message = "REST authN response does not contain " + TOKEN_ID + " entry. The response map: " + responseAsMap;
            throw new TokenValidationException(message);
        }
        return sessionId;
    }
}
