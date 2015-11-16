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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * Service for converting a JS date to starting and ending dates.
 *
 * @author George Shumakov <george.shumakov@gmail.com>
 */
@Service
public class FromToDateConverterImpl implements FromToDateConverter {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final long TIMEADJ = 24*60*60*1000; //one day in ms

    @Override
    public Date convertToStartDate(String from) {
        if (null == from) {
            return null;
        }
        return convert(from);
    }

    @Override
    public Date convertToEndDate(String to) {
        if (null == to) {
            return null;
        }
        //convert to a date with 00:00:00 time
        Date date = convert(to);
        if (null == date) {
            return null;
        }
        //add a day and return
        return new Date (date.getTime ()+TIMEADJ);
    }

    /**
     * String to date converter.
     * 
     * Converted date will always be with 00:00:00 time
     * @param date
     * @return 
     */
    private static Date convert(String date) {
        Date trimmed = null;
        try {
            synchronized (FORMAT) {
                trimmed = FORMAT.parse(date);
            }
        } catch (ParseException e) {
        } // if parsing failed null must be returned
        return trimmed;

    }

}
