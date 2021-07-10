/*
 * The MIT License
 *
 * Copyright 2021 Karus Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.karuslabs.utilitary.texts;

import java.util.*;
import javax.lang.model.element.VariableElement;

import static com.karuslabs.utilitary.texts.Texts.join;

public class VariablesText extends Text {
    
    public static VariablesText of(List<? extends VariableElement> variables, int column, int position) {
        var builder = new StringBuilder();
        var values = new LinkedHashMap<VariableElement, VariableText>();
        
        builder.append('(');
        join(builder, variables, (variable, sb) -> {
            var value = VariableText.inline(variable, column, position + sb.length());
            sb.append(value);
            values.put(variable, value);
        }, ", ");
        builder.append(')');
        
        return new VariablesText(values, builder.toString(), column, position);
    }
    
    public final Map<VariableElement, VariableText> values;
   
    VariablesText(Map<VariableElement, VariableText> values, String value, int column, int position) {
        super(value, column, position);
        this.values = values;
    }
    
}
