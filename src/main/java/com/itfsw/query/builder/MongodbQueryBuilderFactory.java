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

import com.itfsw.query.builder.support.builder.MongodbBuilder;
import com.itfsw.query.builder.support.filter.IRuleFilter;
import com.itfsw.query.builder.support.filter.ValidateRuleFilter;
import com.itfsw.query.builder.support.parser.AbstractMongodbRuleParser;
import com.itfsw.query.builder.support.parser.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/1 18:31
 * ---------------------------------------------------------------------------
 */
public class MongodbQueryBuilderFactory {
    protected List<IRuleFilter> filters;    // filters
    protected List<AbstractMongodbRuleParser> ruleParsers;   // rule parser

    /**
     * 构造函数
     */
    public MongodbQueryBuilderFactory() {
        ruleParsers = new ArrayList<AbstractMongodbRuleParser>();
        filters = new ArrayList<IRuleFilter>();

        // -------------------------- filter -----------------------------
        filters.add(new ValidateRuleFilter());

        // ---------------------- rule parser ----------------------------
        ruleParsers.add(new EqualRuleParser());
        ruleParsers.add(new NotEqualRuleParser());
        ruleParsers.add(new INRuleParser());
        ruleParsers.add(new NotInRuleParser());
        ruleParsers.add(new LessRuleParser());
        ruleParsers.add(new LessOrEqualRuleParser());
        ruleParsers.add(new GreaterRuleParser());
        ruleParsers.add(new GreaterOrEqualRuleParser());
        ruleParsers.add(new BetweenRuleParser());
        ruleParsers.add(new NotBetweenRuleParser());
        ruleParsers.add(new BeginsWithRuleParser());
        ruleParsers.add(new NotBeginsWithRuleParser());
        ruleParsers.add(new ContainsRuleParser());
        ruleParsers.add(new NotContainsRuleParser());
        ruleParsers.add(new EndsWithRuleParser());
        ruleParsers.add(new NotEndsWithRuleParser());
        ruleParsers.add(new IsEmptyRuleParser());
        ruleParsers.add(new IsNotEmptyRuleParser());
        ruleParsers.add(new IsNullRuleParser());
        ruleParsers.add(new IsNotNullRuleParser());

    }

    public MongodbBuilder builder() {
        return new MongodbBuilder(filters, ruleParsers);
    }
}