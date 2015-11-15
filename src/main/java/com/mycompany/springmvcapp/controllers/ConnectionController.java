/* 
 * Copyright (c) 2015, George Shumakov <george.shumakov@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.mycompany.springmvcapp.controllers;

import com.mycompany.springmvcapp.entities.Client;
import com.mycompany.springmvcapp.service.ClientService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author George Shumakov
 */
@Controller
@Scope("singleton")
public class ConnectionController {

    private static final String UNKNOWN = "unknown";
    private static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";
    private static final String USER_AGENT = "User-Agent";

    private static final String CONNECTION_VIEW = "connection";
    private static final String CONNECTIONS_LIST_VIEW = "connection-list";

    private static final String CLIENT_ATTR = "client";
    private static final String CLIENTS_ATTR = "clients";
    private static final String FROM_ATTR = "from";
    private static final String TO_ATTR = "to";

    @Autowired
    private ClientService clientService;

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/connection")
    public String connection(Model model, @RequestHeader(value = USER_AGENT, required = false) String agent, HttpServletRequest request) {

        String ipAddress = null;
        if (null != request) {
            ipAddress = request.getHeader(X_FORWARDED_FOR);
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
        }

        String userAgent = null == agent ? UNKNOWN : HtmlUtils.htmlEscape(agent);
        ipAddress = null == ipAddress ? UNKNOWN : HtmlUtils.htmlEscape(ipAddress);
        Client client = new Client(ipAddress, userAgent);
        logger.info("Request from " + client.getIp() + " at " + client.getDateTime());
        clientService.addClient(client);
        model.addAttribute(CLIENT_ATTR, client);
        return CONNECTION_VIEW;
    }

    @RequestMapping(value = "/connections-list", method = RequestMethod.GET)
    public String connectionsList(Model model,
            @RequestParam(value = FROM_ATTR, required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date from, //FIXME: 2015-14-
            @RequestParam(value = TO_ATTR, required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date to
    ) {

        model.addAttribute(CLIENTS_ATTR, clientService.getAll(from, to));
        model.addAttribute(FROM_ATTR, from);
        model.addAttribute(TO_ATTR, to);
        return CONNECTIONS_LIST_VIEW;
    }
}
