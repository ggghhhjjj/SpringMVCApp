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
package com.mycompany.springmvcapp.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author George Shumakov <george.shumakov@gmail.com>
 */
public class ClientRecord {

    private static final DateFormat DATE_FORMATER = DateFormat.getDateTimeInstance(
            DateFormat.MEDIUM,
            DateFormat.MEDIUM,
            Locale.getDefault());

    private String ipAddress;
    private String userAgent;
    private String time;

    public ClientRecord(String ipAddress, String userAgent) {
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.time = DATE_FORMATER.format(new Date());
    }

    public String getIp() {
        return ipAddress;
    }

    public void setIp(String ip) {
        this.ipAddress = ip;
    }

    public String getAgent() {
        return userAgent;
    }

    public void setAgent(String agent) {
        this.userAgent = agent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
