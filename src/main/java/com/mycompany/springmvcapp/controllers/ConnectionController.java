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
import com.mycompany.springmvcapp.service.FromToDateConverter;
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
 * Application spring MVC controller.
 * 
 * '/connection' path is a gateway for HTTP requests which have to be registered
 * '/connections-list' for reporting of registered requests
 * 
 * State in controller will require synchronization to avoid threading issues.
 * @author George Shumakov
 */
@Controller
@Scope("singleton")
public class ConnectionController {

    private static final String UNKNOWN = "unknown";
    private static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";
    private static final String USER_AGENT = "User-Agent";

    private static final String INDEX_VIEW = "index";
    private static final String CONNECTION_VIEW = "connection";
    private static final String CONNECTIONS_LIST_VIEW = "connections-list";

    private static final String CLIENT_ATTR = "client";
    private static final String CLIENTS_ATTR = "clients";
    private static final String FROM_ATTR = "from";
    private static final String TO_ATTR = "to";

    @Autowired
    private ClientService clientService;
    
    @Autowired
    private FromToDateConverter dateConverter;

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

    /**
     * Renders a client requests report for a period.
     * 
     * The HTML5 date input specification 
     * refers to the RFC3339 specification 
     * {@link http://tools.ietf.org/html/rfc3339}, 
     * which specifies a full-date format equal to: yyyy-mm-dd. 
     * See section 5.6 of the RFC3339 specification for more details.
     * 
     * @param model
     * @param from HTML 5 start date
     * @param to HTML 5 end date
     * @return report view name
     */
    @RequestMapping(value = "/connections-list", method = RequestMethod.GET)
    public String connectionsList(Model model,
            @RequestParam(value = FROM_ATTR, required = false)
            String from, //HTML 5 input date format
            @RequestParam(value = TO_ATTR, required = false)
            String to //HTML 5 input date format
    ) {
        Date startDate = dateConverter.convertToStartDate(from);
        Date endDate = dateConverter.convertToEndDate(to);
        Date renderedEndDate = dateConverter.convertToStartDate(to);
        model.addAttribute(CLIENTS_ATTR, clientService.getAll(startDate, endDate));
        model.addAttribute(FROM_ATTR, startDate);
        model.addAttribute(TO_ATTR, renderedEndDate);
        return CONNECTIONS_LIST_VIEW;
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index () {
        return INDEX_VIEW;
    }
}
