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
package com.karuslabs.utilitary.snippet;

import com.karuslabs.elementary.junit.*;
import com.karuslabs.elementary.junit.annotations.*;

import java.util.List;
import javax.lang.model.element.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ToolsExtension.class)
@Introspect
@Case("empty")
class TypeParametersLineTest {
    
    @Case("generics") static class Type<T extends String, U extends Line> {}
    
    List<? extends TypeParameterElement> parameters = ((TypeElement) Tools.cases().one("generics")).getTypeParameters();      
    TypeParametersLine line = TypeParametersLine.of(parameters, 0, 1);
    
    @Test
    void toString_() {
        assertEquals("<T extends String, U extends Line>", line.toString());
    }
    
    @Test
    void toString_empty() {
        assertEquals("", TypeParametersLine.of(List.of(), 0, 1).toString());
    }
    
    @Test
    void values() {
        assertEquals(2, line.arguments.size());
        assertEquals("T extends String", line.arguments.get(parameters.get(0)).toString());
        assertEquals("U extends Line", line.arguments.get(parameters.get(1)).toString());
    }

} 
