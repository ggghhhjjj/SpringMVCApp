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
package com.mycompany.springmvcapp.entities;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents a client request.
 * 
 * The mandatory field is datetime represents a server time
 * when  HTTP request is registered.
 * 
 * ip represents a client request ip if available.
 * userAgent represents a client browser information if availabale
 * 
 * @author George Shumakov <george.shumakov@gmail.com>
 */
@Entity
@Table(name = "Client")
@NamedQueries({
    @NamedQuery(name = "Client.findAll",
            query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findBetweenFromAndTo",
            query = "SELECT c FROM Client c WHERE c.datetime BETWEEN :from AND :to"),
    @NamedQuery(name = "Client.findAfterFrom",
            query = "SELECT c FROM Client c WHERE c.datetime > :from"),
    @NamedQuery(name = "Client.findBeforeTo",
            query = "SELECT c FROM Client c WHERE c.datetime < :to")
})
public class Client implements java.io.Serializable {

    // Persistent fileds
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false, updatable=false)
    private String ip;
    @Column(nullable=false, updatable=false)
    private String userAgent;
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=false)
    @Column(nullable=false, updatable=false)
    private Date datetime;

    public Client() {
    }

    public Client(String ipAddress, String userAgent) {
        this.ip = ipAddress;
        this.userAgent = userAgent;
        this.datetime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String agent) {
        this.userAgent = agent;
    }

    public Date getDateTime() {
        return datetime;
    }

    public void setDateTime(Date time) {
        this.datetime = time;
    }

    @Override
    public String toString() {
        return "Client [" + id + ": ip=" + ip + ", User agent=" + userAgent + ", Date=" + datetime + "]";
    }

}
