/*
 * Copyright (c) 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itfsw.query.builder;

import com.itfsw.query.builder.support.builder.AbstractBuilder;
import com.itfsw.query.builder.support.filter.DatetimeConvertFilter;
import com.itfsw.query.builder.support.filter.DefaultValueConvertFilter;
import com.itfsw.query.builder.support.filter.IRuleFilter;
import com.itfsw.query.builder.support.filter.ValidateFilter;
import com.itfsw.query.builder.support.parser.IRuleParser;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/2 14:49
 * ---------------------------------------------------------------------------
 */
public abstract class AbstractQueryBuilderFactory {
    protected List<IRuleFilter> filters;    // filters
    protected List<IRuleParser> parsers;   // rule parser

    /**
     * 构造函数
     */
    public AbstractQueryBuilderFactory() {
        this.filters = new ArrayList<>();
        this.parsers = new ArrayList<>();

        // -------------------------- filter -----------------------------
        filters.add(new ValidateFilter());
        filters.add(new DefaultValueConvertFilter());
        filters.add(new DatetimeConvertFilter());
    }

    /**
     * 获取builder
     * @return
     */
    public abstract AbstractBuilder builder();

    /**
     * add filter
     * @param filter
     */
    public void addFilter(IRuleFilter filter) {
        this.filters.add(filter);
    }

    /**
     * add filter before
     * @param filter
     * @param beforeFilter
     */
    public void addFilterBefore(IRuleFilter filter, Class<? extends IRuleFilter> beforeFilter) {
        int index = getIndexOfClass(filters, beforeFilter);
        filters.add(index == -1 ? 0 : index, filter);
    }

    /**
     * add filter after
     * @param filter
     * @param afterFilter
     */
    public void addFilterAfter(IRuleFilter filter, Class<? extends IRuleFilter> afterFilter) {
        int index = getIndexOfClass(filters, afterFilter);
        filters.add(index == -1 ? filters.size() : index, filter);
    }

    /**
     * replace filter
     * @param filter
     * @param atFilter
     */
    public void addFilterAt(IRuleFilter filter, Class<? extends IRuleFilter> atFilter) {
        int index = getIndexOfClass(filters, atFilter);
        if (index >= 0) {
            filters.remove(index);
            filters.add(index, filter);
        } else {
            filters.add(filter);
        }
    }

    /**
     * add parser
     * @param parser
     */
    public void addParser(IRuleParser parser) {
        this.parsers.add(parser);
    }

    /**
     * add parser before
     * @param parser
     * @param beforeParser
     */
    public void addParserBefore(IRuleParser parser, Class<? extends IRuleParser> beforeParser) {
        int index = getIndexOfClass(parsers, beforeParser);
        parsers.add(index == -1 ? 0 : index, parser);
    }

    /**
     * add parser after
     * @param parser
     * @param afterParser
     */
    public void addParserAfter(IRuleParser parser, Class<? extends IRuleParser> afterParser) {
        int index = getIndexOfClass(parsers, afterParser);
        parsers.add(index == -1 ? parsers.size() : index, parser);
    }

    /**
     * replace parser
     * @param parser
     * @param atParser
     */
    public void addParserAt(IRuleParser parser, Class<? extends IRuleParser> atParser) {
        int index = getIndexOfClass(parsers, atParser);
        if (index >= 0) {
            parsers.remove(index);
            parsers.add(index, parser);
        } else {
            parsers.add(parser);
        }
    }


    /**
     * Getter method for property <tt>filters</tt>.
     * @return property value of filters
     * @author hewei
     */
    public List<IRuleFilter> getFilters() {
        return filters;
    }

    /**
     * Getter method for property <tt>parsers</tt>.
     * @return property value of parsers
     * @author hewei
     */
    public List<IRuleParser> getParsers() {
        return parsers;
    }

    /**
     * get class index of list
     * @param list
     * @param cls
     * @return
     */
    private int getIndexOfClass(List list, Class cls) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getClass().equals(cls)) {
                return i;
            }
        }
        return -1;
    }
}