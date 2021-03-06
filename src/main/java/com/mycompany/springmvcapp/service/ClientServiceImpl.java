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
package com.mycompany.springmvcapp.service;

import com.mycompany.springmvcapp.entities.Client;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides clients data for persistent context.
 * 
 * @author George Shumakov <george.shumakov@gmail.com>
 */
@Service
public class ClientServiceImpl implements ClientService {

    // Injected database connection:
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Client> getAll(Date from, Date to) {
        TypedQuery<Client> query;
        if (null == from && null == to) {
            query = em.createNamedQuery("Client.findAll", Client.class);
        } else if (null != from && null != to) {
            query = em.createNamedQuery("Client.findBetweenFromAndTo", Client.class);
            query.setParameter("from", from, TemporalType.DATE).setParameter("to", to, TemporalType.DATE);
        } else if (null == to) {
            query = em.createNamedQuery("Client.findAfterFrom", Client.class);
            query.setParameter("from", from, TemporalType.DATE);
        } else {
            query = em.createNamedQuery("Client.findBeforeTo", Client.class);
            query.setParameter("to", to, TemporalType.DATE);
        }
        return query.getResultList();
    }
    
    @Override
    @Transactional
    public void addClient(Client client) {
        if (null != client) {
            em.persist(client);
        } else {
            throw new IllegalArgumentException ("Null can't be registered as a client request");
        }
    }

}
